package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.declaration.KoSourceDeclaration
import com.lemonappdev.konsist.api.provider.KoUpperBoundsProvider

/**
 * List containing upper bound declarations.
 */
val <T : KoUpperBoundsProvider> List<T>.upperBounds: List<KoSourceDeclaration>
    get() = flatMap { it.upperBounds }

/**
 * List containing declarations with any upper bound.
 *
 * @return A list containing declarations with any upper bound.
 */
fun <T : KoUpperBoundsProvider> List<T>.withUpperBounds(): List<T> = filter { it.hasUpperBounds() }

/**
 * List containing declarations with no upper bounds.
 *
 * @return A list containing declarations with no upper bounds.
 */
fun <T : KoUpperBoundsProvider> List<T>.withoutUpperBounds(): List<T> = filterNot { it.hasUpperBounds() }

/**
 * List containing declarations that have at least one upper bound with the specified name(s).
 *
 * @param name The name of the upper bound to include.
 * @param names The names of additional upper bounds to include.
 * @return A list containing declarations with at least one of the specified upper bound(s).
 */
fun <T : KoUpperBoundsProvider> List<T>.withUpperBoundNamed(
    name: String,
    vararg names: String,
): List<T> = withUpperBoundNamed(listOf(name, *names))

/**
 * List containing declarations that have at least one upper bound with the specified name(s).
 *
 * @param names The names of additional upper bounds to include.
 * @return A list containing declarations with at least one of the specified upper bound(s).
 */
fun <T : KoUpperBoundsProvider> List<T>.withUpperBoundNamed(names: Collection<String>): List<T> =
    filter {
        when {
            names.isEmpty() -> it.hasUpperBounds()
            else -> it.hasUpperBoundWithName(names)
        }
    }

/**
 * List containing declarations without any of specified upper bounds.
 *
 * @param name The name of the upper bound to exclude.
 * @param names The names of additional upper bounds to exclude.
 * @return A list containing declarations without any of specified upper bounds.
 */
fun <T : KoUpperBoundsProvider> List<T>.withoutUpperBoundNamed(
    name: String,
    vararg names: String,
): List<T> = withoutUpperBoundNamed(listOf(name, *names))

/**
 * List containing declarations without any of specified upper bounds.
 *
 * @param names The names of additional upper bounds to exclude.
 * @return A list containing declarations without any of specified upper bounds.
 */
fun <T : KoUpperBoundsProvider> List<T>.withoutUpperBoundNamed(names: Collection<String>): List<T> =
    filterNot {
        when {
            names.isEmpty() -> it.hasUpperBounds()
            else -> it.hasUpperBoundWithName(names)
        }
    }

/**
 * List containing declarations that have all specified upper bounds.
 *
 * @param name The name of the upper bound to include.
 * @param names The name(s) of the upper bound(s) to include.
 * @return A list containing declarations with all specified upper bound(s).
 */
fun <T : KoUpperBoundsProvider> List<T>.withAllUpperBoundsNamed(
    name: String,
    vararg names: String,
): List<T> = withAllUpperBoundsNamed(listOf(name, *names))

/**
 * List containing declarations that have all specified upper bounds.
 *
 * @param names The name(s) of the upper bound(s) to include.
 * @return A list containing declarations with all specified upper bound(s).
 */
fun <T : KoUpperBoundsProvider> List<T>.withAllUpperBoundsNamed(names: Collection<String>): List<T> =
    filter {
        when {
            names.isEmpty() -> it.hasUpperBounds()
            else -> it.hasUpperBoundsWithAllNames(names)
        }
    }

/**
 * List containing declarations without all specified upper bounds.
 *
 * @param name The name of the upper bound to exclude.
 * @param names The name(s) of the upper bound(s) to exclude.
 * @return A list containing declarations without all specified upper bound(s).
 */
fun <T : KoUpperBoundsProvider> List<T>.withoutAllUpperBoundsNamed(
    name: String,
    vararg names: String,
): List<T> = withoutAllUpperBoundsNamed(listOf(name, *names))

/**
 * List containing declarations without all specified upper bounds.
 *
 * @param names The name(s) of the upper bound(s) to exclude.
 * @return A list containing declarations without all specified upper bound(s).
 */
fun <T : KoUpperBoundsProvider> List<T>.withoutAllUpperBoundsNamed(names: Collection<String>): List<T> =
    filterNot {
        when {
            names.isEmpty() -> it.hasUpperBounds()
            else -> it.hasUpperBoundsWithAllNames(names)
        }
    }

/**
 * List containing declarations that have at least one upper bound satisfying the provided predicate.
 *
 * @param predicate A function that defines the condition to be met by a upper bound declaration.
 * @return A list containing declarations with at least one upper bound satisfying the predicate.
 */
fun <T : KoUpperBoundsProvider> List<T>.withUpperBound(predicate: (KoSourceDeclaration) -> Boolean): List<T> =
    filter {
        it.hasUpperBound(predicate)
    }

/**
 * List containing declarations that not have upper bound satisfying the provided predicate.
 *
 * @param predicate A function that defines the condition to be met by a upper bound declaration.
 * @return A list containing declarations without upper bound satisfying the provided predicate.
 */
fun <T : KoUpperBoundsProvider> List<T>.withoutUpperBound(predicate: (KoSourceDeclaration) -> Boolean): List<T> =
    filterNot {
        it.hasUpperBound(predicate)
    }

/**
 * List containing declarations that have all upper bounds satisfying the provided predicate.
 *
 * @param predicate A function that defines the condition to be met by all upper bound declarations.
 * @return A filtered list containing declarations with all upper bounds satisfying the predicate.
 */
fun <T : KoUpperBoundsProvider> List<T>.withAllUpperBounds(predicate: (KoSourceDeclaration) -> Boolean): List<T> =
    filter {
        it.hasAllUpperBounds(predicate)
    }

/**
 * List containing declarations that have at least one upper bound not satisfying the provided predicate.
 *
 * @param predicate A function that defines the condition to be met by all upper bound declarations.
 * @return A list containing declarations that have at least one upper bound not satisfying the provided predicate.
 */
fun <T : KoUpperBoundsProvider> List<T>.withoutAllUpperBounds(predicate: (KoSourceDeclaration) -> Boolean): List<T> =
    filterNot {
        it.hasAllUpperBounds(predicate)
    }

/**
 * List containing declarations with upper bound declarations satisfying the predicate.
 *
 * @param predicate A function that defines the condition to be met by the list of upper bound declarations.
 * @return A list containing declarations with upper bound declarations satisfying the predicate.
 */
fun <T : KoUpperBoundsProvider> List<T>.withUpperBounds(predicate: (List<KoSourceDeclaration>) -> Boolean): List<T> =
    filter {
        predicate(it.upperBounds)
    }

/**
 * List containing declarations without upper bound declarations satisfying the predicate.
 *
 * @param predicate A function that defines the condition to be met by the list of upper bound declarations.
 * @return A list containing declarations without upper bound declarations satisfying the predicate.
 */
fun <T : KoUpperBoundsProvider> List<T>.withoutUpperBounds(predicate: (List<KoSourceDeclaration>) -> Boolean): List<T> =
    filterNot { predicate(it.upperBounds) }
