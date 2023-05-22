package com.lemonappdev.konsist.core.declaration

import com.lemonappdev.konsist.api.declaration.KoBaseDeclaration
import com.lemonappdev.konsist.api.declaration.KoSecondaryConstructorDeclaration
import com.lemonappdev.konsist.core.cache.KoDeclarationCache
import org.jetbrains.kotlin.psi.KtSecondaryConstructor

internal class KoSecondaryConstructorDeclarationImpl private constructor(
    ktSecondaryConstructor: KtSecondaryConstructor,
    parent: KoBaseDeclaration?,
) :
    KoConstructorDeclarationImpl(ktSecondaryConstructor, parent), KoSecondaryConstructorDeclaration {
    internal companion object {
        private val cache: KoDeclarationCache<KoSecondaryConstructorDeclaration> = KoDeclarationCache()

        internal fun getInstance(
            ktSecondaryConstructor: KtSecondaryConstructor,
            parent: KoBaseDeclaration
        ): KoSecondaryConstructorDeclaration =
            cache.getOrCreateInstance(ktSecondaryConstructor, parent) {
                KoSecondaryConstructorDeclarationImpl(
                    ktSecondaryConstructor,
                    parent,
                )
            }
    }
}
