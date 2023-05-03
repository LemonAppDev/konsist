package com.lemonappdev.konsist.api.ext

import com.lemonappdev.konsist.core.declaration.KoParameterDeclarationImpl
import kotlin.reflect.KClass

fun Sequence<KoParameterDeclarationImpl>.withVarargModifier() = filter { it.hasVarargModifier() }

fun Sequence<KoParameterDeclarationImpl>.withoutVarargModifier() = filterNot { it.hasVarargModifier() }

fun Sequence<KoParameterDeclarationImpl>.withNoInlineModifier() = filter { it.hasNoInlineModifier() }

fun Sequence<KoParameterDeclarationImpl>.withoutNoInlineModifier() = filterNot { it.hasNoInlineModifier() }

fun Sequence<KoParameterDeclarationImpl>.withCrossInlineModifier() = filter { it.hasCrossInlineModifier() }

fun Sequence<KoParameterDeclarationImpl>.withoutCrossInlineModifier() = filterNot { it.hasCrossInlineModifier() }

fun Sequence<KoParameterDeclarationImpl>.withDefaultValue(vararg values: String) = filter {
    when {
        values.isEmpty() -> it.hasDefaultValue()
        else -> values.any { value -> it.hasDefaultValue(value) }
    }
}

fun Sequence<KoParameterDeclarationImpl>.withoutDefaultValue(vararg values: String) = filter {
    when {
        values.isEmpty() -> !it.hasDefaultValue()
        else -> values.none { value -> it.hasDefaultValue(value) }
    }
}

fun Sequence<KoParameterDeclarationImpl>.withType(vararg types: String) = filter {
    types.any { type -> it.hasType(type) }
}

fun Sequence<KoParameterDeclarationImpl>.withoutType(vararg types: String) = filter {
    types.none { type -> it.hasType(type) }
}

fun Sequence<KoParameterDeclarationImpl>.withTypeOf(vararg types: KClass<*>) = filter {
    types.any { kClass ->
        kClass
            .simpleName
            ?.let { name -> it.hasType(name) } ?: false
    }
}

fun Sequence<KoParameterDeclarationImpl>.withoutTypeOf(vararg types: KClass<*>) = filter {
    types.none { kClass ->
        kClass
            .simpleName
            ?.let { name -> it.hasType(name) } ?: false
    }
}

inline fun <reified T> Sequence<KoParameterDeclarationImpl>.withTypeOf() = filter { it.hasTypeOf<T>() }

inline fun <reified T> Sequence<KoParameterDeclarationImpl>.withoutTypeOf() = filterNot { it.hasTypeOf<T>() }
