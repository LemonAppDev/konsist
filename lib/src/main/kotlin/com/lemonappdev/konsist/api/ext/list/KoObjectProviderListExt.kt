package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.declaration.KoObjectDeclaration
import com.lemonappdev.konsist.api.provider.KoObjectProvider

/**
 * List containing object declarations.
 *
 * @param includeNested Whether to include nested objects.
 * @return A list containing object declarations.
 */
fun <T : KoObjectProvider> List<T>.objects(includeNested: Boolean = true): List<KoObjectDeclaration> = flatMap { it.objects(includeNested) }

/**
 * List containing declarations with any object.
 *
 * @param includeNested Whether to include nested objects.
 * @return A list containing declarations with any object.
 */
fun <T : KoObjectProvider> List<T>.withObjects(includeNested: Boolean = true): List<T> = filter { it.hasObjects(includeNested) }

/**
 * List containing declarations with no objects.
 *
 * @param includeNested Whether to include nested objects.
 * @return A list containing declarations with no objects.
 */
fun <T : KoObjectProvider> List<T>.withoutObjects(includeNested: Boolean = true): List<T> = filterNot { it.hasObjects(includeNested) }

/**
 * List containing declarations that have at least one object with the specified name(s).
 *
 * @param name The name of the object to include.
 * @param names The names of additional objects to include.
 * @param includeNested Whether to include nested objects.
 * @return A list containing declarations with at least one of the specified object(s).
 */
fun <T : KoObjectProvider> List<T>.withObjectNamed(
    name: String,
    vararg names: String,
    includeNested: Boolean = true,
): List<T> =
    filter {
        it.hasObjectWithName(name, *names, includeNested = includeNested)
    }

/**
 * List containing declarations without any of specified objects.
 *
 * @param name The name of the object to exclude.
 * @param names The names of additional objects to exclude.
 * @param includeNested Whether to include nested objects.
 * @return A list containing declarations without any of specified objects.
 */
fun <T : KoObjectProvider> List<T>.withoutObjectNamed(
    name: String,
    vararg names: String,
    includeNested: Boolean = true,
): List<T> =
    filterNot {
        it.hasObjectWithName(name, *names, includeNested = includeNested)
    }

/**
 * List containing declarations that have all specified objects.
 *
 * @param name The name of the object to include.
 * @param names The name(s) of the object(s) to include.
 * @param includeNested Whether to include nested objects.
 * @return A list containing declarations with all specified object(s).
 */
fun <T : KoObjectProvider> List<T>.withAllObjectsNamed(
    name: String,
    vararg names: String,
    includeNested: Boolean = true,
): List<T> =
    filter {
        it.hasObjectsWithAllNames(name, *names, includeNested = includeNested)
    }

/**
 * List containing declarations without all specified objects.
 *
 * @param name The name of the object to exclude.
 * @param names The name(s) of the object(s) to exclude.
 * @param includeNested Whether to include nested objects.
 * @return A list containing declarations without all specified object(s).
 */
fun <T : KoObjectProvider> List<T>.withoutAllObjectsNamed(
    name: String,
    vararg names: String,
    includeNested: Boolean = true,
): List<T> =
    filterNot {
        it.hasObjectsWithAllNames(name, *names, includeNested = includeNested)
    }

/**
 * List containing declarations that have at least one object satisfying the provided predicate.
 *
 * @param includeNested Whether to include nested objects.
 * @param predicate A function that defines the condition to be met by an object declaration.
 * @return A list containing declarations with at least one object satisfying the predicate.
 */
fun <T : KoObjectProvider> List<T>.withObject(
    includeNested: Boolean = true,
    predicate: (KoObjectDeclaration) -> Boolean,
): List<T> =
    filter {
        it.hasObject(includeNested, predicate)
    }

/**
 * List containing declarations that not have object satisfying the provided predicate.
 *
 * @param includeNested Whether to include nested objects.
 * @param predicate A function that defines the condition to be met by an object declaration.
 * @return A list containing declarations without object satisfying the provided predicate.
 */
fun <T : KoObjectProvider> List<T>.withoutObject(
    includeNested: Boolean = true,
    predicate: (KoObjectDeclaration) -> Boolean,
): List<T> = filterNot { it.hasObject(includeNested, predicate) }

/**
 * List containing declarations that have all objects satisfying the provided predicate.
 *
 * @param includeNested Whether to include nested objects.
 * @param predicate A function that defines the condition to be met by all object declarations.
 * @return A filtered list containing declarations with all objects satisfying the predicate.
 */
fun <T : KoObjectProvider> List<T>.withAllObjects(
    includeNested: Boolean = true,
    predicate: (KoObjectDeclaration) -> Boolean,
): List<T> =
    filter {
        it.hasAllObjects(includeNested, predicate)
    }

/**
 * List containing declarations that have at least one object not satisfying the provided predicate.
 *
 * @param includeNested Whether to include nested objects.
 * @param predicate A function that defines the condition to be met by all object declarations.
 * @return A list containing declarations that have at least one object not satisfying the provided predicate.
 */
fun <T : KoObjectProvider> List<T>.withoutAllObjects(
    includeNested: Boolean = true,
    predicate: (KoObjectDeclaration) -> Boolean,
): List<T> = filterNot { it.hasAllObjects(includeNested, predicate) }

/**
 * List containing declarations with object declarations satisfying the predicate.
 *
 * @param includeNested Whether to include nested objects.
 * @param predicate A function that defines the condition to be met by the list of object declarations.
 * @return A list containing declarations with object declarations satisfying the predicate.
 */
fun <T : KoObjectProvider> List<T>.withObjects(
    includeNested: Boolean = true,
    predicate: (List<KoObjectDeclaration>) -> Boolean,
): List<T> = filter { predicate(it.objects(includeNested)) }

/**
 * List containing declarations without object declarations satisfying the predicate.
 *
 * @param includeNested Whether to include nested objects.
 * @param predicate A function that defines the condition to be met by the list of object declarations.
 * @return A list containing declarations without object declarations satisfying the predicate.
 */
fun <T : KoObjectProvider> List<T>.withoutObjects(
    includeNested: Boolean = true,
    predicate: (List<KoObjectDeclaration>) -> Boolean,
): List<T> = filterNot { predicate(it.objects(includeNested)) }
