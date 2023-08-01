package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.declaration.KoPropertyDeclaration
import com.lemonappdev.konsist.api.provider.KoLocalPropertyProvider

internal interface KoLocalPropertyProviderCore : KoLocalPropertyProvider, KoLocalDeclarationProviderCore, KoBaseProviderCore {
    override val localProperties: List<KoPropertyDeclaration>
        get() = localDeclarations.filterIsInstance<KoPropertyDeclaration>()

    override val numLocalProperties: Int
        get() = localProperties.toList().size

    override fun containsLocalProperty(name: String): Boolean = localProperties.any { it.name == name }
}
