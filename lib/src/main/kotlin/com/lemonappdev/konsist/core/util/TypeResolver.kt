package com.lemonappdev.konsist.core.util

import com.lemonappdev.konsist.api.declaration.KoBaseDeclaration
import com.lemonappdev.konsist.api.declaration.KoClassDeclaration
import com.lemonappdev.konsist.api.declaration.KoFileDeclaration
import com.lemonappdev.konsist.api.declaration.KoInterfaceDeclaration
import com.lemonappdev.konsist.api.declaration.KoObjectDeclaration
import com.lemonappdev.konsist.api.provider.KoContainingDeclarationProvider
import com.lemonappdev.konsist.api.provider.KoFullyQualifiedNameProvider
import com.lemonappdev.konsist.core.declaration.KoFileDeclarationCore
import com.lemonappdev.konsist.core.model.DataCore
import org.jetbrains.kotlin.com.intellij.openapi.util.Key

/*
 * Resolves fully qualified names of the types referenced in the source code, in the order used by the Kotlin compiler:
 * 1. type nested in the containing declaration (or in its containing declarations)
 * 2. explicit import (also with alias)
 * 3. type from the same package
 * 4. wildcard import
 * 5. default import (Kotlin standard library and `java.lang`)
 *
 * The qualified name (e.g. `RecyclerView.ViewHolder`) is resolved by its first segment (`RecyclerView`).
 *
 * Types from the same package and wildcard imports are searched in the containing file and on the classpath first.
 * Project declarations are searched only when the type is not found there and it is not a default import type,
 * so resolving e.g. `String` or `Deprecated` does not parse the whole project. As a result, a project type which
 * has the name of a default import type (e.g. `Result`) is resolved only when it is declared in the same file or it is
 * available on the classpath.
 */
internal object TypeResolver {
    private const val JAVA_LANG_PACKAGE = "java.lang"

    private val fileTypesKey = Key.create<Map<String, KoBaseDeclaration>>("KoFileTypes")

    /*
     * Java classes mapped to Kotlin types are not imported by wildcard imports, e.g. `List` with `import java.util.*`
     * is `kotlin.collections.List`.
     */
    private val javaClassesMappedToKotlinTypes =
        setOf(
            "java.lang.Boolean",
            "java.lang.Byte",
            "java.lang.CharSequence",
            "java.lang.Character",
            "java.lang.Cloneable",
            "java.lang.Comparable",
            "java.lang.Double",
            "java.lang.Enum",
            "java.lang.Float",
            "java.lang.Integer",
            "java.lang.Iterable",
            "java.lang.Long",
            "java.lang.Number",
            "java.lang.Object",
            "java.lang.Short",
            "java.lang.String",
            "java.lang.Throwable",
            "java.lang.annotation.Annotation",
            "java.util.Collection",
            "java.util.Iterator",
            "java.util.List",
            "java.util.ListIterator",
            "java.util.Map",
            "java.util.Set",
        )

    private val defaultImportedKotlinTypes: Map<String, List<String>> by lazy {
        TypeUtil
            .kotlinTypes
            .distinct()
            .groupBy { it.substringAfterLast(".") }
    }

    /**
     * Returns the fully qualified name of the type with the given name (e.g. `Foo` or `RecyclerView.ViewHolder`)
     * or `null` when the type cannot be resolved.
     *
     * @param containingDeclaration the innermost declaration whose nested types are visible at the place of use
     */
    fun resolveFullyQualifiedName(
        name: String,
        containingFile: KoFileDeclaration,
        containingDeclaration: KoBaseDeclaration? = null,
    ): String? {
        val firstSegment = name.substringBefore(".")
        val remainingSegments = name.removePrefix(firstSegment)

        val candidates = getSamePackageAndWildcardImportCandidates(firstSegment, containingFile)

        val fullyQualifiedName =
            getNestedTypeName(firstSegment, containingFile, containingDeclaration)
                ?: getImportedTypeName(firstSegment, containingFile)
                ?: candidates.firstOrNull { it in getFileTypes(containingFile) || TypeUtil.isTypeAvailable(it) }
                ?: getDefaultImportedTypeName(firstSegment)
                ?: candidates.firstOrNull { it in DataCore.typesByFullyQualifiedName }

        return when {
            fullyQualifiedName != null -> fullyQualifiedName + remainingSegments

            // Name is already fully qualified, e.g. `com.app.Foo`
            remainingSegments.isNotEmpty() -> name

            else -> null
        }
    }

