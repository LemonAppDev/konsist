package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.declaration.KoInterfaceDeclaration
import com.lemonappdev.konsist.api.provider.KoInterfaceProvider
import com.lemonappdev.konsist.core.provider.util.KoDeclarationCoreProviderUtil

internal interface KoInterfaceProviderCore: KoInterfaceProvider, KoDeclarationProviderCore {
    override fun interfaces(
        includeNested: Boolean,
    ): Sequence<KoInterfaceDeclaration> =
        KoDeclarationCoreProviderUtil.getKoDeclarations(declarations(), includeNested)

    override fun containsInterface(
        name: String,
        includeNested: Boolean,
    ): Boolean =
        interfaces(includeNested).any { it.name == name }

    override fun numInterfaces(includeNested: Boolean): Int = interfaces(includeNested).toList().size
}
