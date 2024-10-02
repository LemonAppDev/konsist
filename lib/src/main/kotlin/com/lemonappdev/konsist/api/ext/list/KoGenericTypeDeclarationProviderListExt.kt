@file:Suppress("detekt.TooManyFunctions")

package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.declaration.KoTypeArgumentDeclaration
import com.lemonappdev.konsist.api.declaration.type.KoTypeDeclaration
import com.lemonappdev.konsist.api.provider.KoGenericTypeDeclarationProvider
import kotlin.reflect.KClass

/**
 * Returns a list containing generic type declarations.
 */
val <T : KoGenericTypeDeclarationProvider> List<T>.types: List<KoTypeDeclaration>
    get() = mapNotNull { it.type }

/**
 * Returns a list containing type argument declarations.
 */
val <T : KoGenericTypeDeclarationProvider> List<T>.typeArguments: List<KoTypeArgumentDeclaration>
    get() = flatMap { it.typeArguments }

/**
 * Filters the list to include only elements with a generic type satisfying the given predicate.
 *
 * @param predicate A function that defines the condition to be met by the generic type.
 * @return A list containing elements with a generic type satisfying the predicate.
 */
fun <T : KoGenericTypeDeclarationProvider> List<T>.withType(predicate: (KoTypeDeclaration) -> Boolean): List<T> =
    filter { predicate(it.type) }

/**
 * Filters the list to exclude elements with a generic type satisfying the given predicate.
 *
 * @param predicate A function that defines the condition to be met by the generic type.
 * @return A list excluding elements with a generic type satisfying the predicate.
 */
fun <T : KoGenericTypeDeclarationProvider> List<T>.withoutType(predicate: (KoTypeDeclaration) -> Boolean): List<T> =
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

/**
 * List containing declarations that have at least one type argument with the specified name(s).
 *
 * @param name The name of the type argument to include.
 * @param names The names of additional type arguments to include.
 * @return A list containing declarations with at least one of the specified type argument(s).
 */
fun <T : KoGenericTypeDeclarationProvider> List<T>.withTypeArgumentNamed(
    name: String,
    vararg names: String,
): List<T> = withTypeArgumentNamed(listOf(name, *names))

/**
 * List containing declarations that have at least one type argument with the specified name(s).
 *
 * @param names The names of additional type arguments to include.
 * @return A list containing declarations with at least one of the specified type argument(s).
 */
fun <T : KoGenericTypeDeclarationProvider> List<T>.withTypeArgumentNamed(names: Collection<String>): List<T> =
    filter {
        when {
            names.isEmpty() -> true
            else -> it.hasTypeArgumentWithName(names)
        }
    }

/**
 * List containing declarations without any of specified type arguments.
 *
 * @param name The name of the type argument to exclude.
 * @param names The names of additional type arguments to exclude.
 * @return A list containing declarations without any of specified type arguments.
 */
fun <T : KoGenericTypeDeclarationProvider> List<T>.withoutTypeArgumentNamed(
    name: String,
    vararg names: String,
): List<T> = withoutTypeArgumentNamed(listOf(name, *names))

/**
 * List containing declarations without any of specified type arguments.
 *
 * @param names The names of additional type arguments to exclude.
 * @return A list containing declarations without any of specified type arguments.
 */
fun <T : KoGenericTypeDeclarationProvider> List<T>.withoutTypeArgumentNamed(names: Collection<String>): List<T> =
    filterNot {
        when {
            names.isEmpty() -> true
            else -> it.hasTypeArgumentWithName(names)
        }
    }

/**
 * List containing declarations that have all specified type arguments.
 *
 * @param name The name of the type argument to include.
 * @param names The name(s) of the type argument(s) to include.
 * @return A list containing declarations with all specified type argument(s).
 */
fun <T : KoGenericTypeDeclarationProvider> List<T>.withAllTypeArgumentsNamed(
    name: String,
    vararg names: String,
): List<T> = withAllTypeArgumentsNamed(listOf(name, *names))

/**
 * List containing declarations that have all specified type arguments.
 *
 * @param names The name(s) of the type argument(s) to include.
 * @return A list containing declarations with all specified type argument(s).
 */
