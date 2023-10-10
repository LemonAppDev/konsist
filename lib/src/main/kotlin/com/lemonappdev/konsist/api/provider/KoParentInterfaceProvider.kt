package com.lemonappdev.konsist.api.provider

import com.lemonappdev.konsist.api.declaration.KoInterfaceDeclaration

/**
 * An interface representing a Kotlin declaration that provides access to its parent interfaces.
 */
interface KoParentInterfaceProvider : KoBaseProvider {
    /**
     * The parent interfaces of the declaration.
     */
    val parentInterfaces: List<KoInterfaceDeclaration>

    /**
     * The number of parent interfaces.
     */
    val numParentInterfaces: Int

    fun countParentInterfaces(predicate: (KoInterfaceDeclaration) -> Boolean): Int

    /**
     * Whatever declaration has parent interfaces.
     *
     * @param names the names of the parent interfaces to check.
     * @return `true` if the declaration has parent interfaces with the specified names (or any parent interface if [names] is empty),
     * `false` otherwise.
     */
    @Deprecated("Will be removed in v1.0.0", ReplaceWith("hasParents()"))
    fun hasParentInterfaces(vararg names: String): Boolean

    fun hasParentInterfaces(): Boolean

    fun hasParentInterfaceWithName(name: String, vararg names: String): Boolean

    fun hasParentInterfacesWithAllNames(name: String, vararg names: String): Boolean

    fun hasParentInterface(predicate: (KoInterfaceDeclaration) -> Boolean): Boolean

    fun hasAllParentInterfaces(predicate: (KoInterfaceDeclaration) -> Boolean): Boolean
}
