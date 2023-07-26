package com.lemonappdev.konsist.api.ext.sequence

import com.lemonappdev.konsist.api.ext.provider.hasReceiverTypeOf
import com.lemonappdev.konsist.api.provider.KoReceiverTypeProvider
import kotlin.reflect.KClass

/**
 * Sequence containing declarations with receiver type.
 *
 * @param types The receiver type(s) to include.
 * @return A sequence containing declarations with the specified receiver type(s) (or any receiver type if [types] is empty).
 */
fun <T : KoReceiverTypeProvider> Sequence<T>.withReceiverType(vararg types: String): Sequence<T> = filter {
    when {
        types.isEmpty() -> it.hasReceiverType()
        else -> types.any { type -> it.hasReceiverType(type) }
    }
}

/**
 * Sequence containing declarations without receiver type.
 *
 * @param types The receiver type(s) to exclude.
 * @return A sequence containing declarations without specified receiver type(s) (or none receiver type if [types] is empty).
 */
fun <T : KoReceiverTypeProvider> Sequence<T>.withoutReceiverType(vararg types: String): Sequence<T> = filter {
    when {
        types.isEmpty() -> !it.hasReceiverType()
        else -> types.none { type -> it.hasReceiverType(type) }
    }
}

/**
 * Sequence containing declarations with receiver type.
 *
 * @return A sequence containing declarations with the receiver type of the specified type.
 */
inline fun <reified T> Sequence<KoReceiverTypeProvider>.withReceiverTypeOf(): Sequence<KoReceiverTypeProvider> =
    filter { it.hasReceiverTypeOf<T>() }

/**
 * Sequence containing declarations without receiver type.
 *
 * @return A sequence containing declarations without receiver type of the specified type.
 */
inline fun <reified T> Sequence<KoReceiverTypeProvider>.withoutReceiverTypeOf(): Sequence<KoReceiverTypeProvider> =
    filterNot { it.hasReceiverTypeOf<T>() }

/**
 * Sequence containing declarations with receiver type.
 *
 * @param types The Kotlin class(es) representing the receiver type(s) to include.
 * @return A sequence containing declarations with the receiver type of the specified Kotlin class(es).
 */
fun <T : KoReceiverTypeProvider> Sequence<T>.withReceiverTypeOf(vararg types: KClass<*>): Sequence<T> = filter {
    types.any { kClass -> it.hasReceiverType(kClass.simpleName) }
}

/**
 * Sequence containing declarations without receiver type.
 *
 * @param types The Kotlin class(es) representing the receiver type(s) to exclude.
 * @return A sequence containing declarations without receiver type of the specified Kotlin class(es).
 */
fun <T : KoReceiverTypeProvider> Sequence<T>.withoutReceiverTypeOf(vararg types: KClass<*>): Sequence<T> = filter {
    types.none { kClass -> it.hasReceiverType(kClass.simpleName) }
}
