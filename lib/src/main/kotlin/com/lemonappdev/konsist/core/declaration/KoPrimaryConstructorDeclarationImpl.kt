package com.lemonappdev.konsist.core.declaration

import com.lemonappdev.konsist.core.cache.KoDeclarationCache
import org.jetbrains.kotlin.psi.KtPrimaryConstructor

class KoPrimaryConstructorDeclarationImpl private constructor(ktPrimaryConstructor: KtPrimaryConstructor) :
    KoConstructorDeclarationImpl(ktPrimaryConstructor) {
    companion object {
        private val cache = KoDeclarationCache<KoPrimaryConstructorDeclarationImpl>()

        fun getInstance(ktPrimaryConstructor: KtPrimaryConstructor) =
            cache.getOrCreateInstance(ktPrimaryConstructor) { KoPrimaryConstructorDeclarationImpl(ktPrimaryConstructor) }
    }
}
