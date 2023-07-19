package com.lemonappdev.konsist.api.provider

import com.lemonappdev.konsist.api.declaration.KoFunctionDeclaration
import com.lemonappdev.konsist.core.provider.util.KoDeclarationProviderCoreUtil.localDeclarations

interface KoLocalFunctionProvider {
    fun localFunctions(): Sequence<KoFunctionDeclaration>

    fun containsLocalFunction(name: String): Boolean
}
