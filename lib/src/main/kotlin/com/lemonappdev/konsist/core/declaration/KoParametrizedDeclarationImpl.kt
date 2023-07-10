package com.lemonappdev.konsist.core.declaration

import com.lemonappdev.konsist.api.declaration.KoParameterDeclaration
import com.lemonappdev.konsist.api.declaration.KoParametrizedDeclaration
import com.lemonappdev.konsist.api.provider.KoParametersProvider
import com.lemonappdev.konsist.api.provider.KoParentProvider
import com.lemonappdev.konsist.core.provider.KoAnnotationDeclarationProviderCore
import com.lemonappdev.konsist.core.provider.KoDeclarationFullyQualifiedNameProviderCore
import com.lemonappdev.konsist.core.provider.KoModifierProviderCore
import com.lemonappdev.konsist.core.provider.KoPackageDeclarationProviderCore
import com.lemonappdev.konsist.core.provider.KoParametersProviderCore
import com.lemonappdev.konsist.core.provider.KoTopLevelProviderCore
import com.lemonappdev.konsist.core.util.TagUtil
import org.jetbrains.kotlin.psi.KtFunction
import org.jetbrains.kotlin.psi.KtTypeParameterListOwner

internal abstract class KoParametrizedDeclarationImpl(
    override val ktFunction: KtFunction,
    parentDeclaration: KoParentProvider?,
) :
    KoParametrizedDeclaration,
    KoBaseDeclarationImpl(ktFunction),
    KoAnnotationDeclarationProviderCore,
    KoPackageDeclarationProviderCore,
    KoDeclarationFullyQualifiedNameProviderCore,
    KoModifierProviderCore,
    KoTopLevelProviderCore,
    KoParametersProviderCore {
    override val ktTypeParameterListOwner: KtTypeParameterListOwner
        get() = ktFunction

    override fun hasValidParamTag(enabled: Boolean): Boolean = TagUtil.hasValidParamTag(enabled, parameters, kDoc)
}
