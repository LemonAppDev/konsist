package com.lemonappdev.konsist.core.declaration

import com.intellij.psi.PsiElement
import com.lemonappdev.konsist.api.container.KoFile
import com.lemonappdev.konsist.api.declaration.KoSecondaryConstructorDeclaration
import com.lemonappdev.konsist.api.provider.KoParentProvider
import com.lemonappdev.konsist.core.cache.KoDeclarationCache
import com.lemonappdev.konsist.core.provider.KoAnnotationProviderCore
import com.lemonappdev.konsist.core.provider.KoBaseProviderCore
import com.lemonappdev.konsist.core.provider.KoConstructorProviderCore
import com.lemonappdev.konsist.core.provider.KoContainingFileProviderCore
import com.lemonappdev.konsist.core.provider.KoKDocProviderCore
import com.lemonappdev.konsist.core.provider.KoLocationProviderCore
import com.lemonappdev.konsist.core.provider.KoModifierProviderCore
import com.lemonappdev.konsist.core.provider.KoPackageProviderCore
import com.lemonappdev.konsist.core.provider.KoParametersProviderCore
import com.lemonappdev.konsist.core.provider.KoParentProviderCore
import com.lemonappdev.konsist.core.provider.KoPathProviderCore
import com.lemonappdev.konsist.core.provider.KoResideInOrOutsidePackageProviderCore
import com.lemonappdev.konsist.core.provider.KoTextProviderCore
import com.lemonappdev.konsist.core.util.TagUtil
import org.jetbrains.kotlin.psi.KtAnnotated
import org.jetbrains.kotlin.psi.KtCallableDeclaration
import org.jetbrains.kotlin.psi.KtElement
import org.jetbrains.kotlin.psi.KtFile
import org.jetbrains.kotlin.psi.KtSecondaryConstructor
import org.jetbrains.kotlin.psi.KtTypeParameterListOwner

internal class KoSecondaryConstructorDeclarationImpl private constructor(
    private val ktSecondaryConstructor: KtSecondaryConstructor,
    override val parent: KoParentProvider?,
) :
    KoSecondaryConstructorDeclaration,
    KoBaseProviderCore,
    KoAnnotationProviderCore,
    KoConstructorProviderCore,
    KoContainingFileProviderCore,
    KoKDocProviderCore,
    KoLocationProviderCore,
    KoModifierProviderCore,
    KoPackageProviderCore,
    KoParametersProviderCore,
    KoParentProviderCore,
    KoPathProviderCore,
    KoResideInOrOutsidePackageProviderCore,
    KoTextProviderCore {
    override val ktAnnotated: KtAnnotated by lazy { ktSecondaryConstructor }

    override val ktFile: KtFile? by lazy { null }

    override val koFiles: Sequence<KoFile>? by lazy { null }

    override val ktTypeParameterListOwner: KtTypeParameterListOwner by lazy { ktSecondaryConstructor }

    override val ktCallableDeclaration: KtCallableDeclaration by lazy { ktSecondaryConstructor }

    override val psiElement: PsiElement by lazy { ktSecondaryConstructor }

    override val ktElement: KtElement by lazy { ktSecondaryConstructor }

    override fun hasValidParamTag(enabled: Boolean): Boolean = TagUtil.hasValidParamTag(enabled, parameters.toList(), kDoc)

    override fun toString(): String {
        return locationWithText
    }

    internal companion object {
        private val cache: KoDeclarationCache<KoSecondaryConstructorDeclaration> = KoDeclarationCache()

        internal fun getInstance(
            ktSecondaryConstructor: KtSecondaryConstructor,
            parent: KoParentProvider?,
        ): KoSecondaryConstructorDeclaration =
            cache.getOrCreateInstance(ktSecondaryConstructor, parent) {
                KoSecondaryConstructorDeclarationImpl(
                    ktSecondaryConstructor,
                    parent,
                )
            }
    }
}
