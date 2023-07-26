package com.lemonappdev.konsist.api.ext.sequence

import com.lemonappdev.konsist.api.declaration.KoPropertyDeclaration
import com.lemonappdev.konsist.api.provider.KoExplicitTypeProvider
import kotlin.reflect.KClass

/**
 * Sequence containing declarations with explicit type.
 *
 * @param types The type(s) to include.
 * @return A sequence containing declarations with the specified type (or any type if [types] is empty).
 */
fun <T: KoExplicitTypeProvider> Sequence<T>.withExplicitType(vararg types: String): Sequence<T> = filter {
    when {
        types.isEmpty() -> it.hasExplicitType()
        else -> types.any { type -> it.hasExplicitType(type) }
    }
}

/**
 * Sequence containing declarations without explicit type.
 *
 * @param types The type(s) to exclude.
 * @return A sequence containing declarations without specified type (or none type if [types] is empty).
 */
fun <T: KoExplicitTypeProvider> Sequence<T>.withoutExplicitType(vararg types: String): Sequence<T> = filter {
    when {
        types.isEmpty() -> !it.hasExplicitType()
        else -> types.none { type -> it.hasExplicitType(type) }
    }
}

/**
 * Sequence containing declarations with explicit type of.
 *
 * @param types The Kotlin class(es) representing the type(s) to include.
 * @return A sequence containing declarations with the type of the specified Kotlin class(es).
 */
fun <T: KoExplicitTypeProvider> Sequence<T>.withExplicitTypeOf(vararg types: KClass<*>): Sequence<T> = filter {
    types.any { kClass -> it.explicitType?.name == kClass.simpleName }
}

/**
 * Sequence containing declarations without explicit type of.
 *
 * @param types The Kotlin class(es) representing the type(s) to exclude.
 * @return A sequence containing declarations without type of the specified Kotlin class(es).
 */
fun <T: KoExplicitTypeProvider> Sequence<T>.withoutExplicitTypeOf(vararg types: KClass<*>): Sequence<T> = filter {
    types.none { kClass -> it.explicitType?.name == kClass.simpleName }
}

/**
 * Sequence containing declarations with explicit type of.
 *
 * @return A sequence containing declarations with the specified type.
 */
inline fun <reified T> Sequence<KoExplicitTypeProvider>.withExplicitTypeOf(): Sequence<KoExplicitTypeProvider> =
    filter { T::class.simpleName == it.explicitType?.name }

/**
 * Sequence containing declarations without explicit type of.
 *
 * @return A sequence containing declarations without specified type.
 */
inline fun <reified T> Sequence<KoExplicitTypeProvider>.withoutExplicitTypeOf(): Sequence<KoExplicitTypeProvider> =
    filterNot { T::class.simpleName == it.explicitType?.name }
