package com.konsistcore.core.const

import com.konsistcore.core.const.Modifier.ABSTRACT
import com.konsistcore.core.const.Modifier.ACTUAL
import com.konsistcore.core.const.Modifier.ANNOTATION
import com.konsistcore.core.const.Modifier.COMPANION
import com.konsistcore.core.const.Modifier.CONST
import com.konsistcore.core.const.Modifier.CONTRACT
import com.konsistcore.core.const.Modifier.CROSSINLINE
import com.konsistcore.core.const.Modifier.DATA
import com.konsistcore.core.const.Modifier.DEFAULT_VISIBILITY_KEYWORD
import com.konsistcore.core.const.Modifier.ENUM
import com.konsistcore.core.const.Modifier.EXPECT
import com.konsistcore.core.const.Modifier.EXTERNAL
import com.konsistcore.core.const.Modifier.FINAL
import com.konsistcore.core.const.Modifier.INFIX
import com.konsistcore.core.const.Modifier.INLINE
import com.konsistcore.core.const.Modifier.INNER
import com.konsistcore.core.const.Modifier.INTERNAL
import com.konsistcore.core.const.Modifier.LATEINIT
import com.konsistcore.core.const.Modifier.NOINLINE
import com.konsistcore.core.const.Modifier.OPEN
import com.konsistcore.core.const.Modifier.OPERATOR
import com.konsistcore.core.const.Modifier.OUT
import com.konsistcore.core.const.Modifier.OVERRIDE
import com.konsistcore.core.const.Modifier.PRIVATE
import com.konsistcore.core.const.Modifier.PROTECTED
import com.konsistcore.core.const.Modifier.PUBLIC
import com.konsistcore.core.const.Modifier.REIFIED
import com.konsistcore.core.const.Modifier.SEALED
import com.konsistcore.core.const.Modifier.SUSPEND
import com.konsistcore.core.const.Modifier.TAILREC
import com.konsistcore.core.const.Modifier.VALUE
import com.konsistcore.core.const.Modifier.VARARG
import org.jetbrains.kotlin.lexer.KtTokens

enum class Modifier(val type: String) {
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
internal fun Modifier.toKtToken() = when (this) {
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
