package com.lemonappdev.konsist.api.provider

/**
 * An interface representing a Kotlin declaration that provides information about whether it resides in or outside a package.
 */
interface KoResideInPackageProvider : KoBaseProvider {
    /**
     * Determines whether the declaration resides in the specified package.
     *
     * @param name the package name to check.
     * @return `true` if the declaration resides in the specified package, `false` otherwise.
     */
    fun residesInPackage(name: String): Boolean

    /**
     * Determines whether the declaration resides outside the specified package.
     *
     * @param name the package name to check.
     * @return `true` if the declaration resides outside the specified package, `false` otherwise.
     */
    fun residesOutsidePackage(name: String): Boolean

    /**
     * Determines whether the declaration resides in the specified package.
     *
     * @param name the package name to check.
     * @return `true` if the declaration resides in the specified package, `false` otherwise.
     */
    @Deprecated("Will be removed in version 1.0.0-rc1", ReplaceWith("residesInPackage(name)"))
    fun resideInPackage(name: String): Boolean

    /**
     * Determines whether the declaration resides outside the specified package.
     *
     * @param name the package name to check.
     * @return `true` if the declaration resides outside the specified package, `false` otherwise.
     */
    @Deprecated("Will be removed in version 1.0.0-rc1", ReplaceWith("residesOutsidePackage(name)"))
    fun resideOutsidePackage(name: String): Boolean
}
