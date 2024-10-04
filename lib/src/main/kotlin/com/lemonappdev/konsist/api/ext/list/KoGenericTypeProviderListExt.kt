@file:Suppress("detekt.TooManyFunctions")

package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.declaration.type.KoBaseTypeDeclaration
import com.lemonappdev.konsist.api.provider.KoGenericTypeProvider
import kotlin.reflect.KClass

/**
 * Returns a list containing generic type declarations.
 */
val <T : KoGenericTypeProvider> List<T>.genericTypes: List<KoBaseTypeDeclaration>
    get() = mapNotNull { it.genericType }

/**
 * Filters the list to include only elements with a generic type satisfying the given predicate.
 *
 * @param predicate A function that defines the condition to be met by the generic type.
 * @return A list containing elements with a generic type satisfying the predicate.
 */
fun <T : KoGenericTypeProvider> List<T>.withGenericType(predicate: (KoBaseTypeDeclaration) -> Boolean): List<T> =
    filter { predicate(it.genericType) }

/**
 * Filters the list to exclude elements with a generic type satisfying the given predicate.
 *
 * @param predicate A function that defines the condition to be met by the generic type.
 * @return A list excluding elements with a generic type satisfying the predicate.
 */
fun <T : KoGenericTypeProvider> List<T>.withoutGenericType(predicate: (KoBaseTypeDeclaration) -> Boolean): List<T> =
    filterNot { predicate(it.genericType) }

/**
 * Filters the list to include only elements with a generic type matching any of the specified KClass types.
 *
 * @param kClass The primary KClass to match the generic type.
 * @param kClasses Additional KClass types to match the generic type.
 * @return A list containing elements with a generic type matching the specified KClasses.
 */
fun <T : KoGenericTypeProvider> List<T>.withGenericTypeOf(
    kClass: KClass<*>,
    vararg kClasses: KClass<*>,
): List<T> = withGenericTypeOf(listOf(kClass, *kClasses))

/**
 * Filters the list to include only elements with a generic type matching any of the specified KClass types.
 *
 * @param kClasses A collection of KClass types to match the generic type.
 * @return A list containing elements with a generic type matching the specified KClasses.
 */
fun <T : KoGenericTypeProvider> List<T>.withGenericTypeOf(kClasses: Collection<KClass<*>>): List<T> =
    filter {
        when {
            kClasses.isEmpty() -> true
            else -> kClasses.any { kClass -> it.hasGenericTypeOf(kClass) }
        }
    }

/**
 * Filters the list to exclude elements with a generic type matching any of the specified KClass types.
 *
 * @param kClass The primary KClass to match the generic type.
 * @param kClasses Additional KClass types to match the generic type.
 * @return A list excluding elements with a generic type matching the specified KClasses.
 */
fun <T : KoGenericTypeProvider> List<T>.withoutGenericTypeOf(
    kClass: KClass<*>,
    vararg kClasses: KClass<*>,
): List<T> = withoutGenericTypeOf(listOf(kClass, *kClasses))

/**
 * Filters the list to exclude elements with a generic type matching any of the specified KClass types.
 *
 * @param kClasses A collection of KClass types to match the generic type.
 * @return A list excluding elements with a generic type matching the specified KClasses.
 */
fun <T : KoGenericTypeProvider> List<T>.withoutGenericTypeOf(kClasses: Collection<KClass<*>>): List<T> =
    filterNot {
        when {
            kClasses.isEmpty() -> true
            else -> kClasses.any { kClass -> it.hasGenericTypeOf(kClass) }
        }
    }
