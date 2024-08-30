package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.declaration.type.KoTypeDeclaration
import com.lemonappdev.konsist.api.provider.KoReceiverTypeProvider
import kotlin.reflect.KClass

/**
 * List containing receiver type declarations.
 */
val <T : KoReceiverTypeProvider> List<T>.receiverTypes: List<KoTypeDeclaration>
    get() = mapNotNull { it.receiverType }

/**
 * List containing declarations with receiver type.
 *
 * @param names The receiver type name(s) to include.
 * @return A list containing declarations with the specified receiver type(s) (or any receiver type if [names] is empty).
 */
@Deprecated("Will be removed in v0.16.0", ReplaceWith("withReceiverType { it.name == ... }"))
fun <T : KoReceiverTypeProvider> List<T>.withReceiverType(vararg names: String): List<T> =
    filter {
        when {
            names.isEmpty() -> it.hasReceiverType()
            else -> names.any { type -> it.hasReceiverType(type) }
        }
    }

/**
 * List containing declarations without receiver type.
 *
 * @param names The receiver type name(s) to exclude.
 * @return A list containing declarations without specified receiver type(s) (or none receiver type if [names] is empty).
 */
@Deprecated("Will be removed in v0.16.0", ReplaceWith("withoutReceiverType { it.name != ... }"))
fun <T : KoReceiverTypeProvider> List<T>.withoutReceiverType(vararg names: String): List<T> =
    filter {
        when {
            names.isEmpty() -> !it.hasReceiverType()
            else -> names.none { type -> it.hasReceiverType(type) }
        }
    }

/**
 * List containing declarations with the specified receiver type.
 *
 * @param predicate The predicate function to determine if a declaration receiver type satisfies a condition.
 * @return A list containing declarations with the specified receiver type (or any receiver type if [predicate] is null).
 */
fun <T : KoReceiverTypeProvider> List<T>.withReceiverType(predicate: ((KoTypeDeclaration) -> Boolean)? = null): List<T> =
    filter {
        when (predicate) {
            null -> it.hasReceiverType()
            else -> it.receiverType?.let { receiverType -> predicate(receiverType) } ?: false
        }
    }

/**
 * List containing declarations without the specified receiver type.
 *
 * @param predicate The predicate function to determine if a declaration receiver type satisfies a condition.
 * @return A list containing declarations without the specified receiver type (or none receiver type if [predicate] is null).
 */
fun <T : KoReceiverTypeProvider> List<T>.withoutReceiverType(predicate: ((KoTypeDeclaration) -> Boolean)? = null): List<T> =
    filterNot {
        when (predicate) {
            null -> it.hasReceiverType()
            else -> it.receiverType?.let { receiverType -> predicate(receiverType) } ?: false
        }
    }

/**
 * List containing declarations with receiver type.
 *
 * @param kClass The Kotlin class representing the receiver type to include.
 * @param kClasses The Kotlin class(es) representing the receiver type(s) to include.
 * @return A list containing declarations with the receiver type of the specified Kotlin class(es).
 */
fun <T : KoReceiverTypeProvider> List<T>.withReceiverTypeOf(
    kClass: KClass<*>,
    vararg kClasses: KClass<*>,
): List<T> = withReceiverTypeOf(listOf(kClass, *kClasses))

/**
 * List containing declarations with receiver type.
 *
 * @param kClasses The Kotlin class(es) representing the receiver type(s) to include.
 * @return A list containing declarations with the receiver type of the specified Kotlin class(es).
 */
fun <T : KoReceiverTypeProvider> List<T>.withReceiverTypeOf(kClasses: Collection<KClass<*>>): List<T> =
    filter {
        when {
            kClasses.isEmpty() -> it.hasReceiverType()
            else -> kClasses.any { kClass -> it.hasReceiverTypeOf(kClass) }
        }
    }

/**
 * List containing declarations without receiver type.
 *
 * @param kClass The Kotlin class representing the receiver type to exclude.
 * @param kClasses The Kotlin class(es) representing the receiver type(s) to exclude.
 * @return A list containing declarations without receiver type of the specified Kotlin class(es).
 */
fun <T : KoReceiverTypeProvider> List<T>.withoutReceiverTypeOf(
    kClass: KClass<*>,
    vararg kClasses: KClass<*>,
): List<T> = withoutReceiverTypeOf(listOf(kClass, *kClasses))

/**
 * List containing declarations without receiver type.
 *
 * @param kClasses The Kotlin class(es) representing the receiver type(s) to exclude.
 * @return A list containing declarations without receiver type of the specified Kotlin class(es).
 */
fun <T : KoReceiverTypeProvider> List<T>.withoutReceiverTypeOf(kClasses: Collection<KClass<*>>): List<T> =
    filterNot {
        when {
            kClasses.isEmpty() -> it.hasReceiverType()
            else -> kClasses.any { kClass -> it.hasReceiverTypeOf(kClass) }
        }
    }
