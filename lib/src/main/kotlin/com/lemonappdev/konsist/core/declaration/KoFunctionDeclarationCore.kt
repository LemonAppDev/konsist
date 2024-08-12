package com.lemonappdev.konsist.core.declaration

import com.lemonappdev.konsist.api.declaration.KoBaseDeclaration
import com.lemonappdev.konsist.api.declaration.KoFunctionDeclaration
import com.lemonappdev.konsist.core.cache.KoDeclarationCache
import com.lemonappdev.konsist.core.provider.KoAnnotationProviderCore
import com.lemonappdev.konsist.core.provider.KoBaseProviderCore
import com.lemonappdev.konsist.core.provider.KoBodyProviderCore
import com.lemonappdev.konsist.core.provider.KoContainingDeclarationProviderCore
import com.lemonappdev.konsist.core.provider.KoContainingFileProviderCore
import com.lemonappdev.konsist.core.provider.KoDeclarationFullyQualifiedNameProviderCore
import com.lemonappdev.konsist.core.provider.KoInitializerProviderCore
import com.lemonappdev.konsist.core.provider.KoIsInitializedProviderCore
import com.lemonappdev.konsist.core.provider.KoIsTopLevelProviderCore
import com.lemonappdev.konsist.core.provider.KoKDocProviderCore
import com.lemonappdev.konsist.core.provider.KoLocalClassProviderCore
import com.lemonappdev.konsist.core.provider.KoLocalDeclarationProviderCore
import com.lemonappdev.konsist.core.provider.KoLocalFunctionProviderCore
import com.lemonappdev.konsist.core.provider.KoLocationProviderCore
import com.lemonappdev.konsist.core.provider.KoModuleProviderCore
import com.lemonappdev.konsist.core.provider.KoNameProviderCore
import com.lemonappdev.konsist.core.provider.KoParametersProviderCore
import com.lemonappdev.konsist.core.provider.KoPathProviderCore
import com.lemonappdev.konsist.core.provider.KoReceiverTypeProviderCore
import com.lemonappdev.konsist.core.provider.KoResideInPackageProviderCore
import com.lemonappdev.konsist.core.provider.KoReturnProviderCore
import com.lemonappdev.konsist.core.provider.KoSourceSetProviderCore
import com.lemonappdev.konsist.core.provider.KoTextProviderCore
import com.lemonappdev.konsist.core.provider.KoTopLevelProviderCore
import com.lemonappdev.konsist.core.provider.KoVariableProviderCore
import com.lemonappdev.konsist.core.provider.modifier.KoAbstractModifierProviderCore
import com.lemonappdev.konsist.core.provider.modifier.KoActualModifierProviderCore
import com.lemonappdev.konsist.core.provider.modifier.KoExpectModifierProviderCore
import com.lemonappdev.konsist.core.provider.modifier.KoExternalModifierProviderCore
import com.lemonappdev.konsist.core.provider.modifier.KoFinalModifierProviderCore
import com.lemonappdev.konsist.core.provider.modifier.KoInfixModifierProviderCore
import com.lemonappdev.konsist.core.provider.modifier.KoInlineModifierProviderCore
import com.lemonappdev.konsist.core.provider.modifier.KoModifierProviderCore
import com.lemonappdev.konsist.core.provider.modifier.KoOpenModifierProviderCore
import com.lemonappdev.konsist.core.provider.modifier.KoOperatorModifierProviderCore
import com.lemonappdev.konsist.core.provider.modifier.KoOverrideModifierProviderCore
import com.lemonappdev.konsist.core.provider.modifier.KoSuspendModifierProviderCore
import com.lemonappdev.konsist.core.provider.modifier.KoTailrecModifierProviderCore
import com.lemonappdev.konsist.core.provider.modifier.KoVisibilityModifierProviderCore
import com.lemonappdev.konsist.core.provider.packagee.KoPackageDeclarationProviderCore
import com.lemonappdev.konsist.core.provider.util.KoLocalDeclarationProviderCoreUtil
import org.jetbrains.kotlin.com.intellij.psi.PsiElement
import org.jetbrains.kotlin.psi.KtAnnotated
import org.jetbrains.kotlin.psi.KtCallableDeclaration
import org.jetbrains.kotlin.psi.KtDeclaration
import org.jetbrains.kotlin.psi.KtDeclarationWithBody
import org.jetbrains.kotlin.psi.KtElement
import org.jetbrains.kotlin.psi.KtFunction
import org.jetbrains.kotlin.psi.KtModifierListOwner
import org.jetbrains.kotlin.psi.KtTypeParameterListOwner

