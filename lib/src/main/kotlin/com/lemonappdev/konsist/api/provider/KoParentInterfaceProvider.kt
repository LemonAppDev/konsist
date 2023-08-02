package com.lemonappdev.konsist.api.provider

import com.lemonappdev.konsist.api.declaration.KoParentDeclaration
import com.lemonappdev.konsist.api.declaration.KoParentInterfaceDeclaration

/**
 * An interface representing a Kotlin declaration that provides access to its parent interfaces.
 */
interface KoParentInterfaceProvider : KoBaseProvider {
    /**
     * The parent interfaces of the declaration.
     */
    val parentInterfaces: List<KoParentInterfaceDeclaration>

    /**
     * The number of parent interfaces.
     */
    val numParentInterfaces: Int

    /**
     * Whatever declaration has parent interfaces.
     *
     * @param names the names of the parent interfaces to check.
     * @return `true` if the declaration has parent interfaces with the specified names (or any parent interface if [names] is empty),
     * `false` otherwise.
     */
    fun hasParentInterfaces(vararg names: String): Boolean
}
