package com.lemonappdev.konsist.core.ext

import com.lemonappdev.konsist.api.KoModifier
import com.lemonappdev.konsist.api.KoModifier.ABSTRACT
import com.lemonappdev.konsist.api.KoModifier.ACTUAL
import com.lemonappdev.konsist.api.KoModifier.ANNOTATION
import com.lemonappdev.konsist.api.KoModifier.COMPANION
import com.lemonappdev.konsist.api.KoModifier.CONST
import com.lemonappdev.konsist.api.KoModifier.CONTRACT
import com.lemonappdev.konsist.api.KoModifier.CROSSINLINE
import com.lemonappdev.konsist.api.KoModifier.DATA
import com.lemonappdev.konsist.api.KoModifier.DEFAULT_VISIBILITY_KEYWORD
import com.lemonappdev.konsist.api.KoModifier.ENUM
import com.lemonappdev.konsist.api.KoModifier.EXPECT
import com.lemonappdev.konsist.api.KoModifier.EXTERNAL
import com.lemonappdev.konsist.api.KoModifier.FINAL
import com.lemonappdev.konsist.api.KoModifier.FUN
import com.lemonappdev.konsist.api.KoModifier.INFIX
import com.lemonappdev.konsist.api.KoModifier.INLINE
import com.lemonappdev.konsist.api.KoModifier.INNER
import com.lemonappdev.konsist.api.KoModifier.INTERNAL
import com.lemonappdev.konsist.api.KoModifier.LATEINIT
import com.lemonappdev.konsist.api.KoModifier.NOINLINE
import com.lemonappdev.konsist.api.KoModifier.OPEN
import com.lemonappdev.konsist.api.KoModifier.OPERATOR
import com.lemonappdev.konsist.api.KoModifier.OUT
import com.lemonappdev.konsist.api.KoModifier.OVERRIDE
import com.lemonappdev.konsist.api.KoModifier.PRIVATE
import com.lemonappdev.konsist.api.KoModifier.PROTECTED
import com.lemonappdev.konsist.api.KoModifier.PUBLIC
import com.lemonappdev.konsist.api.KoModifier.REIFIED
import com.lemonappdev.konsist.api.KoModifier.SEALED
import com.lemonappdev.konsist.api.KoModifier.SUSPEND
import com.lemonappdev.konsist.api.KoModifier.TAILREC
import com.lemonappdev.konsist.api.KoModifier.VALUE
import com.lemonappdev.konsist.api.KoModifier.VARARG
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
