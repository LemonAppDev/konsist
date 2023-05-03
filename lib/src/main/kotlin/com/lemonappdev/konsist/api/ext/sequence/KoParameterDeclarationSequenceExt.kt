package com.lemonappdev.konsist.api.ext.sequence

import com.lemonappdev.konsist.api.declaration.KoParameterDeclaration
import com.lemonappdev.konsist.api.ext.declaration.hasTypeOf
import kotlin.reflect.KClass

fun Sequence<KoParameterDeclaration>.withVarargModifier() = filter { it.hasVarargModifier() }

fun Sequence<KoParameterDeclaration>.withoutVarargModifier() = filterNot { it.hasVarargModifier() }

fun Sequence<KoParameterDeclaration>.withNoInlineModifier() = filter { it.hasNoInlineModifier() }

fun Sequence<KoParameterDeclaration>.withoutNoInlineModifier() = filterNot { it.hasNoInlineModifier() }

fun Sequence<KoParameterDeclaration>.withCrossInlineModifier() = filter { it.hasCrossInlineModifier() }

fun Sequence<KoParameterDeclaration>.withoutCrossInlineModifier() = filterNot { it.hasCrossInlineModifier() }

fun Sequence<KoParameterDeclaration>.withDefaultValue(vararg values: String) = filter {
    when {
        values.isEmpty() -> it.hasDefaultValue()
        else -> values.any { value -> it.hasDefaultValue(value) }
    }
}

fun Sequence<KoParameterDeclaration>.withoutDefaultValue(vararg values: String) = filter {
    when {
        values.isEmpty() -> !it.hasDefaultValue()
        else -> values.none { value -> it.hasDefaultValue(value) }
    }
}

fun Sequence<KoParameterDeclaration>.withType(vararg types: String) = filter {
    types.any { type -> it.hasType(type) }
}

fun Sequence<KoParameterDeclaration>.withoutType(vararg types: String) = filter {
    types.none { type -> it.hasType(type) }
}

fun Sequence<KoParameterDeclaration>.withTypeOf(vararg types: KClass<*>) = filter {
    types.any { kClass ->
        kClass
            .simpleName
            ?.let { name -> it.hasType(name) } ?: false
    }
}

fun Sequence<KoParameterDeclaration>.withoutTypeOf(vararg types: KClass<*>) = filter {
    types.none { kClass ->
        kClass
            .simpleName
            ?.let { name -> it.hasType(name) } ?: false
    }
}

inline fun <reified T> Sequence<KoParameterDeclaration>.withTypeOf() = filter { it.hasTypeOf<T>() }

inline fun <reified T> Sequence<KoParameterDeclaration>.withoutTypeOf() = filterNot { it.hasTypeOf<T>() }
