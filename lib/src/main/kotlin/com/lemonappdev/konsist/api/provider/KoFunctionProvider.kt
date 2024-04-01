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
        includeNested: Boolean = true,
        includeLocal: Boolean = true,
    ): List<KoFunctionDeclaration>

    /**
     * Determines whatever the declaration contains a function that satisfies the specified predicate.
     *
     * @param includeNested Specifies whether to include nested functions in the check (optional, default is `true`).
     * @param includeLocal Specifies whether to include local functions in the check (optional, default is `true`).
     * @param predicate The predicate function to determine if a function satisfies a condition.
     * @return `true` if the declaration contains a function with the specified predicate, `true` otherwise.
     */
    @Deprecated("Will be removed in v0.16.0", ReplaceWith("hasFunction()"))
    fun containsFunction(
        includeNested: Boolean = true,
        includeLocal: Boolean = true,
        predicate: (KoFunctionDeclaration) -> Boolean,
    ): Boolean

    /**
     * Returns the number of functions present in the declaration.
     *
     * @param includeNested Specifies whether to include nested functions in the count (optional, default is `true`).
     * @param includeLocal Specifies whether to include local functions in the count (optional, default is `true`).
     * @return The number of functions in the declaration.
     */
    fun numFunctions(
        includeNested: Boolean = true,
        includeLocal: Boolean = true,
    ): Int

    /**
     * Returns the number of functions that satisfies the specified predicate present in the declaration.
     *
     * @param includeNested Specifies whether to include nested functions in the count (optional, default is `true`).
     * @param includeLocal Specifies whether to include local functions in the count (optional, default is `true`).
     * @param predicate The predicate function to determine if a function satisfies a condition.
     * @return The number of functions in the declaration.
     */
    fun countFunctions(
        includeNested: Boolean = true,
        includeLocal: Boolean = true,
        predicate: (KoFunctionDeclaration) -> Boolean,
    ): Int

    /**
     * Determines whatever the declaration has functions.
     *
     * @param includeNested Specifies whether to include nested functions in the check (optional, default is `true`).
     * @param includeLocal Specifies whether to include local functions in the check (optional, default is `true`).
     * @return `true` if the declaration has any function, `false` otherwise.
     */
    fun hasFunctions(
        includeNested: Boolean = true,
        includeLocal: Boolean = true,
    ): Boolean

    /**
     * Determines whether the declaration has at least one function whose name matches any of the specified names.
     *
     * @param name the name of the function to check.
     * @param names the names of the functions to check.
     * @param includeNested Specifies whether to include nested functions in the check (optional, default is `true`).
     * @param includeLocal Specifies whether to include local functions in the check (optional, default is `true`).
     * @return `true` if there is a matching declaration, `false` otherwise.
     */
    fun hasFunctionWithName(
        name: String,
        vararg names: String,
        includeNested: Boolean = true,
        includeLocal: Boolean = true,
    ): Boolean

    /**
     * Determines whether the declaration has at least one function whose name matches any of the specified names.
     *
     * @param names the names of the functions to check.
     * @param includeNested Specifies whether to include nested functions in the check (optional, default is `true`).
     * @param includeLocal Specifies whether to include local functions in the check (optional, default is `true`).
     * @return `true` if there is a matching declaration, `false` otherwise.
     */
    fun hasFunctionWithName(
        names: Collection<String>,
        includeNested: Boolean = true,
        includeLocal: Boolean = true,
    ): Boolean

    /**
     * Determines whether the declaration has functions with all the specified names.
     *
     * @param name The name of the function to check.
     * @param names The names of the functions to check.
     * @param includeNested Specifies whether to include nested functions in the check (optional, default is `true`).
     * @param includeLocal Specifies whether to include local functions in the check (optional, default is `true`).
     * @return `true` if there are declarations with all the specified names, `false` otherwise.
     */
    fun hasFunctionsWithAllNames(
        name: String,
        vararg names: String,
        includeNested: Boolean = true,
        includeLocal: Boolean = true,
    ): Boolean

    /**
     * Determines whether the declaration has functions with all the specified names.
     *
     * @param names The names of the functions to check.
     * @param includeNested Specifies whether to include nested functions in the check (optional, default is `true`).
     * @param includeLocal Specifies whether to include local functions in the check (optional, default is `true`).
     * @return `true` if there are declarations with all the specified names, `false` otherwise.
     */
    fun hasFunctionsWithAllNames(
        names: Collection<String>,
        includeNested: Boolean = true,
        includeLocal: Boolean = true,
    ): Boolean

    /**
     * Determines whether the declaration has at least one function that satisfies the provided predicate.
     *
     * @param includeNested Specifies whether to include nested functions in the check (optional, default is `true`).
     * @param includeLocal Specifies whether to include local functions in the check (optional, default is `true`).
     * @param predicate A function that defines the condition to be met by a function declaration.
     * @return `true` if there is a matching declaration, `false` otherwise.
     */
    fun hasFunction(
        includeNested: Boolean = true,
        includeLocal: Boolean = true,
        predicate: (KoFunctionDeclaration) -> Boolean,
    ): Boolean

    /**
     * Determines whether the declaration has all functions that satisfy the provided predicate.
     *
     * Note that if the functions contains no elements, the function returns `true` because there are no elements in it
     * that do not match the predicate.
     *
     * @param includeNested Specifies whether to include nested functions in the check (optional, default is `true`).
     * @param includeLocal Specifies whether to include local functions in the check (optional, default is `true`).
     * @param predicate A function that defines the condition to be met by function declarations.
     * @return `true` if all function declarations satisfy the predicate, `false` otherwise.
     */
    fun hasAllFunctions(
        includeNested: Boolean = true,
        includeLocal: Boolean = true,
        predicate: (KoFunctionDeclaration) -> Boolean,
    ): Boolean
}
