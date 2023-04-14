package com.lemon.konsist.core.declaration

import com.lemon.konsist.core.cache.KoDeclarationCache
import org.jetbrains.kotlin.psi.KtClass

class KoInterface private constructor(ktClass: KtClass) : KoComplexDeclaration(ktClass) {
    companion object {
        private val cache = KoDeclarationCache<KoInterface>()

        fun getInstance(ktClass: KtClass) = cache.getOrCreateInstance(ktClass) { KoInterface(ktClass) }
    }
}
