@file:Suppress("detekt.TooManyFunctions")

package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.declaration.type.KoTypeDeclaration
import com.lemonappdev.konsist.api.provider.KoGenericTypeDeclarationProvider
import kotlin.reflect.KClass

/**
 * Returns a list containing generic type declarations.
 */
val <T : KoGenericTypeDeclarationProvider> List<T>.genericTypes: List<KoTypeDeclaration>
    get() = mapNotNull { it.genericType }

/**
 * Returns a list containing type argument declarations.
 */
val <T : KoGenericTypeDeclarationProvider> List<T>.typeArguments: List<KoTypeDeclaration>
    get() = flatMap { it.typeArguments }

/**
 * Returns a list containing flattened type argument declarations.
 */
val <T : KoGenericTypeDeclarationProvider> List<T>.typeArgumentsFlatten: List<KoTypeDeclaration>
    get() = flatMap { it.typeArgumentsFlatten }

/**
 * Filters the list to include only elements with a generic type satisfying the given predicate.
 *
 * @param predicate A function that defines the condition to be met by the generic type.
 * @return A list containing elements with a generic type satisfying the predicate.
 */
fun <T : KoGenericTypeDeclarationProvider> List<T>.withGenericType(predicate: (KoTypeDeclaration) -> Boolean): List<T> =
    filter { predicate(it.genericType) }

/**
 * Filters the list to exclude elements with a generic type satisfying the given predicate.
 *
 * @param predicate A function that defines the condition to be met by the generic type.
 * @return A list excluding elements with a generic type satisfying the predicate.
 */
fun <T : KoGenericTypeDeclarationProvider> List<T>.withoutGenericType(predicate: (KoTypeDeclaration) -> Boolean): List<T> =
    filterNot { predicate(it.genericType) }

/**
 * Filters the list to include only elements with a generic type matching any of the specified KClass types.
 *
 * @param kClass The primary KClass to match the generic type.
 * @param kClasses Additional KClass types to match the generic type.
 * @return A list containing elements with a generic type matching the specified KClasses.
 */
fun <T : KoGenericTypeDeclarationProvider> List<T>.withGenericTypeOf(
    kClass: KClass<*>,
    vararg kClasses: KClass<*>,
): List<T> = withGenericTypeOf(listOf(kClass, *kClasses))

/**
 * Filters the list to include only elements with a generic type matching any of the specified KClass types.
 *
 * @param kClasses A collection of KClass types to match the generic type.
 * @return A list containing elements with a generic type matching the specified KClasses.
 */
fun <T : KoGenericTypeDeclarationProvider> List<T>.withGenericTypeOf(kClasses: Collection<KClass<*>>): List<T> =
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
fun <T : KoGenericTypeDeclarationProvider> List<T>.withoutGenericTypeOf(
    kClass: KClass<*>,
    vararg kClasses: KClass<*>,
): List<T> = withoutGenericTypeOf(listOf(kClass, *kClasses))

/**
 * Filters the list to exclude elements with a generic type matching any of the specified KClass types.
 *
 * @param kClasses A collection of KClass types to match the generic type.
 * @return A list excluding elements with a generic type matching the specified KClasses.
 */
