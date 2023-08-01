package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.declaration.KoPropertyDeclaration
import com.lemonappdev.konsist.api.provider.KoLocalPropertyProvider

internal interface KoLocalPropertyProviderCore : KoLocalPropertyProvider, KoLocalDeclarationProviderCore, KoBaseProviderCore {
    override val localProperties: Sequence<KoPropertyDeclaration>
        get() = localDeclarations.filterIsInstance<KoPropertyDeclaration>()

    override fun containsLocalProperty(name: String): Boolean = localProperties.any { it.name == name }
}
