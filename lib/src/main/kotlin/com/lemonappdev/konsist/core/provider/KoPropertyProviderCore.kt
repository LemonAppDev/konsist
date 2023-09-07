package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.declaration.KoPropertyDeclaration
import com.lemonappdev.konsist.api.provider.KoPropertyProvider
import com.lemonappdev.konsist.core.provider.util.KoDeclarationProviderCoreUtil

internal interface KoPropertyProviderCore : KoPropertyProvider, KoDeclarationProviderCore, KoBaseProviderCore {
    override fun properties(
        includeNested: Boolean,
        includeLocal: Boolean,
    ): List<KoPropertyDeclaration> =
        KoDeclarationProviderCoreUtil.getKoDeclarations(
            declarations(includeNested = false, includeLocal = false),
            includeNested,
            includeLocal
        )

    override fun containsProperty(
        includeNested: Boolean,
        includeLocal: Boolean,
        predicate: (KoPropertyDeclaration) -> Boolean,
    ): Boolean =
        properties(includeNested, includeLocal).any { predicate(it) }

    override fun numProperties(includeNested: Boolean, includeLocal: Boolean): Int =
        properties(includeNested, includeLocal).size

    override fun countProperties(
        includeNested: Boolean,
        includeLocal: Boolean,
        predicate: (KoPropertyDeclaration) -> Boolean,
    ): Int = properties(includeNested, includeLocal).count { predicate(it) }
}
