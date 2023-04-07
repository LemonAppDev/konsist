package com.lemon.konsist.core.declaration

import org.jetbrains.kotlin.lexer.KtTokens
import org.jetbrains.kotlin.psi.KtModifierList
import org.jetbrains.kotlin.psi.KtProperty
import org.jetbrains.kotlin.psi.KtTypeReference
import org.jetbrains.kotlin.utils.addToStdlib.firstIsInstanceOrNull

class KoProperty private constructor(private val ktProperty: KtProperty) : KoDeclaration(ktProperty) {
    val isVar by lazy { ktProperty.isVar }

    val isVal by lazy { !ktProperty.isVar }

    val isLateinit by lazy { ktProperty.modifierList?.hasModifier(KtTokens.LATEINIT_KEYWORD) ?: false }

    val isOverride by lazy { ktProperty.modifierList?.hasModifier(KtTokens.OVERRIDE_KEYWORD) ?: false }

    val isAbstract by lazy { ktProperty.modifierList?.hasModifier(KtTokens.ABSTRACT_KEYWORD) ?: false }

    val isOpen by lazy { ktProperty.modifierList?.hasModifier(KtTokens.OPEN_KEYWORD) ?: false }

    val isFinal by lazy { ktProperty.modifierList?.hasModifier(KtTokens.FINAL_KEYWORD) ?: false }

    val isConst by lazy {
        ktProperty
            .children
            .firstIsInstanceOrNull<KtModifierList>()
            ?.hasModifier(KtTokens.CONST_KEYWORD)
            ?: false
    }

    val explicitType by lazy {
        val type = ktProperty
            .children
            .firstIsInstanceOrNull<KtTypeReference>()

        type?.let { KoType.getInstance(it) }
    }

    companion object {
        private val cache = KoDeclarationCache<KoProperty>()

        fun getInstance(ktProperty: KtProperty) = cache.getOrCreateInstance(ktProperty) { KoProperty(ktProperty) }
    }
}
