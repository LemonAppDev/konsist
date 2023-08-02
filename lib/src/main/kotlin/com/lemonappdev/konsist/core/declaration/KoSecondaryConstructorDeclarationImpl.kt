package com.lemonappdev.konsist.core.declaration

import com.lemonappdev.konsist.api.declaration.KoSecondaryConstructorDeclaration
import com.lemonappdev.konsist.api.provider.KoContainingDeclarationProvider
import com.lemonappdev.konsist.core.cache.KoDeclarationCache
import com.lemonappdev.konsist.core.provider.KoKDocProviderCore
import org.jetbrains.kotlin.psi.KtConstructor
import org.jetbrains.kotlin.psi.KtSecondaryConstructor

internal class KoSecondaryConstructorDeclarationImpl private constructor(
    private val ktSecondaryConstructor: KtSecondaryConstructor,
    override val containingDeclaration: KoContainingDeclarationProvider?,
) :
    KoConstructorDeclarationImpl,
    KoSecondaryConstructorDeclaration,
    KoKDocProviderCore {
    override val ktConstructor: KtConstructor<*> by lazy { ktSecondaryConstructor }

    override fun toString(): String {
        return locationWithText
    }

    internal companion object {
        private val cache: KoDeclarationCache<KoSecondaryConstructorDeclaration> = KoDeclarationCache()

        internal fun getInstance(
            ktSecondaryConstructor: KtSecondaryConstructor,
            parent: KoContainingDeclarationProvider?,
        ): KoSecondaryConstructorDeclaration =
            cache.getOrCreateInstance(ktSecondaryConstructor, parent) {
                KoSecondaryConstructorDeclarationImpl(
                    ktSecondaryConstructor,
                    parent,
                )
            }
    }
}
