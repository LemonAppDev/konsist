package com.lemonappdev.konsist.core.declaration

import com.lemonappdev.konsist.api.declaration.KoSecondaryConstructorDeclaration
import com.lemonappdev.konsist.core.cache.KoDeclarationCache
import org.jetbrains.kotlin.psi.KtSecondaryConstructor

internal class KoSecondaryConstructorDeclarationImpl private constructor(ktSecondaryConstructor: KtSecondaryConstructor) :
    KoConstructorDeclarationImpl(ktSecondaryConstructor), KoSecondaryConstructorDeclaration {
    internal companion object {
        private val cache = KoDeclarationCache<KoSecondaryConstructorDeclarationImpl>()

        internal fun getInstance(ktSecondaryConstructor: KtSecondaryConstructor, parent: KoBaseDeclarationImpl) =
            cache.getOrCreateInstance(ktSecondaryConstructor, parent) { KoSecondaryConstructorDeclarationImpl(ktSecondaryConstructor) }
    }
}
