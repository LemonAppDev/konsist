package com.lemonappdev.konsist.core.declaration

import com.lemonappdev.konsist.api.declaration.KoConstructorDeclaration
import com.lemonappdev.konsist.api.provider.KoParentProvider
import com.lemonappdev.konsist.core.provider.KoAnnotationDeclarationProviderCore
import com.lemonappdev.konsist.core.provider.KoDeclarationFullyQualifiedNameProviderCore
import com.lemonappdev.konsist.core.provider.KoModifierProviderCore
import com.lemonappdev.konsist.core.provider.KoPackageDeclarationProviderCore
import com.lemonappdev.konsist.core.provider.KoParametersProviderCore
import com.lemonappdev.konsist.core.provider.KoTopLevelProviderCore
import com.lemonappdev.konsist.core.util.TagUtil
import org.jetbrains.kotlin.psi.KtCallableDeclaration
import org.jetbrains.kotlin.psi.KtConstructor
import org.jetbrains.kotlin.psi.KtTypeParameterListOwner

internal open class KoConstructorDeclarationImpl(
    private val ktConstructor: KtConstructor<*>,
    parentDeclaration: KoParentProvider?,
) : KoBaseDeclarationImpl(ktConstructor),
    KoAnnotationDeclarationProviderCore,
    KoPackageDeclarationProviderCore,
    KoDeclarationFullyQualifiedNameProviderCore,
    KoModifierProviderCore,
    KoTopLevelProviderCore,
    KoParametersProviderCore,
    KoConstructorDeclaration {
    override val ktTypeParameterListOwner: KtTypeParameterListOwner
        get() = ktConstructor

    override val ktCallableDeclaration: KtCallableDeclaration
        get() = ktConstructor

    override fun hasValidParamTag(enabled: Boolean): Boolean = TagUtil.hasValidParamTag(enabled, parameters, kDoc)
    }
