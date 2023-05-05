package com.lemonappdev.konsist.core.declaration

import com.lemonappdev.konsist.api.declaration.KoParametrizedDeclaration
import org.jetbrains.kotlin.psi.KtFunction

internal abstract class KoParametrizedDeclarationImpl(
    private val ktFunction: KtFunction,
    parent: KoBaseDeclarationImpl?,
) : KoDeclarationImpl(ktFunction, parent), KoParametrizedDeclaration {

    override val parameters by lazy { ktFunction.valueParameters.map { KoParameterDeclarationImpl.getInstance(it, this) } }

    override fun hasParameterNamed(name: String?) = when (name) {
        null -> parameters.isNotEmpty()
        else -> parameters.firstOrNull()?.name == name
    }
}
