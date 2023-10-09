package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.declaration.KoInterfaceDeclaration
import com.lemonappdev.konsist.api.provider.KoInterfaceProvider
import com.lemonappdev.konsist.core.provider.util.KoDeclarationProviderCoreUtil

internal interface KoInterfaceProviderCore : KoInterfaceProvider, KoDeclarationProviderCore, KoBaseProviderCore {
    override fun interfaces(
        includeNested: Boolean,
    ): List<KoInterfaceDeclaration> =
        KoDeclarationProviderCoreUtil.getKoDeclarations(
            declarations(includeNested = false, includeLocal = false),
            includeNested,
        )

    override fun containsInterface(
        includeNested: Boolean,
        predicate: (KoInterfaceDeclaration) -> Boolean,
    ): Boolean =
        interfaces(includeNested).any { predicate(it) }

    override fun numInterfaces(includeNested: Boolean): Int = interfaces(includeNested).size

    override fun countInterfaces(
        includeNested: Boolean,
        predicate: (KoInterfaceDeclaration) -> Boolean,
    ): Int = interfaces(includeNested).count { predicate(it) }
}
