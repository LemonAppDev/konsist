package com.lemonappdev.konsist.api.ext.sequence

import com.lemonappdev.konsist.api.provider.KoExplicitReturnTypeProvider
import kotlin.reflect.KClass

/**
 * Sequence containing declarations with explicit return type.
 *
 * @param types The return type(s) to include.
 * @return A sequence containing declarations with the specified return type(s) (or any return type if [types] is empty).
 */
fun <T : KoExplicitReturnTypeProvider> Sequence<T>.withExplicitReturnType(vararg types: String): Sequence<T> = filter {
    when {
        types.isEmpty() -> it.hasExplicitReturnType()
        else -> types.any { type -> it.explicitReturnType?.name == type }
    }
}

/**
 * Sequence containing declarations without explicit return type.
 *
 * @param types The return type(s) to exclude.
 * @return A sequence containing declarations without specified return type(s) (or none return type if [types] is empty).
 */
fun <T : KoExplicitReturnTypeProvider> Sequence<T>.withoutExplicitReturnType(vararg types: String): Sequence<T> = filter {
    when {
        types.isEmpty() -> !it.hasExplicitReturnType()
        else -> types.none { type -> it.explicitReturnType?.name == type }
    }
}

/**
 * Sequence containing declarations with explicit return type.
 *
 * @return A sequence containing declarations with the return type of the specified type.
 */
inline fun <reified T> Sequence<KoExplicitReturnTypeProvider>.withExplicitReturnTypeOf(): Sequence<KoExplicitReturnTypeProvider> =
    filter { T::class.simpleName == it.explicitReturnType?.name }

/**
 * Sequence containing declarations without explicit return type.
 *
 * @return A sequence containing declarations without return type of the specified type.
 */
inline fun <reified T> Sequence<KoExplicitReturnTypeProvider>.withoutExplicitReturnTypeOf(): Sequence<KoExplicitReturnTypeProvider> =
    filterNot { T::class.simpleName == it.explicitReturnType?.name }

/**
 * Sequence containing declarations with explicit return type.
 *
 * @param types The Kotlin class(es) representing the return type(s) to include.
 * @return A sequence containing declarations with the return type of the specified Kotlin class(es).
 */
fun <T : KoExplicitReturnTypeProvider> Sequence<T>.withExplicitReturnTypeOf(vararg types: KClass<*>): Sequence<T> = filter {
    types.any { kClass -> it.explicitReturnType?.name == kClass.simpleName }
}

/**
 * Sequence containing declarations without return type.
 *
 * @param types The Kotlin class(es) representing the return type(s) to exclude.
 * @return A sequence containing declarations without return type of the specified Kotlin class(es).
 */
fun <T : KoExplicitReturnTypeProvider> Sequence<T>.withoutExplicitReturnTypeOf(vararg types: KClass<*>): Sequence<T> = filter {
    types.none { kClass -> it.explicitReturnType?.name == kClass.simpleName }
}
