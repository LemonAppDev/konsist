package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.declaration.KoFunctionDeclaration
import com.lemonappdev.konsist.api.provider.KoLocalFunctionProvider

internal interface KoLocalFunctionProviderCore : KoLocalFunctionProvider, KoLocalDeclarationProviderCore, KoBaseProviderCore {
    override fun localFunctions(): Sequence<KoFunctionDeclaration> = localDeclarations().filterIsInstance<KoFunctionDeclaration>()

    override fun containsLocalFunction(name: String): Boolean = localFunctions().any { it.name == name }
}
