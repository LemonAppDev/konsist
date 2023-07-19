package com.lemonappdev.konsist.api.provider

import com.lemonappdev.konsist.api.declaration.KoBaseDeclaration

interface KoDeclarationProvider: KoProvider {
    /**
     * The declarations present in the scope, file or declaration.
     *
     * @param includeNested specifies whether to include nested declarations.
     * @param includeLocal specifies whether to include local declarations.
     * @return a sequence of [KoBaseDeclaration] representing the declarations in the scope, file or declaration.
     */
    fun declarations(
        includeNested: Boolean = false,
        includeLocal: Boolean = false,
    ): Sequence<KoBaseDeclaration>

    fun containsDeclarations(
        name: String,
        includeNested: Boolean = false,
    ): Boolean

    fun numDeclarations(includeNested: Boolean = false): Int
}
