package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.declaration.type.KoTypeDeclaration
import com.lemonappdev.konsist.api.provider.KoNonNullableTypeProvider
import kotlin.reflect.KClass

/**
 * List containing type declarations.
 */
val <T : KoNonNullableTypeProvider> List<T>.types: List<KoTypeDeclaration>
    get() = map { it.type }

/**
 * List containing declarations with the specified type.
 *
 * @param predicate The predicate function to determine if a declaration type satisfies a condition.
 * @return A list containing declarations with the specified type.
 */
fun <T : KoNonNullableTypeProvider> List<T>.withType(predicate: (KoTypeDeclaration) -> Boolean): List<T> = filter { predicate(it.type) }

/**
 * List containing declarations without the specified type.
 *
 * @param predicate The predicate function to determine if a declaration type satisfies a condition.
 * @return A list containing declarations without the specified type.
 */
fun <T : KoNonNullableTypeProvider> List<T>.withoutType(predicate: (KoTypeDeclaration) -> Boolean): List<T> =
    filterNot { predicate(it.type) }

/**
 * List containing declarations with type of.
 *
 * @param kClass The Kotlin class representing the type to include.
 * @param kClasses The Kotlin class(es) representing the type(s) to include.
 * @return A list containing declarations with the type of the specified Kotlin class(es).
 */
fun <T : KoNonNullableTypeProvider> List<T>.withTypeOf(
    kClass: KClass<*>,
    vararg kClasses: KClass<*>,
): List<T> =
    filter {
        it.hasTypeOf(kClass) ||
            if (kClasses.isNotEmpty()) {
                kClasses.any { kClass -> it.hasTypeOf(kClass) }
            } else {
                false
            }
    }

/**
 * List containing declarations with type of.
 *
 * @param kClasses The Kotlin class(es) representing the type(s) to include.
 * @return A list containing declarations with the type of the specified Kotlin class(es).
 */
fun <T : KoNonNullableTypeProvider> List<T>.withTypeOf(kClasses: Collection<KClass<*>>): List<T> =
    filter {
        when {
            kClasses.isEmpty() -> true
            else -> kClasses.any { kClass -> it.hasTypeOf(kClass) }
        }
    }

/**
 * List containing declarations without type of.
 *
 * @param kClass The Kotlin class representing the type to exclude.
 * @param kClasses The Kotlin class(es) representing the type(s) to exclude.
 * @return A list containing declarations without type of the specified Kotlin class(es).
 */
fun <T : KoNonNullableTypeProvider> List<T>.withoutTypeOf(
    kClass: KClass<*>,
    vararg kClasses: KClass<*>,
): List<T> =
    filterNot {
        val hasMatchingType =
            if (kClasses.isNotEmpty()) {
                kClasses.any { kClass -> it.hasTypeOf(kClass) }
            } else {
                false
            }

        it.hasTypeOf(kClass) || hasMatchingType
    }

/**
 * List containing declarations without type of.
 *
 * @param kClasses The Kotlin class(es) representing the type(s) to exclude.
 * @return A list containing declarations without type of the specified Kotlin class(es).
 */
fun <T : KoNonNullableTypeProvider> List<T>.withoutTypeOf(kClasses: Collection<KClass<*>>): List<T> =
    filterNot {
        when {
            kClasses.isEmpty() -> true
            else -> kClasses.any { kClass -> it.hasTypeOf(kClass) }
        }
    }
