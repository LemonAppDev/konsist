package com.lemonappdev.konsist.core.model

import com.lemonappdev.konsist.api.declaration.KoAnnotationDeclaration
import com.lemonappdev.konsist.api.declaration.KoBaseDeclaration
import com.lemonappdev.konsist.api.declaration.KoFileDeclaration
import com.lemonappdev.konsist.api.provider.KoFullyQualifiedNameProvider
import com.lemonappdev.konsist.core.cache.KoReferenceCache
import com.lemonappdev.konsist.core.util.TypeResolver
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.runBlocking
import java.io.File
import java.lang.ref.SoftReference

/**
 * Finds declarations of the project by fully qualified name without parsing the whole project.
 *
 * The package of every file is known before the file is parsed (see [getPackageName]). Declaration with the fully
 * qualified name `a.b.C.D` can be declared only in the package `a.b.C`, `a.b`, `a` or in the root package, so only
 * files of these packages are parsed.
 *
 * Declarations of the package are cached using soft references, so they can be released from memory (together with
 * parsed files) when the memory is low.
 *
 * @param getFiles returns files of the project
 * @param getPackageName returns the package name of the file (empty for the root package) or `null` when the file
 * can't be read
 * @param getKoFile returns the parsed file
 */
internal class ProjectDeclarationIndex(
    private val getFiles: () -> List<File>,
    private val getPackageName: (File) -> String?,
    private val getKoFile: (File) -> KoFileDeclaration,
) {
    private val filesByPackageName: Map<String, List<File>> by lazy {
        val files = getFiles()

        val packageNames =
            runBlocking(Dispatchers.IO) {
                files
                    .map { async { getPackageName(it) } }
                    .awaitAll()
            }

        files
            .zip(packageNames)
            .mapNotNull { (file, packageName) -> packageName?.let { it to file } }
            .groupBy({ it.first }, { it.second })
    }

    /**
     * Names of the project packages (empty for the root package).
     */
    val packageNames: Set<String>
        get() = filesByPackageName.keys

    private val typesByPackageName = KoReferenceCache<Map<String, KoBaseDeclaration>> { SoftReference(it) }

    private val declarationsByPackageName = KoReferenceCache<Map<String, KoBaseDeclaration>> { SoftReference(it) }

    /**
     * Returns the class, interface, object or type alias with the given fully qualified name.
     */
    fun getTypeDeclaration(fullyQualifiedName: String): KoBaseDeclaration? =
        getPackageNames(fullyQualifiedName).firstNotNullOfOrNull { getTypes(it)[fullyQualifiedName] }

    /**
     * Returns the declaration (e.g. class, top-level function, enum constant) with the given fully qualified name.
     * Types are searched first.
     */
    fun getDeclaration(fullyQualifiedName: String): KoBaseDeclaration? =
        getTypeDeclaration(fullyQualifiedName)
            ?: getPackageNames(fullyQualifiedName).firstNotNullOfOrNull { getDeclarations(it)[fullyQualifiedName] }

    private fun getTypes(packageName: String): Map<String, KoBaseDeclaration> {
        val files = filesByPackageName[packageName] ?: return emptyMap()

        return typesByPackageName.getOrCreateInstance(packageName) {
            TypeResolver.getTypesByFullyQualifiedName(files.map { getKoFile(it) })
        }
    }

    /*
     * Local declarations are not included, because they have no fully qualified name.
     */
    private fun getDeclarations(packageName: String): Map<String, KoBaseDeclaration> {
        val files = filesByPackageName[packageName] ?: return emptyMap()

        return declarationsByPackageName.getOrCreateInstance(packageName) {
            buildMap {
                files
                    .flatMap { getKoFile(it).declarations(includeNested = true, includeLocal = false) }
                    // Fully qualified name of the annotation is the name of the annotation class, not the declaration
                    .filterNot { it is KoAnnotationDeclaration }
                    .forEach { declaration ->
                        (declaration as? KoFullyQualifiedNameProvider)
                            ?.fullyQualifiedName
                            ?.let { putIfAbsent(it, declaration) }
                    }
            }
        }
    }

    /*
     * Returns names of the project packages that can contain the declaration, e.g. for `a.b.C` returns `a.b`, `a` and
     * the root package (nested type `b.C` declared in the package `a`).
     */
    private fun getPackageNames(fullyQualifiedName: String): List<String> {
        val packageNames = mutableListOf<String>()
        var dotIndex = fullyQualifiedName.lastIndexOf('.')

        while (dotIndex != -1) {
            packageNames += fullyQualifiedName.substring(0, dotIndex)
            dotIndex = fullyQualifiedName.lastIndexOf('.', dotIndex - 1)
        }

        packageNames += ""

        return packageNames.filter { it in filesByPackageName }
    }
}
