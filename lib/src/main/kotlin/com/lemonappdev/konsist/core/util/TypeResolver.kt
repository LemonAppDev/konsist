package com.lemonappdev.konsist.core.util

import com.lemonappdev.konsist.api.declaration.KoBaseDeclaration
import com.lemonappdev.konsist.api.declaration.KoClassDeclaration
import com.lemonappdev.konsist.api.declaration.KoExternalDeclaration
import com.lemonappdev.konsist.api.declaration.KoFileDeclaration
import com.lemonappdev.konsist.api.declaration.KoInterfaceDeclaration
import com.lemonappdev.konsist.api.declaration.KoObjectDeclaration
import com.lemonappdev.konsist.api.provider.KoContainingDeclarationProvider
import com.lemonappdev.konsist.api.provider.KoFullyQualifiedNameProvider
import com.lemonappdev.konsist.api.provider.KoParentProvider
import com.lemonappdev.konsist.api.provider.modifier.KoVisibilityModifierProvider
import com.lemonappdev.konsist.core.declaration.KoFileDeclarationCore
import com.lemonappdev.konsist.core.model.DataCore
import org.jetbrains.kotlin.com.intellij.openapi.util.Key
import java.lang.reflect.Modifier
import java.util.concurrent.ConcurrentHashMap

/*
 * Resolves fully qualified names of the types referenced in the source code, in the order used by the Kotlin compiler:
 * 1. type nested in the containing declaration (or in its containing declarations) or inherited from its superclasses
 * 2. explicit import (also with alias)
 * 3. type from the same package
 * 4. wildcard import
 * 5. default import (Kotlin standard library and `java.lang`)
 *
 * The qualified name (e.g. `RecyclerView.ViewHolder`) is resolved by its first segment (`RecyclerView`).
 *
 * Types from the same package and wildcard imports are searched in the containing file, on the classpath and in the
 * project before default imports, so a project type which has the name of a default import type (e.g. `Result`) is
 * resolved to the project type. Only project files of the packages that can contain the type are parsed (see
 * `ProjectDeclarationIndex`).
 */
internal object TypeResolver {
    private const val JAVA_LANG_PACKAGE = "java.lang"

    private val fileTypesKey = Key.create<Map<String, KoBaseDeclaration>>("KoFileTypes")

