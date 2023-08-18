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
        includeNested: Boolean = false,
        includeLocal: Boolean = false,
    ): List<KoPropertyDeclaration>

    /**
     * Checks whether the declaration contains a property that satisfies the specified predicate.
     *
     * @param includeNested Specifies whether to include nested properties in the check (optional, default is `false`).
     * @param includeLocal Specifies whether to include local properties in the check (optional, default is `false`).
     * @param predicate The predicate function to determine if a property satisfies a condition.
     * @return `true` if the declaration contains a property with the specified predicate, `false` otherwise.
     */
    fun containsProperty(
        includeNested: Boolean = false,
        includeLocal: Boolean = false,
        predicate: (KoPropertyDeclaration) -> Boolean,
    ): Boolean

    /**
     * Gets the number of properties present in the declaration.
     *
     * @param includeNested Specifies whether to include nested properties in the count (optional, default is `false`).
     * @param includeLocal Specifies whether to include local properties in the count (optional, default is `false`).
     * @return The number of properties in the declaration.
     */
    fun countProperties(includeNested: Boolean = false, includeLocal: Boolean = false): Int

    /**
     * Gets the number of properties that satisfies the specified predicate present in the declaration.
     *
     * @param includeNested Specifies whether to include nested properties in the count (optional, default is `false`).
     * @param includeLocal Specifies whether to include local properties in the count (optional, default is `false`).
     * @param predicate The predicate function to determine if a property satisfies a condition.
     * @return The number of properties in the declaration.
     */
    fun countProperties(
        includeNested: Boolean = false,
        includeLocal: Boolean = false,
        predicate: (KoPropertyDeclaration) -> Boolean,
    ): Int
}
