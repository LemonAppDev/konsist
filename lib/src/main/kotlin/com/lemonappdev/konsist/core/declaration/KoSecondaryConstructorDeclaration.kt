package com.lemonappdev.konsist.core.declaration

import com.lemonappdev.konsist.core.cache.KoDeclarationCache
import org.jetbrains.kotlin.psi.KtSecondaryConstructor

class KoSecondaryConstructorDeclaration private constructor(ktSecondaryConstructor: KtSecondaryConstructor) : KoConstructorDeclaration(ktSecondaryConstructor) {
    companion object {
        private val cache = KoDeclarationCache<KoSecondaryConstructorDeclaration>()

        fun getInstance(ktSecondaryConstructor: KtSecondaryConstructor) =
            cache.getOrCreateInstance(ktSecondaryConstructor) { KoSecondaryConstructorDeclaration(ktSecondaryConstructor) }
    }
}
