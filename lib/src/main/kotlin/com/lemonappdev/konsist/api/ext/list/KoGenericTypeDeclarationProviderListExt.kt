@file:Suppress("detekt.TooManyFunctions")

package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.declaration.KoTypeArgumentDeclaration
import com.lemonappdev.konsist.api.declaration.type.KoBaseTypeDeclaration
import com.lemonappdev.konsist.api.declaration.type.KoTypeDeclaration
import com.lemonappdev.konsist.api.provider.KoGenericTypeDeclarationProvider
import kotlin.reflect.KClass

/**
 * Returns a list containing generic type declarations.
 */
val <T : KoGenericTypeDeclarationProvider> List<T>.types: List<KoBaseTypeDeclaration>
    get() = mapNotNull { it.type }

/**
 * Filters the list to include only elements with a generic type satisfying the given predicate.
 *
 * @param predicate A function that defines the condition to be met by the generic type.
 * @return A list containing elements with a generic type satisfying the predicate.
 */
fun <T : KoGenericTypeDeclarationProvider> List<T>.withType(predicate: (KoBaseTypeDeclaration) -> Boolean): List<T> =
    filter { predicate(it.type) }

/**
 * Filters the list to exclude elements with a generic type satisfying the given predicate.
 *
 * @param predicate A function that defines the condition to be met by the generic type.
 * @return A list excluding elements with a generic type satisfying the predicate.
 */
fun <T : KoGenericTypeDeclarationProvider> List<T>.withoutType(predicate: (KoBaseTypeDeclaration) -> Boolean): List<T> =
    filterNot { predicate(it.type) }

/**
 * Filters the list to include only elements with a generic type matching any of the specified KClass types.
 *
 * @param kClass The primary KClass to match the generic type.
 * @param kClasses Additional KClass types to match the generic type.
 * @return A list containing elements with a generic type matching the specified KClasses.
 */
fun <T : KoGenericTypeDeclarationProvider> List<T>.withTypeOf(
    kClass: KClass<*>,
    vararg kClasses: KClass<*>,
): List<T> = withTypeOf(listOf(kClass, *kClasses))

/**
 * Filters the list to include only elements with a generic type matching any of the specified KClass types.
 *
 * @param kClasses A collection of KClass types to match the generic type.
 * @return A list containing elements with a generic type matching the specified KClasses.
 */
fun <T : KoGenericTypeDeclarationProvider> List<T>.withTypeOf(kClasses: Collection<KClass<*>>): List<T> =
    filter {
        when {
            kClasses.isEmpty() -> true
            else -> kClasses.any { kClass -> it.hasTypeOf(kClass) }
        }
    }

/**
 * Filters the list to exclude elements with a generic type matching any of the specified KClass types.
 *
 * @param kClass The primary KClass to match the generic type.
 * @param kClasses Additional KClass types to match the generic type.
 * @return A list excluding elements with a generic type matching the specified KClasses.
 */
fun <T : KoGenericTypeDeclarationProvider> List<T>.withoutTypeOf(
    kClass: KClass<*>,
    vararg kClasses: KClass<*>,
): List<T> = withoutTypeOf(listOf(kClass, *kClasses))

/**
 * Filters the list to exclude elements with a generic type matching any of the specified KClass types.
 *
 * @param kClasses A collection of KClass types to match the generic type.
 * @return A list excluding elements with a generic type matching the specified KClasses.
 */
fun <T : KoGenericTypeDeclarationProvider> List<T>.withoutTypeOf(kClasses: Collection<KClass<*>>): List<T> =
    filterNot {
        when {
            kClasses.isEmpty() -> true
            else -> kClasses.any { kClass -> it.hasTypeOf(kClass) }
        }
    }
