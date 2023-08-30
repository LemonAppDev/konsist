package com.lemonappdev.konsist.core.declaration

import com.intellij.psi.PsiElement
import com.lemonappdev.konsist.api.declaration.KoKDocDeclaration
import com.lemonappdev.konsist.api.declaration.KoPropertyDeclaration
import com.lemonappdev.konsist.api.provider.KoContainingDeclarationProvider
import com.lemonappdev.konsist.api.provider.KoKDocProvider
import com.lemonappdev.konsist.core.cache.KoDeclarationCache
import com.lemonappdev.konsist.core.provider.KoAnnotationProviderCore
import com.lemonappdev.konsist.core.provider.KoBaseProviderCore
import com.lemonappdev.konsist.core.provider.KoContainingDeclarationProviderCore
import com.lemonappdev.konsist.core.provider.KoContainingFileProviderCore
import com.lemonappdev.konsist.core.provider.KoDeclarationFullyQualifiedNameProviderCore
import com.lemonappdev.konsist.core.provider.KoDelegateProviderCore
import com.lemonappdev.konsist.core.provider.KoImplementationProviderCore
import com.lemonappdev.konsist.core.provider.KoKDocProviderCore
import com.lemonappdev.konsist.core.provider.KoLocationProviderCore
import com.lemonappdev.konsist.core.provider.KoNameProviderCore
import com.lemonappdev.konsist.core.provider.KoPathProviderCore
import com.lemonappdev.konsist.core.provider.KoPropertyTypeProviderCore
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
import org.jetbrains.kotlin.psi.KtParameter
import org.jetbrains.kotlin.psi.KtProperty
import org.jetbrains.kotlin.psi.KtTypeParameterListOwner
import org.jetbrains.kotlin.psi.psiUtil.hasBody

internal class KoPropertyDeclarationCore private constructor(
    /*
    KtProperty - property defined as member (inside class/object/interface body) e.g.
    class SampleClass() {
        val sampleProperty: String
    }

    KtParameter - property defined inside constructor e.g.
    class SampleClass(val sampleProperty: String)

    KtCallableDeclaration - common parent for KtProperty and KtParameter
     */
    override val ktCallableDeclaration: KtCallableDeclaration,
    override val containingDeclaration: KoContainingDeclarationProvider,
) :
    KoPropertyDeclaration,
    KoBaseProviderCore,
    KoAnnotationProviderCore,
    KoContainingFileProviderCore,
    KoDeclarationFullyQualifiedNameProviderCore,
    KoDelegateProviderCore,
    KoPropertyTypeProviderCore,
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
    override val ktAnnotated: KtAnnotated by lazy { ktCallableDeclaration }

    override val ktTypeParameterListOwner: KtTypeParameterListOwner by lazy { ktCallableDeclaration }

    override val psiElement: PsiElement by lazy { ktCallableDeclaration }

    override val ktElement: KtElement by lazy { ktCallableDeclaration }

    override val hasImplementation: Boolean = ktCallableDeclaration.hasBody()

    override val delegateName: String? by lazy {
        if (ktCallableDeclaration is KtProperty) {
            ktCallableDeclaration
                .delegateExpression
                ?.text
                ?.replace(EndOfLine.UNIX.value, " ")
                ?.substringAfter("by ")
                ?.substringBefore("{")
                ?.removeSuffix(" ")
        } else {
            null
        }
    }

    override val hasValModifier: Boolean by lazy {
        when (ktCallableDeclaration) {
            is KtProperty -> !ktCallableDeclaration.isVar
            is KtParameter -> ktCallableDeclaration.valOrVarKeyword?.text == "val"
            else -> false
        }
    }

    override val hasVarModifier: Boolean by lazy {
        when (ktCallableDeclaration) {
            is KtProperty -> ktCallableDeclaration.isVar
            is KtParameter -> ktCallableDeclaration.valOrVarKeyword?.text == "var"
            else -> false
        }
    }

    override val kDoc: KoKDocDeclaration? by lazy {
        if (ktCallableDeclaration is KtParameter) {
            (containingDeclaration as? KoKDocProvider)?.kDoc
        } else {
            super<KoKDocProviderCore>.kDoc
        }
    }

    override val isConstructorDefined: Boolean by lazy { ktCallableDeclaration is KtParameter }

    override fun toString(): String {
        return locationWithText
    }

    internal companion object {
        private val cache: KoDeclarationCache<KoPropertyDeclaration> = KoDeclarationCache()

        internal fun getInstance(
            ktCallableDeclaration: KtCallableDeclaration,
            containingDeclaration: KoContainingDeclarationProvider,
        ): KoPropertyDeclaration =
            cache.getOrCreateInstance(ktCallableDeclaration, containingDeclaration) {
                KoPropertyDeclarationCore(ktCallableDeclaration, containingDeclaration)
            }
    }
}
