package com.lemonappdev.konsist.core.declaration

import com.lemonappdev.konsist.api.declaration.KoBaseDeclaration
import com.lemonappdev.konsist.api.declaration.KoKDocDeclaration
import com.lemonappdev.konsist.api.declaration.KoPropertyDeclaration
import com.lemonappdev.konsist.api.provider.KoKDocProvider
import com.lemonappdev.konsist.core.cache.KoDeclarationCache
import com.lemonappdev.konsist.core.provider.KoAnnotationProviderCore
import com.lemonappdev.konsist.core.provider.KoBaseProviderCore
import com.lemonappdev.konsist.core.provider.KoConstructorDefinedProviderCore
import com.lemonappdev.konsist.core.provider.KoContainingDeclarationProviderCore
import com.lemonappdev.konsist.core.provider.KoContainingFileProviderCore
import com.lemonappdev.konsist.core.provider.KoDeclarationFullyQualifiedNameProviderCore
import com.lemonappdev.konsist.core.provider.KoDelegateProviderCore
import com.lemonappdev.konsist.core.provider.KoGetterProviderCore
import com.lemonappdev.konsist.core.provider.KoInitializerProviderCore
import com.lemonappdev.konsist.core.provider.KoIsConstructorDefinedProviderCore
import com.lemonappdev.konsist.core.provider.KoIsInitializedProviderCore
import com.lemonappdev.konsist.core.provider.KoIsReadOnlyProviderCore
import com.lemonappdev.konsist.core.provider.KoIsTopLevelProviderCore
import com.lemonappdev.konsist.core.provider.KoKDocProviderCore
import com.lemonappdev.konsist.core.provider.KoLocationProviderCore
import com.lemonappdev.konsist.core.provider.KoModuleProviderCore
import com.lemonappdev.konsist.core.provider.KoNameProviderCore
import com.lemonappdev.konsist.core.provider.KoNullableTypeProviderCore
import com.lemonappdev.konsist.core.provider.KoPathProviderCore
import com.lemonappdev.konsist.core.provider.KoReadOnlyProviderCore
import com.lemonappdev.konsist.core.provider.KoReceiverTypeProviderCore
import com.lemonappdev.konsist.core.provider.KoResideInPackageProviderCore
import com.lemonappdev.konsist.core.provider.KoSetterProviderCore
import com.lemonappdev.konsist.core.provider.KoSourceSetProviderCore
import com.lemonappdev.konsist.core.provider.KoTacitTypeProviderCore
import com.lemonappdev.konsist.core.provider.KoTextProviderCore
import com.lemonappdev.konsist.core.provider.KoTopLevelProviderCore
import com.lemonappdev.konsist.core.provider.KoValueProviderCore
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
import org.jetbrains.kotlin.com.intellij.psi.PsiElement
import org.jetbrains.kotlin.psi.KtAnnotated
import org.jetbrains.kotlin.psi.KtCallableDeclaration
import org.jetbrains.kotlin.psi.KtDeclaration
import org.jetbrains.kotlin.psi.KtElement
import org.jetbrains.kotlin.psi.KtExpression
import org.jetbrains.kotlin.psi.KtModifierListOwner
import org.jetbrains.kotlin.psi.KtParameter
import org.jetbrains.kotlin.psi.KtProperty
import org.jetbrains.kotlin.psi.KtPropertyAccessor
import org.jetbrains.kotlin.psi.KtTypeParameterListOwner

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
    override val containingDeclaration: KoBaseDeclaration,
) :
    KoPropertyDeclaration,
        KoBaseProviderCore,
        KoAnnotationProviderCore,
        KoConstructorDefinedProviderCore,
        KoIsConstructorDefinedProviderCore,
        KoContainingFileProviderCore,
        KoDeclarationFullyQualifiedNameProviderCore,
        KoDelegateProviderCore,
        KoNullableTypeProviderCore,
        KoInitializerProviderCore,
        KoIsInitializedProviderCore,
        KoKDocProviderCore,
        KoLocationProviderCore,
        KoModifierProviderCore,
        KoNameProviderCore,
        KoPackageDeclarationProviderCore,
        KoContainingDeclarationProviderCore,
        KoPathProviderCore,
        KoModuleProviderCore,
        KoSourceSetProviderCore,
        KoReceiverTypeProviderCore,
        KoResideInPackageProviderCore,
        KoTextProviderCore,
        KoTopLevelProviderCore,
        KoIsTopLevelProviderCore,
        KoValueProviderCore,
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
        KoConstModifierProviderCore,
        KoGetterProviderCore,
        KoTacitTypeProviderCore,
        KoSetterProviderCore,
        KoReadOnlyProviderCore,
        KoIsReadOnlyProviderCore {
        override val ktAnnotated: KtAnnotated by lazy { ktCallableDeclaration }

        override val ktModifierListOwner: KtModifierListOwner by lazy { ktCallableDeclaration }

        override val ktTypeParameterListOwner: KtTypeParameterListOwner by lazy { ktCallableDeclaration }

        override val psiElement: PsiElement by lazy { ktCallableDeclaration }

        override val ktElement: KtElement by lazy { ktCallableDeclaration }

        override val ktDeclaration: KtDeclaration by lazy { ktCallableDeclaration }

    /*
    Remove in version 0.18.0
     */
        override val isInitialized: Boolean
            get() = super<KoIsInitializedProviderCore>.isInitialized

    /*
    Remove in version 0.18.0
     */
        override val isConstructorDefined: Boolean
            get() = super<KoIsConstructorDefinedProviderCore>.isConstructorDefined

    /*
    Remove in version 0.18.0
     */
        override val isReadOnly: Boolean
            get() = super<KoIsReadOnlyProviderCore>.isReadOnly

    /*
    Remove in version 0.18.0
     */
        override val isTopLevel: Boolean
            get() = super<KoIsTopLevelProviderCore>.isTopLevel

        override val ktExpression: KtExpression? by lazy {
            ktCallableDeclaration
                .children
                .filterNot { it is KtPropertyAccessor }
                .filterIsInstance<KtExpression>()
                .firstOrNull()
        }

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

        override fun toString(): String = name

        internal companion object {
            private val cache: KoDeclarationCache<KoPropertyDeclaration> = KoDeclarationCache()

            internal fun getInstance(
                ktCallableDeclaration: KtCallableDeclaration,
                containingDeclaration: KoBaseDeclaration,
            ): KoPropertyDeclaration =
                cache.getOrCreateInstance(ktCallableDeclaration, containingDeclaration) {
                    KoPropertyDeclarationCore(ktCallableDeclaration, containingDeclaration)
                }
        }
    }
