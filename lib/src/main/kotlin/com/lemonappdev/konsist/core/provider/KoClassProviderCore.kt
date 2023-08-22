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
        includeNested: Boolean,
        includeLocal: Boolean,
        predicate: (KoClassDeclaration) -> Boolean,
    ): Boolean =
        classes(includeNested, includeLocal).any { predicate(it) }

    override fun numClasses(includeNested: Boolean, includeLocal: Boolean): Int =
        classes(includeNested, includeLocal).size

    override fun countClasses(
        includeNested: Boolean,
        includeLocal: Boolean,
        predicate: (KoClassDeclaration) -> Boolean,
    ): Int = classes(includeNested, includeLocal).count { predicate(it) }
}
