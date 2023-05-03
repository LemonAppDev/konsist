package com.lemonappdev.konsist.core.declaration

import com.lemonappdev.konsist.core.cache.KoDeclarationCache
import org.jetbrains.kotlin.psi.KtObjectDeclaration

class KoCompanionObjectDeclarationImpl private constructor(ktObjectDeclaration: KtObjectDeclaration) :
    KoComplexDeclarationImpl(ktObjectDeclaration) {
    fun hasName() = name != DEFAULT_COMPANION_OBJECT_NAME

    companion object {
        const val DEFAULT_COMPANION_OBJECT_NAME = "Companion"

        private val cache = KoDeclarationCache<KoCompanionObjectDeclarationImpl>()

        fun getInstance(ktObjectDeclaration: KtObjectDeclaration) =
            cache.getOrCreateInstance(ktObjectDeclaration) { KoCompanionObjectDeclarationImpl(ktObjectDeclaration) }
    }
}
