package com.lemonappdev.konsist.core.declaration

import com.lemonappdev.konsist.api.declaration.KoBaseDeclaration
import com.lemonappdev.konsist.api.declaration.KoParametrizedDeclaration
import com.lemonappdev.konsist.core.util.TagHelper
import org.jetbrains.kotlin.psi.KtFunction

internal abstract class KoParametrizedDeclarationImpl(
    private val ktFunction: KtFunction,
    parent: KoBaseDeclaration?,
) : KoDeclarationImpl(ktFunction, parent), KoParametrizedDeclaration {

    override val parameters by lazy {
        ktFunction
            .valueParameters
            .map { KoParameterDeclarationImpl.getInstance(it, this) }
    }

    override fun hasValidParamTag(enabled: Boolean) = TagHelper.hasValidParamTag(enabled, parameters, kDoc)

    override fun hasParameterNamed(name: String) = parameters.firstOrNull()?.name == name
}
