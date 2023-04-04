package com.lemon.konsist.core.declaration

import org.jetbrains.kotlin.psi.KtPrimaryConstructor

class KoPrimaryConstructor private constructor(ktPrimaryConstructor: KtPrimaryConstructor) : KoConstructor(ktPrimaryConstructor) {
    companion object {
        private val cache = KoDeclarationCache<KoPrimaryConstructor>()
        fun getInstance(ktPrimaryConstructor: KtPrimaryConstructor) =
            if (cache.hasKey(ktPrimaryConstructor)) {
                cache.get(ktPrimaryConstructor)
            } else {
                cache.set(ktPrimaryConstructor, KoPrimaryConstructor(ktPrimaryConstructor))
                cache.get(ktPrimaryConstructor)
            }
    }
}
