package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.provider.KoLocalDeclarationProvider

internal interface KoLocalDeclarationProviderCore : KoLocalDeclarationProvider {
    override fun containsLocalDeclarations(name: String): Boolean = localDeclarations().any { it.name == name }
}
