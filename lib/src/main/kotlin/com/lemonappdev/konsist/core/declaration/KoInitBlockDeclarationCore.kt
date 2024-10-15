package com.lemonappdev.konsist.core.declaration

import com.lemonappdev.konsist.api.declaration.KoBaseDeclaration
import com.lemonappdev.konsist.api.declaration.KoInitBlockDeclaration
import com.lemonappdev.konsist.core.cache.KoDeclarationCache
import com.lemonappdev.konsist.core.provider.KoBaseProviderCore
import com.lemonappdev.konsist.core.provider.KoContainingDeclarationProviderCore
import com.lemonappdev.konsist.core.provider.KoContainingFileProviderCore
import com.lemonappdev.konsist.core.provider.KoLocalClassProviderCore
import com.lemonappdev.konsist.core.provider.KoLocalDeclarationProviderCore
import com.lemonappdev.konsist.core.provider.KoLocalFunctionProviderCore
import com.lemonappdev.konsist.core.provider.KoLocationProviderCore
import com.lemonappdev.konsist.core.provider.KoModuleProviderCore
import com.lemonappdev.konsist.core.provider.KoPathProviderCore
import com.lemonappdev.konsist.core.provider.KoSourceSetProviderCore
import com.lemonappdev.konsist.core.provider.KoTextProviderCore
import com.lemonappdev.konsist.core.provider.KoVariableProviderCore
import com.lemonappdev.konsist.core.provider.util.KoLocalDeclarationProviderCoreUtil
import org.jetbrains.kotlin.com.intellij.psi.PsiElement
import org.jetbrains.kotlin.psi.KtAnonymousInitializer
import org.jetbrains.kotlin.psi.KtElement

internal class KoInitBlockDeclarationCore private constructor(
    private val ktAnonymousInitializer: KtAnonymousInitializer,
    override val containingDeclaration: KoBaseDeclaration,
) : KoInitBlockDeclaration,
    KoBaseProviderCore,
    KoLocalClassProviderCore,
    KoLocalDeclarationProviderCore,
    KoLocalFunctionProviderCore,
    KoVariableProviderCore,
    KoContainingFileProviderCore,
    KoLocationProviderCore,
    KoContainingDeclarationProviderCore,
    KoPathProviderCore,
    KoModuleProviderCore,
    KoSourceSetProviderCore,
    KoTextProviderCore {
    override val psiElement: PsiElement = ktAnonymousInitializer

    override val ktElement: KtElement = ktAnonymousInitializer

    override val localDeclarations: List<KoBaseDeclaration> by lazy {
        val psiElements =
            ktAnonymousInitializer
                .body
                ?.children

        KoLocalDeclarationProviderCoreUtil.getKoLocalDeclarations(psiElements, this)
    }

    override fun toString(): String = locationWithText

    internal companion object {
        private val cache: KoDeclarationCache<KoInitBlockDeclaration> = KoDeclarationCache()

        internal fun getInstance(
            ktAnonymousInitializer: KtAnonymousInitializer,
            containingDeclaration: KoBaseDeclaration,
        ): KoInitBlockDeclaration =
            cache.getOrCreateInstance(ktAnonymousInitializer, containingDeclaration) {
                KoInitBlockDeclarationCore(ktAnonymousInitializer, containingDeclaration)
            }
    }
}
