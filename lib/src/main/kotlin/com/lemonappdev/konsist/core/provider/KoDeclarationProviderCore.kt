package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.provider.KoDeclarationProvider

internal interface KoDeclarationProviderCore : KoDeclarationProvider, KoBaseProviderCore {
    override fun containsDeclaration(
        name: String,
        includeNested: Boolean,
        includeLocal: Boolean
    ): Boolean = declarations(
        includeNested = includeNested,
        includeLocal = includeLocal
    ).any { (it as KoNameProviderCore).name == name }

    override fun numDeclarations(includeNested: Boolean, includeLocal: Boolean): Int =
        declarations(includeNested, includeLocal).size
}
