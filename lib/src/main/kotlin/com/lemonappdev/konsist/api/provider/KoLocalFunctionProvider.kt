package com.lemonappdev.konsist.api.provider

import com.lemonappdev.konsist.api.declaration.KoFunctionDeclaration

/**
 * An interface representing a Kotlin declaration that provides information about local functions.
 */
interface KoLocalFunctionProvider : KoBaseProvider {
    /**
     * The local functions present in the declaration.
     */
    val localFunctions: List<KoFunctionDeclaration>

    /**
     * The number of local functions.
     */
    val numLocalFunctions: Int

    /**
     * Returns the number of local functions that satisfies the specified predicate present in the declaration.
     *
     * @param predicate The predicate function to determine if a local function satisfies a condition.
     * @return The number of local functions in the declaration.
     */
    fun countLocalFunctions(predicate: (KoFunctionDeclaration) -> Boolean): Int

    /**
     * Determines whatever the declaration contains a local function with the specified name.
     *
     * @param predicate The predicate function to determine if a local function satisfies a condition.
     * @return `true` if the declaration contains a local function with the specified name, `false` otherwise.
     */
    @Deprecated("Will be removed in v1.0.0", ReplaceWith("hasLocalFunction()"))
    fun containsLocalFunction(predicate: (KoFunctionDeclaration) -> Boolean): Boolean

    /**
     * Determines whatever the declaration has local functions.
     *
     * @return `true` if the declaration has any local function, `false` otherwise.
     */
    fun hasLocalFunctions(): Boolean

    /**
     * Determines whether the declaration has at least one local function whose name matches any of the specified names.
     *
     * @param name the name of the local function to check.
     * @param names the names of the local functions to check.
     * @return `true` if there is a matching declaration, `false` otherwise.
     */
    fun hasLocalFunctionWithName(name: String, vararg names: String): Boolean

    /**
     * Determines whether the declaration has local functions with all the specified names.
     *
     * @param name The name of the local function to check.
     * @param names The names of the local functions to check.
     * @return `true` if there are declarations with all the specified names, `false` otherwise.
     */
    fun hasLocalFunctionsWithAllNames(name: String, vararg names: String): Boolean

    /**
     * Determines whether the declaration has at least one local function that satisfies the provided predicate.
     *
     * @param predicate A function that defines the condition to be met by a local function declaration.
     * @return `true` if there is a matching declaration, `false` otherwise.
     */
    fun hasLocalFunction(predicate: (KoFunctionDeclaration) -> Boolean): Boolean

    /**
     * Determines whether the declaration has all local functions that satisfy the provided predicate.
     *
     * Note that if the local functions contains no elements, the function returns `true` because there are no elements in it
     * that do not match the predicate.
     *
     * @param predicate A function that defines the condition to be met by local function declarations.
     * @return `true` if all local function declarations satisfy the predicate, `false` otherwise.
     */
    fun hasAllLocalFunctions(predicate: (KoFunctionDeclaration) -> Boolean): Boolean
}
