package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.declaration.KoClassDeclaration
import com.lemonappdev.konsist.api.provider.KoLocalClassProvider

/**
 * List containing local class declarations.
 */
val <T : KoLocalClassProvider> List<T>.localClasses: List<KoClassDeclaration>
    get() = flatMap { it.localClasses }

/**
 * List containing declarations with any local class.
 *
 * @return A list containing declarations with any local class.
 */
fun <T : KoLocalClassProvider> List<T>.withLocalClasses(): List<T> = filter { it.hasLocalClasses() }

/**
 * List containing declarations with no local classes.
 *
 * @return A list containing declarations with no local classes.
 */
fun <T : KoLocalClassProvider> List<T>.withoutLocalClasses(): List<T> = filterNot { it.hasLocalClasses() }

/**
 * List containing declarations that have at least one local class with the specified name(s).
 *
 * @param name The name of the local class to include.
 * @param names The names of additional local classes to include.
 * @return A list containing declarations with at least one of the specified local class(es).
 */
fun <T : KoLocalClassProvider> List<T>.withLocalClassNamed(
    name: String,
    vararg names: String,
): List<T> = withLocalClassNamed(listOf(name, *names))

/**
 * List containing declarations that have at least one local class with the specified name(s).
 *
 * @param names The names of additional local classes to include.
 * @return A list containing declarations with at least one of the specified local class(es).
 */
fun <T : KoLocalClassProvider> List<T>.withLocalClassNamed(names: Collection<String>): List<T> =
    filter {
        when {
            names.isEmpty() -> it.hasLocalClasses()
            else -> it.hasLocalClassWithName(names.first(), *names.drop(1).toTypedArray())
        }
    }

/**
 * List containing declarations without any of specified local classes.
 *
 * @param name The name of the local class to exclude.
 * @param names The names of additional local classes to exclude.
 * @return A list containing declarations without any of specified local classes.
 */
fun <T : KoLocalClassProvider> List<T>.withoutLocalClassNamed(
    name: String,
    vararg names: String,
): List<T> = withoutLocalClassNamed(listOf(name, *names))

/**
 * List containing declarations without any of specified local classes.
 *
 * @param names The names of additional local classes to exclude.
 * @return A list containing declarations without any of specified local classes.
 */
fun <T : KoLocalClassProvider> List<T>.withoutLocalClassNamed(names: Collection<String>): List<T> =
    filterNot {
        when {
            names.isEmpty() -> it.hasLocalClasses()
            else -> it.hasLocalClassWithName(names.first(), *names.drop(1).toTypedArray())
        }
    }

/**
 * List containing declarations that have all specified local classes.
 *
 * @param name The name of the local class to include.
 * @param names The name(s) of the local class(es) to include.
 * @return A list containing declarations with all specified local class(es).
 */
fun <T : KoLocalClassProvider> List<T>.withAllLocalClassesNamed(
    name: String,
    vararg names: String,
): List<T> = withAllLocalClassesNamed(listOf(name, *names))

/**
 * List containing declarations that have all specified local classes.
 *
 * @param names The name(s) of the local class(es) to include.
 * @return A list containing declarations with all specified local class(es).
 */
fun <T : KoLocalClassProvider> List<T>.withAllLocalClassesNamed(names: Collection<String>): List<T> =
    filter {
        when {
            names.isEmpty() -> it.hasLocalClasses()
            else -> it.hasLocalClassesWithAllNames(names.first(), *names.drop(1).toTypedArray())
        }
    }

/**
 * List containing declarations without all specified local classes.
 *
 * @param name The name of the local class to exclude.
 * @param names The name(s) of the local class(es) to exclude.
 * @return A list containing declarations without all specified local class(es).
 */
fun <T : KoLocalClassProvider> List<T>.withoutAllLocalClassesNamed(
    name: String,
    vararg names: String,
): List<T> = withoutAllLocalClassesNamed(listOf(name, *names))

/**
 * List containing declarations without all specified local classes.
 *
 * @param names The name(s) of the local class(es) to exclude.
 * @return A list containing declarations without all specified local class(es).
 */
fun <T : KoLocalClassProvider> List<T>.withoutAllLocalClassesNamed(names: Collection<String>): List<T> =
    filterNot {
        when {
            names.isEmpty() -> it.hasLocalClasses()
            else -> it.hasLocalClassesWithAllNames(names.first(), *names.drop(1).toTypedArray())
        }
    }

/**
 * List containing declarations that have at least one local class satisfying the provided predicate.
 *
 * @param predicate A function that defines the condition to be met by a local class declaration.
 * @return A list containing declarations with at least one local class satisfying the predicate.
 */
fun <T : KoLocalClassProvider> List<T>.withLocalClass(predicate: (KoClassDeclaration) -> Boolean): List<T> =
    filter { it.hasLocalClass(predicate) }

/**
 * List containing declarations that not have local class satisfying the provided predicate.
 *
 * @param predicate A function that defines the condition to be met by a local class declaration.
 * @return A list containing declarations without local class satisfying the provided predicate.
 */
fun <T : KoLocalClassProvider> List<T>.withoutLocalClass(predicate: (KoClassDeclaration) -> Boolean): List<T> =
    filterNot { it.hasLocalClass(predicate) }

/**
 * List containing declarations that have all local classes satisfying the provided predicate.
 *
 * @param predicate A function that defines the condition to be met by all local class declarations.
 * @return A filtered list containing declarations with all local classes satisfying the predicate.
 */
fun <T : KoLocalClassProvider> List<T>.withAllLocalClasses(predicate: (KoClassDeclaration) -> Boolean): List<T> =
    filter { it.hasAllLocalClasses(predicate) }

/**
 * List containing declarations that have at least one local class not satisfying the provided predicate.
 *
 * @param predicate A function that defines the condition to be met by all local class declarations.
 * @return A list containing declarations that have at least one local class not satisfying the provided predicate.
 */
fun <T : KoLocalClassProvider> List<T>.withoutAllLocalClasses(predicate: (KoClassDeclaration) -> Boolean): List<T> =
    filterNot { it.hasAllLocalClasses(predicate) }

/**
 * List containing declarations with local class declarations satisfying the predicate.
 *
 * @param predicate A function that defines the condition to be met by the list of local class declarations.
 * @return A list containing declarations with local class declarations satisfying the predicate.
 */
fun <T : KoLocalClassProvider> List<T>.withLocalClasses(predicate: (List<KoClassDeclaration>) -> Boolean): List<T> =
    filter { predicate(it.localClasses) }

/**
 * List containing declarations without local class declarations satisfying the predicate.
 *
 * @param predicate A function that defines the condition to be met by the list of local class declarations.
 * @return A list containing declarations without local class declarations satisfying the predicate.
 */
fun <T : KoLocalClassProvider> List<T>.withoutLocalClasses(predicate: (List<KoClassDeclaration>) -> Boolean): List<T> =
    filterNot { predicate(it.localClasses) }
