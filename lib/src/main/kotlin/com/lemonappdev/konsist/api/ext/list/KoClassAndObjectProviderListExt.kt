package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.declaration.combined.KoClassAndObjectDeclaration
import com.lemonappdev.konsist.api.provider.KoClassAndObjectProvider

/**
 * List containing class or/and object declarations.
 *
 * @param includeNested Whether to include nested classes and objects.
 * @param includeLocal Whether to include local classes.
 * @return A list containing class or/and objects declarations.
 */
fun <T : KoClassAndObjectProvider> List<T>.classesAndObjects(
    includeNested: Boolean = true,
    includeLocal: Boolean = true,
): List<KoClassAndObjectDeclaration> = flatMap { it.classesAndObjects(includeNested, includeLocal) }

/**
 * List containing declarations with any class or object.
 *
 * @param includeNested Whether to include nested classes and objects.
 * @param includeLocal Whether to include local classes.
 * @return A list containing declarations with any class or object.
 */
fun <T : KoClassAndObjectProvider> List<T>.withClassesAndObjects(
    includeNested: Boolean = true,
    includeLocal: Boolean = true,
): List<T> = filter { it.hasClassesOrObjects(includeNested, includeLocal) }

/**
 * List containing declarations with no classes and objects.
 *
 * @param includeNested Whether to include nested classes and objects.
 * @param includeLocal Whether to include local classes.
 * @return A list containing declarations with no classes and objects.
 */
fun <T : KoClassAndObjectProvider> List<T>.withoutClassesAndObjects(
    includeNested: Boolean = true,
    includeLocal: Boolean = true,
): List<T> = filterNot { it.hasClassesOrObjects(includeNested, includeLocal) }

/**
 * List containing declarations that have at least one class or object with the specified name(s).
 *
 * @param name The name of the class or object to include.
 * @param names The names of additional classes and objects to include.
 * @param includeNested Whether to include nested classes and objects.
 * @param includeLocal Whether to include local classes.
 * @return A list containing declarations with at least one of the specified class(es) or object(s).
 */
fun <T : KoClassAndObjectProvider> List<T>.withClassOrObjectNamed(
    name: String,
    vararg names: String,
    includeNested: Boolean = true,
    includeLocal: Boolean = true,
): List<T> = withClassOrObjectNamed(listOf(name, *names), includeNested, includeLocal)

/**
 * List containing declarations that have at least one class or object with the specified name(s).
 *
 * @param names The names of additional classes and objects to include.
 * @param includeNested Whether to include nested classes and objects.
 * @param includeLocal Whether to include local classes.
 * @return A list containing declarations with at least one of the specified class(es) or object(s).
 */
fun <T : KoClassAndObjectProvider> List<T>.withClassOrObjectNamed(
    names: Collection<String>,
    includeNested: Boolean = true,
    includeLocal: Boolean = true,
): List<T> =
    filter {
        when {
            names.isEmpty() -> it.hasClassesOrObjects(includeNested, includeLocal)
            else -> it.hasClassOrObjectWithName(names, includeNested = includeNested, includeLocal = includeLocal)
        }
    }

/**
 * List containing declarations without any of specified classes and objects.
 *
 * @param name The name of the class or object to exclude.
 * @param names The names of additional classes and objects to exclude.
 * @param includeNested Whether to include nested classes and objects.
 * @param includeLocal Whether to include local classes.
 * @return A list containing declarations without any of specified classes and objects.
 */
fun <T : KoClassAndObjectProvider> List<T>.withoutClassOrObjectNamed(
    name: String,
    vararg names: String,
    includeNested: Boolean = true,
    includeLocal: Boolean = true,
): List<T> = withoutClassOrObjectNamed(listOf(name, *names), includeNested, includeLocal)

/**
 * List containing declarations without any of specified classes and objects.
 *
 * @param names The names of additional classes and objects to exclude.
 * @param includeNested Whether to include nested classes and objects.
 * @param includeLocal Whether to include local classes.
 * @return A list containing declarations without any of specified classes and objects.
 */
fun <T : KoClassAndObjectProvider> List<T>.withoutClassOrObjectNamed(
    names: Collection<String>,
    includeNested: Boolean = true,
    includeLocal: Boolean = true,
): List<T> =
    filterNot {
        when {
            names.isEmpty() -> it.hasClassesOrObjects(includeNested, includeLocal)
            else -> it.hasClassOrObjectWithName(names, includeNested = includeNested, includeLocal = includeLocal)
        }
    }

/**
 * List containing declarations that have all specified classes and objects.
 *
 * @param name The name of the class or object to include.
 * @param names The name(s) of the class(es) and object(s) to include.
 * @param includeNested Whether to include nested classes and objects.
 * @param includeLocal Whether to include local classes.
 * @return A list containing declarations with all specified class(es) and object(s).
 */
fun <T : KoClassAndObjectProvider> List<T>.withAllClassesAndObjectsNamed(
    name: String,
    vararg names: String,
    includeNested: Boolean = true,
    includeLocal: Boolean = true,
): List<T> = withAllClassesAndObjectsNamed(listOf(name, *names), includeNested, includeLocal)

/**
 * List containing declarations that have all specified classes and objects.
 *
 * @param names The name(s) of the class(es) and object(s) to include.
 * @param includeNested Whether to include nested classes and objects.
 * @param includeLocal Whether to include local classes.
 * @return A list containing declarations with all specified class(es) and object(s).
 */
fun <T : KoClassAndObjectProvider> List<T>.withAllClassesAndObjectsNamed(
    names: Collection<String>,
    includeNested: Boolean = true,
    includeLocal: Boolean = true,
): List<T> =
    filter {
        when {
            names.isEmpty() -> it.hasClassesOrObjects(includeNested, includeLocal)
            else -> it.hasClassesAndObjectsWithAllNames(names, includeNested = includeNested, includeLocal = includeLocal)
        }
    }

