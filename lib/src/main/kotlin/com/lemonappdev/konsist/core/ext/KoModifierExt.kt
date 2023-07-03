package com.lemonappdev.konsist.core.ext

import com.lemonappdev.konsist.api.KoModifier
import com.lemonappdev.konsist.api.KoModifier.*
import org.jetbrains.kotlin.lexer.KtModifierKeywordToken
import org.jetbrains.kotlin.lexer.KtTokens

@Suppress("detekt.ComplexMethod")
internal fun KoModifier.toKtToken(): KtModifierKeywordToken = when (this) {
    DATA -> KtTokens.DATA_KEYWORD
    VALUE -> KtTokens.VALUE_KEYWORD
    INLINE -> KtTokens.INLINE_KEYWORD
    NOINLINE -> KtTokens.NOINLINE_KEYWORD
    TAILREC -> KtTokens.TAILREC_KEYWORD
    EXTERNAL -> KtTokens.EXTERNAL_KEYWORD
    ANNOTATION -> KtTokens.ANNOTATION_KEYWORD
    CROSSINLINE -> KtTokens.CROSSINLINE_KEYWORD
    OPERATOR -> KtTokens.OPERATOR_KEYWORD
    INFIX -> KtTokens.INFIX_KEYWORD
    ABSTRACT -> KtTokens.ABSTRACT_KEYWORD
    ENUM -> KtTokens.ENUM_KEYWORD
    CONTRACT -> KtTokens.CONTRACT_KEYWORD
    OPEN -> KtTokens.OPEN_KEYWORD
    INNER -> KtTokens.INNER_KEYWORD
    OVERRIDE -> KtTokens.OVERRIDE_KEYWORD
    PRIVATE -> KtTokens.PRIVATE_KEYWORD
    PUBLIC -> KtTokens.PUBLIC_KEYWORD
    DEFAULT_VISIBILITY_KEYWORD -> KtTokens.DEFAULT_VISIBILITY_KEYWORD
    INTERNAL -> KtTokens.INTERNAL_KEYWORD
    PROTECTED -> KtTokens.PROTECTED_KEYWORD
    OUT -> KtTokens.OUT_KEYWORD
    VARARG -> KtTokens.VARARG_KEYWORD
    REIFIED -> KtTokens.REIFIED_KEYWORD
    COMPANION -> KtTokens.COMPANION_KEYWORD
    SEALED -> KtTokens.SEALED_KEYWORD
    FINAL -> KtTokens.FINAL_KEYWORD
    LATEINIT -> KtTokens.LATEINIT_KEYWORD
    CONST -> KtTokens.CONST_KEYWORD
    SUSPEND -> KtTokens.SUSPEND_KEYWORD
    EXPECT -> KtTokens.EXPECT_KEYWORD
    ACTUAL -> KtTokens.ACTUAL_KEYWORD
    FUN -> KtTokens.FUN_KEYWORD
}
