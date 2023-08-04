package com.lemonappdev.konsist.api.provider

import com.lemonappdev.konsist.api.declaration.KoFunctionDeclaration

/**
 * An interface representing a Kotlin declaration that provides information about functions.
 */
interface KoFunctionProvider : KoBaseProvider {
    /**
     * The functions present in the declaration.
     *
     * @param includeNested specifies whether to include nested functions.
     * @param includeLocal specifies whether to include local functions.
     * @return a list of [KoFunctionDeclaration] representing the functions in the declaration.
     */
    fun functions(
        includeNested: Boolean = false,
        includeLocal: Boolean = false,
    ): List<KoFunctionDeclaration>

    /**
     * Checks whether the declaration contains a function with the specified name.
     *
     * @param name The name of the function to check.
     * @param includeNested Specifies whether to include nested functions in the check (optional, default is `false`).
     * @param includeLocal Specifies whether to include local functions in the check (optional, default is `false`).
     * @return `true` if the declaration contains a function with the specified name, `false` otherwise.
     */
    fun containsFunction(
        name: String,
        includeNested: Boolean = false,
        includeLocal: Boolean = false,
    ): Boolean

    /**
     * Gets the number of functions present in the declaration.
     *
     * @param includeNested Specifies whether to include nested functions in the count (optional, default is `false`).
     * @param includeLocal Specifies whether to include local functions in the count (optional, default is `false`).
     * @return The number of functions in the declaration.
     */
    fun numFunctions(includeNested: Boolean = false, includeLocal: Boolean = false): Int
}
