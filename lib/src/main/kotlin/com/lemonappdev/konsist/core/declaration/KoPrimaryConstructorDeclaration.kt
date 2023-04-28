package com.lemonappdev.konsist.core.declaration

import com.lemonappdev.konsist.core.cache.KoDeclarationCache
import org.jetbrains.kotlin.psi.KtPrimaryConstructor

class KoPrimaryConstructorDeclaration private constructor(ktPrimaryConstructor: KtPrimaryConstructor) :
    KoConstructorDeclaration(ktPrimaryConstructor) {
    companion object {
        private val cache = KoDeclarationCache<KoPrimaryConstructorDeclaration>()

        fun getInstance(ktPrimaryConstructor: KtPrimaryConstructor) =
            cache.getOrCreateInstance(ktPrimaryConstructor) { KoPrimaryConstructorDeclaration(ktPrimaryConstructor) }
    }
}
