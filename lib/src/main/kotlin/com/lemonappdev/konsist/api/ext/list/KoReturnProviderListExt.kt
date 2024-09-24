package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.declaration.type.KoTypeDeclaration
import com.lemonappdev.konsist.api.provider.KoReturnProvider
import kotlin.reflect.KClass

/**
 * List containing return type declarations.
 */
val <T : KoReturnProvider> List<T>.returnTypes: List<KoTypeDeclaration>
    get() = mapNotNull { it.returnType }

/**
 * List containing declarations with non-Unit return values, which may or may not have explicitly defined return types.
 *
 * @return A list containing declarations with the return value other than `Unit`.
 */
fun <T : KoReturnProvider> List<T>.withReturnValue(): List<T> = filter { it.hasReturnValue }

/**
 * List containing declarations with the `Unit` return value, which may or may not have explicitly defined return types.
 *
 * @return A list containing declarations with the `Unit` return value.
 */
fun <T : KoReturnProvider> List<T>.withoutReturnValue(): List<T> = filterNot { it.hasReturnValue }

/**
 * List containing declarations with the specified return type.
 *
 * @param predicate The predicate function to determine if a declaration return type satisfies a condition.
 * @return A list containing declarations with the specified return type (or any return type if [predicate] is null).
 */
fun <T : KoReturnProvider> List<T>.withReturnType(predicate: ((KoTypeDeclaration) -> Boolean)? = null): List<T> =
    filter {
        when (predicate) {
            null -> it.hasReturnType()
            else -> it.returnType?.let { returnType -> predicate(returnType) } ?: false
        }
    }

/**
 * List containing declarations without the specified return type.
 *
 * @param predicate The predicate function to determine if a declaration return type satisfies a condition.
 * @return A list containing declarations without the specified return type (or none return type if [predicate] is null).
 */
fun <T : KoReturnProvider> List<T>.withoutReturnType(predicate: ((KoTypeDeclaration) -> Boolean)? = null): List<T> =
    filterNot {
        when (predicate) {
            null -> it.hasReturnType()
            else -> it.returnType?.let { returnType -> predicate(returnType) } ?: false
        }
    }

/**
 * List containing declarations with return type.
 *
 * @param kClass The Kotlin class representing the return type to include.
 * @param kClasses The Kotlin class(es) representing the return type(s) to include.
 * @return A list containing declarations with the return type of the specified Kotlin class(es).
 */
fun <T : KoReturnProvider> List<T>.withReturnTypeOf(
    kClass: KClass<*>,
    vararg kClasses: KClass<*>,
): List<T> = withReturnTypeOf(listOf(kClass, *kClasses))

/**
 * List containing declarations with return type.
 *
 * @param kClasses The Kotlin class(es) representing the return type(s) to include.
 * @return A list containing declarations with the return type of the specified Kotlin class(es).
 */
fun <T : KoReturnProvider> List<T>.withReturnTypeOf(kClasses: Collection<KClass<*>>): List<T> =
    filter {
        when {
            kClasses.isEmpty() -> it.hasReturnType()
            else -> kClasses.any { kClass -> it.hasReturnTypeOf(kClass) }
        }
    }

/**
 * List containing declarations without return type.
 *
 * @param kClass The Kotlin class representing the return type to exclude.
 * @param kClasses The Kotlin class(es) representing the return type(s) to exclude.
 * @return A list containing declarations without return type of the specified Kotlin class(es).
 */
fun <T : KoReturnProvider> List<T>.withoutReturnTypeOf(
    kClass: KClass<*>,
    vararg kClasses: KClass<*>,
): List<T> = withoutReturnTypeOf(listOf(kClass, *kClasses))

/**
 * List containing declarations without return type.
 *
 * @param kClasses The Kotlin class(es) representing the return type(s) to exclude.
 * @return A list containing declarations without return type of the specified Kotlin class(es).
 */
fun <T : KoReturnProvider> List<T>.withoutReturnTypeOf(kClasses: Collection<KClass<*>>): List<T> =
    filterNot {
        when {
            kClasses.isEmpty() -> it.hasReturnType()
            else -> kClasses.any { kClass -> it.hasReturnTypeOf(kClass) }
        }
    }
