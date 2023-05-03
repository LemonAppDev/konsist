package com.lemonappdev.konsist.core.declaration

import com.lemonappdev.konsist.core.cache.KoDeclarationCache
import com.lemonappdev.konsist.api.KoModifier
import org.jetbrains.kotlin.psi.KtObjectDeclaration

class KoObjectDeclarationImpl(private val ktObjectDeclaration: KtObjectDeclaration) : KoComplexDeclarationImpl(ktObjectDeclaration) {
    fun hasDataModifier() = hasModifiers(KoModifier.DATA)

    companion object {
        private val cache = KoDeclarationCache<KoObjectDeclarationImpl>()

        fun getInstance(ktObjectDeclaration: KtObjectDeclaration) =
            cache.getOrCreateInstance(ktObjectDeclaration) { KoObjectDeclarationImpl(ktObjectDeclaration) }
    }
}
