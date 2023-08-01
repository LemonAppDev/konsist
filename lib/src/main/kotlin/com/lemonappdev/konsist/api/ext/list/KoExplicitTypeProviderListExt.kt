package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.provider.KoExplicitTypeProvider
import kotlin.reflect.KClass

/**
 * List containing declarations with explicit type.
 *
 * @param types The type(s) to include.
 * @return A list containing declarations with the specified type (or any type if [types] is empty).
 */
fun <T : KoExplicitTypeProvider> List<T>.withExplicitType(vararg types: String): List<T> = filter {
    when {
        types.isEmpty() -> it.hasExplicitType()
        else -> types.any { type -> it.hasExplicitType(type) }
    }
}

/**
 * List containing declarations without explicit type.
 *
 * @param types The type(s) to exclude.
 * @return A list containing declarations without specified type (or none type if [types] is empty).
 */
fun <T : KoExplicitTypeProvider> List<T>.withoutExplicitType(vararg types: String): List<T> = filter {
    when {
        types.isEmpty() -> !it.hasExplicitType()
        else -> types.none { type -> it.hasExplicitType(type) }
    }
}

/**
 * List containing declarations with explicit type of.
 *
 * @param types The Kotlin class(es) representing the type(s) to include.
 * @return A list containing declarations with the type of the specified Kotlin class(es).
 */
fun <T : KoExplicitTypeProvider> List<T>.withExplicitTypeOf(vararg types: KClass<*>): List<T> = filter {
    types.any { kClass -> it.explicitType?.name == kClass.simpleName }
}

/**
 * List containing declarations without explicit type of.
 *
 * @param types The Kotlin class(es) representing the type(s) to exclude.
 * @return A list containing declarations without type of the specified Kotlin class(es).
 */
fun <T : KoExplicitTypeProvider> List<T>.withoutExplicitTypeOf(vararg types: KClass<*>): List<T> = filter {
    types.none { kClass -> it.explicitType?.name == kClass.simpleName }
}

/**
 * List containing declarations with explicit type of.
 *
 * @return A list containing declarations with the specified type.
 */
inline fun <reified T> List<KoExplicitTypeProvider>.withExplicitTypeOf(): List<KoExplicitTypeProvider> =
    withExplicitTypeOf(T::class)

/**
 * List containing declarations without explicit type of.
 *
 * @return A list containing declarations without specified type.
 */
inline fun <reified T> List<KoExplicitTypeProvider>.withoutExplicitTypeOf(): List<KoExplicitTypeProvider> =
    withoutExplicitTypeOf(T::class)
