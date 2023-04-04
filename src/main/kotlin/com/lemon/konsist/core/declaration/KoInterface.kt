package com.lemon.konsist.core.declaration

import org.jetbrains.kotlin.psi.KtClass

class KoInterface private constructor(private val ktClass: KtClass) : KoComplexDeclaration(ktClass) {
    companion object {
        private val cache = KoDeclarationCache<KoInterface>()
        fun getInstance(ktClass: KtClass) = if (cache.hasKey(ktClass)) {
            cache.get(ktClass)
        } else {
            cache.set(ktClass, KoInterface(ktClass))
            cache.get(ktClass)
        }
    }
}
