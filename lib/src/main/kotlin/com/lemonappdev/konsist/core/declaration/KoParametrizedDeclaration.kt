package com.lemonappdev.konsist.core.declaration

import org.jetbrains.kotlin.psi.KtFunction

abstract class KoParametrizedDeclaration(
    private val ktFunction: KtFunction,
) : KoDeclaration(ktFunction) {

    val parameters by lazy { ktFunction.valueParameters.map { KoParameterDeclaration.getInstance(it) } }

    fun hasParameterNamed(name: String? = null) = when (name) {
        null -> parameters.isNotEmpty()
        else -> parameters.firstOrNull()?.name == name
    }
}
