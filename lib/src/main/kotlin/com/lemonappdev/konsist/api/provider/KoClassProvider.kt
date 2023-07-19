package com.lemonappdev.konsist.api.provider

import com.lemonappdev.konsist.api.declaration.KoClassDeclaration

interface KoClassProvider : KoDeclarationProvider {
    fun classes(
        includeNested: Boolean = false,
        includeLocal: Boolean = false,
    ): Sequence<KoClassDeclaration>

    fun containsClass(
        name: String,
        includeNested: Boolean = false,
    ): Boolean

    fun numClasses(includeNested: Boolean = false): Int
}
