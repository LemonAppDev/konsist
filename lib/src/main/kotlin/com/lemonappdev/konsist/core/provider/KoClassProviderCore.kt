package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.declaration.KoClassDeclaration
import com.lemonappdev.konsist.api.provider.KoClassProvider
import com.lemonappdev.konsist.core.provider.util.KoDeclarationProviderCoreUtil

internal interface KoClassProviderCore : KoClassProvider, KoDeclarationProviderCore, KoBaseProviderCore {
    override fun classes(
        includeNested: Boolean,
        includeLocal: Boolean,
    ): List<KoClassDeclaration> =
        KoDeclarationProviderCoreUtil.getKoDeclarations(declarations(), includeNested, includeLocal)

    override fun containsClass(
        name: String,
        includeNested: Boolean,
        includeLocal: Boolean,
    ): Boolean =
        classes(includeNested, includeLocal).any { it.name == name }

    override fun numClasses(includeNested: Boolean, includeLocal: Boolean): Int =
        classes(includeNested, includeLocal).size
}
