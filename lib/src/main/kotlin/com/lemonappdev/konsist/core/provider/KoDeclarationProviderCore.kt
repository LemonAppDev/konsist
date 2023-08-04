package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.provider.KoDeclarationProvider

internal interface KoDeclarationProviderCore : KoDeclarationProvider, KoBaseProviderCore {
    override fun containsDeclarations(
        name: String,
        includeNested: Boolean,
    ): Boolean = declarations(includeNested = includeNested).any { (it as? KoNameProviderCore)?.name == name }

    override fun numDeclarations(includeNested: Boolean): Int = declarations(includeNested).size
}
