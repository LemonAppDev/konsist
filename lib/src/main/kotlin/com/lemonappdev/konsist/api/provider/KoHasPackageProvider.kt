package com.lemonappdev.konsist.api.provider

/**
 * An interface representing a Kotlin declaration that provides information about whether it has a package.
 */
interface KoHasPackageProvider : KoBaseProvider {
    /**
     * Determines whatever the package matches the directory structure (file path).
     */
    val hasMatchingPackage: Boolean

    /**
     * Determines whatever the declaration has package.
     *
     * @param name the name of the package to check.
     * @return `true` if the declaration has a package with the specified name, `false` otherwise.
     */
    fun hasPackage(name: String): Boolean
}
