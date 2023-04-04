package com.lemon.konsist.core.declaration

import org.jetbrains.kotlin.psi.KtObjectDeclaration

class KoCompanionObject private constructor(private val ktObjectDeclaration: KtObjectDeclaration) :
    KoComplexDeclaration(ktObjectDeclaration) {
    fun hasExplicitName() = name != DEFAULT_COMPANION_OBJECT_NAME

    companion object {
        const val DEFAULT_COMPANION_OBJECT_NAME = "Companion"

        private val cache = KoDeclarationCache<KoCompanionObject>()
        fun getInstance(ktObjectDeclaration: KtObjectDeclaration) = if (cache.hasKey(ktObjectDeclaration)) {
            cache.get(ktObjectDeclaration)
        } else {
            cache.set(ktObjectDeclaration, KoCompanionObject(ktObjectDeclaration))
            cache.get(ktObjectDeclaration)
        }
    }
}
