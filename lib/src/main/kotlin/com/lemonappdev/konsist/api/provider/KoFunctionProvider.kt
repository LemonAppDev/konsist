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
     * Checks whether the declaration contains a function that satisfies the specified predicate.
     *
     * @param includeNested Specifies whether to include nested functions in the check (optional, default is `false`).
     * @param includeLocal Specifies whether to include local functions in the check (optional, default is `false`).
     * @param predicate The predicate function to determine if a function satisfies a condition.
     * @return `true` if the declaration contains a function with the specified predicate, `false` otherwise.
     */
    fun containsFunction(
        includeNested: Boolean = false,
        includeLocal: Boolean = false,
        predicate: (KoFunctionDeclaration) -> Boolean,
    ): Boolean

    /**
     * Gets the number of functions present in the declaration.
     *
     * @param includeNested Specifies whether to include nested functions in the count (optional, default is `false`).
     * @param includeLocal Specifies whether to include local functions in the count (optional, default is `false`).
     * @return The number of functions in the declaration.
     */
    fun numFunctions(includeNested: Boolean = false, includeLocal: Boolean = false): Int

    /**
     * Gets the number of functions that satisfies the specified predicate present in the declaration.
     *
     * @param includeNested Specifies whether to include nested functions in the count (optional, default is `false`).
     * @param includeLocal Specifies whether to include local functions in the count (optional, default is `false`).
     * @param predicate The predicate function to determine if a function satisfies a condition.
     * @return The number of functions in the declaration.
     */
    fun countFunctions(
        includeNested: Boolean = false,
        includeLocal: Boolean = false,
        predicate: (KoFunctionDeclaration) -> Boolean,
    ): Int
}
