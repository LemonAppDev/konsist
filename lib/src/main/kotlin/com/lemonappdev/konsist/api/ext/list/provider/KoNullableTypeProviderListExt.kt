package com.lemonappdev.konsist.api.ext.list.provider

import com.lemonappdev.konsist.api.declaration.type.KoTypeDeclaration
import com.lemonappdev.konsist.api.provider.KoNullableTypeProvider
import kotlin.reflect.KClass

/**
 * List containing type declarations.
 */
val <T : KoNullableTypeProvider> List<T>.types: List<KoTypeDeclaration>
    get() = mapNotNull { it.type }

/**
 * List containing declarations with the specified type.
 *
 * @param predicate The predicate function to determine if a declaration type satisfies a condition.
 * @return A list containing declarations with the specified type (or any type if [predicate] is null).
 */
fun <T : KoNullableTypeProvider> List<T>.withType(predicate: ((KoTypeDeclaration) -> Boolean)? = null): List<T> =
    filter {
        when (predicate) {
            null -> it.hasType()
            else -> it.type?.let { type -> predicate(type) } ?: false
        }
    }

/**
 * List containing declarations without the specified type.
 *
 * @param predicate The predicate function to determine if a declaration type satisfies a condition.
 * @return A list containing declarations without the specified type (or none type if [predicate] is null).
 */
fun <T : KoNullableTypeProvider> List<T>.withoutType(predicate: ((KoTypeDeclaration) -> Boolean)? = null): List<T> =
    filterNot {
        when (predicate) {
            null -> it.hasType()
            else -> it.type?.let { type -> predicate(type) } ?: false
        }
    }

/**
 * List containing declarations with type of.
 *
 * @param kClass The Kotlin class representing the type to include.
 * @param kClasses The Kotlin class(es) representing the type(s) to include.
 * @return A list containing declarations with the type of the specified Kotlin class(es).
 */
fun <T : KoNullableTypeProvider> List<T>.withTypeOf(
    kClass: KClass<*>,
    vararg kClasses: KClass<*>,
): List<T> = withTypeOf(listOf(kClass, *kClasses))

/**
 * List containing declarations with type of.
 *
 * @param kClasses The Kotlin class(es) representing the type(s) to include.
 * @return A list containing declarations with the type of the specified Kotlin class(es).
 */
fun <T : KoNullableTypeProvider> List<T>.withTypeOf(kClasses: Collection<KClass<*>>): List<T> =
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
fun <T : KoNullableTypeProvider> List<T>.withoutTypeOf(
    kClass: KClass<*>,
    vararg kClasses: KClass<*>,
): List<T> = withoutTypeOf(listOf(kClass, *kClasses))

/**
 * List containing declarations without type of.
 *
 * @param kClasses The Kotlin class(es) representing the type(s) to exclude.
 * @return A list containing declarations without type of the specified Kotlin class(es).
 */
fun <T : KoNullableTypeProvider> List<T>.withoutTypeOf(kClasses: Collection<KClass<*>>): List<T> =
    filterNot {
        when {
            kClasses.isEmpty() -> true
            else -> kClasses.any { kClass -> it.hasTypeOf(kClass) }
        }
    }
