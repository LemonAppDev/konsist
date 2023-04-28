package com.lemonappdev.konsist.core.declaration

import com.lemonappdev.konsist.core.cache.KoDeclarationCache
import com.lemonappdev.konsist.core.const.KoModifier
import org.jetbrains.kotlin.psi.KtObjectDeclaration

class KoObjectDeclaration(private val ktObjectDeclaration: KtObjectDeclaration) : KoComplexDeclaration(ktObjectDeclaration) {
    fun hasDataModifier() = hasModifiers(KoModifier.DATA)

    companion object {
        private val cache = KoDeclarationCache<KoObjectDeclaration>()

        fun getInstance(ktObjectDeclaration: KtObjectDeclaration) =
            cache.getOrCreateInstance(ktObjectDeclaration) { KoObjectDeclaration(ktObjectDeclaration) }
    }
}
