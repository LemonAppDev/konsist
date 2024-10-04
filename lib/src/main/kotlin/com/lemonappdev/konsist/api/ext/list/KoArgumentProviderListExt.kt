package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.declaration.KoArgumentDeclaration
import com.lemonappdev.konsist.api.provider.KoArgumentProvider

/**
 * List containing argument declarations.
 */
val <T : KoArgumentProvider> List<T>.arguments: List<KoArgumentDeclaration>
    get() = flatMap { it.arguments }

/**
 * List containing declarations with any argument.
 *
 * @return A list containing declarations with any argument.
 */
fun <T : KoArgumentProvider> List<T>.withArguments(): List<T> = filter { it.hasArguments() }

/**
 * List containing declarations with no arguments.
 *
 * @return A list containing declarations with no arguments.
 */
fun <T : KoArgumentProvider> List<T>.withoutArguments(): List<T> = filterNot { it.hasArguments() }

/**
 * List containing declarations that have at least one argument with the specified name(s).
 *
 * @param name The name of the argument to include.
 * @param names The names of additional arguments to include.
 * @return A list containing declarations with at least one of the specified argument(s).
 */
fun <T : KoArgumentProvider> List<T>.withArgumentNamed(
    name: String,
    vararg names: String,
): List<T> = withArgumentNamed(listOf(name, *names))

/**
 * List containing declarations that have at least one argument with the specified name(s).
 *
 * @param names The names of additional arguments to include.
 * @return A list containing declarations with at least one of the specified argument(s).
 */
fun <T : KoArgumentProvider> List<T>.withArgumentNamed(names: Collection<String>): List<T> =
    filter {
        when {
            names.isEmpty() -> it.hasArguments()
            else -> it.hasArgumentWithName(names)
        }
    }

/**
 * List containing declarations without any of specified arguments.
 *
 * @param name The name of the argument to exclude.
 * @param names The names of additional arguments to exclude.
 * @return A list containing declarations without any of specified arguments.
 */
fun <T : KoArgumentProvider> List<T>.withoutArgumentNamed(
    name: String,
    vararg names: String,
): List<T> = withoutArgumentNamed(listOf(name, *names))

/**
 * List containing declarations without any of specified arguments.
 *
 * @param names The names of additional arguments to exclude.
 * @return A list containing declarations without any of specified arguments.
 */
fun <T : KoArgumentProvider> List<T>.withoutArgumentNamed(names: Collection<String>): List<T> =
    filterNot {
        when {
            names.isEmpty() -> it.hasArguments()
            else -> it.hasArgumentWithName(names)
        }
    }

/**
 * List containing declarations that have all specified arguments.
 *
 * @param name The name of the argument to include.
 * @param names The name(s) of the argument(s) to include.
 * @return A list containing declarations with all specified argument(s).
 */
fun <T : KoArgumentProvider> List<T>.withAllArgumentsNamed(
    name: String,
    vararg names: String,
): List<T> = withAllArgumentsNamed(listOf(name, *names))

/**
 * List containing declarations that have all specified arguments.
 *
 * @param names The name(s) of the argument(s) to include.
 * @return A list containing declarations with all specified argument(s).
 */
fun <T : KoArgumentProvider> List<T>.withAllArgumentsNamed(names: Collection<String>): List<T> =
    filter {
        when {
            names.isEmpty() -> it.hasArguments()
            else -> it.hasArgumentsWithAllNames(names)
        }
    }

/**
 * List containing declarations without all specified arguments.
 *
 * @param name The name of the argument to exclude.
 * @param names The name(s) of the argument(s) to exclude.
 * @return A list containing declarations without all specified argument(s).
 */
fun <T : KoArgumentProvider> List<T>.withoutAllArgumentsNamed(
    name: String,
    vararg names: String,
): List<T> = withoutAllArgumentsNamed(listOf(name, *names))

/**
 * List containing declarations without all specified arguments.
 *
 * @param names The name(s) of the argument(s) to exclude.
 * @return A list containing declarations without all specified argument(s).
 */
fun <T : KoArgumentProvider> List<T>.withoutAllArgumentsNamed(names: Collection<String>): List<T> =
    filterNot {
        when {
            names.isEmpty() -> it.hasArguments()
            else -> it.hasArgumentsWithAllNames(names)
        }
    }

/**
 * List containing declarations that have at least one argument satisfying the provided predicate.
 *
 * @param predicate A function that defines the condition to be met by an argument declaration.
 * @return A list containing declarations with at least one argument satisfying the predicate.
 */
fun <T : KoArgumentProvider> List<T>.withArgument(predicate: (KoArgumentDeclaration) -> Boolean): List<T> =
    filter {
        it.hasArgument(predicate)
    }

/**
 * List containing declarations that not have argument satisfying the provided predicate.
 *
 * @param predicate A function that defines the condition to be met by an argument declaration.
 * @return A list containing declarations without argument satisfying the provided predicate.
 */
fun <T : KoArgumentProvider> List<T>.withoutArgument(predicate: (KoArgumentDeclaration) -> Boolean): List<T> =
    filterNot { it.hasArgument(predicate) }

/**
 * List containing declarations that have all arguments satisfying the provided predicate.
 *
 * @param predicate A function that defines the condition to be met by all argument declarations.
 * @return A filtered list containing declarations with all arguments satisfying the predicate.
 */
fun <T : KoArgumentProvider> List<T>.withAllArguments(predicate: (KoArgumentDeclaration) -> Boolean): List<T> =
    filter {
        it.hasAllArguments(predicate)
    }

/**
 * List containing declarations that have at least one argument not satisfying the provided predicate.
 *
 * @param predicate A function that defines the condition to be met by all argument declarations.
 * @return A list containing declarations that have at least one argument not satisfying the provided predicate.
 */
fun <T : KoArgumentProvider> List<T>.withoutAllArguments(predicate: (KoArgumentDeclaration) -> Boolean): List<T> =
    filterNot { it.hasAllArguments(predicate) }

/**
 * List containing declarations with argument declarations satisfying the predicate.
 *
 * @param predicate A function that defines the condition to be met by the list of argument declarations.
 * @return A list containing declarations with argument declarations satisfying the predicate.
 */
fun <T : KoArgumentProvider> List<T>.withArguments(predicate: (List<KoArgumentDeclaration>) -> Boolean): List<T> =
    filter { predicate(it.arguments) }

/**
 * List containing declarations without argument declarations satisfying the predicate.
 *
 * @param predicate A function that defines the condition to be met by the list of argument declarations.
 * @return A list containing declarations without argument declarations satisfying the predicate.
 */
fun <T : KoArgumentProvider> List<T>.withoutArguments(predicate: (List<KoArgumentDeclaration>) -> Boolean): List<T> =
    filterNot { predicate(it.arguments) }
