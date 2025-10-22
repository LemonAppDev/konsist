@file:Suppress("detekt.TooManyFunctions")

package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.declaration.KoCompanionObjectDeclaration
import com.lemonappdev.konsist.api.provider.KoCompanionObjectProvider

/**
 * Returns a list of direct companion objects from each declaration in this list.
 *
 * Only direct companion objects are included; nested companion objects are ignored.
 *
 * @return A list containing direct [KoCompanionObjectDeclaration] instances from the declarations in this list.
 */
val <T : KoCompanionObjectProvider> List<T>.companionObjects: List<KoCompanionObjectDeclaration>
    get() = mapNotNull { it.companionObject }

/**
 * Returns a list of companion objects from each declaration in this list.
 *
 * @param includeNested Whether to include companion objects declared in nested declarations.
 * @return A list containing [KoCompanionObjectDeclaration] instances from the declarations in this list.
 */
fun <T : KoCompanionObjectProvider> List<T>.companionObjects(includeNested: Boolean = true): List<KoCompanionObjectDeclaration> =
    flatMap { it.companionObjects(includeNested) }

/**
 * Returns a list of declarations that have a direct companion object.
 *
 * @return A list containing declarations that directly declare a companion object.
 */
fun <T : KoCompanionObjectProvider> List<T>.withCompanionObject(): List<T> = filter { it.hasCompanionObject() }

/**
 * Returns a list of declarations that do not have a direct companion object.
 *
 * @return A list containing declarations without any direct companion object.
 */
fun <T : KoCompanionObjectProvider> List<T>.withoutCompanionObject(): List<T> = filterNot { it.hasCompanionObject() }

/**
 * Returns a list of declarations that have at least one companion object.
 *
 * @param includeNested Whether to include nested companion objects when checking.
 * @return A list containing declarations that have at least one companion object.
 */
fun <T : KoCompanionObjectProvider> List<T>.withCompanionObjects(includeNested: Boolean = true): List<T> =
    filter { it.hasCompanionObjects(includeNested) }

/**
 * Returns a list of declarations that do not have any companion objects.
 *
 * @param includeNested Whether to include nested companion objects when checking.
 * @return A list containing declarations without any companion objects.
 */
fun <T : KoCompanionObjectProvider> List<T>.withoutCompanionObjects(includeNested: Boolean = true): List<T> =
    filterNot { it.hasCompanionObjects(includeNested) }

/**
 * Returns a list of declarations that have at least one companion object matching the given [predicate].
 *
 * @param includeNested Whether to include nested companion objects.
 * @param predicate A condition that a companion object must satisfy.
 * @return A list containing declarations with at least one companion object satisfying the [predicate].
 */
fun <T : KoCompanionObjectProvider> List<T>.withCompanionObject(
    includeNested: Boolean = true,
    predicate: (KoCompanionObjectDeclaration) -> Boolean,
): List<T> = filter { it.hasCompanionObject(includeNested, predicate) }

/**
 * Returns a list of declarations that have no companion objects matching the given [predicate].
 *
 * @param includeNested Whether to include nested companion objects.
 * @param predicate A condition that a companion object must satisfy.
 * @return A list containing declarations without any companion object satisfying the [predicate].
 */
fun <T : KoCompanionObjectProvider> List<T>.withoutCompanionObject(
    includeNested: Boolean = true,
    predicate: (KoCompanionObjectDeclaration) -> Boolean,
): List<T> = filterNot { it.hasCompanionObject(includeNested, predicate) }

/**
 * Returns a list of declarations whose companion objects all satisfy the given [predicate].
 *
 * @param includeNested Whether to include nested companion objects.
 * @param predicate A condition that all companion objects must satisfy.
 * @return A list containing declarations where all companion objects satisfy the [predicate].
 */
