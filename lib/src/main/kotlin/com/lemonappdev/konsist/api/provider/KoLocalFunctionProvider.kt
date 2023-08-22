package com.lemonappdev.konsist.api.provider

import com.lemonappdev.konsist.api.declaration.KoClassDeclaration
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
     * Gets the number of local functions that satisfies the specified predicate present in the declaration.
     *
     * @param predicate The predicate function to determine if a local function satisfies a condition.
     * @return The number of local functions in the declaration.
     */
    fun countLocalFunctions(predicate: (KoFunctionDeclaration) -> Boolean): Int

    /**
     * Checks whether the declaration contains a local function with the specified name.
     *
     * @param predicate The predicate function to determine if a local function satisfies a condition.
     * @return `true` if the declaration contains a local function with the specified name, `false` otherwise.
     */
    fun containsLocalFunction(predicate: (KoFunctionDeclaration) -> Boolean): Boolean
}
