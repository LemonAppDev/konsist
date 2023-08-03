package com.lemonappdev.konsist.api.provider

import com.lemonappdev.konsist.api.declaration.KoInterfaceDeclaration

/**
 * An interface representing a Kotlin declaration that provides information about interfaces.
 */
interface KoInterfaceProvider : KoBaseProvider {
    /**
     * The interfaces present in the declaration.
     *
     * @param includeNested specifies whether to include nested interfaces.
     * @return a list of [KoInterfaceDeclaration] representing the interfaces in the declaration.
     */
    fun interfaces(
        includeNested: Boolean = false,
    ): List<KoInterfaceDeclaration>

    /**
     * Checks whether the declaration contains an interface with the specified name.
     *
     * @param name The name of the interface to check.
     * @param includeNested Specifies whether to include nested interfaces in the check (optional, default is `false`).
     * @return `true` if the declaration contains an interface with the specified name, `false` otherwise.
     */
    fun containsInterface(
        name: String,
        includeNested: Boolean = false,
    ): Boolean

    /**
     * Gets the number of interfaces present in the declaration.
     *
     * @param includeNested Specifies whether to include nested interfaces in the count (optional, default is `false`).
     * @return The number of interfaces in the declaration.
     */
    fun numInterfaces(includeNested: Boolean = false): Int
}