fun <T : KoCompanionObjectProvider> List<T>.withAllCompanionObjects(
    includeNested: Boolean = true,
    predicate: (KoCompanionObjectDeclaration) -> Boolean,
): List<T> = filter { it.hasAllCompanionObjects(includeNested, predicate) }

/**
 * Returns a list of declarations that have at least one companion object not satisfying the given [predicate].
 *
 * @param includeNested Whether to include nested companion objects.
 * @param predicate A condition that all companion objects must satisfy.
 * @return A list containing declarations with at least one companion object that does not satisfy the [predicate].
 */
fun <T : KoCompanionObjectProvider> List<T>.withoutAllCompanionObjects(
    includeNested: Boolean = true,
    predicate: (KoCompanionObjectDeclaration) -> Boolean,
): List<T> = filterNot { it.hasAllCompanionObjects(includeNested, predicate) }

/**
 * Returns a list of declarations where the list of companion objects satisfies the given [predicate].
 *
 * @param includeNested Whether to include nested companion objects.
 * @param predicate A condition evaluated on the list of companion objects.
 * @return A list containing declarations whose list of companion objects satisfies the [predicate].
 */
fun <T : KoCompanionObjectProvider> List<T>.withCompanionObjects(
    includeNested: Boolean = true,
    predicate: (List<KoCompanionObjectDeclaration>) -> Boolean,
): List<T> = filter { predicate(it.companionObjects(includeNested)) }

/**
 * Returns a list of declarations where the list of companion objects does not satisfy the given [predicate].
 *
 * @param includeNested Whether to include nested companion objects.
 * @param predicate A condition evaluated on the list of companion objects.
 * @return A list containing declarations whose list of companion objects does not satisfy the [predicate].
 */
fun <T : KoCompanionObjectProvider> List<T>.withoutCompanionObjects(
    includeNested: Boolean = true,
    predicate: (List<KoCompanionObjectDeclaration>) -> Boolean,
): List<T> = filterNot { predicate(it.companionObjects(includeNested)) }

/**
 * Returns a list of declarations that have a companion object with any of the specified [names].
 *
 * @param name The first companion object name to include.
 * @param names Additional companion object names to include.
 * @param includeNested Whether to include nested companion objects.
 * @param ignoreCase Whether name comparison should ignore case sensitivity.
 * @return A list containing declarations that have a companion object with one of the specified names.
 */
fun <T : KoCompanionObjectProvider> List<T>.withCompanionObjectNamed(
    name: String,
    vararg names: String,
    includeNested: Boolean = true,
    ignoreCase: Boolean = false,
): List<T> = withCompanionObjectNamed(listOf(name, *names), includeNested, ignoreCase)

/**
 * Returns a list of declarations that have a companion object with any of the specified [names].
 *
 * @param names The companion object names to include.
 * @param includeNested Whether to include nested companion objects.
 * @param ignoreCase Whether name comparison should ignore case sensitivity.
 * @return A list containing declarations that have a companion object with one of the specified names.
 */
fun <T : KoCompanionObjectProvider> List<T>.withCompanionObjectNamed(
    names: Collection<String>,
    includeNested: Boolean = true,
    ignoreCase: Boolean = false,
): List<T> =
    filter {
        when {
            names.isEmpty() -> it.hasCompanionObjects(includeNested)
            else -> it.hasCompanionObjectWithName(names, includeNested = includeNested, ignoreCase = ignoreCase)
        }
    }

/**
 * Returns a list of declarations that do not have a companion object with any of the specified [names].
 *
 * @param name The first companion object name to exclude.
 * @param names Additional companion object names to exclude.
 * @param includeNested Whether to include nested companion objects.
 * @param ignoreCase Whether name comparison should ignore case sensitivity.
 * @return A list containing declarations that do not have a companion object with the specified names.
 */
fun <T : KoCompanionObjectProvider> List<T>.withoutCompanionObjectNamed(
    name: String,
    vararg names: String,
    includeNested: Boolean = true,
    ignoreCase: Boolean = false,
): List<T> = withoutCompanionObjectNamed(listOf(name, *names), includeNested, ignoreCase)

