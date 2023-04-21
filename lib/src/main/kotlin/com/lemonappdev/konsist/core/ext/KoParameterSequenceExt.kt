package com.lemonappdev.konsist.core.ext

import com.lemonappdev.konsist.core.declaration.KoParameter

fun Sequence<KoParameter>.withVarargModifier() = filter { it.hasVarargModifier() }

fun Sequence<KoParameter>.withoutVarargModifier() = filterNot { it.hasVarargModifier() }

fun Sequence<KoParameter>.withNoInlineModifier() = filter { it.hasNoInlineModifier() }

fun Sequence<KoParameter>.withoutNoInlineModifier() = filterNot { it.hasNoInlineModifier() }

fun Sequence<KoParameter>.withCrossInlineModifier() = filter { it.hasCrossInlineModifier() }

fun Sequence<KoParameter>.withoutCrossInlineModifier() = filterNot { it.hasCrossInlineModifier() }

fun Sequence<KoParameter>.withDefaultValue(value: String? = null) = filter { it.hasDefaultValue(value) }

fun Sequence<KoParameter>.withoutDefaultValue(value: String? = null) = filterNot { it.hasDefaultValue(value) }

fun Sequence<KoParameter>.withType(type: String) = filter { it.hasType(type) }

fun Sequence<KoParameter>.withoutType(type: String) = filterNot { it.hasType(type) }

inline fun <reified T> Sequence<KoParameter>.withTypeOf() = filter { it.hasType<T>() }

inline fun <reified T> Sequence<KoParameter>.withoutTypeOf() = filterNot { it.hasType<T>() }
