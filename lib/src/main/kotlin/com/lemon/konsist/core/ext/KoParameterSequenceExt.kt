package com.lemon.konsist.core.ext

import com.lemon.konsist.core.declaration.KoParameter

fun Sequence<KoParameter>.withVarargModifier() = filter { it.hasVarargModifier() }

fun Sequence<KoParameter>.withoutVarargModifier() = filterNot { it.hasVarargModifier() }

fun Sequence<KoParameter>.withNoInlineModifier() = filter { it.hasNoInlineModifier() }

fun Sequence<KoParameter>.withoutNoInlineModifier() = filterNot { it.hasNoInlineModifier() }

fun Sequence<KoParameter>.withCrossInlineModifier() = filter { it.hasCrossInlineModifier() }

fun Sequence<KoParameter>.withoutCrossInlineModifier() = filterNot { it.hasCrossInlineModifier() }

fun Sequence<KoParameter>.withDefaultValue(value: String? = null) = filter {
    when (value){
        null -> it.hasDefaultValue()
        else -> it.defaultValue == value
    }
}

fun Sequence<KoParameter>.withoutDefaultValue(value: String? = null) = this - withDefaultValue(value).toSet()

fun Sequence<KoParameter>.withType(type: String) = filter { it.type.name == type }

fun Sequence<KoParameter>.withoutType(type: String) = filterNot { it.type.name == type }

inline fun <reified T> Sequence<KoParameter>.withTypeOf() = filter { T::class.simpleName == it.type.name }

inline fun <reified T> Sequence<KoParameter>.withoutTypeOf() =
    filterNot { T::class.simpleName == it.type.name }
