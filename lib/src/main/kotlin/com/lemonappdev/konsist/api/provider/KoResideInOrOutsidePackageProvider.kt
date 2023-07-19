package com.lemonappdev.konsist.api.provider

interface KoResideInOrOutsidePackageProvider {
    /**
     * Whether the declaration resides in a package.
     *
     * @param packagee the package name to check.
     * @return `true` if the declaration resides in the specified package, `false` otherwise.
     */
    fun resideInPackage(packagee: String): Boolean

    /**
     * Whether the declaration resides outside a package.
     *
     * @param packagee the package name to check.
     * @return `true` if the declaration resides outside the specified package, `false` otherwise.
     */
    fun resideOutsidePackage(packagee: String): Boolean
}
