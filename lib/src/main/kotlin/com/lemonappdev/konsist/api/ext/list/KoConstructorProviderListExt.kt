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
fun <T : KoConstructorProvider> List<T>.withConstructor(): List<T> = filter { it.hasConstructors() }

/**
 * List containing declarations whose all constructors match the given predicate.
 *
 * @param predicate The predicate function to determine if a declaration constructor satisfies a condition.
 * @return A list containing declarations with the specified constructor(s).
 */
fun <T : KoConstructorProvider> List<T>.withAllConstructors(predicate: (KoConstructorDeclaration) -> Boolean): List<T> =
    filter { it.hasAllConstructors(predicate) }

/**
 * List containing declarations whose at least one constructor matches the given predicate.
 *
 * @param predicate The predicate function to determine if a declaration constructor satisfies a condition.
 * @return A list containing declarations with the specified constructor(s).
 */
fun <T : KoConstructorProvider> List<T>.withConstructor(predicate: (KoConstructorDeclaration) -> Boolean): List<T> =
    filter { it.hasConstructor(predicate) }

/**
 * List containing declarations without constructor.
 *
 * @return A list containing declarations without the constructor.
 */
fun <T : KoConstructorProvider> List<T>.withoutConstructor(): List<T> = filterNot { it.hasConstructors() }

/**
 * List containing declarations whose all constructors do not match the predicate.
 *
 * @param predicate The predicate function to determine if a declaration constructor satisfies a condition.
 * @return A list containing declarations without the specified constructor(s).
 */
fun <T : KoConstructorProvider> List<T>.withoutAllConstructors(predicate: (KoConstructorDeclaration) -> Boolean): List<T> =
    filterNot { it.hasAllConstructors(predicate) }

/**
 * List containing declarations whose at least one constructor does not match the predicate.
 *
 * @param predicate The predicate function to determine if a declaration constructor satisfies a condition.
 * @return A list containing declarations with the specified constructor(s).
 */
fun <T : KoConstructorProvider> List<T>.withoutConstructor(predicate: (KoConstructorDeclaration) -> Boolean): List<T> =
    filterNot { it.hasConstructor(predicate) }
