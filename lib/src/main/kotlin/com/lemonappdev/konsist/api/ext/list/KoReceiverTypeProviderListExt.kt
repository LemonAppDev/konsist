package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.provider.KoReceiverTypeProvider
import kotlin.reflect.KClass

/**
 * List containing declarations with receiver type.
 *
 * @param types The receiver type(s) to include.
 * @return A list containing declarations with the specified receiver type(s) (or any receiver type if [types] is empty).
 */
fun <T : KoReceiverTypeProvider> List<T>.withReceiverType(vararg types: String): List<T> = filter {
    when {
        types.isEmpty() -> it.hasReceiverType()
        else -> types.any { type -> it.hasReceiverType(type) }
    }
}

/**
 * List containing declarations without receiver type.
 *
 * @param types The receiver type(s) to exclude.
 * @return A list containing declarations without specified receiver type(s) (or none receiver type if [types] is empty).
 */
fun <T : KoReceiverTypeProvider> List<T>.withoutReceiverType(vararg types: String): List<T> = filter {
    when {
        types.isEmpty() -> !it.hasReceiverType()
        else -> types.none { type -> it.hasReceiverType(type) }
    }
}

/**
 * List containing declarations with receiver type.
 *
 * @return A list containing declarations with the receiver type of the specified type.
 */
inline fun <reified T> List<KoReceiverTypeProvider>.withReceiverTypeOf(): List<KoReceiverTypeProvider> =
    withReceiverTypeOf(T::class)

/**
 * List containing declarations without receiver type.
 *
 * @return A list containing declarations without receiver type of the specified type.
 */
inline fun <reified T> List<KoReceiverTypeProvider>.withoutReceiverTypeOf(): List<KoReceiverTypeProvider> =
    withoutReceiverTypeOf(T::class)

/**
 * List containing declarations with receiver type.
 *
 * @param types The Kotlin class(es) representing the receiver type(s) to include.
 * @return A list containing declarations with the receiver type of the specified Kotlin class(es).
 */
fun <T : KoReceiverTypeProvider> List<T>.withReceiverTypeOf(vararg types: KClass<*>): List<T> = filter {
    types.any { kClass -> it.hasReceiverType(kClass.simpleName) }
}

/**
 * List containing declarations without receiver type.
 *
 * @param types The Kotlin class(es) representing the receiver type(s) to exclude.
 * @return A list containing declarations without receiver type of the specified Kotlin class(es).
 */
fun <T : KoReceiverTypeProvider> List<T>.withoutReceiverTypeOf(vararg types: KClass<*>): List<T> = filter {
    types.none { kClass -> it.hasReceiverType(kClass.simpleName) }
}
