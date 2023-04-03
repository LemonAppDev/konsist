package com.lemon.konsist.core.declaration

import org.jetbrains.kotlin.psi.KtConstructor

open class KoConstructor(
    private val ktPrimaryConstructor: KtConstructor<*>,
) : KoDeclaration(ktPrimaryConstructor) {

    val parameters by lazy { ktPrimaryConstructor.valueParameters.map { KoParameter(it) } }

    fun hasParameterNamed(name: String) = parameters.firstOrNull()?.name == name
}
