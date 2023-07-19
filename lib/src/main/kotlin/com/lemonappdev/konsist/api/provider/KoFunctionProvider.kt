package com.lemonappdev.konsist.api.provider

import com.lemonappdev.konsist.api.declaration.KoFunctionDeclaration

interface KoFunctionProvider : KoDeclarationProvider {
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
