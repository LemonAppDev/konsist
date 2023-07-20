package com.lemonappdev.konsist.api.provider

import com.lemonappdev.konsist.api.declaration.KoFunctionDeclaration

interface KoFunctionProvider : KoBaseProvider {
    /**
     * The functions present in the scope, file or declaration.
     *
     * @param includeNested specifies whether to include nested functions.
     * @param includeLocal specifies whether to include local functions.
     * @return a sequence of [KoFunctionDeclaration] representing the functions in the scope, file or declaration.
     */
    fun functions(
        includeNested: Boolean = false,
        includeLocal: Boolean = false,
    ): Sequence<KoFunctionDeclaration>

    fun containsFunction(
        name: String,
        includeNested: Boolean = false,
        includeLocal: Boolean = false,
    ): Boolean

    fun numFunctions(includeNested: Boolean = false, includeLocal: Boolean = false): Int
}
