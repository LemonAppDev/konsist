package com.lemonappdev.konsist.api.ext.list.provider

import com.lemonappdev.konsist.api.declaration.KoSecondaryConstructorDeclaration
import com.lemonappdev.konsist.api.provider.KoSecondaryConstructorsProvider

/**
 * List containing secondary constructor declarations.
 */
val <T : KoSecondaryConstructorsProvider> List<T>.secondaryConstructors: List<KoSecondaryConstructorDeclaration>
    get() = flatMap { it.secondaryConstructors }

/**
 * List containing declarations that have secondary constructors.
 *
 * @return A list containing declarations with secondary constructor(s).
 */
fun <T : KoSecondaryConstructorsProvider> List<T>.withSecondaryConstructors(): List<T> = filter { it.hasSecondaryConstructors() }

/**
 * List containing declarations that don't have secondary constructors.
 *
 * @return A list containing declarations without secondary constructor(s).
 */
fun <T : KoSecondaryConstructorsProvider> List<T>.withoutSecondaryConstructors(): List<T> = filterNot { it.hasSecondaryConstructors() }

/**
 * List containing declarations that have at least one secondary constructor satisfying the provided predicate.
 *
 * @param predicate A function that defines the condition to be met by a secondary constructor declaration.
 * @return A list containing declarations with at least one secondary constructor satisfying the predicate.
 */
fun <T : KoSecondaryConstructorsProvider> List<T>.withSecondaryConstructor(
    predicate: (KoSecondaryConstructorDeclaration) -> Boolean,
): List<T> = filter { it.hasSecondaryConstructor(predicate) }

/**
 * List containing declarations that not have secondary constructor satisfying the provided predicate.
 *
 * @param predicate A function that defines the condition to be met by a secondary constructor declaration.
 * @return A list containing declarations without secondary constructor satisfying the provided predicate.
 */
fun <T : KoSecondaryConstructorsProvider> List<T>.withoutSecondaryConstructor(
    predicate: (KoSecondaryConstructorDeclaration) -> Boolean,
): List<T> = filterNot { it.hasSecondaryConstructor(predicate) }

/**
 * List containing declarations that have all secondary constructors satisfying the provided predicate.
 *
 * @param predicate A function that defines the condition to be met by all secondary constructor declarations.
 * @return A filtered list containing declarations with all secondary constructors satisfying the predicate.
 */
fun <T : KoSecondaryConstructorsProvider> List<T>.withAllSecondaryConstructors(
    predicate: (KoSecondaryConstructorDeclaration) -> Boolean,
): List<T> = filter { it.hasAllSecondaryConstructors(predicate) }

/**
 * List containing declarations that have at least one secondary constructor not satisfying the provided predicate.
 *
 * @param predicate A function that defines the condition to be met by all secondary constructor declarations.
 * @return A list containing declarations that have at least one secondary constructor not satisfying the provided predicate.
 */
fun <T : KoSecondaryConstructorsProvider> List<T>.withoutAllSecondaryConstructors(
    predicate: (KoSecondaryConstructorDeclaration) -> Boolean,
): List<T> = filterNot { it.hasAllSecondaryConstructors(predicate) }

/**
 * List containing declarations with secondary constructor declarations satisfying the predicate.
 *
 * @param predicate A function that defines the condition to be met by the list of secondary constructor declarations.
 * @return A list containing declarations with secondary constructor declarations satisfying the predicate.
 */
fun <T : KoSecondaryConstructorsProvider> List<T>.withSecondaryConstructors(
    predicate: (List<KoSecondaryConstructorDeclaration>) -> Boolean,
): List<T> = filter { predicate(it.secondaryConstructors) }

/**
 * List containing declarations without secondary constructor declarations satisfying the predicate.
 *
 * @param predicate A function that defines the condition to be met by the list of secondary constructor declarations.
 * @return A list containing declarations without secondary constructor declarations satisfying the predicate.
 */
fun <T : KoSecondaryConstructorsProvider> List<T>.withoutSecondaryConstructors(
    predicate: (List<KoSecondaryConstructorDeclaration>) -> Boolean,
): List<T> = filterNot { predicate(it.secondaryConstructors) }
