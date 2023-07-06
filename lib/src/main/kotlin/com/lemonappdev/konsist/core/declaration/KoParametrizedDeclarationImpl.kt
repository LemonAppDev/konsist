package com.lemonappdev.konsist.core.declaration

import com.lemonappdev.konsist.api.declaration.KoParameterDeclaration
import com.lemonappdev.konsist.api.declaration.KoParametrizedDeclaration
import com.lemonappdev.konsist.api.provider.KoParentProvider
import com.lemonappdev.konsist.core.provider.KoAnnotationDeclarationProviderCore
import com.lemonappdev.konsist.core.provider.KoDeclarationFullyQualifiedNameProviderCore
import com.lemonappdev.konsist.core.provider.KoModifierProviderCore
import com.lemonappdev.konsist.core.provider.KoPackageDeclarationProviderCore
import com.lemonappdev.konsist.core.provider.KoTopLevelProviderCore
import com.lemonappdev.konsist.core.util.TagUtil
import org.jetbrains.kotlin.psi.KtFunction
import org.jetbrains.kotlin.psi.KtTypeParameterListOwner

internal abstract class KoParametrizedDeclarationImpl(
    private val ktFunction: KtFunction,
    parentDeclaration: KoParentProvider?,
) :
    KoParametrizedDeclaration,
    KoBaseDeclarationImpl(ktFunction),
    KoAnnotationDeclarationProviderCore,
    KoPackageDeclarationProviderCore,
    KoDeclarationFullyQualifiedNameProviderCore,
    KoModifierProviderCore,
    KoTopLevelProviderCore {
    override val ktTypeParameterListOwner: KtTypeParameterListOwner
        get() = ktFunction

    override val parameters: List<KoParameterDeclaration> by lazy {
        ktFunction
            .valueParameters
            .map { KoParameterDeclarationImpl.getInstance(it, this) }
    }

    override fun hasValidParamTag(enabled: Boolean): Boolean = TagUtil.hasValidParamTag(enabled, parameters, kDoc)

    override fun hasParameterNamed(name: String): Boolean = parameters.any { it.name == name }
}
