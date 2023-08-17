package com.lemonappdev.konsist.core.declaration

import com.intellij.psi.PsiElement
import com.lemonappdev.konsist.api.declaration.KoPropertyDeclaration
import com.lemonappdev.konsist.api.provider.KoContainingDeclarationProvider
import com.lemonappdev.konsist.core.cache.KoDeclarationCache
import com.lemonappdev.konsist.core.provider.KoAnnotationProviderCore
import com.lemonappdev.konsist.core.provider.KoBaseProviderCore
import com.lemonappdev.konsist.core.provider.KoContainingDeclarationProviderCore
import com.lemonappdev.konsist.core.provider.KoContainingFileProviderCore
import com.lemonappdev.konsist.core.provider.KoDeclarationFullyQualifiedNameProviderCore
import com.lemonappdev.konsist.core.provider.KoDelegateProviderCore
import com.lemonappdev.konsist.core.provider.KoExplicitTypeProviderCore
import com.lemonappdev.konsist.core.provider.KoExtensionProviderCore
import com.lemonappdev.konsist.core.provider.KoImplementationProviderCore
import com.lemonappdev.konsist.core.provider.KoKDocProviderCore
import com.lemonappdev.konsist.core.provider.KoLocationProviderCore
import com.lemonappdev.konsist.core.provider.KoNameProviderCore
import com.lemonappdev.konsist.core.provider.KoPathProviderCore
import com.lemonappdev.konsist.core.provider.KoReceiverTypeProviderCore
import com.lemonappdev.konsist.core.provider.KoResideInOrOutsidePackageProviderCore
import com.lemonappdev.konsist.core.provider.KoTextProviderCore
import com.lemonappdev.konsist.core.provider.KoTopLevelProviderCore
import com.lemonappdev.konsist.core.provider.modifier.KoAbstractModifierProviderCore
import com.lemonappdev.konsist.core.provider.modifier.KoActualModifierProviderCore
import com.lemonappdev.konsist.core.provider.modifier.KoConstModifierProviderCore
import com.lemonappdev.konsist.core.provider.modifier.KoExpectModifierProviderCore
import com.lemonappdev.konsist.core.provider.modifier.KoFinalModifierProviderCore
import com.lemonappdev.konsist.core.provider.modifier.KoLateinitModifierProviderCore
import com.lemonappdev.konsist.core.provider.modifier.KoModifierProviderCore
import com.lemonappdev.konsist.core.provider.modifier.KoOpenModifierProviderCore
import com.lemonappdev.konsist.core.provider.modifier.KoOverrideModifierProviderCore
import com.lemonappdev.konsist.core.provider.modifier.KoValModifierProviderCore
import com.lemonappdev.konsist.core.provider.modifier.KoVarModifierProviderCore
import com.lemonappdev.konsist.core.provider.modifier.KoVisibilityModifierProviderCore
import com.lemonappdev.konsist.core.provider.packagee.KoPackageDeclarationProviderCore
import com.lemonappdev.konsist.core.util.EndOfLine
import org.jetbrains.kotlin.psi.KtAnnotated
import org.jetbrains.kotlin.psi.KtCallableDeclaration
import org.jetbrains.kotlin.psi.KtElement
import org.jetbrains.kotlin.psi.KtProperty
import org.jetbrains.kotlin.psi.KtTypeParameterListOwner

internal class KoPropertyDeclarationCore private constructor(
    override val ktProperty: KtProperty,
    override val containingDeclaration: KoContainingDeclarationProvider,
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
    KoPackageDeclarationProviderCore,
    KoContainingDeclarationProviderCore,
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

    override val ktTypeParameterListOwner: KtTypeParameterListOwner by lazy { ktProperty }

    override val ktCallableDeclaration: KtCallableDeclaration by lazy { ktProperty }

    override val psiElement: PsiElement by lazy { ktProperty }

    override val ktElement: KtElement by lazy { ktProperty }

    override val hasImplementation: Boolean = ktProperty.hasBody()

    override val delegateName: String? by lazy {
        ktProperty
            .delegateExpression
            ?.text
            ?.replace(EndOfLine.UNIX.value, " ")
            ?.substringAfter("by ")
            ?.substringBefore("{")
            ?.removeSuffix(" ")
    }

    override val hasValModifier: Boolean by lazy { !ktProperty.isVar }

    override val hasVarModifier: Boolean by lazy { ktProperty.isVar }

    override fun toString(): String {
        return locationWithText
    }

    internal companion object {
        private val cache: KoDeclarationCache<KoPropertyDeclaration> = KoDeclarationCache()

        internal fun getInstance(ktProperty: KtProperty, containingDeclaration: KoContainingDeclarationProvider): KoPropertyDeclaration =
            cache.getOrCreateInstance(ktProperty, containingDeclaration) {
                KoPropertyDeclarationCore(ktProperty, containingDeclaration)
            }
    }
}
