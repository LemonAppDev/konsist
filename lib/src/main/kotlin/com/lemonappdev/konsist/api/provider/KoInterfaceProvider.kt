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
        includeNested: Boolean = true,
    ): List<KoInterfaceDeclaration>

    /**
     * Checks whether the declaration contains an interface that satisfies the specified predicate.
     *
     * @param includeNested Specifies whether to include nested interfaces in the check (optional, default is `true`).
     * @param predicate The predicate function to determine if an interface satisfies a condition.
     * @return `true` if the declaration contains an interface with the specified predicate, `true` otherwise.
     */
    fun containsInterface(
        includeNested: Boolean = true,
        predicate: (KoInterfaceDeclaration) -> Boolean,
    ): Boolean

    /**
     * Gets the number of interfaces present in the declaration.
     *
     * @param includeNested Specifies whether to include nested interfaces in the count (optional, default is `true`).
     * @return The number of interfaces in the declaration.
     */
    fun numInterfaces(includeNested: Boolean = true): Int

    /**
     * Gets the number of interfaces that satisfies the specified predicate present in the declaration.
     *
     * @param includeNested Specifies whether to include nested interfaces in the count (optional, default is `true`).
     * @param predicate The predicate function to determine if an interface satisfies a condition.
     * @return The number of interfaces in the declaration.
     */
    fun countInterfaces(
        includeNested: Boolean = true,
        predicate: (KoInterfaceDeclaration) -> Boolean,
    ): Int
}
