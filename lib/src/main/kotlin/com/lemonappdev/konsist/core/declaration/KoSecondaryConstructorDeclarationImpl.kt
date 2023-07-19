package com.lemonappdev.konsist.core.declaration

import com.lemonappdev.konsist.api.declaration.KoSecondaryConstructorDeclaration
import com.lemonappdev.konsist.api.provider.KoParentProvider
import com.lemonappdev.konsist.core.cache.KoDeclarationCache
import com.lemonappdev.konsist.core.provider.KoAnnotationProviderCore
import com.lemonappdev.konsist.core.provider.KoConstructorProviderCore
import com.lemonappdev.konsist.core.provider.KoDeclarationFullyQualifiedNameProviderCore
import com.lemonappdev.konsist.core.provider.KoModifierProviderCore
import com.lemonappdev.konsist.core.provider.KoPackageProviderCore
import com.lemonappdev.konsist.core.provider.KoParametersProviderCore
import com.lemonappdev.konsist.core.provider.KoResideInOrOutsidePackageProviderCore
import com.lemonappdev.konsist.core.provider.KoTopLevelProviderCore
import com.lemonappdev.konsist.core.util.TagUtil
import org.jetbrains.kotlin.psi.KtAnnotated
import org.jetbrains.kotlin.psi.KtCallableDeclaration
import org.jetbrains.kotlin.psi.KtFile
import org.jetbrains.kotlin.psi.KtSecondaryConstructor
import org.jetbrains.kotlin.psi.KtTypeParameterListOwner

internal class KoSecondaryConstructorDeclarationImpl private constructor(
    private val ktSecondaryConstructor: KtSecondaryConstructor,
    override val parentDeclaration: KoParentProvider?,
) :
    KoBaseDeclarationImpl(ktSecondaryConstructor),
    KoAnnotationProviderCore,
    KoPackageProviderCore,
    KoResideInOrOutsidePackageProviderCore,
    KoDeclarationFullyQualifiedNameProviderCore,
    KoModifierProviderCore,
    KoTopLevelProviderCore,
    KoParametersProviderCore,
    KoConstructorProviderCore,
    KoSecondaryConstructorDeclaration {
    override val ktAnnotated: KtAnnotated
        get() = ktSecondaryConstructor

    override val ktFile: KtFile?
        get() = null

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
