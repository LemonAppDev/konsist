package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.provider.KoDeclarationProvider

internal interface KoDeclarationProviderCore: KoDeclarationProvider {
    override fun containsDeclarations(
        name: String,
        includeNested: Boolean,
    ): Boolean = declarations(includeNested).any { it.name == name }

    override fun numDeclarations(includeNested: Boolean): Int = declarations(includeNested).toList().size
}
