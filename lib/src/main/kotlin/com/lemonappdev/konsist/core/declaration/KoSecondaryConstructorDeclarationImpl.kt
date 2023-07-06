package com.lemonappdev.konsist.core.declaration

import com.lemonappdev.konsist.api.declaration.KoBaseDeclaration
import com.lemonappdev.konsist.api.declaration.KoSecondaryConstructorDeclaration
import com.lemonappdev.konsist.api.provider.KoParentProvider
import com.lemonappdev.konsist.core.cache.KoDeclarationCache
import org.jetbrains.kotlin.psi.KtSecondaryConstructor

internal class KoSecondaryConstructorDeclarationImpl private constructor(
    ktSecondaryConstructor: KtSecondaryConstructor,
    parentDeclaration: KoParentProvider?,
) :
    KoConstructorDeclarationImpl(ktSecondaryConstructor, parentDeclaration), KoSecondaryConstructorDeclaration {
    internal companion object {
        private val cache: KoDeclarationCache<KoSecondaryConstructorDeclaration> = KoDeclarationCache()

        internal fun getInstance(
            ktSecondaryConstructor: KtSecondaryConstructor,
            parentDeclaration: KoParentProvider?,
        ): KoSecondaryConstructorDeclaration =
            cache.getOrCreateInstance(ktSecondaryConstructor, parentDeclaration) {
                KoSecondaryConstructorDeclarationImpl(
                    ktSecondaryConstructor,
                    parentDeclaration,
                )
            }
    }
}
