package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.declaration.KoPropertyDeclaration
import com.lemonappdev.konsist.api.provider.KoPropertyProvider
import com.lemonappdev.konsist.core.provider.util.KoDeclarationCoreProviderUtil

internal interface KoPropertyProviderCore: KoPropertyProvider, KoDeclarationProviderCore {
    override fun properties(
        includeNested: Boolean,
        includeLocal: Boolean,
    ): Sequence<KoPropertyDeclaration> = KoDeclarationCoreProviderUtil.getKoDeclarations(declarations(), includeNested, includeLocal)

    override fun containsProperty(
        name: String,
        includeNested: Boolean,
        includeLocal: Boolean,
    ): Boolean =
        properties(includeNested, includeLocal).any { it.name == name }

    override fun numProperties(includeNested: Boolean, includeLocal: Boolean): Int =
        properties(includeNested, includeLocal).toList().size
}