/**
 * List containing declarations without all specified classes and objects.
 *
 * @param name The name of the class or object to exclude.
 * @param names The name(s) of the class(es) and object(s) to exclude.
 * @param includeNested Whether to include nested classes and objects.
 * @param includeLocal Whether to include local classes.
 * @return A list containing declarations without all specified class(es) and object(s).
 */
fun <T : KoClassAndObjectProvider> List<T>.withoutAllClassesAndObjectsNamed(
    name: String,
    vararg names: String,
    includeNested: Boolean = true,
    includeLocal: Boolean = true,
): List<T> = withoutAllClassesAndObjectsNamed(listOf(name, *names), includeNested, includeLocal)

/**
 * List containing declarations without all specified classes and objects.
 *
 * @param names The name(s) of the class(es) and object(s) to exclude.
 * @param includeNested Whether to include nested classes and objects.
 * @param includeLocal Whether to include local classes.
 * @return A list containing declarations without all specified class(es) and object(s).
 */
fun <T : KoClassAndObjectProvider> List<T>.withoutAllClassesAndObjectsNamed(
    names: Collection<String>,
    includeNested: Boolean = true,
    includeLocal: Boolean = true,
): List<T> =
    filterNot {
        when {
            names.isEmpty() -> it.hasClassesOrObjects(includeNested, includeLocal)
            else -> it.hasClassesAndObjectsWithAllNames(names, includeNested = includeNested, includeLocal = includeLocal)
        }
    }

/**
 * List containing declarations that have at least one class or object satisfying the provided predicate.
 *
 * @param includeNested Whether to include nested classes and objects.
 * @param includeLocal Whether to include local classes.
 * @param predicate A function that defines the condition to be met by a class or object declaration.
 * @return A list containing declarations with at least one class or object satisfying the predicate.
 */
fun <T : KoClassAndObjectProvider> List<T>.withClassOrObject(
    includeNested: Boolean = true,
    includeLocal: Boolean = true,
    predicate: (KoClassAndObjectDeclaration) -> Boolean,
): List<T> = filter { it.hasClassOrObject(includeNested, includeLocal, predicate) }

/**
 * List containing declarations that not have class or object satisfying the provided predicate.
 *
 * @param includeNested Whether to include nested classes and objects.
 * @param includeLocal Whether to include local classes.
 * @param predicate A function that defines the condition to be met by a class or object declaration.
 * @return A list containing declarations without class or object satisfying the provided predicate.
 */
fun <T : KoClassAndObjectProvider> List<T>.withoutClassOrObject(
    includeNested: Boolean = true,
    includeLocal: Boolean = true,
    predicate: (KoClassAndObjectDeclaration) -> Boolean,
): List<T> = filterNot { it.hasClassOrObject(includeNested, includeLocal, predicate) }

/**
 * List containing declarations that have all classes and objects satisfying the provided predicate.
 *
 * @param includeNested Whether to include nested classes and objects.
 * @param includeLocal Whether to include local classes.
 * @param predicate A function that defines the condition to be met by all class and objects declarations.
 * @return A filtered list containing declarations with all classes and objects satisfying the predicate.
 */
fun <T : KoClassAndObjectProvider> List<T>.withAllClassesAndObjects(
    includeNested: Boolean = true,
    includeLocal: Boolean = true,
    predicate: (KoClassAndObjectDeclaration) -> Boolean,
): List<T> =
    filter {
        it.hasAllClassesAndObjects(includeNested, includeLocal, predicate)
    }

/**
 * List containing declarations that have at least one class or object not satisfying the provided predicate.
 *
 * @param includeNested Whether to include nested classes and objects.
 * @param includeLocal Whether to include local classes.
 * @param predicate A function that defines the condition to be met by all class or objects declarations.
 * @return A list containing declarations that have at least one class or object not satisfying the provided predicate.
 */
fun <T : KoClassAndObjectProvider> List<T>.withoutAllClassesAndObjects(
    includeNested: Boolean = true,
    includeLocal: Boolean = true,
    predicate: (KoClassAndObjectDeclaration) -> Boolean,
): List<T> = filterNot { it.hasAllClassesAndObjects(includeNested, includeLocal, predicate) }

/**
 * List containing declarations with classes and objects declarations satisfying the predicate.
 *
 * @param includeNested Whether to include nested classes and objects.
 * @param includeLocal Whether to include local classes.
 * @param predicate A function that defines the condition to be met by the list of classes and objects declarations.
 * @return A list containing declarations with classes and objects declarations satisfying the predicate.
 */
fun <T : KoClassAndObjectProvider> List<T>.withClassesAndObjects(
    includeNested: Boolean = true,
    includeLocal: Boolean = true,
    predicate: (List<KoClassAndObjectDeclaration>) -> Boolean,
): List<T> = filter { predicate(it.classesAndObjects(includeNested, includeLocal)) }

/**
 * List containing declarations without classes and objects declarations satisfying the predicate.
 *
 * @param includeNested Whether to include nested classes and objects.
 * @param includeLocal Whether to include local classes.
 * @param predicate A function that defines the condition to be met by the list of classes and objects declarations.
 * @return A list containing declarations without classes and objects declarations satisfying the predicate.
 */
fun <T : KoClassAndObjectProvider> List<T>.withoutClassesAndObjects(
    includeNested: Boolean = true,
    includeLocal: Boolean = true,
    predicate: (List<KoClassAndObjectDeclaration>) -> Boolean,
): List<T> = filterNot { predicate(it.classesAndObjects(includeNested, includeLocal)) }
