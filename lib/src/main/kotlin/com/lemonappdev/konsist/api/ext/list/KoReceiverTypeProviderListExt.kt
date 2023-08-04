package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.provider.KoReceiverTypeProvider
import kotlin.reflect.KClass

/**
 * List containing elements with receiver type.
 *
 * @param types The receiver type(s) to include.
 * @return A list containing elements with the specified receiver type(s) (or any receiver type if [types] is empty).
 */
fun <T : KoReceiverTypeProvider> List<T>.withReceiverType(vararg types: String): List<T> = filter {
    when {
        types.isEmpty() -> it.hasReceiverType()
        else -> types.any { type -> it.hasReceiverType(type) }
    }
}

/**
 * List containing elements without receiver type.
 *
 * @param types The receiver type(s) to exclude.
 * @return A list containing elements without specified receiver type(s) (or none receiver type if [types] is empty).
 */
fun <T : KoReceiverTypeProvider> List<T>.withoutReceiverType(vararg types: String): List<T> = filter {
    when {
        types.isEmpty() -> !it.hasReceiverType()
        else -> types.none { type -> it.hasReceiverType(type) }
    }
}

/**
 * List containing elements with receiver type.
 *
 * @param type The Kotlin class representing the receiver type to include.
 * @param types The Kotlin class(es) representing the receiver type(s) to include.
 * @return A list containing elements with the receiver type of the specified Kotlin class(es).
 */
fun <T : KoReceiverTypeProvider> List<T>.withReceiverTypeOf(type: KClass<*>, vararg types: KClass<*>): List<T> =
    filter {
        it.hasReceiverType(type.simpleName) ||
                if (types.isNotEmpty()) {
                    types.any { kClass -> it.hasReceiverType(kClass.simpleName) }
                } else {
                    false
                }
    }

/**
 * List containing elements without receiver type.
 *
 * @param type The Kotlin class representing the receiver type to exclude.
 * @param types The Kotlin class(es) representing the receiver type(s) to exclude.
 * @return A list containing elements without receiver type of the specified Kotlin class(es).
 */
fun <T : KoReceiverTypeProvider> List<T>.withoutReceiverTypeOf(type: KClass<*>, vararg types: KClass<*>): List<T> =
    filter {
        !it.hasReceiverType(type.simpleName) &&
                if (types.isNotEmpty()) {
                    types.none { kClass -> it.hasReceiverType(kClass.simpleName) }
                } else {
                    true
                }
    }
