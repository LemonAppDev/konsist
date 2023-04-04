package com.lemon.konsist.core.declaration

import org.jetbrains.kotlin.psi.KtPrimaryConstructor

class KoPrimaryConstructor private constructor(ktPrimaryConstructor: KtPrimaryConstructor) : KoConstructor(ktPrimaryConstructor) {
    companion object {
        private val cache = KoDeclarationCache<KoPrimaryConstructor>()
        fun getInstance(ktPrimaryConstructor: KtPrimaryConstructor) =
            cache.getOrCreateInstance(ktPrimaryConstructor) { KoPrimaryConstructor(ktPrimaryConstructor) }
    }
}
