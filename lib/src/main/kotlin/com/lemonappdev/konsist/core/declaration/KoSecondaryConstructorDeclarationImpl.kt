package com.lemonappdev.konsist.core.declaration

import com.lemonappdev.konsist.api.declaration.KoSecondaryConstructorDeclaration
import com.lemonappdev.konsist.core.cache.KoDeclarationCache
import com.lemonappdev.konsist.core.parent.KoParent
import org.jetbrains.kotlin.psi.KtSecondaryConstructor

internal class KoSecondaryConstructorDeclarationImpl private constructor(
    ktSecondaryConstructor: KtSecondaryConstructor,
    parent: KoParent,
) :
    KoConstructorDeclarationImpl(ktSecondaryConstructor, parent), KoSecondaryConstructorDeclaration {
    internal companion object {
        private val cache = KoDeclarationCache<KoSecondaryConstructorDeclarationImpl>()

        internal fun getInstance(ktSecondaryConstructor: KtSecondaryConstructor, parent: KoParent) =
            cache.getOrCreateInstance(ktSecondaryConstructor, parent) {
                KoSecondaryConstructorDeclarationImpl(
                    ktSecondaryConstructor,
                    parent,
                )
            }
    }
}
