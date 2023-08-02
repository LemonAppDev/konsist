package com.lemonappdev.konsist.core.declaration

import com.intellij.psi.PsiElement
import com.lemonappdev.konsist.api.declaration.KoBaseDeclaration
import com.lemonappdev.konsist.api.declaration.KoInitBlockDeclaration
import com.lemonappdev.konsist.api.provider.KoContainingDeclarationProvider
import com.lemonappdev.konsist.core.cache.KoDeclarationCache
import com.lemonappdev.konsist.core.provider.KoBaseProviderCore
import com.lemonappdev.konsist.core.provider.KoClassProviderCore
import com.lemonappdev.konsist.core.provider.KoContainingFileProviderCore
import com.lemonappdev.konsist.core.provider.KoDeclarationProviderCore
import com.lemonappdev.konsist.core.provider.KoFunctionProviderCore
import com.lemonappdev.konsist.core.provider.KoLocationProviderCore
import com.lemonappdev.konsist.core.provider.KoContainingDeclarationProviderCore
import com.lemonappdev.konsist.core.provider.KoPathProviderCore
import com.lemonappdev.konsist.core.provider.KoPropertyProviderCore
import com.lemonappdev.konsist.core.provider.KoTextProviderCore
import com.lemonappdev.konsist.core.provider.util.KoDeclarationProviderCoreUtil
import org.jetbrains.kotlin.psi.KtAnonymousInitializer
import org.jetbrains.kotlin.psi.KtElement

internal class KoInitBlockDeclarationImpl private constructor(
    private val ktAnonymousInitializer: KtAnonymousInitializer,
    override val containingDeclaration: KoContainingDeclarationProvider?,
) :
    KoInitBlockDeclaration,
    KoBaseProviderCore,
    KoClassProviderCore,
    KoContainingFileProviderCore,
    KoDeclarationProviderCore,
    KoFunctionProviderCore,
    KoLocationProviderCore,
    KoContainingDeclarationProviderCore,
    KoPathProviderCore,
    KoPropertyProviderCore,
    KoTextProviderCore {
    override val psiElement: PsiElement by lazy { ktAnonymousInitializer }

    override val ktElement: KtElement by lazy { ktAnonymousInitializer }

    override fun declarations(
        includeNested: Boolean,
        includeLocal: Boolean,
    ): List<KoBaseDeclaration> = KoDeclarationProviderCoreUtil
        .getKoDeclarations(ktAnonymousInitializer, includeNested, includeLocal, this)

    override fun toString(): String {
        return locationWithText
    }

    internal companion object {
        private val cache: KoDeclarationCache<KoInitBlockDeclaration> = KoDeclarationCache()

        internal fun getInstance(
            ktAnonymousInitializer: KtAnonymousInitializer,
            parent: KoContainingDeclarationProvider?,
        ): KoInitBlockDeclaration =
            cache.getOrCreateInstance(ktAnonymousInitializer, parent) {
                KoInitBlockDeclarationImpl(ktAnonymousInitializer, parent)
            }
    }
}