    /**
     * Returns the class, interface, object or type alias with the given fully qualified name declared in the
     * containing file or in the project. Kotlin standard library types are not searched in the project.
     */
    fun getTypeDeclaration(
        fullyQualifiedName: String,
        containingFile: KoFileDeclaration,
    ): KoBaseDeclaration? =
        getFileTypes(containingFile)[fullyQualifiedName]
            ?: if (TypeUtil.isKotlinTypeFullyQualifiedName(fullyQualifiedName)) {
                null
            } else {
                DataCore.typesByFullyQualifiedName[fullyQualifiedName]
            }

    private fun getNestedTypeName(
        name: String,
        containingFile: KoFileDeclaration,
        containingDeclaration: KoBaseDeclaration?,
    ): String? =
        generateSequence(containingDeclaration) { (it as? KoContainingDeclarationProvider)?.containingDeclaration }
            .takeWhile { it !is KoFileDeclaration }
            .filter { it is KoClassDeclaration || it is KoInterfaceDeclaration || it is KoObjectDeclaration }
            .mapNotNull { (it as? KoFullyQualifiedNameProvider)?.fullyQualifiedName }
            .map { "$it.$name" }
            .firstOrNull { it in getFileTypes(containingFile) }

    private fun getImportedTypeName(
        name: String,
        containingFile: KoFileDeclaration,
    ): String? =
        containingFile
            .imports
            .firstOrNull { import ->
                !import.isWildcard &&
                    (import.alias?.name ?: import.name.substringAfterLast(".")) == name &&
                    !isKotlinFunctionImport(import.name)
            }?.name

    /*
     * Import of the Kotlin standard library function with the name of a type (e.g. `kotlin.text.String` function)
     * does not import the type.
     */
    private fun isKotlinFunctionImport(fullyQualifiedName: String): Boolean =
        fullyQualifiedName.startsWith("kotlin.") &&
            !TypeUtil.isKotlinTypeFullyQualifiedName(fullyQualifiedName) &&
            defaultImportedKotlinTypes.containsKey(fullyQualifiedName.substringAfterLast(".")) &&
            !TypeUtil.isTypeAvailable(fullyQualifiedName)

    private fun getSamePackageAndWildcardImportCandidates(
        name: String,
        containingFile: KoFileDeclaration,
    ): List<String> {
        val packagePrefix =
            containingFile.packagee
                ?.name
                ?.let { "$it." }
                .orEmpty()

        val wildcardImportCandidates =
            containingFile
                .imports
                .filter { it.isWildcard }
                .map { "${it.name}.$name" }
                .filterNot { it in javaClassesMappedToKotlinTypes }

        return listOf("$packagePrefix$name") + wildcardImportCandidates
    }

    private fun getDefaultImportedTypeName(name: String): String? {
        val kotlinTypes = defaultImportedKotlinTypes[name].orEmpty()

        // Type available on the classpath is preferred, e.g. `kotlin.jvm.Throws` over `kotlin.Throws` (alias on JVM)
        // and `kotlin.EagerInitialization` over `kotlin.js.EagerInitialization`
        return kotlinTypes.firstOrNull { TypeUtil.isTypeAvailable(it) }
            ?: kotlinTypes.firstOrNull()
            ?: "$JAVA_LANG_PACKAGE.$name".takeIf { TypeUtil.isTypeAvailable(it) }
    }

    /*
     * Types declared in the file. They are cached in the user data of the file, because the containing file
     * declaration is created again for every declaration.
     */
    private fun getFileTypes(containingFile: KoFileDeclaration): Map<String, KoBaseDeclaration> {
        val ktFile = (containingFile as KoFileDeclarationCore).ktFile

        return ktFile.getUserData(fileTypesKey)
            ?: DataCore
                .associateByFullyQualifiedName(
                    containingFile.classes(includeNested = true, includeLocal = false) +
                        containingFile.interfaces(includeNested = true) +
                        containingFile.objects(includeNested = true) +
                        containingFile.typeAliases,
                ).also { ktFile.putUserData(fileTypesKey, it) }
    }
}
