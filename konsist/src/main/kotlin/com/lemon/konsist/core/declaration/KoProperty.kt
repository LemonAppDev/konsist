package com.lemon.konsist.core.declaration

import org.jetbrains.kotlin.lexer.KtTokens
import org.jetbrains.kotlin.psi.KtModifierList
import org.jetbrains.kotlin.psi.KtProperty
import org.jetbrains.kotlin.utils.addToStdlib.firstIsInstanceOrNull

class KoProperty(private val ktProperty: KtProperty) : KoDeclaration(ktProperty) {
    val isVar by lazy { ktProperty.isVar }

    val isVal by lazy { !ktProperty.isVar }

    val isConst by lazy {
        ktProperty
            .children
            .firstIsInstanceOrNull<KtModifierList>()
            ?.hasModifier(KtTokens.CONST_KEYWORD)
            ?: false
    }
}
