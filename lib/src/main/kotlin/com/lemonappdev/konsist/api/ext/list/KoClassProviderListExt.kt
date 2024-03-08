package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.declaration.KoClassDeclaration
import com.lemonappdev.konsist.api.provider.KoClassProvider

/**
 * List containing class declarations.
 *
 * @param includeNested Whether to include nested classes.
 * @param includeLocal Whether to include local classes.
 * @return A list containing class declarations.
 */
fun <T : KoClassProvider> List<T>.classes(
    includeNested: Boolean = true,
    includeLocal: Boolean = true,
): List<KoClassDeclaration> = flatMap { it.classes(includeNested, includeLocal) }

/**
 * List containing declarations with any class.
 *
 * @param includeNested Whether to include nested classes.
 * @param includeLocal Whether to include local classes.
 * @return A list containing declarations with any class.
 */
fun <T : KoClassProvider> List<T>.withClasses(
    includeNested: Boolean = true,
    includeLocal: Boolean = true,
): List<T> = filter { it.hasClasses(includeNested, includeLocal) }

/**
 * List containing declarations with no classes.
 *
 * @param includeNested Whether to include nested classes.
 * @param includeLocal Whether to include local classes.
 * @return A list containing declarations with no classes.
 */
fun <T : KoClassProvider> List<T>.withoutClasses(
    includeNested: Boolean = true,
    includeLocal: Boolean = true,
): List<T> = filterNot { it.hasClasses(includeNested, includeLocal) }

/**
 * List containing declarations that have at least one class with the specified name(s).
 *
 * @param name The name of the class to include.
 * @param names The names of additional classes to include.
 * @param includeNested Whether to include nested classes.
 * @param includeLocal Whether to include local classes.
 * @return A list containing declarations with at least one of the specified class(s).
 */
fun <T : KoClassProvider> List<T>.withClassNamed(
    name: String,
    vararg names: String,
    includeNested: Boolean = true,
    includeLocal: Boolean = true,
): List<T> =
    filter {
        it.hasClassWithName(name, *names, includeNested = includeNested, includeLocal = includeLocal)
    }

/**
 * List containing declarations without any of specified classes.
 *
 * @param name The name of the class to exclude.
 * @param names The names of additional classes to exclude.
 * @param includeNested Whether to include nested classes.
 * @param includeLocal Whether to include local classes.
 * @return A list containing declarations without any of specified classes.
 */
fun <T : KoClassProvider> List<T>.withoutClassNamed(
    name: String,
    vararg names: String,
    includeNested: Boolean = true,
    includeLocal: Boolean = true,
): List<T> =
    filterNot {
        it.hasClassWithName(name, *names, includeNested = includeNested, includeLocal = includeLocal)
    }

/**
 * List containing declarations that have all specified classes.
 *
 * @param name The name of the class to include.
 * @param names The name(s) of the class(s) to include.
 * @param includeNested Whether to include nested classes.
 * @param includeLocal Whether to include local classes.
 * @return A list containing declarations with all specified class(s).
 */
fun <T : KoClassProvider> List<T>.withAllClassesNamed(
    name: String,
    vararg names: String,
    includeNested: Boolean = true,
    includeLocal: Boolean = true,
): List<T> =
    filter {
        it.hasClassesWithAllNames(name, *names, includeNested = includeNested, includeLocal = includeLocal)
    }

/**
 * List containing declarations without all specified classes.
 *
 * @param name The name of the class to exclude.
 * @param names The name(s) of the class(s) to exclude.
 * @param includeNested Whether to include nested classes.
 * @param includeLocal Whether to include local classes.
 * @return A list containing declarations without all specified class(s).
 */
fun <T : KoClassProvider> List<T>.withoutAllClassesNamed(
    name: String,
    vararg names: String,
    includeNested: Boolean = true,
    includeLocal: Boolean = true,
): List<T> =
    filterNot {
        it.hasClassesWithAllNames(name, *names, includeNested = includeNested, includeLocal = includeLocal)
    }

/**
 * List containing declarations that have at least one class satisfying the provided predicate.
 *
 * @param includeNested Whether to include nested classes.
 * @param includeLocal Whether to include local classes.
 * @param predicate A function that defines the condition to be met by a class declaration.
 * @return A list containing declarations with at least one class satisfying the predicate.
 */
fun <T : KoClassProvider> List<T>.withClass(
    includeNested: Boolean = true,
    includeLocal: Boolean = true,
    predicate: (KoClassDeclaration) -> Boolean,
): List<T> =
    filter {
        it.hasClass(includeNested, includeLocal, predicate)
    }

/**
 * List containing declarations that not have class satisfying the provided predicate.
 *
 * @param includeNested Whether to include nested classes.
 * @param includeLocal Whether to include local classes.
 * @param predicate A function that defines the condition to be met by a class declaration.
 * @return A list containing declarations without class satisfying the provided predicate.
 */
fun <T : KoClassProvider> List<T>.withoutClass(
    includeNested: Boolean = true,
    includeLocal: Boolean = true,
    predicate: (KoClassDeclaration) -> Boolean,
): List<T> = filterNot { it.hasClass(includeNested, includeLocal, predicate) }

/**
 * List containing declarations that have all classes satisfying the provided predicate.
 *
 * @param includeNested Whether to include nested classes.
 * @param includeLocal Whether to include local classes.
 * @param predicate A function that defines the condition to be met by all class declarations.
 * @return A filtered list containing declarations with all classes satisfying the predicate.
 */
fun <T : KoClassProvider> List<T>.withAllClasses(
    includeNested: Boolean = true,
    includeLocal: Boolean = true,
    predicate: (KoClassDeclaration) -> Boolean,
): List<T> =
    filter {
        it.hasAllClasses(includeNested, includeLocal, predicate)
    }

/**
 * List containing declarations that have at least one class not satisfying the provided predicate.
 *
 * @param includeNested Whether to include nested classes.
 * @param includeLocal Whether to include local classes.
 * @param predicate A function that defines the condition to be met by all class declarations.
 * @return A list containing declarations that have at least one class not satisfying the provided predicate.
 */
fun <T : KoClassProvider> List<T>.withoutAllClasses(
    includeNested: Boolean = true,
    includeLocal: Boolean = true,
    predicate: (KoClassDeclaration) -> Boolean,
): List<T> = filterNot { it.hasAllClasses(includeNested, includeLocal, predicate) }

/**
 * List containing declarations with class declarations satisfying the predicate.
 *
 * @param includeNested Whether to include nested classes.
 * @param includeLocal Whether to include local classes.
 * @param predicate A function that defines the condition to be met by the list of class declarations.
 * @return A list containing declarations with class declarations satisfying the predicate.
 */
fun <T : KoClassProvider> List<T>.withClasses(
    includeNested: Boolean = true,
    includeLocal: Boolean = true,
    predicate: (List<KoClassDeclaration>) -> Boolean,
): List<T> = filter { predicate(it.classes(includeNested, includeLocal)) }

/**
 * List containing declarations without class declarations satisfying the predicate.
 *
 * @param includeNested Whether to include nested classes.
 * @param includeLocal Whether to include local classes.
 * @param predicate A function that defines the condition to be met by the list of class declarations.
 * @return A list containing declarations without class declarations satisfying the predicate.
 */
fun <T : KoClassProvider> List<T>.withoutClasses(
    includeNested: Boolean = true,
    includeLocal: Boolean = true,
    predicate: (List<KoClassDeclaration>) -> Boolean,
): List<T> = filterNot { predicate(it.classes(includeNested, includeLocal)) }