    // Nested types of the classpath class and its superclasses (simple name to fully qualified name) by class name
    private val classpathNestedTypes = ConcurrentHashMap<String, Map<String, String>>()

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
                ?: candidates.firstOrNull {
                    it in getFileTypes(containingFile) ||
                        TypeUtil.isTypeAvailable(it) ||
                        DataCore.projectDeclarationIndex.getTypeDeclaration(it) != null
                }
                ?: getDefaultImportedTypeName(firstSegment)

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
                DataCore.projectDeclarationIndex.getTypeDeclaration(fullyQualifiedName)
            }

    private fun getNestedTypeName(
        name: String,
        containingFile: KoFileDeclaration,
        containingDeclaration: KoBaseDeclaration?,
    ): String? =
        generateSequence(containingDeclaration) { (it as? KoContainingDeclarationProvider)?.containingDeclaration }
            .takeWhile { it !is KoFileDeclaration }
            .filter { it is KoClassDeclaration || it is KoInterfaceDeclaration || it is KoObjectDeclaration }
            .firstNotNullOfOrNull { declaration ->
                (declaration as? KoFullyQualifiedNameProvider)
                    ?.fullyQualifiedName
                    ?.let { "$it.$name" }
                    ?.takeIf { it in getFileTypes(containingFile) }
                    ?: getInheritedNestedTypeName(name, declaration, mutableSetOf(declaration))
            }

    /*
     * Types nested in superclasses are visible in the class body, e.g. `Inner` in `class Child : Base()` is
     * `Base.Inner`. Types nested in interfaces and private types are not inherited.
     */
    private fun getInheritedNestedTypeName(
        name: String,
        declaration: KoBaseDeclaration,
        visitedDeclarations: MutableSet<KoBaseDeclaration>,
    ): String? =
        (declaration as? KoParentProvider)
            ?.parents()
            ?.firstNotNullOfOrNull { parent ->
                when (val superclass = parent.sourceDeclaration) {
                    is KoClassDeclaration -> {
                        superclass
                            .fullyQualifiedName
                            ?.let { "$it.$name" }
                            ?.takeIf { isInheritedNestedType(getFileTypes(superclass.containingFile)[it]) }
                            ?: superclass
                                .takeIf { visitedDeclarations.add(it) }
                                ?.let { getInheritedNestedTypeName(name, it, visitedDeclarations) }
                    }

                    is KoExternalDeclaration -> {
                        superclass.fullyQualifiedName?.let { getClasspathNestedTypes(it)[name] }
                    }

                    else -> {
                        null
                    }
                }
            }

    private fun isInheritedNestedType(declaration: KoBaseDeclaration?): Boolean =
        declaration != null && (declaration as? KoVisibilityModifierProvider)?.hasPrivateModifier != true

    /*
     * Returns nested types of the classpath class and its superclasses. The closest declaration of the name is used.
     * Classes are loaded without initialization.
     */
    private fun getClasspathNestedTypes(fullyQualifiedName: String): Map<String, String> =
        classpathNestedTypes.computeIfAbsent(fullyQualifiedName) {
            val clazz = getClasspathClass(fullyQualifiedName)

            if (clazz == null || clazz.isInterface) {
                emptyMap()
            } else {
                buildMap {
                    generateSequence(clazz) { it.superclass }
                        .flatMap { runCatching { it.declaredClasses.toList() }.getOrDefault(emptyList()) }
                        .filterNot {
                            it.isAnonymousClass || it.isLocalClass || it.isSynthetic || Modifier.isPrivate(it.modifiers)
                        }.forEach { nestedClass ->
                            nestedClass.canonicalName?.let { putIfAbsent(nestedClass.simpleName, it) }
                        }
                }
            }
        }

    /*
     * Nested class has the binary name with `$` (e.g. `a.B$C` for `a.B.C`), so the dots are replaced from the end until
     * the class is found.
     */
    private fun getClasspathClass(fullyQualifiedName: String): Class<*>? =
        generateSequence(fullyQualifiedName) { name ->
            val dotIndex = name.lastIndexOf('.')
            if (dotIndex == -1) null else name.substring(0, dotIndex) + "$" + name.substring(dotIndex + 1)
        }.firstNotNullOfOrNull { name ->
            runCatching { Class.forName(name, false, TypeUtil::class.java.classLoader) }.getOrNull()
        }

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

    /**
     * Returns classes, interfaces, objects and type aliases declared in the files by fully qualified name. When more
     * types have the same fully qualified name (e.g. in different source sets), the first class is used, then the first
     * interface, object and type alias.
     */
    internal fun getTypesByFullyQualifiedName(files: List<KoFileDeclaration>): Map<String, KoBaseDeclaration> =
        buildMap {
            val types =
                files.flatMap { it.classes(includeNested = true, includeLocal = false) } +
                    files.flatMap { it.interfaces(includeNested = true) } +
                    files.flatMap { it.objects(includeNested = true) } +
                    files.flatMap { it.typeAliases }

            types.forEach { type ->
                (type as? KoFullyQualifiedNameProvider)
                    ?.fullyQualifiedName
                    ?.let { putIfAbsent(it, type) }
            }
        }

    /*
     * Types declared in the file. They are cached in the user data of the file, so they are computed once for the file
     * and released from memory together with the file.
     */
    private fun getFileTypes(containingFile: KoFileDeclaration): Map<String, KoBaseDeclaration> {
        val ktFile = (containingFile as KoFileDeclarationCore).ktFile

        return ktFile.getUserData(fileTypesKey)
            ?: getTypesByFullyQualifiedName(listOf(containingFile)).also { ktFile.putUserData(fileTypesKey, it) }
    }
}
