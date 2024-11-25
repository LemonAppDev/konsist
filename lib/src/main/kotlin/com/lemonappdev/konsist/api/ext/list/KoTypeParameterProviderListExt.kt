package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.declaration.KoTypeParameterDeclaration
import com.lemonappdev.konsist.api.provider.KoTypeParameterProvider

/**
 * List containing type parameter declarations.
 */
val <T : KoTypeParameterProvider> List<T>.typeParameters: List<KoTypeParameterDeclaration>
    get() = flatMap { it.typeParameters }

/**
 * List containing declarations with any type parameter.
 *
 * @return A list containing declarations with any type parameter.
 */
fun <T : KoTypeParameterProvider> List<T>.withTypeParameters(): List<T> = filter { it.hasTypeParameters() }

/**
 * List containing declarations with no type parameters.
 *
 * @return A list containing declarations with no type parameters.
 */
fun <T : KoTypeParameterProvider> List<T>.withoutTypeParameters(): List<T> = filterNot { it.hasTypeParameters() }

/**
 * List containing declarations that have at least one type parameter with the specified name(s).
 *
 * @param name The name of the type parameter to include.
 * @param names The names of additional type parameters to include.
 * @return A list containing declarations with at least one of the specified type parameter(s).
 */
fun <T : KoTypeParameterProvider> List<T>.withTypeParameterNamed(
    name: String,
    vararg names: String,
): List<T> = withTypeParameterNamed(listOf(name, *names))

/**
 * List containing declarations that have at least one type parameter with the specified name(s).
 *
 * @param names The names of additional type parameters to include.
 * @return A list containing declarations with at least one of the specified type parameter(s).
 */
fun <T : KoTypeParameterProvider> List<T>.withTypeParameterNamed(names: Collection<String>): List<T> =
    filter {
        when {
            names.isEmpty() -> it.hasTypeParameters()
            else -> it.hasTypeParameterWithName(names)
        }
    }

/**
 * List containing declarations without any of specified type parameters.
 *
 * @param name The name of the type parameter to exclude.
 * @param names The names of additional type parameters to exclude.
 * @return A list containing declarations without any of specified type parameters.
 */
fun <T : KoTypeParameterProvider> List<T>.withoutTypeParameterNamed(
    name: String,
    vararg names: String,
): List<T> = withoutTypeParameterNamed(listOf(name, *names))

/**
 * List containing declarations without any of specified type parameters.
 *
 * @param names The names of additional type parameters to exclude.
 * @return A list containing declarations without any of specified type parameters.
 */
fun <T : KoTypeParameterProvider> List<T>.withoutTypeParameterNamed(names: Collection<String>): List<T> =
    filterNot {
        when {
            names.isEmpty() -> it.hasTypeParameters()
            else -> it.hasTypeParameterWithName(names)
        }
    }

/**
 * List containing declarations that have all specified type parameters.
 *
 * @param name The name of the type parameter to include.
 * @param names The name(s) of the type parameter(s) to include.
 * @return A list containing declarations with all specified type parameter(s).
 */
fun <T : KoTypeParameterProvider> List<T>.withAllTypeParametersNamed(
    name: String,
    vararg names: String,
): List<T> = withAllTypeParametersNamed(listOf(name, *names))

/**
 * List containing declarations that have all specified type parameters.
 *
 * @param names The name(s) of the type parameter(s) to include.
 * @return A list containing declarations with all specified type parameter(s).
 */
fun <T : KoTypeParameterProvider> List<T>.withAllTypeParametersNamed(names: Collection<String>): List<T> =
    filter {
        when {
            names.isEmpty() -> it.hasTypeParameters()
            else -> it.hasTypeParametersWithAllNames(names)
        }
    }

/**
 * List containing declarations without all specified type parameters.
 *
 * @param name The name of the type parameter to exclude.
 * @param names The name(s) of the type parameter(s) to exclude.
 * @return A list containing declarations without all specified type parameter(s).
 */
fun <T : KoTypeParameterProvider> List<T>.withoutAllTypeParametersNamed(
    name: String,
    vararg names: String,
): List<T> = withoutAllTypeParametersNamed(listOf(name, *names))

/**
 * List containing declarations without all specified type parameters.
 *
 * @param names The name(s) of the type parameter(s) to exclude.
 * @return A list containing declarations without all specified type parameter(s).
 */
fun <T : KoTypeParameterProvider> List<T>.withoutAllTypeParametersNamed(names: Collection<String>): List<T> =
    filterNot {
        when {
            names.isEmpty() -> it.hasTypeParameters()
            else -> it.hasTypeParametersWithAllNames(names)
        }
    }

/**
 * List containing declarations that have at least one type parameter satisfying the provided predicate.
 *
 * @param predicate A function that defines the condition to be met by a type parameter declaration.
 * @return A list containing declarations with at least one type parameter satisfying the predicate.
 */
fun <T : KoTypeParameterProvider> List<T>.withTypeParameter(predicate: (KoTypeParameterDeclaration) -> Boolean): List<T> =
    filter {
        it.hasTypeParameter(predicate)
    }

/**
 * List containing declarations that not have type parameter satisfying the provided predicate.
 *
 * @param predicate A function that defines the condition to be met by a type parameter declaration.
 * @return A list containing declarations without type parameter satisfying the provided predicate.
 */
fun <T : KoTypeParameterProvider> List<T>.withoutTypeParameter(predicate: (KoTypeParameterDeclaration) -> Boolean): List<T> =
    filterNot {
        it.hasTypeParameter(predicate)
    }

/**
 * List containing declarations that have all type parameters satisfying the provided predicate.
 *
 * @param predicate A function that defines the condition to be met by all type parameter declarations.
 * @return A filtered list containing declarations with all type parameters satisfying the predicate.
 */
fun <T : KoTypeParameterProvider> List<T>.withAllTypeParameters(predicate: (KoTypeParameterDeclaration) -> Boolean): List<T> =
    filter {
        it.hasAllTypeParameters(predicate)
    }

/**
 * List containing declarations that have at least one type parameter not satisfying the provided predicate.
 *
 * @param predicate A function that defines the condition to be met by all type parameter declarations.
 * @return A list containing declarations that have at least one type parameter not satisfying the provided predicate.
 */
fun <T : KoTypeParameterProvider> List<T>.withoutAllTypeParameters(predicate: (KoTypeParameterDeclaration) -> Boolean): List<T> =
    filterNot {
        it.hasAllTypeParameters(predicate)
    }

/**
 * List containing declarations with type parameter declarations satisfying the predicate.
 *
 * @param predicate A function that defines the condition to be met by the list of type parameter declarations.
 * @return A list containing declarations with type parameter declarations satisfying the predicate.
 */
fun <T : KoTypeParameterProvider> List<T>.withTypeParameters(predicate: (List<KoTypeParameterDeclaration>) -> Boolean): List<T> =
    filter {
        predicate(it.typeParameters)
    }

/**
 * List containing declarations without type parameter declarations satisfying the predicate.
 *
 * @param predicate A function that defines the condition to be met by the list of type parameter declarations.
 * @return A list containing declarations without type parameter declarations satisfying the predicate.
 */
fun <T : KoTypeParameterProvider> List<T>.withoutTypeParameters(predicate: (List<KoTypeParameterDeclaration>) -> Boolean): List<T> =
    filterNot { predicate(it.typeParameters) }
