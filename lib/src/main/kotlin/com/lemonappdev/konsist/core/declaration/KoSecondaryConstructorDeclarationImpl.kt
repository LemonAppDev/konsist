package com.lemonappdev.konsist.core.declaration

import com.intellij.psi.PsiElement
import com.lemonappdev.konsist.api.declaration.KoFileDeclaration
import com.lemonappdev.konsist.api.declaration.KoSecondaryConstructorDeclaration
import com.lemonappdev.konsist.api.provider.KoContainingDeclarationProvider
import com.lemonappdev.konsist.core.cache.KoDeclarationCache
import com.lemonappdev.konsist.core.provider.KoKDocProviderCore
import org.jetbrains.kotlin.psi.KtConstructor
import org.jetbrains.kotlin.psi.KtSecondaryConstructor

internal class KoSecondaryConstructorDeclarationImpl private constructor(
    private val ktSecondaryConstructor: KtSecondaryConstructor,
    override val containingDeclaration: KoContainingDeclarationProvider,
) :
    KoSecondaryConstructorDeclaration,
    KoConstructorDeclarationImpl,
    KoKDocProviderCore {
    override val ktConstructor: KtConstructor<*> by lazy { ktSecondaryConstructor }

    override fun toString(): String {
        return locationWithText
    }

    internal companion object {
        private val cache: KoDeclarationCache<KoSecondaryConstructorDeclaration> = KoDeclarationCache()

        internal fun getInstance(
            ktSecondaryConstructor: KtSecondaryConstructor,
            containingDeclaration: KoContainingDeclarationProvider,
        ): KoSecondaryConstructorDeclaration =
            cache.getOrCreateInstance(ktSecondaryConstructor, containingDeclaration) {
                KoSecondaryConstructorDeclarationImpl(
                    ktSecondaryConstructor,
                    containingDeclaration,
                )
            }
    }
}
