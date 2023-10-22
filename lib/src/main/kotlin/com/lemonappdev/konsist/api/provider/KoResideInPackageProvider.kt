package com.lemonappdev.konsist.api.provider

/**
 * An interface representing a Kotlin declaration that provides information about whether it resides in or outside a package.
 */
interface KoResideInPackageProvider : KoBaseProvider {
    /**
     * Determines whatever the declaration resides in a package.
     *
     * @param name the package name to check.
     * @return `true` if the declaration resides in the specified package, `false` otherwise.
     */
    fun resideInPackage(name: String): Boolean

    /**
     * Determines whatever the declaration resides outside a package.
     *
     * @param name the package name to check.
     * @return `true` if the declaration resides outside the specified package, `false` otherwise.
     */
    fun resideOutsidePackage(name: String): Boolean
}