fun <T : KoGenericTypeDeclarationProvider> List<T>.withoutGenericTypeOf(kClasses: Collection<KClass<*>>): List<T> =
    filterNot {
        when {
            kClasses.isEmpty() -> true
            else -> kClasses.any { kClass -> it.hasGenericTypeOf(kClass) }
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
fun <T : KoGenericTypeDeclarationProvider> List<T>.withTypeArgument(predicate: (KoTypeDeclaration) -> Boolean): List<T> =
    filter {
        it.hasTypeArgument(predicate)
    }

/**
 * List containing declarations that not have type argument satisfying the provided predicate.
 *
 * @param predicate A function that defines the condition to be met by a type argument declaration.
 * @return A list containing declarations without type argument satisfying the provided predicate.
 */
fun <T : KoGenericTypeDeclarationProvider> List<T>.withoutTypeArgument(predicate: (KoTypeDeclaration) -> Boolean): List<T> =
    filterNot { it.hasTypeArgument(predicate) }

/**
 * List containing declarations that have all type arguments satisfying the provided predicate.
 *
 * @param predicate A function that defines the condition to be met by all type argument declarations.
 * @return A filtered list containing declarations with all type arguments satisfying the predicate.
 */
fun <T : KoGenericTypeDeclarationProvider> List<T>.withAllTypeArguments(predicate: (KoTypeDeclaration) -> Boolean): List<T> =
    filter {
        it.hasAllTypeArguments(predicate)
    }

/**
 * List containing declarations that have at least one type argument not satisfying the provided predicate.
 *
 * @param predicate A function that defines the condition to be met by all type argument declarations.
 * @return A list containing declarations that have at least one type argument not satisfying the provided predicate.
 */
fun <T : KoGenericTypeDeclarationProvider> List<T>.withoutAllTypeArguments(predicate: (KoTypeDeclaration) -> Boolean): List<T> =
    filterNot { it.hasAllTypeArguments(predicate) }

/**
 * List containing declarations with type argument declarations satisfying the predicate.
 *
 * @param predicate A function that defines the condition to be met by the list of type argument declarations.
 * @return A list containing declarations with type argument declarations satisfying the predicate.
 */
fun <T : KoGenericTypeDeclarationProvider> List<T>.withTypeArguments(predicate: (List<KoTypeDeclaration>) -> Boolean): List<T> =
    filter { predicate(it.typeArguments) }

/**
 * List containing declarations without type argument declarations satisfying the predicate.
 *
 * @param predicate A function that defines the condition to be met by the list of type argument declarations.
 * @return A list containing declarations without type argument declarations satisfying the predicate.
 */
fun <T : KoGenericTypeDeclarationProvider> List<T>.withoutTypeArguments(predicate: (List<KoTypeDeclaration>) -> Boolean): List<T> =
    filterNot { predicate(it.typeArguments) }

/**
 * List containing declarations that have at least one type argument flatten with the specified name(s).
 *
 * @param name The name of the type argument flatten to include.
 * @param names The names of additional type arguments flatten to include.
 * @return A list containing declarations with at least one of the specified type argument flatten(s).
 */
fun <T : KoGenericTypeDeclarationProvider> List<T>.withTypeArgumentFlattenNamed(
    name: String,
    vararg names: String,
): List<T> = withTypeArgumentFlattenNamed(listOf(name, *names))

/**
 * List containing declarations that have at least one type argument flatten with the specified name(s).
 *
 * @param names The names of additional type arguments flatten to include.
 * @return A list containing declarations with at least one of the specified type argument flatten(s).
 */
fun <T : KoGenericTypeDeclarationProvider> List<T>.withTypeArgumentFlattenNamed(names: Collection<String>): List<T> =
    filter {
        when {
            names.isEmpty() -> true
            else -> it.hasTypeArgumentFlattenWithName(names)
        }
    }

/**
 * List containing declarations without any of specified type arguments flatten.
 *
 * @param name The name of the type argument flatten to exclude.
 * @param names The names of additional type arguments flatten to exclude.
 * @return A list containing declarations without any of specified type arguments flatten.
 */
fun <T : KoGenericTypeDeclarationProvider> List<T>.withoutTypeArgumentFlattenNamed(
    name: String,
    vararg names: String,
): List<T> = withoutTypeArgumentFlattenNamed(listOf(name, *names))

/**
 * List containing declarations without any of specified type arguments flatten.
 *
 * @param names The names of additional type arguments flatten to exclude.
 * @return A list containing declarations without any of specified type arguments flatten.
 */
fun <T : KoGenericTypeDeclarationProvider> List<T>.withoutTypeArgumentFlattenNamed(names: Collection<String>): List<T> =
    filterNot {
        when {
            names.isEmpty() -> true
            else -> it.hasTypeArgumentFlattenWithName(names)
        }
    }

/**
 * List containing declarations that have all specified type arguments flatten.
 *
 * @param name The name of the type argument flatten to include.
 * @param names The name(s) of the type argument flatten(s) to include.
 * @return A list containing declarations with all specified type argument flatten(s).
 */
fun <T : KoGenericTypeDeclarationProvider> List<T>.withAllTypeArgumentsFlattenNamed(
    name: String,
    vararg names: String,
): List<T> = withAllTypeArgumentsFlattenNamed(listOf(name, *names))

/**
 * List containing declarations that have all specified type arguments flatten.
 *
 * @param names The name(s) of the type argument flatten(s) to include.
 * @return A list containing declarations with all specified type argument flatten(s).
 */
fun <T : KoGenericTypeDeclarationProvider> List<T>.withAllTypeArgumentsFlattenNamed(names: Collection<String>): List<T> =
    filter {
        when {
            names.isEmpty() -> true
            else -> it.hasTypeArgumentsFlattenWithAllNames(names)
        }
    }

/**
 * List containing declarations without all specified type arguments flatten.
 *
 * @param name The name of the type argument flatten to exclude.
 * @param names The name(s) of the type argument flatten(s) to exclude.
 * @return A list containing declarations without all specified type argument flatten(s).
 */
fun <T : KoGenericTypeDeclarationProvider> List<T>.withoutAllTypeArgumentsFlattenNamed(
    name: String,
    vararg names: String,
): List<T> = withoutAllTypeArgumentsFlattenNamed(listOf(name, *names))

/**
 * List containing declarations without all specified type arguments flatten.
 *
 * @param names The name(s) of the type argument flatten(s) to exclude.
 * @return A list containing declarations without all specified type argument flatten(s).
 */
fun <T : KoGenericTypeDeclarationProvider> List<T>.withoutAllTypeArgumentsFlattenNamed(names: Collection<String>): List<T> =
    filterNot {
        when {
            names.isEmpty() -> true
            else -> it.hasTypeArgumentsFlattenWithAllNames(names)
        }
    }

/**
 * List containing declarations that have at least one type argument flatten satisfying the provided predicate.
 *
 * @param predicate A function that defines the condition to be met by a type argument flatten declaration.
 * @return A list containing declarations with at least one type argument flatten satisfying the predicate.
 */
fun <T : KoGenericTypeDeclarationProvider> List<T>.withTypeArgumentFlatten(predicate: (KoTypeDeclaration) -> Boolean): List<T> =
    filter {
        it.hasTypeArgumentFlatten(predicate)
    }

/**
 * List containing declarations that not have type argument flatten satisfying the provided predicate.
 *
 * @param predicate A function that defines the condition to be met by a type argument flatten declaration.
 * @return A list containing declarations without type argument flatten satisfying the provided predicate.
 */
fun <T : KoGenericTypeDeclarationProvider> List<T>.withoutTypeArgumentFlatten(predicate: (KoTypeDeclaration) -> Boolean): List<T> =
    filterNot { it.hasTypeArgumentFlatten(predicate) }

/**
 * List containing declarations that have all type arguments flatten satisfying the provided predicate.
 *
 * @param predicate A function that defines the condition to be met by all type argument flatten declarations.
 * @return A filtered list containing declarations with all type arguments flatten satisfying the predicate.
 */
fun <T : KoGenericTypeDeclarationProvider> List<T>.withAllTypeArgumentsFlatten(predicate: (KoTypeDeclaration) -> Boolean): List<T> =
    filter {
        it.hasAllTypeArgumentsFlatten(predicate)
    }

/**
 * List containing declarations that have at least one type argument flatten not satisfying the provided predicate.
 *
 * @param predicate A function that defines the condition to be met by all type argument flatten declarations.
 * @return A list containing declarations that have at least one type argument flatten not satisfying the provided predicate.
 */
fun <T : KoGenericTypeDeclarationProvider> List<T>.withoutAllTypeArgumentsFlatten(predicate: (KoTypeDeclaration) -> Boolean): List<T> =
    filterNot { it.hasAllTypeArgumentsFlatten(predicate) }

/**
 * List containing declarations with type argument flatten declarations satisfying the predicate.
 *
 * @param predicate A function that defines the condition to be met by the list of type argument flatten declarations.
 * @return A list containing declarations with type argument flatten declarations satisfying the predicate.
 */
fun <T : KoGenericTypeDeclarationProvider> List<T>.withTypeArgumentsFlatten(predicate: (List<KoTypeDeclaration>) -> Boolean): List<T> =
    filter { predicate(it.typeArgumentsFlatten) }

/**
 * List containing declarations without type argument flatten declarations satisfying the predicate.
 *
 * @param predicate A function that defines the condition to be met by the list of type argument flatten declarations.
 * @return A list containing declarations without type argument flatten declarations satisfying the predicate.
 */
fun <T : KoGenericTypeDeclarationProvider> List<T>.withoutTypeArgumentsFlatten(predicate: (List<KoTypeDeclaration>) -> Boolean): List<T> =
    filterNot { predicate(it.typeArgumentsFlatten) }
