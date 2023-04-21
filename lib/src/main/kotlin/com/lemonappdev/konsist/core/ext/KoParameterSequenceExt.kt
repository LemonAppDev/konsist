package com.lemonappdev.konsist.core.ext

import com.lemonappdev.konsist.core.declaration.KoParameter

fun Sequence<KoParameter>.withVarargModifier() = filter { it.hasVarargModifier() }

fun Sequence<KoParameter>.withoutVarargModifier() = filterNot { it.hasVarargModifier() }

fun Sequence<KoParameter>.withNoInlineModifier() = filter { it.hasNoInlineModifier() }

fun Sequence<KoParameter>.withoutNoInlineModifier() = filterNot { it.hasNoInlineModifier() }

fun Sequence<KoParameter>.withCrossInlineModifier() = filter { it.hasCrossInlineModifier() }

fun Sequence<KoParameter>.withoutCrossInlineModifier() = filterNot { it.hasCrossInlineModifier() }

fun Sequence<KoParameter>.withDefaultValue(vararg values: String) = filter {
    when {
        values.isEmpty() -> it.hasDefaultValue()
        else -> values.any { value -> it.hasDefaultValue(value) }
    }
}

fun Sequence<KoParameter>.withoutDefaultValue(vararg values: String) = filter {
    when {
        values.isEmpty() -> !it.hasDefaultValue()
        else -> values.none { value -> it.hasDefaultValue(value) }
    }
}

fun Sequence<KoParameter>.withType(vararg types: String) = filter {
    types.any { type -> it.hasType(type) }
}

fun Sequence<KoParameter>.withoutType(vararg types: String) = filter {
    types.none { type -> it.hasType(type) }
}

inline fun <reified T> Sequence<KoParameter>.withTypeOf() = filter { it.hasType<T>() }

inline fun <reified T> Sequence<KoParameter>.withoutTypeOf() = filterNot { it.hasType<T>() }