internal class KoFunctionDeclarationCore private constructor(
    override val ktFunction: KtFunction,
    override val containingDeclaration: KoBaseDeclaration,
) :
    KoFunctionDeclaration,
        KoBaseProviderCore,
        KoAnnotationProviderCore,
        KoBodyProviderCore,
        KoContainingFileProviderCore,
        KoDeclarationFullyQualifiedNameProviderCore,
        KoReturnProviderCore,
        KoInitializerProviderCore,
        KoIsInitializedProviderCore,
        KoKDocProviderCore,
        KoLocalClassProviderCore,
        KoLocalDeclarationProviderCore,
        KoLocalFunctionProviderCore,
        KoVariableProviderCore,
        KoLocationProviderCore,
        KoModifierProviderCore,
        KoNameProviderCore,
        KoPackageDeclarationProviderCore,
        KoParametersProviderCore,
        KoContainingDeclarationProviderCore,
        KoPathProviderCore,
        KoModuleProviderCore,
        KoSourceSetProviderCore,
        KoReceiverTypeProviderCore,
        KoResideInPackageProviderCore,
        KoTextProviderCore,
        KoTopLevelProviderCore,
        KoIsTopLevelProviderCore,
        KoVisibilityModifierProviderCore,
        KoOperatorModifierProviderCore,
        KoInlineModifierProviderCore,
        KoTailrecModifierProviderCore,
        KoInfixModifierProviderCore,
        KoExternalModifierProviderCore,
        KoSuspendModifierProviderCore,
        KoOpenModifierProviderCore,
        KoOverrideModifierProviderCore,
        KoFinalModifierProviderCore,
        KoAbstractModifierProviderCore,
        KoActualModifierProviderCore,
        KoExpectModifierProviderCore {
        override val ktAnnotated: KtAnnotated by lazy { ktCallableDeclaration }

    override val ktModifierListOwner: KtModifierListOwner by lazy { ktCallableDeclaration }

    override val ktTypeParameterListOwner: KtTypeParameterListOwner by lazy { ktCallableDeclaration }

    override val ktCallableDeclaration: KtCallableDeclaration by lazy { ktFunction }

    override val psiElement: PsiElement by lazy { ktFunction }

    override val ktElement: KtElement by lazy { ktFunction }

    override val ktDeclarationWithBody: KtDeclarationWithBody by lazy { ktFunction }

    override val ktDeclaration: KtDeclaration by lazy { ktFunction }

    override val localDeclarations: List<KoBaseDeclaration> by lazy {
        val psiElements =
            ktFunction
                .bodyBlockExpression
                ?.children

        KoLocalDeclarationProviderCoreUtil.getKoLocalDeclarations(psiElements, this)
    }

    /*
    Remove in version 0.18.0
     */
        override val isInitialized: Boolean
            get() = super<KoIsInitializedProviderCore>.isInitialized

    /*
    Remove in version 0.18.0
     */
        override val isTopLevel: Boolean
            get() = super<KoIsTopLevelProviderCore>.isTopLevel

    override val fullyQualifiedName: String? by lazy {
        if (this.isTopLevel) {
            super.fullyQualifiedName
        } else {
            null
        }
    }

        override fun toString(): String = name

    internal companion object {
        private val cache: KoDeclarationCache<KoFunctionDeclaration> = KoDeclarationCache()

        internal fun getInstance(
            ktFunction: KtFunction,
            containingDeclaration: KoBaseDeclaration,
        ): KoFunctionDeclaration =
            cache.getOrCreateInstance(ktFunction, containingDeclaration) {
                KoFunctionDeclarationCore(ktFunction, containingDeclaration)
            }
    }
}
