package com.lemonappdev.konsist.api.provider

import com.lemonappdev.konsist.api.declaration.KoPackageDeclaration

/**
 * An interface representing a Kotlin scope that provides access to packages.
 */
interface KoPackagesProvider : KoBaseProvider {
    /**
     * Sequence of packages.
     */
    val packages: Sequence<KoPackageDeclaration>

    /**
     * Whether the scope has packages.
     *
     * @param names the names of the packages to check.
     * @return `true` if the scope has packages with the specified names (or any package if [names] is empty), `false` otherwise.
     */
    fun hasPackages(vararg names: String): Boolean
}
