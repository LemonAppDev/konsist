package com.lemonappdev.konsist.core.declaration

import com.lemonappdev.konsist.core.cache.KoDeclarationCache
import org.jetbrains.kotlin.psi.KtSecondaryConstructor

class KoSecondaryConstructorDeclarationImpl private constructor(ktSecondaryConstructor: KtSecondaryConstructor) :
    KoConstructorDeclarationImpl(ktSecondaryConstructor) {
    companion object {
        private val cache = KoDeclarationCache<KoSecondaryConstructorDeclarationImpl>()

        fun getInstance(ktSecondaryConstructor: KtSecondaryConstructor) =
            cache.getOrCreateInstance(ktSecondaryConstructor) { KoSecondaryConstructorDeclarationImpl(ktSecondaryConstructor) }
    }
}
