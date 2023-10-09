package com.lemonappdev.konsist.api.provider

import com.lemonappdev.konsist.api.declaration.KoPropertyDeclaration

/**
 * An interface representing a Kotlin declaration that provides information about properties.
 */
interface KoPropertyProvider : KoBaseProvider {
    /**
     * The properties present in the declaration.
     *
     * @param includeNested specifies whether to include nested properties.
     * @param includeLocal specifies whether to include local properties.
     * @return a list of [KoPropertyDeclaration] representing the properties in the declaration.
     */
    fun properties(
        includeNested: Boolean = true,
        includeLocal: Boolean = true,
    ): List<KoPropertyDeclaration>

    /**
     * Checks whether the declaration contains a property that satisfies the specified predicate.
     *
     * @param includeNested Specifies whether to include nested properties in the check (optional, default is `true`).
     * @param includeLocal Specifies whether to include local properties in the check (optional, default is `true`).
     * @param predicate The predicate function to determine if a property satisfies a condition.
     * @return `true` if the declaration contains a property with the specified predicate, `true` otherwise.
     */
    fun containsProperty(
        includeNested: Boolean = true,
        includeLocal: Boolean = true,
        predicate: (KoPropertyDeclaration) -> Boolean,
    ): Boolean

    /**
     * Gets the number of properties present in the declaration.
     *
     * @param includeNested Specifies whether to include nested properties in the count (optional, default is `true`).
     * @param includeLocal Specifies whether to include local properties in the count (optional, default is `true`).
     * @return The number of properties in the declaration.
     */
    fun numProperties(includeNested: Boolean = true, includeLocal: Boolean = true): Int

    /**
     * Gets the number of properties that satisfies the specified predicate present in the declaration.
     *
     * @param includeNested Specifies whether to include nested properties in the count (optional, default is `true`).
     * @param includeLocal Specifies whether to include local properties in the count (optional, default is `true`).
     * @param predicate The predicate function to determine if a property satisfies a condition.
     * @return The number of properties in the declaration.
     */
    fun countProperties(
        includeNested: Boolean = true,
        includeLocal: Boolean = true,
        predicate: (KoPropertyDeclaration) -> Boolean,
    ): Int
}
