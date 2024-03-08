package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.declaration.KoConstructorDeclaration
import com.lemonappdev.konsist.api.provider.KoConstructorProvider

/**
 * List containing constructor declarations.
 */
val <T : KoConstructorProvider> List<T>.constructors: List<KoConstructorDeclaration>
    get() = flatMap { it.constructors }

/**
 * List containing declarations with constructor.
 *
 * @return A list containing declarations with the constructor.
 */
@Deprecated("Will be removed in v0.16.0", ReplaceWith("withConstructors()"))
fun <T : KoConstructorProvider> List<T>.withConstructor(): List<T> = filter { it.hasConstructors() }

/**
 * List containing declarations without constructor.
 *
 * @return A list containing declarations without the constructor.
 */
@Deprecated("Will be removed in v0.16.0", ReplaceWith("withoutConstructors()"))
fun <T : KoConstructorProvider> List<T>.withoutConstructor(): List<T> = filterNot { it.hasConstructors() }

/**
 * List containing declarations with constructor.
 *
 * @return A list containing declarations with the constructor.
 */
fun <T : KoConstructorProvider> List<T>.withConstructors(): List<T> = filter { it.hasConstructors() }

/**
 * List containing declarations without constructor.
 *
 * @return A list containing declarations without the constructor.
 */
fun <T : KoConstructorProvider> List<T>.withoutConstructors(): List<T> = filterNot { it.hasConstructors() }

/**
 * List containing declarations whose at least one constructor matches the given predicate.
 *
 * @param predicate The predicate function to determine if a declaration constructor satisfies a condition.
 * @return A list containing declarations with the specified constructor(s).
 */
fun <T : KoConstructorProvider> List<T>.withConstructor(predicate: (KoConstructorDeclaration) -> Boolean): List<T> =
    filter { it.hasConstructor(predicate) }

/**
 * List containing declarations that not have constructor satisfying the provided predicate.
 *
 * @param predicate A function that defines the condition to be met by n constructor declaration.
 * @return A list containing declarations without constructor satisfying the provided predicate.
 */
fun <T : KoConstructorProvider> List<T>.withoutConstructor(predicate: (KoConstructorDeclaration) -> Boolean): List<T> =
    filterNot { it.hasConstructor(predicate) }

/**
 * List containing declarations whose all constructors match the given predicate.
 *
 * @param predicate The predicate function to determine if a declaration constructor satisfies a condition.
 * @return A list containing declarations with the specified constructor(s).
 */
fun <T : KoConstructorProvider> List<T>.withAllConstructors(predicate: (KoConstructorDeclaration) -> Boolean): List<T> =
    filter { it.hasAllConstructors(predicate) }

/**
 * List containing declarations that have at least one constructor not satisfying the provided predicate.
 *
 * @param predicate A function that defines the condition to be met by all constructor declarations.
 * @return A list containing declarations that have at least one constructor not satisfying the provided predicate.
 */
fun <T : KoConstructorProvider> List<T>.withoutAllConstructors(predicate: (KoConstructorDeclaration) -> Boolean): List<T> =
    filterNot { it.hasAllConstructors(predicate) }

/**
 * List containing declarations with constructor declarations satisfying the predicate.
 *
 * @param predicate A function that defines the condition to be met by the list of constructor declarations.
 * @return A list containing declarations with constructor declarations satisfying the predicate.
 */
fun <T : KoConstructorProvider> List<T>.withConstructors(predicate: (List<KoConstructorDeclaration>) -> Boolean): List<T> =
    filter { predicate(it.constructors) }

/**
 * List containing declarations without constructor declarations satisfying the predicate.
 *
 * @param predicate A function that defines the condition to be met by the list of constructor declarations.
 * @return A list containing declarations without constructor declarations satisfying the predicate.
 */
fun <T : KoConstructorProvider> List<T>.withoutConstructors(predicate: (List<KoConstructorDeclaration>) -> Boolean): List<T> =
    filterNot { predicate(it.constructors) }
