@file:Suppress("detekt.TooManyFunctions")

package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.declaration.KoCompanionObjectDeclaration
import com.lemonappdev.konsist.api.provider.KoCompanionObjectProvider

/**
 * List containing a direct companion object.
 */
val <T : KoCompanionObjectProvider> List<T>.companionObjects: List<KoCompanionObjectDeclaration>
    get() = mapNotNull { it.companionObject }

/**
 * List containing a companion object.
 *
 * @param includeNested Whether to include nested companion objects.
 * @return A list containing companion object declarations.
 */
fun <T : KoCompanionObjectProvider> List<T>.companionObjects(includeNested: Boolean = true): List<KoCompanionObjectDeclaration> =
    flatMap { it.companionObjects(includeNested) }

/**
 * List containing declarations with any direct companion object.
 *
 * @return A list containing declarations with any direct companion object.
 */
fun <T : KoCompanionObjectProvider> List<T>.withCompanionObject(): List<T> = filter { it.hasCompanionObject() }

/**
 * List containing declarations without a direct companion object.
 *
 * @return A list containing declarations with a none-direct companion object.
 */
fun <T : KoCompanionObjectProvider> List<T>.withoutCompanionObject(): List<T> = filterNot { it.hasCompanionObject() }

/**
 * List containing declarations with any companion object.
 *
 * @param includeNested Whether to include nested companion objects.
 * @return A list containing declarations with any companion object.
 */
fun <T : KoCompanionObjectProvider> List<T>.withCompanionObjects(includeNested: Boolean = true): List<T> =
    filter { it.hasCompanionObjects(includeNested) }

/**
 * List containing declarations with a none companion object.
 *
 * @param includeNested Whether to include an indirect companion object.
 * @return A list containing declarations with no companion object.
 */
fun <T : KoCompanionObjectProvider> List<T>.withoutCompanionObjects(includeNested: Boolean = true): List<T> =
    filterNot { it.hasCompanionObjects(includeNested) }

/**
 * List containing declarations with the specified companion object.
 *
 * @param includeNested Whether to include nested companion objects.
 * @param predicate The predicate function to determine if a declaration companion object satisfies a condition.
 * @return A list containing declarations with the specified companion object.
 */
fun <T : KoCompanionObjectProvider> List<T>.withCompanionObject(
    includeNested: Boolean = true,
    predicate: (KoCompanionObjectDeclaration) -> Boolean,
): List<T> = filter { it.hasCompanionObject(includeNested, predicate) }

/**
 * List containing declarations without the specified companion object.
 *
 * @param includeNested Whether to include nested companion objects.
 * @param predicate The predicate function to determine if a declaration companion object satisfies a condition.
 * @return A list containing declarations without the specified companion object (or none companion object if [predicate] is null).
 */
fun <T : KoCompanionObjectProvider> List<T>.withoutCompanionObject(
    includeNested: Boolean = true,
    predicate: (KoCompanionObjectDeclaration) -> Boolean,
): List<T> = filterNot { it.hasCompanionObject(includeNested, predicate) }

/**
 * List containing declarations that have all companion objects satisfying the provided predicate.
 *
 * @param includeNested Whether to include nested companion objects.
 * @param predicate A function that defines the condition to be met by all companion object declarations.
 * @return A filtered list containing declarations with all companion objects satisfying the predicate.
 */
fun <T : KoCompanionObjectProvider> List<T>.withAllCompanionObjects(
    includeNested: Boolean = true,
    predicate: (KoCompanionObjectDeclaration) -> Boolean,
): List<T> = filter { it.hasAllCompanionObjects(includeNested, predicate) }

/**
 * List containing declarations that have at least one companion object not satisfying the provided predicate.
 *
 * @param includeNested Whether to include nested companion objects.
 * @param predicate A function that defines the condition to be met by all companion object declarations.
 * @return A list containing declarations that have at least one companion object not satisfying the provided predicate.
 */
fun <T : KoCompanionObjectProvider> List<T>.withoutAllCompanionObjects(
    includeNested: Boolean = true,
    predicate: (KoCompanionObjectDeclaration) -> Boolean,
): List<T> = filterNot { it.hasAllCompanionObjects(includeNested, predicate) }

/**
 * List containing declarations with companion object declarations satisfying the predicate.
 *
 * @param includeNested Whether to include nested companion objects.
 * @param predicate A function that defines the condition to be met by the list of companion object declarations.
 * @return A list containing declarations with companion object declarations satisfying the predicate.
 */
fun <T : KoCompanionObjectProvider> List<T>.withCompanionObjects(
    includeNested: Boolean = true,
    predicate: (List<KoCompanionObjectDeclaration>) -> Boolean,
): List<T> = filter { predicate(it.companionObjects(includeNested)) }

/**
 * List containing declarations without companion object declarations satisfying the predicate.
 *
 * @param includeNested Whether to include nested companion objects.
 * @param predicate A function that defines the condition to be met by the list of companion object declarations.
 * @return A list containing declarations without companion object declarations satisfying the predicate.
 */