fun <T : KoGenericTypeDeclarationProvider> List<T>.withAllTypeArgumentsNamed(names: Collection<String>): List<T> =
    filter {
        when {
            names.isEmpty() -> true
            else -> it.hasTypeArgumentsWithAllNames(names)
        }
    }

/**
 * List containing declarations without all specified type arguments.
 *
 * @param name The name of the type argument to exclude.
 * @param names The name(s) of the type argument(s) to exclude.
 * @return A list containing declarations without all specified type argument(s).
 */
fun <T : KoGenericTypeDeclarationProvider> List<T>.withoutAllTypeArgumentsNamed(
    name: String,
    vararg names: String,
): List<T> = withoutAllTypeArgumentsNamed(listOf(name, *names))

/**
 * List containing declarations without all specified type arguments.
 *
 * @param names The name(s) of the type argument(s) to exclude.
 * @return A list containing declarations without all specified type argument(s).
 */
fun <T : KoGenericTypeDeclarationProvider> List<T>.withoutAllTypeArgumentsNamed(names: Collection<String>): List<T> =
    filterNot {
        when {
            names.isEmpty() -> true
            else -> it.hasTypeArgumentsWithAllNames(names)
        }
    }

/**
 * List containing declarations that have at least one type argument satisfying the provided predicate.
 *
 * @param predicate A function that defines the condition to be met by a type argument declaration.
 * @return A list containing declarations with at least one type argument satisfying the predicate.
 */
fun <T : KoGenericTypeDeclarationProvider> List<T>.withTypeArgument(predicate: (KoTypeArgumentDeclaration) -> Boolean): List<T> =
    filter {
        it.hasTypeArgument(predicate)
    }

/**
 * List containing declarations that not have type argument satisfying the provided predicate.
 *
 * @param predicate A function that defines the condition to be met by a type argument declaration.
 * @return A list containing declarations without type argument satisfying the provided predicate.
 */
fun <T : KoGenericTypeDeclarationProvider> List<T>.withoutTypeArgument(predicate: (KoTypeArgumentDeclaration) -> Boolean): List<T> =
    filterNot { it.hasTypeArgument(predicate) }

/**
 * List containing declarations that have all type arguments satisfying the provided predicate.
 *
 * @param predicate A function that defines the condition to be met by all type argument declarations.
 * @return A filtered list containing declarations with all type arguments satisfying the predicate.
 */
fun <T : KoGenericTypeDeclarationProvider> List<T>.withAllTypeArguments(predicate: (KoTypeArgumentDeclaration) -> Boolean): List<T> =
    filter {
        it.hasAllTypeArguments(predicate)
    }

/**
 * List containing declarations that have at least one type argument not satisfying the provided predicate.
 *
 * @param predicate A function that defines the condition to be met by all type argument declarations.
 * @return A list containing declarations that have at least one type argument not satisfying the provided predicate.
 */
fun <T : KoGenericTypeDeclarationProvider> List<T>.withoutAllTypeArguments(predicate: (KoTypeArgumentDeclaration) -> Boolean): List<T> =
    filterNot { it.hasAllTypeArguments(predicate) }

/**
 * List containing declarations with type argument declarations satisfying the predicate.
 *
 * @param predicate A function that defines the condition to be met by the list of type argument declarations.
 * @return A list containing declarations with type argument declarations satisfying the predicate.
 */
fun <T : KoGenericTypeDeclarationProvider> List<T>.withTypeArguments(predicate: (List<KoTypeArgumentDeclaration>) -> Boolean): List<T> =
    filter { predicate(it.typeArguments) }

/**
 * List containing declarations without type argument declarations satisfying the predicate.
 *
 * @param predicate A function that defines the condition to be met by the list of type argument declarations.
 * @return A list containing declarations without type argument declarations satisfying the predicate.
 */
fun <T : KoGenericTypeDeclarationProvider> List<T>.withoutTypeArguments(predicate: (List<KoTypeArgumentDeclaration>) -> Boolean): List<T> =
    filterNot { predicate(it.typeArguments) }
