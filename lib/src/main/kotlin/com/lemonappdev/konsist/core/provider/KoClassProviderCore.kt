package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.declaration.KoClassDeclaration
import com.lemonappdev.konsist.api.provider.KoClassProvider
import com.lemonappdev.konsist.core.provider.util.KoDeclarationProviderCoreUtil

internal interface KoClassProviderCore : KoClassProvider, KoDeclarationProviderCore, KoBaseProviderCore {
    override fun classes(
        includeNested: Boolean,
        includeLocal: Boolean,
    ): Sequence<KoClassDeclaration> =
        KoDeclarationProviderCoreUtil.getKoDeclarations(declarations(), includeNested, includeLocal)

    override fun containsClass(
        name: String,
        includeNested: Boolean,
    ): Boolean =
        classes(includeNested).any { it.name == name }

    override fun numClasses(includeNested: Boolean): Int = classes(includeNested).toList().size
}
