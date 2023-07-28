package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.provider.KoLocalDeclarationProvider
import com.lemonappdev.konsist.api.provider.KoNameProvider

internal interface KoLocalDeclarationProviderCore : KoLocalDeclarationProvider, KoBaseProviderCore {
    override fun containsLocalDeclarations(name: String): Boolean = localDeclarations().any { (it as KoNameProvider).name == name }
}
