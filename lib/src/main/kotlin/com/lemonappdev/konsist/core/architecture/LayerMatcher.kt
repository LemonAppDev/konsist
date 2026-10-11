package com.lemonappdev.konsist.core.architecture

import com.lemonappdev.konsist.api.architecture.Layer
import com.lemonappdev.konsist.api.declaration.KoFileDeclaration
import com.lemonappdev.konsist.api.declaration.KoImportDeclaration
import com.lemonappdev.konsist.core.model.DataCore
import com.lemonappdev.konsist.core.util.LocationUtil

/**
 * Checks which files and imports belong to the layer.
 *
 * File belongs to the layer when its package matches the layer root package and none of the layer excluded packages.
 *
 * Package definition ending with '..' is matched against the import name, because '..' also matches the names of the
 * imported declaration, e.g. "com.app.." matches "com.app.Outer.Inner". Exact package definition is matched against
 * the package of the imported declaration, e.g. "com.app" matches "com.app.Outer.Inner", but not "com.app.data.Repository".
 *
 * @param files files of the scope, their packages are used (together with project packages) to find the package of the
 * imported declaration
 */
internal class LayerMatcher(
    private val files: List<KoFileDeclaration>,
) {
    private val packageNames: Set<String> by lazy {
        files.mapNotNullTo(mutableSetOf()) { it.packagee?.name } + DataCore.projectDeclarationIndex.packageNames
    }

    private val packageNamesByImportName = mutableMapOf<String, String?>()

    fun getFiles(layer: Layer): List<KoFileDeclaration> = files.filter { containsFile(layer, it) }

    fun containsImport(
        layer: Layer,
        import: KoImportDeclaration,
    ): Boolean =
        importMatches(layer.rootPackage, import.name) &&
            layer.excludedPackages.none { importMatches(it, import.name) }

    fun containsFile(
        layer: Layer,
        file: KoFileDeclaration,
    ): Boolean {
        val packageName = file.packagee?.name ?: return false

        return LocationUtil.residesInLocation(layer.rootPackage, packageName) &&
            layer.excludedPackages.none { LocationUtil.residesInLocation(it, packageName) }
    }

    private fun importMatches(
        packageDefinition: String,
        importName: String,
    ): Boolean {
        if (packageDefinition.endsWith("..")) {
            return LocationUtil.residesInLocation(packageDefinition, importName)
        }

        return getPackageName(importName)
            ?.let { LocationUtil.residesInLocation(packageDefinition, it) }
            ?: false
    }

    /*
     * Returns the longest known package that the import name starts with, e.g. for "com.app.Outer.Inner" returns
     * "com.app". Wildcard import name (e.g. "com.app" for "com.app.*") can be the package itself.
     */
    private fun getPackageName(importName: String): String? =
        packageNamesByImportName.getOrPut(importName) {
            var name = importName

            while (name.isNotEmpty() && name !in packageNames) {
                name = name.substringBeforeLast('.', "")
            }

            name.ifEmpty { null }
        }
}
