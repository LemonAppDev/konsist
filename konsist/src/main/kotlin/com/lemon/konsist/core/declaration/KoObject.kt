package com.lemon.konsist.core.declaration

import org.jetbrains.kotlin.psi.KtObjectDeclaration

class KoObject private constructor(private val ktObjectDeclaration: KtObjectDeclaration) : KoComplexDeclaration(ktObjectDeclaration) {
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
