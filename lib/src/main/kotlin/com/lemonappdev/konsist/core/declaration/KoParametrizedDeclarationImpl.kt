package com.lemonappdev.konsist.core.declaration

import org.jetbrains.kotlin.psi.KtFunction

abstract class KoParametrizedDeclarationImpl(
    private val ktFunction: KtFunction,
) : KoDeclarationImpl(ktFunction) {

    val parameters by lazy { ktFunction.valueParameters.map { KoParameterDeclarationImpl.getInstance(it) } }

    fun hasParameterNamed(name: String? = null) = when (name) {
        null -> parameters.isNotEmpty()
        else -> parameters.firstOrNull()?.name == name
    }
}
