package com.lemonappdev.konsist.core.declaration

import com.intellij.psi.PsiElement
import com.lemonappdev.konsist.api.container.KoFile
import com.lemonappdev.konsist.api.declaration.KoPropertyDeclaration
import com.lemonappdev.konsist.api.provider.KoParentProvider
import com.lemonappdev.konsist.api.provider.komodifierprovider.KoAbstractModifierProvider
import com.lemonappdev.konsist.api.provider.komodifierprovider.KoActualModifierProvider
import com.lemonappdev.konsist.api.provider.komodifierprovider.KoConstModifierProvider
import com.lemonappdev.konsist.api.provider.komodifierprovider.KoExpectModifierProvider
import com.lemonappdev.konsist.api.provider.komodifierprovider.KoFinalModifierProvider
import com.lemonappdev.konsist.api.provider.komodifierprovider.KoLateinitModifierProvider
import com.lemonappdev.konsist.api.provider.komodifierprovider.KoOpenModifierProvider
import com.lemonappdev.konsist.api.provider.komodifierprovider.KoOverrideModifierProvider
import com.lemonappdev.konsist.api.provider.komodifierprovider.KoValModifierProvider
import com.lemonappdev.konsist.api.provider.komodifierprovider.KoVarModifierProvider
import com.lemonappdev.konsist.api.provider.komodifierprovider.KoVisibilityModifierProvider
import com.lemonappdev.konsist.core.cache.KoDeclarationCache
import com.lemonappdev.konsist.core.provider.KoAnnotationProviderCore
import com.lemonappdev.konsist.core.provider.KoBaseProviderCore
import com.lemonappdev.konsist.core.provider.KoContainingFileProviderCore
import com.lemonappdev.konsist.core.provider.KoDeclarationFullyQualifiedNameProviderCore
import com.lemonappdev.konsist.core.provider.KoDelegateProviderCore
import com.lemonappdev.konsist.core.provider.KoExplicitTypeProviderCore
import com.lemonappdev.konsist.core.provider.KoExtensionProviderCore
import com.lemonappdev.konsist.core.provider.KoImplementationProviderCore
import com.lemonappdev.konsist.core.provider.KoKDocProviderCore
import com.lemonappdev.konsist.core.provider.KoLocationProviderCore
import com.lemonappdev.konsist.core.provider.komodifierprovider.KoModifierProviderCore
import com.lemonappdev.konsist.core.provider.KoNameProviderCore
import com.lemonappdev.konsist.core.provider.KoPackageProviderCore
import com.lemonappdev.konsist.core.provider.KoParentProviderCore
import com.lemonappdev.konsist.core.provider.KoPathProviderCore
import com.lemonappdev.konsist.core.provider.KoReceiverTypeProviderCore
import com.lemonappdev.konsist.core.provider.KoResideInOrOutsidePackageProviderCore
import com.lemonappdev.konsist.core.provider.KoTextProviderCore
import com.lemonappdev.konsist.core.provider.KoTopLevelProviderCore
import com.lemonappdev.konsist.core.provider.komodifierprovider.KoAbstractModifierProviderCore
import com.lemonappdev.konsist.core.provider.komodifierprovider.KoActualModifierProviderCore
import com.lemonappdev.konsist.core.provider.komodifierprovider.KoConstModifierProviderCore
import com.lemonappdev.konsist.core.provider.komodifierprovider.KoExpectModifierProviderCore
import com.lemonappdev.konsist.core.provider.komodifierprovider.KoFinalModifierProviderCore
import com.lemonappdev.konsist.core.provider.komodifierprovider.KoLateinitModifierProviderCore
import com.lemonappdev.konsist.core.provider.komodifierprovider.KoOpenModifierProviderCore
import com.lemonappdev.konsist.core.provider.komodifierprovider.KoOverrideModifierProviderCore
import com.lemonappdev.konsist.core.provider.komodifierprovider.KoValModifierProviderCore
import com.lemonappdev.konsist.core.provider.komodifierprovider.KoVarModifierProviderCore
import com.lemonappdev.konsist.core.provider.komodifierprovider.KoVisibilityModifierProviderCore
import org.jetbrains.kotlin.psi.KtAnnotated
import org.jetbrains.kotlin.psi.KtCallableDeclaration
import org.jetbrains.kotlin.psi.KtElement
import org.jetbrains.kotlin.psi.KtFile
import org.jetbrains.kotlin.psi.KtProperty
import org.jetbrains.kotlin.psi.KtTypeParameterListOwner

internal class KoPropertyDeclarationImpl private constructor(
    override val ktProperty: KtProperty,
    override val parent: KoParentProvider?,
) :
    KoPropertyDeclaration,
    KoBaseProviderCore,
    KoAnnotationProviderCore,
    KoContainingFileProviderCore,
    KoDeclarationFullyQualifiedNameProviderCore,
    KoDelegateProviderCore,
    KoExplicitTypeProviderCore,
    KoExtensionProviderCore,
    KoImplementationProviderCore,
    KoKDocProviderCore,
    KoLocationProviderCore,
    KoModifierProviderCore,
    KoNameProviderCore,
    KoPackageProviderCore,
    KoParentProviderCore,
    KoPathProviderCore,
    KoReceiverTypeProviderCore,
    KoResideInOrOutsidePackageProviderCore,
    KoTextProviderCore,
    KoTopLevelProviderCore,
    KoVisibilityModifierProviderCore,
    KoValModifierProviderCore,
    KoVarModifierProviderCore,
    KoLateinitModifierProviderCore,
    KoOverrideModifierProviderCore,
    KoAbstractModifierProviderCore,
    KoOpenModifierProviderCore,
    KoFinalModifierProviderCore,
    KoActualModifierProviderCore,
    KoExpectModifierProviderCore,
    KoConstModifierProviderCore {
    override val ktAnnotated: KtAnnotated by lazy { ktProperty }

    override val ktFile: KtFile? by lazy { null }

    override val ktTypeParameterListOwner: KtTypeParameterListOwner by lazy { ktProperty }

    override val ktCallableDeclaration: KtCallableDeclaration by lazy { ktProperty }

    override val koFiles: List<KoFile>? by lazy { null }

    override val psiElement: PsiElement by lazy { ktProperty }

    override val ktElement: KtElement by lazy { ktProperty }

    override val hasImplementation: Boolean = ktProperty.hasBody()

    override val delegateName: String? by lazy {
        ktProperty
            .delegateExpression
            ?.text
            ?.replace("\n", " ")
            ?.substringAfter("by ")
            ?.substringBefore("{")
            ?.removeSuffix(" ")
    }

    override fun toString(): String {
        return locationWithText
    }

    internal companion object {
        private val cache: KoDeclarationCache<KoPropertyDeclaration> = KoDeclarationCache()

        internal fun getInstance(ktProperty: KtProperty, parent: KoParentProvider?): KoPropertyDeclaration =
            cache.getOrCreateInstance(ktProperty, parent) {
                KoPropertyDeclarationImpl(ktProperty, parent)
            }
    }
}
