package com.lemon.konsist.core.declaration

import org.jetbrains.kotlin.psi.KtObjectDeclaration

class KoObject(private val ktObjectDeclaration: KtObjectDeclaration) : KoComplexDeclaration(ktObjectDeclaration) {
    val isData by lazy { ktObjectDeclaration.isData() }

    companion object {
        private val cache = KoDeclarationCache<KoObject>()
        fun getInstance(ktObjectDeclaration: KtObjectDeclaration) = if (cache.hasKey(ktObjectDeclaration)) {
            cache.get(ktObjectDeclaration)
        } else {
            cache.set(ktObjectDeclaration, KoObject(ktObjectDeclaration))
            cache.get(ktObjectDeclaration)
        }
    }
}
