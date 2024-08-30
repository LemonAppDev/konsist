package com.lemonappdev.konsist.core.declaration

import com.lemonappdev.konsist.api.declaration.KoBaseDeclaration
import com.lemonappdev.konsist.api.declaration.KoSecondaryConstructorDeclaration
import com.lemonappdev.konsist.core.cache.KoDeclarationCache
import com.lemonappdev.konsist.core.provider.KoKDocProviderCore
import org.jetbrains.kotlin.psi.KtConstructor
import org.jetbrains.kotlin.psi.KtSecondaryConstructor

internal class KoSecondaryConstructorDeclarationCore private constructor(
    private val ktSecondaryConstructor: KtSecondaryConstructor,
    override val containingDeclaration: KoBaseDeclaration,
) : KoSecondaryConstructorDeclaration,
    KoConstructorDeclarationCore,
    KoKDocProviderCore {
    override val ktConstructor: KtConstructor<*> by lazy { ktSecondaryConstructor }

    override fun toString(): String = locationWithText

    internal companion object {
        private val cache: KoDeclarationCache<KoSecondaryConstructorDeclaration> = KoDeclarationCache()

        internal fun getInstance(
            ktSecondaryConstructor: KtSecondaryConstructor,
            containingDeclaration: KoBaseDeclaration,
        ): KoSecondaryConstructorDeclaration =
            cache.getOrCreateInstance(ktSecondaryConstructor, containingDeclaration) {
                KoSecondaryConstructorDeclarationCore(
                    ktSecondaryConstructor,
                    containingDeclaration,
                )
            }
    }
}
