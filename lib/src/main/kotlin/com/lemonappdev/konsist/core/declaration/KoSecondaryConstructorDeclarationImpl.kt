package com.lemonappdev.konsist.core.declaration

import com.lemonappdev.konsist.api.declaration.KoSecondaryConstructorDeclaration
import com.lemonappdev.konsist.api.provider.KoParentProvider
import com.lemonappdev.konsist.core.cache.KoDeclarationCache
import com.lemonappdev.konsist.core.provider.KoAnnotationDeclarationProviderCore
import com.lemonappdev.konsist.core.provider.KoConstructorDeclarationProviderCore
import com.lemonappdev.konsist.core.provider.KoDeclarationFullyQualifiedNameProviderCore
import com.lemonappdev.konsist.core.provider.KoModifierProviderCore
import com.lemonappdev.konsist.core.provider.KoPackageDeclarationProviderCore
import com.lemonappdev.konsist.core.provider.KoParametersProviderCore
import com.lemonappdev.konsist.core.provider.KoTopLevelProviderCore
import com.lemonappdev.konsist.core.util.TagUtil
import org.jetbrains.kotlin.psi.KtCallableDeclaration
import org.jetbrains.kotlin.psi.KtSecondaryConstructor
import org.jetbrains.kotlin.psi.KtTypeParameterListOwner

internal class KoSecondaryConstructorDeclarationImpl private constructor(
    private val ktSecondaryConstructor: KtSecondaryConstructor,
    parentDeclaration: KoParentProvider?,
) :
    KoBaseDeclarationImpl(ktSecondaryConstructor),
    KoAnnotationDeclarationProviderCore,
    KoPackageDeclarationProviderCore,
    KoDeclarationFullyQualifiedNameProviderCore,
    KoModifierProviderCore,
    KoTopLevelProviderCore,
    KoParametersProviderCore,
    KoConstructorDeclarationProviderCore,
    KoSecondaryConstructorDeclaration {
    override val ktTypeParameterListOwner: KtTypeParameterListOwner
        get() = ktSecondaryConstructor

    override val ktCallableDeclaration: KtCallableDeclaration
        get() = ktSecondaryConstructor

    override fun hasValidParamTag(enabled: Boolean): Boolean = TagUtil.hasValidParamTag(enabled, parameters, kDoc)
    internal companion object {
        private val cache: KoDeclarationCache<KoSecondaryConstructorDeclaration> = KoDeclarationCache()

        internal fun getInstance(
            ktSecondaryConstructor: KtSecondaryConstructor,
            parentDeclaration: KoParentProvider?,
        ): KoSecondaryConstructorDeclaration =
            cache.getOrCreateInstance(ktSecondaryConstructor, parentDeclaration) {
                KoSecondaryConstructorDeclarationImpl(
                    ktSecondaryConstructor,
                    parentDeclaration,
                )
            }
    }
}
