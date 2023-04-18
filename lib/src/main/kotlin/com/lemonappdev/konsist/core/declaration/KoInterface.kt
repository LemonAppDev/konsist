package com.lemonappdev.konsist.core.declaration

import com.lemonappdev.konsist.core.cache.KoDeclarationCache
import org.jetbrains.kotlin.lexer.KtTokens
import org.jetbrains.kotlin.psi.KtClass

class KoInterface private constructor(private val ktClass: KtClass) : KoComplexDeclaration(ktClass) {

    fun hasActualModifier() = ktClass.modifierList?.hasModifier(KtTokens.ACTUAL_KEYWORD) ?: false

    fun hasExpectModifier() = ktClass.modifierList?.hasModifier(KtTokens.EXPECT_KEYWORD) ?: false

    companion object {
        private val cache = KoDeclarationCache<KoInterface>()

        fun getInstance(ktClass: KtClass) = cache.getOrCreateInstance(ktClass) { KoInterface(ktClass) }
    }
}
