package com.lemonappdev.konsist.api.ext.sequence

import com.lemonappdev.konsist.api.declaration.KoParameterDeclaration
import com.lemonappdev.konsist.api.ext.declaration.representsTypeOf
import kotlin.reflect.KClass

/**
 * Sequence containing all declarations that have 'vararg' modifier.
 *
 * @return A sequence containing parameter declarations with the 'vararg' modifier.
 */
fun Sequence<KoParameterDeclaration>.withVarargModifier(): Sequence<KoParameterDeclaration> = filter { it.hasVarargModifier() }

/**
 * Sequence containing all declarations that don't have 'vararg' modifier.
 *
 * @return A sequence containing parameter declarations without the 'vararg' modifier.
 */
fun Sequence<KoParameterDeclaration>.withoutVarargModifier(): Sequence<KoParameterDeclaration> = filterNot { it.hasVarargModifier() }

/**
 * Sequence containing all declarations that have 'noinline' modifier.
 *
 * @return A sequence containing parameter declarations with the 'noinline' modifier.
 */
fun Sequence<KoParameterDeclaration>.withNoInlineModifier(): Sequence<KoParameterDeclaration> = filter { it.hasNoInlineModifier() }

/**
 * Sequence containing all declarations that don't have 'vararg' modifier.
 *
 * @return A sequence containing parameter declarations without the 'noinline' modifier.
 */
fun Sequence<KoParameterDeclaration>.withoutNoInlineModifier(): Sequence<KoParameterDeclaration> = filterNot { it.hasNoInlineModifier() }

/**
 * Sequence containing all declarations that have 'crossinline' modifier.
 *
 * @return A sequence containing parameter declarations with the 'crossinline' modifier.
 */
fun Sequence<KoParameterDeclaration>.withCrossInlineModifier(): Sequence<KoParameterDeclaration> = filter { it.hasCrossInlineModifier() }

/**
 * Sequence containing all declarations that don't have 'vararg' modifier.
 *
 * @return A sequence containing parameter declarations without the 'crossinline' modifier.
 */
fun Sequence<KoParameterDeclaration>.withoutCrossInlineModifier(): Sequence<KoParameterDeclaration> =
    filterNot { it.hasCrossInlineModifier() }

/**
 * Sequence containing all declarations that have default value.
 *
 * @param values The default values to include. If empty, all declarations with default values are included.
 * @return A sequence containing parameter declarations with the specified default values.
 */
fun Sequence<KoParameterDeclaration>.withDefaultValue(vararg values: String): Sequence<KoParameterDeclaration> = filter {
    when {
        values.isEmpty() -> it.hasDefaultValue()
        else -> values.any { value -> it.hasDefaultValue(value) }
    }
}

/**
 * Sequence containing all declarations that don't have default value.
 *
 * @param values The default values to exclude. If empty, all declarations without default values are included.
 * @return A sequence containing parameter declarations without the specified default values.
 */
fun Sequence<KoParameterDeclaration>.withoutDefaultValue(vararg values: String): Sequence<KoParameterDeclaration> = filter {
    when {
        values.isEmpty() -> !it.hasDefaultValue()
        else -> values.none { value -> it.hasDefaultValue(value) }
    }
}

/**
 * Sequence containing all declarations that have type.
 *
 * @param types The types to include.
 * @return A sequence containing parameter declarations with the specified types.
 */
fun Sequence<KoParameterDeclaration>.withType(vararg types: String): Sequence<KoParameterDeclaration> = filter {
    types.any { type -> it.representsType(type) }
}

/**
 * Sequence containing all declarations that don't have type.
 *
 * @param types The types to exclude.
 * @return A sequence containing parameter declarations without the specified types.
 */
fun Sequence<KoParameterDeclaration>.withoutType(vararg types: String): Sequence<KoParameterDeclaration> = filter {
    types.none { type -> it.representsType(type) }
}

/**
 * Sequence containing all declarations that have type of.
 *
 * @param types The Kotlin classes representing the types to include.
 * @return A sequence containing parameter declarations with types matching the specified Kotlin classes.
 */
fun Sequence<KoParameterDeclaration>.withTypeOf(vararg types: KClass<*>): Sequence<KoParameterDeclaration> = filter {
    types.any { kClass ->
        kClass
            .simpleName
            ?.let { name -> it.representsType(name) } ?: false
    }
}

/**
 * Sequence containing all declarations that don't have type of.
 *
 * @param types The Kotlin classes representing the types to exclude.
 * @return A sequence containing parameter declarations without types matching the specified Kotlin classes.
 */
fun Sequence<KoParameterDeclaration>.withoutTypeOf(vararg types: KClass<*>): Sequence<KoParameterDeclaration> = filter {
    types.none { kClass ->
        kClass
            .simpleName
            ?.let { name -> it.representsType(name) } ?: false
    }
}

/**
 * Sequence containing all declarations that have type of.
 *
 * @return A sequence containing parameter declarations with types matching the specified reified type parameter.
 */
inline fun <reified T> Sequence<KoParameterDeclaration>.withTypeOf(): Sequence<KoParameterDeclaration> = filter { it.representsTypeOf<T>() }

/**
 * Sequence containing all declarations that don't have type of.
 *
 * @return A sequence containing parameter declarations without types matching the specified reified type parameter.
 */
inline fun <reified T> Sequence<KoParameterDeclaration>.withoutTypeOf(): Sequence<KoParameterDeclaration> =
    filterNot { it.representsTypeOf<T>() }
