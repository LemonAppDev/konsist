package com.lemonappdev.konsist.core.const

import com.lemonappdev.konsist.core.const.KoModifier.ABSTRACT
import com.lemonappdev.konsist.core.const.KoModifier.ACTUAL
import com.lemonappdev.konsist.core.const.KoModifier.ANNOTATION
import com.lemonappdev.konsist.core.const.KoModifier.COMPANION
import com.lemonappdev.konsist.core.const.KoModifier.CONST
import com.lemonappdev.konsist.core.const.KoModifier.CONTRACT
import com.lemonappdev.konsist.core.const.KoModifier.CROSSINLINE
import com.lemonappdev.konsist.core.const.KoModifier.DATA
import com.lemonappdev.konsist.core.const.KoModifier.DEFAULT_VISIBILITY_KEYWORD
import com.lemonappdev.konsist.core.const.KoModifier.ENUM
import com.lemonappdev.konsist.core.const.KoModifier.EXPECT
import com.lemonappdev.konsist.core.const.KoModifier.EXTERNAL
import com.lemonappdev.konsist.core.const.KoModifier.FINAL
import com.lemonappdev.konsist.core.const.KoModifier.INFIX
import com.lemonappdev.konsist.core.const.KoModifier.INLINE
import com.lemonappdev.konsist.core.const.KoModifier.INNER
import com.lemonappdev.konsist.core.const.KoModifier.INTERNAL
import com.lemonappdev.konsist.core.const.KoModifier.LATEINIT
import com.lemonappdev.konsist.core.const.KoModifier.NOINLINE
import com.lemonappdev.konsist.core.const.KoModifier.OPEN
import com.lemonappdev.konsist.core.const.KoModifier.OPERATOR
import com.lemonappdev.konsist.core.const.KoModifier.OUT
import com.lemonappdev.konsist.core.const.KoModifier.OVERRIDE
import com.lemonappdev.konsist.core.const.KoModifier.PRIVATE
import com.lemonappdev.konsist.core.const.KoModifier.PROTECTED
import com.lemonappdev.konsist.core.const.KoModifier.PUBLIC
import com.lemonappdev.konsist.core.const.KoModifier.REIFIED
import com.lemonappdev.konsist.core.const.KoModifier.SEALED
import com.lemonappdev.konsist.core.const.KoModifier.SUSPEND
import com.lemonappdev.konsist.core.const.KoModifier.TAILREC
import com.lemonappdev.konsist.core.const.KoModifier.VALUE
import com.lemonappdev.konsist.core.const.KoModifier.VARARG
import org.jetbrains.kotlin.lexer.KtTokens

enum class KoModifier(val type: String) {
    DATA("data"),
    VALUE("value"),
    INLINE("inline"),
    NOINLINE("noinline"),
    TAILREC("tailrec"),
    EXTERNAL("external"),
    ANNOTATION("annotation"),
    CROSSINLINE("crossinline"),
    OPERATOR("operator"),
    INFIX("infix"),
    ABSTRACT("abstract"),
    ENUM("enum"),
    CONTRACT("contract"),
    OPEN("open"),
    INNER("inner"),
    OVERRIDE("override"),
    PRIVATE("private"),
    PUBLIC("public"),
    DEFAULT_VISIBILITY_KEYWORD("public"),
    INTERNAL("internal"),
    PROTECTED("protected"),
    OUT("out"),
    VARARG("vararg"),
    REIFIED("reified"),
    COMPANION("companion"),
    SEALED("sealed"),
    FINAL("final"),
    LATEINIT("lateinit"),
    CONST("const"),
    SUSPEND("suspend"),
    EXPECT("expect"),
    ACTUAL("actual"),
}

@Suppress("detekt.ComplexMethod")
internal fun KoModifier.toKtToken() = when (this) {
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
}
