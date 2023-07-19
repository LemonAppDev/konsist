package com.lemonappdev.konsist.api.provider

import com.lemonappdev.konsist.api.declaration.KoPropertyDeclaration

interface KoPropertyProvider : KoDeclarationProvider {
    fun properties(
        includeNested: Boolean = false,
        includeLocal: Boolean = false,
    ): Sequence<KoPropertyDeclaration>

    fun containsProperty(
        name: String,
        includeNested: Boolean = false,
        includeLocal: Boolean = false,
    ): Boolean

    fun numProperties(includeNested: Boolean = false, includeLocal: Boolean = false): Int
}
