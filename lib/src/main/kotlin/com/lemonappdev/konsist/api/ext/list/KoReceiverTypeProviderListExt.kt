package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.declaration.KoTypeDeclaration
import com.lemonappdev.konsist.api.provider.KoReceiverTypeProvider
import kotlin.reflect.KClass

/**
 * List containing receiver type declarations.
 */
val <T : KoReceiverTypeProvider> List<T>.receiverTypes: List<KoTypeDeclaration>
    get() = mapNotNull { it.receiverType }

/**
 * List containing elements with receiver type.
 *
 * @param names The receiver type name(s) to include.
 * @return A list containing elements with the specified receiver type(s) (or any receiver type if [names] is empty).
 */
fun <T : KoReceiverTypeProvider> List<T>.withReceiverType(vararg names: String): List<T> = filter {
    when {
        names.isEmpty() -> it.hasReceiverType()
        else -> names.any { type -> it.hasReceiverType(type) }
    }
}

/**
 * List containing elements without receiver type.
 *
 * @param names The receiver type name(s) to exclude.
 * @return A list containing elements without specified receiver type(s) (or none receiver type if [names] is empty).
 */
fun <T : KoReceiverTypeProvider> List<T>.withoutReceiverType(vararg names: String): List<T> = filter {
    when {
        names.isEmpty() -> !it.hasReceiverType()
        else -> names.none { type -> it.hasReceiverType(type) }
    }
}

/**
 * List containing elements with receiver type.
 *
 * @param kClass The Kotlin class representing the receiver type to include.
 * @param kClasses The Kotlin class(es) representing the receiver type(s) to include.
 * @return A list containing elements with the receiver type of the specified Kotlin class(es).
 */
fun <T : KoReceiverTypeProvider> List<T>.withReceiverTypeOf(kClass: KClass<*>, vararg kClasses: KClass<*>): List<T> =
    filter {
        it.hasReceiverType(kClass.simpleName) ||
            if (kClasses.isNotEmpty()) {
                kClasses.any { kClass -> it.hasReceiverType(kClass.simpleName) }
            } else {
                false
            }
    }

/**
 * List containing elements without receiver type.
 *
 * @param kClass The Kotlin class representing the receiver type to exclude.
 * @param kClasses The Kotlin class(es) representing the receiver type(s) to exclude.
 * @return A list containing elements without receiver type of the specified Kotlin class(es).
 */
fun <T : KoReceiverTypeProvider> List<T>.withoutReceiverTypeOf(kClass: KClass<*>, vararg kClasses: KClass<*>): List<T> =
    filter {
        !it.hasReceiverType(kClass.simpleName) &&
            if (kClasses.isNotEmpty()) {
                kClasses.none { kClass -> it.hasReceiverType(kClass.simpleName) }
            } else {
                true
            }
    }
