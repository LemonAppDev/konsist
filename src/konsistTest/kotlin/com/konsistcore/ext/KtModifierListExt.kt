package com.konsistcore.ext

import org.jetbrains.kotlin.lexer.KtTokens
import org.jetbrains.kotlin.psi.KtModifierList

fun KtModifierList?.isPublic(): Boolean {
    if (this?.hasModifier(KtTokens.PUBLIC_KEYWORD) == true) {
        return true
    }

    val hasOtherVisibilityModifier = this?.hasModifier(KtTokens.PRIVATE_KEYWORD) == true ||
        this?.hasModifier(KtTokens.PROTECTED_KEYWORD) == true ||
        this?.hasModifier(KtTokens.INTERNAL_KEYWORD) == true

    return hasOtherVisibilityModifier.not()
}

fun KtModifierList?.isPrivate() = this?.hasModifier(KtTokens.PRIVATE_KEYWORD) ?: false

fun KtModifierList?.isProtected() = this?.hasModifier(KtTokens.PROTECTED_KEYWORD) ?: false

fun KtModifierList?.isInternal() = this?.hasModifier(KtTokens.INTERNAL_KEYWORD) ?: false
