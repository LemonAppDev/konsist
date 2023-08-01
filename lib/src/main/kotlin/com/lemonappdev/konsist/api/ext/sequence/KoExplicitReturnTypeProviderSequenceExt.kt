package com.lemonappdev.konsist.api.ext.sequence

import com.lemonappdev.konsist.api.provider.KoExplicitReturnTypeProvider
import kotlin.reflect.KClass

/**
 * List containing declarations with explicit return type.
 *
 * @param types The return type(s) to include.
 * @return A sequence containing declarations with the specified return type(s) (or any return type if [types] is empty).
 */
fun <T : KoExplicitReturnTypeProvider> List<T>.withExplicitReturnType(vararg types: String): List<T> = filter {
    when {
        types.isEmpty() -> it.hasExplicitReturnType
        else -> types.any { type -> it.explicitReturnType?.name == type }
    }
}

/**
 * List containing declarations without explicit return type.
 *
 * @param types The return type(s) to exclude.
 * @return A sequence containing declarations without specified return type(s) (or none return type if [types] is empty).
 */
fun <T : KoExplicitReturnTypeProvider> List<T>.withoutExplicitReturnType(vararg types: String): List<T> = filter {
    when {
        types.isEmpty() -> !it.hasExplicitReturnType
        else -> types.none { type -> it.explicitReturnType?.name == type }
    }
}

/**
 * List containing declarations with explicit return type.
 *
 * @return A sequence containing declarations with the return type of the specified type.
 */
inline fun <reified T> List<KoExplicitReturnTypeProvider>.withExplicitReturnTypeOf(): List<KoExplicitReturnTypeProvider> =
    withExplicitReturnTypeOf(T::class)

/**
 * List containing declarations without explicit return type.
 *
 * @return A sequence containing declarations without return type of the specified type.
 */
inline fun <reified T> List<KoExplicitReturnTypeProvider>.withoutExplicitReturnTypeOf(): List<KoExplicitReturnTypeProvider> =
    withoutExplicitReturnTypeOf(T::class)

/**
 * List containing declarations with explicit return type.
 *
 * @param types The Kotlin class(es) representing the return type(s) to include.
 * @return A sequence containing declarations with the return type of the specified Kotlin class(es).
 */
fun <T : KoExplicitReturnTypeProvider> List<T>.withExplicitReturnTypeOf(vararg types: KClass<*>): List<T> = filter {
    types.any { kClass -> it.explicitReturnType?.name == kClass.simpleName }
}

/**
 * List containing declarations without return type.
 *
 * @param types The Kotlin class(es) representing the return type(s) to exclude.
 * @return A sequence containing declarations without return type of the specified Kotlin class(es).
 */
fun <T : KoExplicitReturnTypeProvider> List<T>.withoutExplicitReturnTypeOf(vararg types: KClass<*>): List<T> = filter {
    types.none { kClass -> it.explicitReturnType?.name == kClass.simpleName }
}
