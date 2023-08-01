package com.lemonappdev.konsist.api.provider

import com.lemonappdev.konsist.api.declaration.KoPropertyDeclaration

/**
 * An interface representing a Kotlin scope, file, or declaration that provides information about properties.
 */
interface KoPropertyProvider : KoBaseProvider {
    /**
     * The properties present in the scope, file or declaration.
     *
     * @param includeNested specifies whether to include nested properties.
     * @param includeLocal specifies whether to include local properties.
     * @return a list of [KoPropertyDeclaration] representing the properties in the scope, file or declaration.
     */
    fun properties(
        includeNested: Boolean = false,
        includeLocal: Boolean = false,
    ): List<KoPropertyDeclaration>

    /**
     * Checks whether the scope, file or declaration contains a property with the specified name.
     *
     * @param name The name of the property to check.
     * @param includeNested Specifies whether to include nested properties in the check (optional, default is `false`).
     * @param includeLocal Specifies whether to include local properties in the check (optional, default is `false`).
     * @return `true` if the scope, file, or declaration contains a property with the specified name, `false` otherwise.
     */
    fun containsProperty(
        name: String,
        includeNested: Boolean = false,
        includeLocal: Boolean = false,
    ): Boolean

    /**
     * Gets the number of properties present in the scope, file, or declaration.
     *
     * @param includeNested Specifies whether to include nested properties in the count (optional, default is `false`).
     * @param includeLocal Specifies whether to include local properties in the count (optional, default is `false`).
     * @return The number of properties in the scope, file, or declaration.
     */
    fun numProperties(includeNested: Boolean = false, includeLocal: Boolean = false): Int
}
