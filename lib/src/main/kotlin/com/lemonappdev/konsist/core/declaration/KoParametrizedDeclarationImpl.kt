package com.lemonappdev.konsist.core.declaration

import com.lemonappdev.konsist.api.declaration.KoBaseDeclaration
import com.lemonappdev.konsist.api.declaration.KoParameterDeclaration
import com.lemonappdev.konsist.api.declaration.KoParametrizedDeclaration
import com.lemonappdev.konsist.core.util.TagUtil
import org.jetbrains.kotlin.psi.KtFunction

internal abstract class KoParametrizedDeclarationImpl(
    private val ktFunction: KtFunction,
    parentDeclaration: KoBaseDeclaration?,
) : KoDeclarationImpl(ktFunction, parentDeclaration), KoParametrizedDeclaration {

    override val parameters: List<KoParameterDeclaration> by lazy {
        ktFunction
            .valueParameters
            .map { KoParameterDeclarationImpl.getInstance(it, this) }
    }

    override fun hasValidParamTag(enabled: Boolean): Boolean = TagUtil.hasValidParamTag(enabled, parameters, kDoc)

    override fun hasParameterNamed(name: String): Boolean = parameters.any { it.name == name }
}
