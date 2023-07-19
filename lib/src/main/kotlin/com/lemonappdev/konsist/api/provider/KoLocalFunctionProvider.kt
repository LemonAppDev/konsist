package com.lemonappdev.konsist.api.provider

import com.lemonappdev.konsist.api.declaration.KoFunctionDeclaration

interface KoLocalFunctionProvider : KoProvider {
    fun localFunctions(): Sequence<KoFunctionDeclaration>

    fun containsLocalFunction(name: String): Boolean
}
