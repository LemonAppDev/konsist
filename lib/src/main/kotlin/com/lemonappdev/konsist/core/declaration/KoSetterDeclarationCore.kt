package com.lemonappdev.konsist.core.declaration

import com.lemonappdev.konsist.api.declaration.KoBaseDeclaration
import com.lemonappdev.konsist.api.declaration.KoSetterDeclaration
import com.lemonappdev.konsist.core.cache.KoDeclarationCache
import com.lemonappdev.konsist.core.provider.KoBaseProviderCore
import com.lemonappdev.konsist.core.provider.KoBodyProviderCore
import com.lemonappdev.konsist.core.provider.KoContainingDeclarationProviderCore
import com.lemonappdev.konsist.core.provider.KoContainingFileProviderCore
import com.lemonappdev.konsist.core.provider.KoInitializerProviderCore
import com.lemonappdev.konsist.core.provider.KoIsInitializedProviderCore
import com.lemonappdev.konsist.core.provider.KoLocalClassProviderCore
import com.lemonappdev.konsist.core.provider.KoLocalDeclarationProviderCore
import com.lemonappdev.konsist.core.provider.KoLocalFunctionProviderCore
import com.lemonappdev.konsist.core.provider.KoLocationProviderCore
import com.lemonappdev.konsist.core.provider.KoModuleProviderCore
import com.lemonappdev.konsist.core.provider.KoPathProviderCore
import com.lemonappdev.konsist.core.provider.KoSourceSetProviderCore
import com.lemonappdev.konsist.core.provider.KoTextProviderCore
import com.lemonappdev.konsist.core.provider.KoVariableProviderCore
import com.lemonappdev.konsist.core.provider.modifier.KoModifierProviderCore
import com.lemonappdev.konsist.core.provider.modifier.KoVisibilityModifierProviderCore
import com.lemonappdev.konsist.core.provider.util.KoLocalDeclarationProviderCoreUtil
import org.jetbrains.kotlin.com.intellij.psi.PsiElement
import org.jetbrains.kotlin.psi.KtDeclaration
import org.jetbrains.kotlin.psi.KtDeclarationWithBody
import org.jetbrains.kotlin.psi.KtElement
import org.jetbrains.kotlin.psi.KtModifierListOwner
import org.jetbrains.kotlin.psi.KtPropertyAccessor

internal class KoSetterDeclarationCore private constructor(
    private val ktPropertyAccessor: KtPropertyAccessor,
    override val containingDeclaration: KoBaseDeclaration,
) : KoSetterDeclaration,
    KoBaseProviderCore,
    KoBodyProviderCore,
    KoContainingDeclarationProviderCore,
    KoContainingFileProviderCore,
    KoInitializerProviderCore,
    KoIsInitializedProviderCore,
    KoLocalClassProviderCore,
    KoLocalDeclarationProviderCore,
    KoLocalFunctionProviderCore,
    KoVariableProviderCore,
    KoLocationProviderCore,
    KoPathProviderCore,
    KoModuleProviderCore,
    KoSourceSetProviderCore,
    KoTextProviderCore,
    KoModifierProviderCore,
    KoVisibilityModifierProviderCore {
    override val ktElement: KtElement = ktPropertyAccessor

    override val psiElement: PsiElement = ktPropertyAccessor

    override val ktDeclarationWithBody: KtDeclarationWithBody = ktPropertyAccessor

    override val ktModifierListOwner: KtModifierListOwner = ktPropertyAccessor

    override val ktDeclaration: KtDeclaration = ktPropertyAccessor

    override val localDeclarations: List<KoBaseDeclaration> by lazy {
        val psiElements =
            ktPropertyAccessor
                .bodyBlockExpression
                ?.children

        KoLocalDeclarationProviderCoreUtil.getKoLocalDeclarations(psiElements, this)
    }

    override val hasPublicOrDefaultModifier: Boolean by lazy { !(hasPrivateModifier || hasProtectedModifier || hasInternalModifier) }

    override val isInitialized: Boolean by lazy { hasExpressionBody || hasBlockBody }

    override fun toString(): String = locationWithText

    internal companion object {
        private val cache: KoDeclarationCache<KoSetterDeclaration> = KoDeclarationCache()

        internal fun getInstance(
            ktPropertyAccessor: KtPropertyAccessor,
            containingDeclaration: KoBaseDeclaration,
        ): KoSetterDeclaration =
            cache.getOrCreateInstance(ktPropertyAccessor, containingDeclaration) {
                KoSetterDeclarationCore(ktPropertyAccessor, containingDeclaration)
            }
    }
}
