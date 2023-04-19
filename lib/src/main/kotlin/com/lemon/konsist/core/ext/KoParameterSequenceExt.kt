package com.lemon.konsist.core.ext

import com.lemon.konsist.core.declaration.KoParameter

fun Sequence<KoParameter>.withVarargModifier() = filter { it.hasVarargModifier() }

fun Sequence<KoParameter>.withoutVarargModifier() = filterNot { it.hasVarargModifier() }

fun Sequence<KoParameter>.withNoInlineModifier() = filter { it.hasNoInlineModifier() }

fun Sequence<KoParameter>.withoutNoInlineModifier() = filterNot { it.hasNoInlineModifier() }

fun Sequence<KoParameter>.withCrossInlineModifier() = filter { it.hasCrossInlineModifier() }

fun Sequence<KoParameter>.withoutCrossInlineModifier() = filterNot { it.hasCrossInlineModifier() }

fun Sequence<KoParameter>.withDefaultValue() = filter { it.hasDefaultValue() }

fun Sequence<KoParameter>.withoutDefaultValue() = filterNot { it.hasDefaultValue() }
