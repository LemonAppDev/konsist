package com.lemonappdev.konsist.core.declaration

import com.lemonappdev.konsist.core.cache.KoDeclarationCache
import org.jetbrains.kotlin.psi.KtPrimaryConstructor

class KoPrimaryConstructor private constructor(ktPrimaryConstructor: KtPrimaryConstructor) : KoConstructor(ktPrimaryConstructor) {
    companion object {
        private val cache = KoDeclarationCache<KoPrimaryConstructor>()

        fun getInstance(ktPrimaryConstructor: KtPrimaryConstructor) =
            cache.getOrCreateInstance(ktPrimaryConstructor) { KoPrimaryConstructor(ktPrimaryConstructor) }
    }
}
