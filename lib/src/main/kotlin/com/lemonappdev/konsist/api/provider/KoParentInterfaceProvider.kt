package com.lemonappdev.konsist.api.provider

import com.lemonappdev.konsist.api.declaration.KoParentInterfaceDeclaration

/**
 * An interface representing a Kotlin declaration that provides access to its parent interfaces.
 */
@Deprecated("Will be removed in v1.0.0", ReplaceWith("KoParentProvider"))
interface KoParentInterfaceProvider : KoBaseProvider {
    /**
     * The parent interfaces of the declaration.
     */
    @Deprecated("Will be removed in v1.0.0", ReplaceWith("parents"))
    val parentInterfaces: List<KoParentInterfaceDeclaration>

    /**
     * The number of parent interfaces.
     */
    @Deprecated("Will be removed in v1.0.0", ReplaceWith("numParents"))
    val numParentInterfaces: Int

    /**
     * Whatever declaration has parent interfaces.
     *
     * @param names the names of the parent interfaces to check.
     * @return `true` if the declaration has parent interfaces with the specified names (or any parent interface if [names] is empty),
     * `false` otherwise.
     */
    @Deprecated("Will be removed in v1.0.0", ReplaceWith("hasParents()"))
    fun hasParentInterfaces(vararg names: String): Boolean
}
