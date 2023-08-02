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
     * Checks whether the declaration contains a local function with the specified name.
     *
     * @param name The name of the local function to check.
     * @return `true` if the declaration contains a local function with the specified name, `false` otherwise.
     */
    fun containsLocalFunction(name: String): Boolean
}
