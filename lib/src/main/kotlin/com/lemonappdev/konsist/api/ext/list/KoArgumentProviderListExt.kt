package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.declaration.KoArgumentDeclaration
import com.lemonappdev.konsist.api.provider.KoArgumentProvider

/**
 * List containing argument declarations.
 */
val <T : KoArgumentProvider> List<T>.arguments: List<KoArgumentDeclaration>
    get() = flatMap { it.arguments }

/**
 * List containing elements with any argument.
 *
 * @return A list containing elements with any argument.
 */
fun <T : KoArgumentProvider> List<T>.withArguments(): List<T> = filter { it.hasArguments() }

/**
 * List containing declarations whose all arguments match the given predicate.
 *
 * @param predicate The predicate function to determine if a declaration argument satisfies a condition.
 * @return A list containing declarations with the specified argument(s).
 */
fun <T : KoArgumentProvider> List<T>.withAllArguments(predicate: (KoArgumentDeclaration) -> Boolean): List<T> = filter {
    it.hasAllArguments(predicate)
}

/**
 * List containing declarations whose at least one argument matches the given predicate.
 *
 * @param predicate The predicate function to determine if a declaration argument satisfies a condition.
 * @return A list containing declarations with the specified argument(s).
 */
fun <T : KoArgumentProvider> List<T>.withArgument(predicate: (KoArgumentDeclaration) -> Boolean): List<T> = filter {
    it.hasArgument(predicate)
}

/**
 * List containing elements with no argument.
 *
 * @return A list containing elements with no argument.
 */
fun <T : KoArgumentProvider> List<T>.withoutArguments(): List<T> = filterNot { it.hasArguments() }

/**
 * List containing declarations whose all arguments do not match the predicate.
 *
 * @param predicate The predicate function to determine if a declaration argument satisfies a condition.
 * @return A list containing declarations without the specified argument(s).
 */
fun <T : KoArgumentProvider> List<T>.withoutAllArguments(predicate: (KoArgumentDeclaration) -> Boolean): List<T> = filterNot {
    it.hasAllArguments(predicate)
}

/**
 * List containing declarations whose at least one argument does not match the predicate.
 *
 * @param predicate The predicate function to determine if a declaration argument satisfies a condition.
 * @return A list containing declarations with the specified argument(s).
 */
fun <T : KoArgumentProvider> List<T>.withoutArgument(predicate: (KoArgumentDeclaration) -> Boolean): List<T> = filterNot {
    it.hasArgument(predicate)
}
