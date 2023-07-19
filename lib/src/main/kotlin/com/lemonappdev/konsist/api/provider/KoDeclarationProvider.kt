package com.lemonappdev.konsist.api.provider

import com.lemonappdev.konsist.api.declaration.KoBaseDeclaration

interface KoDeclarationProvider {
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
