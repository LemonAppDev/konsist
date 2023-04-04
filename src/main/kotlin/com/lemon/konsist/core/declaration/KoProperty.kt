package com.lemon.konsist.core.declaration

import org.jetbrains.kotlin.lexer.KtTokens
import org.jetbrains.kotlin.psi.KtModifierList
import org.jetbrains.kotlin.psi.KtProperty

class KoProperty(private val ktProperty: KtProperty) : KoDeclaration(ktProperty) {
    val isVar by lazy { ktProperty.isVar }

    val isVal by lazy { !ktProperty.isVar }

    val isConst by lazy {
        val modifiers = ktProperty
            .children
            .filterIsInstance<KtModifierList>()

        modifiers.firstOrNull()?.hasModifier(KtTokens.CONST_KEYWORD) ?: false
    }
}
