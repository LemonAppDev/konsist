package com.lemon.konsist.core.declaration

import org.jetbrains.kotlin.psi.KtSecondaryConstructor

class KoSecondaryConstructor private constructor(ktSecondaryConstructor: KtSecondaryConstructor) : KoConstructor(ktSecondaryConstructor) {
    companion object {
        private val cache = KoDeclarationCache<KoSecondaryConstructor>()
        fun getInstance(ktSecondaryConstructor: KtSecondaryConstructor) =
            cache.getOrCreateInstance(ktSecondaryConstructor) { KoSecondaryConstructor(ktSecondaryConstructor) }
    }
}