/**
 * Returns a list of declarations that do not have a companion object with any of the specified [names].
 *
 * @param names The companion object names to exclude.
 * @param includeNested Whether to include nested companion objects.
 * @param ignoreCase Whether name comparison should ignore case sensitivity.
 * @return A list containing declarations that do not have a companion object with the specified names.
 */
fun <T : KoCompanionObjectProvider> List<T>.withoutCompanionObjectNamed(
    names: Collection<String>,
    includeNested: Boolean = true,
    ignoreCase: Boolean = false,
): List<T> =
    filterNot {
        when {
            names.isEmpty() -> it.hasCompanionObjects(includeNested)
            else -> it.hasCompanionObjectWithName(names, includeNested = includeNested, ignoreCase = ignoreCase)
        }
    }

/**
 * Returns a list of declarations that have companion objects with all the specified [names].
 *
 * @param name The first companion object name to include.
 * @param names Additional companion object names to include.
 * @param includeNested Whether to include nested companion objects.
 * @param ignoreCase Whether name comparison should ignore case sensitivity.
 * @return A list containing declarations that have companion objects with all the specified names.
 */
fun <T : KoCompanionObjectProvider> List<T>.withAllCompanionObjectsNamed(
    name: String,
    vararg names: String,
    includeNested: Boolean = true,
    ignoreCase: Boolean = false,
): List<T> = withAllCompanionObjectsNamed(listOf(name, *names), includeNested, ignoreCase)

/**
 * Returns a list of declarations that have companion objects with all the specified [names].
 *
 * @param names The companion object names that must all be present.
 * @param includeNested Whether to include nested companion objects.
 * @param ignoreCase Whether name comparison should ignore case sensitivity.
 * @return A list containing declarations that have companion objects with all the specified names.
 */
fun <T : KoCompanionObjectProvider> List<T>.withAllCompanionObjectsNamed(
    names: Collection<String>,
    includeNested: Boolean = true,
    ignoreCase: Boolean = false,
): List<T> =
    filter {
        when {
            names.isEmpty() -> it.hasCompanionObjects(includeNested)
            else -> it.hasCompanionObjectsWithAllNames(names, includeNested = includeNested, ignoreCase = ignoreCase)
        }
    }

/**
 * Returns a list of declarations that do not have companion objects with all the specified [names].
 *
 * @param name The first companion object name to exclude.
 * @param names Additional companion object names to exclude.
 * @param includeNested Whether to include nested companion objects.
 * @param ignoreCase Whether name comparison should ignore case sensitivity.
 * @return A list containing declarations that do not have companion objects with all the specified names.
 */
fun <T : KoCompanionObjectProvider> List<T>.withoutAllCompanionObjectsNamed(
    name: String,
    vararg names: String,
    includeNested: Boolean = true,
    ignoreCase: Boolean = false,
): List<T> = withoutAllCompanionObjectsNamed(listOf(name, *names), includeNested, ignoreCase)

/**
 * Returns a list of declarations that do not have companion objects with all the specified [names].
 *
 * @param names The companion object names to exclude.
 * @param includeNested Whether to include nested companion objects.
 * @param ignoreCase Whether name comparison should ignore case sensitivity.
 * @return A list containing declarations that do not have companion objects with all the specified names.
 */
fun <T : KoCompanionObjectProvider> List<T>.withoutAllCompanionObjectsNamed(
    names: Collection<String>,
    includeNested: Boolean = true,
    ignoreCase: Boolean = false,
): List<T> =
    filterNot {
        when {
            names.isEmpty() -> it.hasCompanionObjects(includeNested)
            else -> it.hasCompanionObjectsWithAllNames(names, includeNested = includeNested, ignoreCase = ignoreCase)
        }
    }
