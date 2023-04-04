package com.lemon.konsist.core.declaration

import org.jetbrains.kotlin.lexer.KtTokens
import org.jetbrains.kotlin.psi.KtModifierList
import org.jetbrains.kotlin.psi.KtProperty
import org.jetbrains.kotlin.utils.addToStdlib.firstIsInstanceOrNull

class KoProperty private constructor(private val ktProperty: KtProperty) : KoDeclaration(ktProperty) {
    val isVar by lazy { ktProperty.isVar }

    val isVal by lazy { !ktProperty.isVar }

    val isConst by lazy {
        ktProperty
            .children
            .firstIsInstanceOrNull<KtModifierList>()
            ?.hasModifier(KtTokens.CONST_KEYWORD)
            ?: false
    }

    companion object {
        private val cache = KoDeclarationCache<KoProperty>()
        fun getInstance(ktProperty: KtProperty) = if (cache.hasKey(ktProperty)) {
            cache.get(ktProperty)
        } else {
            cache.set(ktProperty, KoProperty(ktProperty))
            cache.get(ktProperty)
        }
    }
}
