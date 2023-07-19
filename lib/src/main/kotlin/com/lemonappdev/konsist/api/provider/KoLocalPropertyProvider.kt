package com.lemonappdev.konsist.api.provider

import com.lemonappdev.konsist.api.declaration.KoPropertyDeclaration
import com.lemonappdev.konsist.core.provider.util.KoDeclarationProviderCoreUtil.localDeclarations

interface KoLocalPropertyProvider: KoProvider {
    fun localProperties(): Sequence<KoPropertyDeclaration>

    fun containsLocalProperty(name: String): Boolean
}
