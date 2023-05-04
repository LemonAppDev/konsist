package com.lemonappdev.konsist.api.ext.sequence

import com.lemonappdev.konsist.api.declaration.KoParameterDeclaration
import com.lemonappdev.konsist.api.ext.declaration.representsTypeOf
import kotlin.reflect.KClass

/**
 * Sequence containing all declarations that have 'vararg' modifier.
 */
fun Sequence<KoParameterDeclaration>.withVarargModifier() = filter { it.hasVarargModifier() }

/**
 * Sequence containing all declarations that don't have 'vararg' modifier.
 */
fun Sequence<KoParameterDeclaration>.withoutVarargModifier() = filterNot { it.hasVarargModifier() }

/**
 * Sequence containing all declarations that have 'noinline' modifier.
 */
fun Sequence<KoParameterDeclaration>.withNoInlineModifier() = filter { it.hasNoInlineModifier() }

/**
 * Sequence containing all declarations that don't have 'vararg' modifier.
 */
fun Sequence<KoParameterDeclaration>.withoutNoInlineModifier() = filterNot { it.hasNoInlineModifier() }

/**
 * Sequence containing all declarations that have 'crossinline' modifier.
 */
fun Sequence<KoParameterDeclaration>.withCrossInlineModifier() = filter { it.hasCrossInlineModifier() }

/**
 * Sequence containing all declarations that don't have 'vararg' modifier.
 */
fun Sequence<KoParameterDeclaration>.withoutCrossInlineModifier() = filterNot { it.hasCrossInlineModifier() }

/**
 * Sequence containing all declarations that have default value.
 */
fun Sequence<KoParameterDeclaration>.withDefaultValue(vararg values: String) = filter {
    when {
        values.isEmpty() -> it.hasDefaultValue()
        else -> values.any { value -> it.hasDefaultValue(value) }
    }
}

/**
 * Sequence containing all declarations that don't have default value.
 */
fun Sequence<KoParameterDeclaration>.withoutDefaultValue(vararg values: String) = filter {
    when {
        values.isEmpty() -> !it.hasDefaultValue()
        else -> values.none { value -> it.hasDefaultValue(value) }
    }
}

/**
 * Sequence containing all declarations that have type.
 */
fun Sequence<KoParameterDeclaration>.withType(vararg types: String) = filter {
    types.any { type -> it.representsType(type) }
}

/**
 * Sequence containing all declarations that don't have type.
 */
fun Sequence<KoParameterDeclaration>.withoutType(vararg types: String) = filter {
    types.none { type -> it.representsType(type) }
}

/**
 * Sequence containing all declarations that have type of.
 */
fun Sequence<KoParameterDeclaration>.withTypeOf(vararg types: KClass<*>) = filter {
    types.any { kClass ->
        kClass
            .simpleName
            ?.let { name -> it.representsType(name) } ?: false
    }
}

/**
 * Sequence containing all declarations that don't have type of.
 */
fun Sequence<KoParameterDeclaration>.withoutTypeOf(vararg types: KClass<*>) = filter {
    types.none { kClass ->
        kClass
            .simpleName
            ?.let { name -> it.representsType(name) } ?: false
    }
}

/**
 * Sequence containing all declarations that have type of.
 */
inline fun <reified T> Sequence<KoParameterDeclaration>.withTypeOf() = filter { it.representsTypeOf<T>() }

/**
 * Sequence containing all declarations that don't have type of.
 */
inline fun <reified T> Sequence<KoParameterDeclaration>.withoutTypeOf() = filterNot { it.representsTypeOf<T>() }
