package com.lemonappdev.konsist.core.declaration

import org.jetbrains.kotlin.psi.KtFunction

abstract class KoParametrizedDeclaration(
    private val ktFunction: KtFunction,
) : KoDeclaration(ktFunction) {

    val parameters by lazy { ktFunction.valueParameters.map { KoParameter.getInstance(it) } }

    fun hasParameterNamed(name: String) = parameters.firstOrNull()?.name == name
}