fun <T : KoCompanionObjectProvider> List<T>.withoutCompanionObjects(
    includeNested: Boolean = true,
    predicate: (List<KoCompanionObjectDeclaration>) -> Boolean,
): List<T> = filterNot { predicate(it.companionObjects(includeNested)) }

/**
 * List containing declarations that have a companion object with the specified name(s).
 *
 * @param name The name of the companion object to include.
 * @param names The names of an additional companion object to include.
 * @param includeNested Whether to include nested companion objects.
 * @param ignoreCase Specifies whether the comparison should ignore a case.
 *        If `true`, the prefix comparison will be case-insensitive.
 *        If `false`, the comparison will consider case sensitivity.
 * @return A list containing declarations with the specified companion object(s).
 */
fun <T : KoCompanionObjectProvider> List<T>.withCompanionObjectNamed(
    name: String,
    vararg names: String,
    includeNested: Boolean = true,
    ignoreCase: Boolean = false,
): List<T> = withCompanionObjectNamed(listOf(name, *names), includeNested, ignoreCase)

/**
 * List containing declarations that have a companion object with the specified name(s).
 *
 * @param names The names of an additional companion object to include.
 * @param includeNested Whether to include nested companion objects.
 * @param ignoreCase Specifies whether the comparison should ignore a case.
 *        If `true`, the prefix comparison will be case-insensitive.
 *        If `false`, the comparison will consider case sensitivity.
 * @return A list containing declarations with the specified companion object(s).
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
 * List containing declarations without any of a specified companion object.
 *
 * @param name The name of the companion object to exclude.
 * @param names The names of an additional companion object to exclude.
 * @param includeNested Whether to include nested companion objects.
 * @param ignoreCase Specifies whether the comparison should ignore a case.
 *        If `true`, the prefix comparison will be case-insensitive.
 *        If `false`, the comparison will consider case sensitivity.
 * @return A list containing declarations without any of a specified companion object.
 */
fun <T : KoCompanionObjectProvider> List<T>.withoutCompanionObjectNamed(
    name: String,
    vararg names: String,
    includeNested: Boolean = true,
    ignoreCase: Boolean = false,
): List<T> = withoutCompanionObjectNamed(listOf(name, *names), includeNested, ignoreCase)

/**
 * List containing declarations without any of a specified companion object.
 *
 * @param names The names of an additional companion object to exclude.
 * @param includeNested Whether to include nested companion objects.
 * @param ignoreCase Specifies whether the comparison should ignore a case.
 *        If `true`, the prefix comparison will be case-insensitive.
 *        If `false`, the comparison will consider case sensitivity.
 * @return A list containing declarations without any of a specified companion object.
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
 * List containing declarations that have all specified companion objects.
 *
 * @param name The name of the companion object to include.
 * @param names The name(s) of the companion object(s) to include it.
 * @param includeNested Whether to include nested companion objects.
 * @param ignoreCase Specifies whether the comparison should ignore a case.
 *        If `true`, the prefix comparison will be case-insensitive.
 *        If `false`, the comparison will consider case sensitivity.
 * @return A list containing declarations with all specified companion object(s).
 */
fun <T : KoCompanionObjectProvider> List<T>.withAllCompanionObjectsNamed(
    name: String,
    vararg names: String,
    includeNested: Boolean = true,
    ignoreCase: Boolean = false,
): List<T> = withAllCompanionObjectsNamed(listOf(name, *names), includeNested, ignoreCase)

/**
 * List containing declarations that have all specified companion objects.
 *
 * @param names The name(s) of the companion object(s) to include it.
 * @param includeNested Whether to include nested companion objects.
 * @param ignoreCase Specifies whether the comparison should ignore a case.
 *        If `true`, the prefix comparison will be case-insensitive.
 *        If `false`, the comparison will consider case sensitivity.
 * @return A list containing declarations with all specified companion object(s).
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
 * List containing declarations without all specified companion objects.
 *
 * @param name The name of the companion object to exclude.
 * @param names The name(s) of the companion object(s) to exclude.
 * @param includeNested Whether to include nested companion objects.
 * @param ignoreCase Specifies whether the comparison should ignore a case.
 *        If `true`, the prefix comparison will be case-insensitive.
 *        If `false`, the comparison will consider case sensitivity.
 * @return A list containing declarations without all specified companion object(s).
 */
fun <T : KoCompanionObjectProvider> List<T>.withoutAllCompanionObjectsNamed(
    name: String,
    vararg names: String,
    includeNested: Boolean = true,
    ignoreCase: Boolean = false,
): List<T> = withoutAllCompanionObjectsNamed(listOf(name, *names), includeNested, ignoreCase)

/**
 * List containing declarations without all specified companion objects.
 *
 * @param names The name(s) of the companion object(s) to exclude.
 * @param includeNested Whether to include nested companion objects.
 * @param ignoreCase Specifies whether the comparison should ignore a case.
 *        If `true`, the prefix comparison will be case-insensitive.
 *        If `false`, the comparison will consider case sensitivity.
 * @return A list containing declarations without all specified companion object(s).
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
