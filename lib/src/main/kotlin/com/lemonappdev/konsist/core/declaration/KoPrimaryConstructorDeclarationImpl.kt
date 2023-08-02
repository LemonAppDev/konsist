package com.lemonappdev.konsist.core.declaration

import com.intellij.psi.PsiElement
import com.lemonappdev.konsist.api.declaration.KoFileDeclaration
import com.lemonappdev.konsist.api.declaration.KoPrimaryConstructorDeclaration
import com.lemonappdev.konsist.api.provider.KoContainingDeclarationProvider
import com.lemonappdev.konsist.core.cache.KoDeclarationCache
import org.jetbrains.kotlin.psi.KtConstructor
import org.jetbrains.kotlin.psi.KtPrimaryConstructor

internal class KoPrimaryConstructorDeclarationImpl private constructor(
    private val ktPrimaryConstructor: KtPrimaryConstructor,
    override val containingDeclaration: KoContainingDeclarationProvider,
) :
    KoConstructorDeclarationImpl,
    KoPrimaryConstructorDeclaration {
    override val ktConstructor: KtConstructor<*> by lazy { ktPrimaryConstructor }

    override fun toString(): String {
        return locationWithText
    }

    internal companion object {
        private val cache: KoDeclarationCache<KoPrimaryConstructorDeclaration> = KoDeclarationCache()

        internal fun getInstance(
            ktPrimaryConstructor: KtPrimaryConstructor,
            containingDeclaration: KoContainingDeclarationProvider,
        ): KoPrimaryConstructorDeclaration =
            cache.getOrCreateInstance(ktPrimaryConstructor, containingDeclaration) {
                KoPrimaryConstructorDeclarationImpl(
                    ktPrimaryConstructor,
                    containingDeclaration,
                )
            }
    }
}
