package com.lemonappdev.konsist.api.provider

/**
 * An interface representing a Kotlin file that provides information about whether it has a package.
 */
interface KoHasPackageProvider : KoBaseProvider {
    /**
     * Whether the file has package.
     *
     * @param name the name of the package to check.
     * @return `true` if the file has a package with the specified name, `false` otherwise.
     */
    fun hasPackage(name: String): Boolean
}
