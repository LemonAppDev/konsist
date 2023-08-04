package com.lemonappdev.konsist.api.provider

import com.lemonappdev.konsist.api.declaration.KoBaseDeclaration

/**
 * An interface representing a Kotlin declaration that provides information about declarations.
 */
interface KoDeclarationProvider : KoBaseProvider {
    /**
     * The declarations present in the declaration.
     *
     * @param includeNested specifies whether to include nested declarations.
     * @param includeLocal specifies whether to include local declarations.
     * @return a list of [KoBaseDeclaration] representing the declarations in the declaration.
     */
    fun declarations(
        includeNested: Boolean = false,
        includeLocal: Boolean = false,
    ): List<KoBaseDeclaration>

    /**
     * Checks whether the declaration contains a declarations with the specified name.
     *
     * @param name The name of the declarations to check.
     * @param includeNested Specifies whether to include nested declarations in the check (optional, default is `false`).
     * @param includeLocal Specifies whether to include local declarations in the check (optional, default is `false`).
     * @return `true` if the declaration contains a declarations with the specified name, `false` otherwise.
     */
    fun containsDeclaration(
        name: String,
        includeNested: Boolean = false,
        includeLocal: Boolean = false,
    ): Boolean

    /**
     * Gets the number of declarations present in the declaration.
     *
     * @param includeNested Specifies whether to include nested declarations in the count (optional, default is `false`).
     * @param includeLocal Specifies whether to include local declarations in the count (optional, default is `false`).
     * @return The number of declarations in the declaration.
     */
    fun numDeclarations(includeNested: Boolean = false, includeLocal: Boolean = false,): Int
}
