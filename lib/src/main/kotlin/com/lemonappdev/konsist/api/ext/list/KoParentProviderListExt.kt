@file:Suppress("detekt.TooManyFunctions")

package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.declaration.KoParentDeclaration
import com.lemonappdev.konsist.api.provider.KoParentProvider
import kotlin.reflect.KClass

/**
 * List containing parent declarations.
 *
 * @param indirectParents Whether to include indirect parents.
 * @return A list containing parent declarations.
 */
fun <T : KoParentProvider> List<T>.parents(indirectParents: Boolean = false): List<KoParentDeclaration> =
    flatMap { it.parents(indirectParents) }

/**
 * List containing declarations with any parent.
 *
 * @param indirectParents Whether to include indirect parents.
 * @return A list containing declarations with any parent.
 */
fun <T : KoParentProvider> List<T>.withParents(indirectParents: Boolean = false): List<T> = filter { it.hasParents(indirectParents) }

/**
 * List containing declarations with none parent - declaration does not extend any class and does not implement any interface.
 *
 * @param indirectParents Whether to include indirect parents.
 * @return A list containing declarations with no parent - declaration does not extend any class and does not implement any
 * interface.
 */
fun <T : KoParentProvider> List<T>.withoutParents(indirectParents: Boolean = false): List<T> = filterNot { it.hasParents(indirectParents) }

/**
 * List containing declarations that have at least one parent with the specified name(s).
 *
 * @param name The name of the parent to include.
 * @param names The names of additional parents to include.
 * @param indirectParents Whether to include indirect parents.
 * @return A list containing declarations with at least one of the specified parent(s).
 */
fun <T : KoParentProvider> List<T>.withParentNamed(
    name: String,
    vararg names: String,
    indirectParents: Boolean = false,
): List<T> = withParentNamed(listOf(name, *names), indirectParents)

/**
 * List containing declarations that have at least one parent with the specified name(s).
 *
 * @param names The names of additional parents to include.
 * @param indirectParents Whether to include indirect parents.
 * @return A list containing declarations with at least one of the specified parent(s).
 */
fun <T : KoParentProvider> List<T>.withParentNamed(
    names: Collection<String>,
    indirectParents: Boolean = false,
): List<T> =
    filter {
        when {
            names.isEmpty() -> it.hasParents(indirectParents)
            else -> it.hasParentWithName(names, indirectParents = indirectParents)
        }
    }

/**
 * List containing declarations without any of specified parents.
 *
 * @param name The name of the parent to exclude.
 * @param names The names of additional parents to exclude.
 * @param indirectParents Whether to include indirect parents.
 * @return A list containing declarations without any of specified parents.
 */
fun <T : KoParentProvider> List<T>.withoutParentNamed(
    name: String,
    vararg names: String,
    indirectParents: Boolean = false,
): List<T> = withoutParentNamed(listOf(name, *names), indirectParents)

/**
 * List containing declarations without any of specified parents.
 *
 * @param names The names of additional parents to exclude.
 * @param indirectParents Whether to include indirect parents.
 * @return A list containing declarations without any of specified parents.
 */
fun <T : KoParentProvider> List<T>.withoutParentNamed(
    names: Collection<String>,
    indirectParents: Boolean = false,
): List<T> =
    filterNot {
        when {
            names.isEmpty() -> it.hasParents(indirectParents)
            else -> it.hasParentWithName(names, indirectParents = indirectParents)
        }
    }

/**
 * List containing declarations that have all specified parents.
 *
 * @param name The name of the parent to include.
 * @param names The name(s) of the parent(s) to include.
 * @param indirectParents Whether to include indirect parents.
 * @return A list containing declarations with all specified parent(s).
 */
fun <T : KoParentProvider> List<T>.withAllParentsNamed(
    name: String,
    vararg names: String,
    indirectParents: Boolean = false,
): List<T> = withAllParentsNamed(listOf(name, *names), indirectParents)

/**
 * List containing declarations that have all specified parents.
 *
 * @param names The name(s) of the parent(s) to include.
 * @param indirectParents Whether to include indirect parents.
 * @return A list containing declarations with all specified parent(s).
 */
fun <T : KoParentProvider> List<T>.withAllParentsNamed(
    names: Collection<String>,
    indirectParents: Boolean = false,
): List<T> =
    filter {
        when {
            names.isEmpty() -> it.hasParents(indirectParents)
            else -> it.hasParentsWithAllNames(names, indirectParents = indirectParents)
        }
    }

/**
 * List containing declarations without all specified parents.
 *
 * @param name The name of the parent to exclude.
 * @param names The name(s) of the parent(s) to exclude.
 * @param indirectParents Whether to include indirect parents.
 * @return A list containing declarations without all specified parent(s).
 */
fun <T : KoParentProvider> List<T>.withoutAllParentsNamed(
    name: String,
    vararg names: String,
    indirectParents: Boolean = false,
): List<T> = withoutAllParentsNamed(listOf(name, *names), indirectParents)

/**
 * List containing declarations without all specified parents.
 *
 * @param names The name(s) of the parent(s) to exclude.
 * @param indirectParents Whether to include indirect parents.
 * @return A list containing declarations without all specified parent(s).
 */
fun <T : KoParentProvider> List<T>.withoutAllParentsNamed(
    names: Collection<String>,
    indirectParents: Boolean = false,
): List<T> =
    filterNot {
        when {
            names.isEmpty() -> it.hasParents(indirectParents)
            else -> it.hasParentsWithAllNames(names, indirectParents = indirectParents)
        }
    }

/**
 * List containing declarations that have at least one parent satisfying the provided predicate.
 *
 * @param indirectParents Whether to include indirect parents.
 * @param predicate A function that defines the condition to be met by a parent declaration.
 * @return A list containing declarations with at least one parent satisfying the predicate.
 */
fun <T : KoParentProvider> List<T>.withParent(
    indirectParents: Boolean = false,
    predicate: (KoParentDeclaration) -> Boolean,
): List<T> =
    filter {
        it.hasParent(indirectParents, predicate)
    }

/**
 * List containing declarations that not have parent satisfying the provided predicate.
 *
 * @param indirectParents Whether to include indirect parents.
 * @param predicate A function that defines the condition to be met by a parent declaration.
 * @return A list containing declarations without parent satisfying the provided predicate.
 */
fun <T : KoParentProvider> List<T>.withoutParent(
    indirectParents: Boolean = false,
    predicate: (KoParentDeclaration) -> Boolean,
): List<T> =
    filterNot {
        it.hasParent(indirectParents, predicate)
    }

/**
 * List containing declarations that have all parents satisfying the provided predicate.
 *
 * @param indirectParents Whether to include indirect parents.
 * @param predicate A function that defines the condition to be met by all parent declarations.
 * @return A filtered list containing declarations with all parents satisfying the predicate.
 */
fun <T : KoParentProvider> List<T>.withAllParents(
    indirectParents: Boolean = false,
    predicate: (KoParentDeclaration) -> Boolean,
): List<T> =
    filter {
        it.hasAllParents(indirectParents, predicate)
    }

/**
 * List containing declarations that have at least one parent not satisfying the provided predicate.
 *
 * @param indirectParents Whether to include indirect parents.
 * @param predicate A function that defines the condition to be met by all parent declarations.
 * @return A list containing declarations that have at least one parent not satisfying the provided predicate.
 */
fun <T : KoParentProvider> List<T>.withoutAllParents(
    indirectParents: Boolean = false,
    predicate: (KoParentDeclaration) -> Boolean,
): List<T> =
    filterNot {
        it.hasAllParents(indirectParents, predicate)
    }

/**
 * List containing declarations with parent declarations satisfying the predicate.
 *
 * @param indirectParents Whether to include indirect parents.
 * @param predicate A function that defines the condition to be met by the list of parent declarations.
 * @return A list containing declarations with parent declarations satisfying the predicate.
 */
fun <T : KoParentProvider> List<T>.withParents(
    indirectParents: Boolean = false,
    predicate: (List<KoParentDeclaration>) -> Boolean,
): List<T> =
    filter {
        predicate(it.parents(indirectParents))
    }

/**
 * List containing declarations without parent declarations satisfying the predicate.
 *
 * @param indirectParents Whether to include indirect parents.
 * @param predicate A function that defines the condition to be met by the list of parent declarations.
 * @return A list containing declarations without parent declarations satisfying the predicate.
 */
fun <T : KoParentProvider> List<T>.withoutParents(
    indirectParents: Boolean = false,
    predicate: (List<KoParentDeclaration>) -> Boolean,
): List<T> = filterNot { predicate(it.parents(indirectParents)) }

/**
 * List containing declarations that have at least one parent of the specified `KClass` type.
 *
 * @param kClass The Kotlin class representing parent to include.
 * @param kClasses The Kotlin classes representing parents to include.
 * @param indirectParents Whether to include indirect parents.
 * @return A list containing declarations with at least one parent of the specified `KClass` type.
 */
fun <T : KoParentProvider> List<T>.withParentOf(
    kClass: KClass<*>,
    vararg kClasses: KClass<*>,
    indirectParents: Boolean = false,
): List<T> = withParentOf(listOf(kClass, *kClasses), indirectParents)

/**
 * List containing declarations that have at least one parent of the specified `KClass` type.
 *
 * @param kClasses The Kotlin classes representing parents to include.
 * @param indirectParents Whether to include indirect parents.
 * @return A list containing declarations with at least one parent of the specified `KClass` type.
 */
fun <T : KoParentProvider> List<T>.withParentOf(
    kClasses: Collection<KClass<*>>,
    indirectParents: Boolean = false,
): List<T> =
    filter {
        when {
            kClasses.isEmpty() -> it.hasParents(indirectParents)
            else -> it.hasParentOf(kClasses, indirectParents = indirectParents)
        }
    }

/**
 * List containing declarations without any parent of the specified `KClass` type.
 *
 * @param kClass The Kotlin class representing parent to exclude.
 * @param kClasses The Kotlin classes representing parents to exclude.
 * @param indirectParents Whether to include indirect parents.
 * @return A list containing declarations without any of the specified parents.
 */
fun <T : KoParentProvider> List<T>.withoutParentOf(
    kClass: KClass<*>,
    vararg kClasses: KClass<*>,
    indirectParents: Boolean = false,
): List<T> = withoutParentOf(listOf(kClass, *kClasses), indirectParents)

/**
 * List containing declarations without any parent of the specified `KClass` type.
 *
 * @param kClasses The Kotlin classes representing parents to exclude.
 * @param indirectParents Whether to include indirect parents.
 * @return A list containing declarations without any of the specified parents.
 */
fun <T : KoParentProvider> List<T>.withoutParentOf(
    kClasses: Collection<KClass<*>>,
    indirectParents: Boolean = false,
): List<T> =
    filterNot {
        when {
            kClasses.isEmpty() -> it.hasParents(indirectParents)
            else -> it.hasParentOf(kClasses, indirectParents = indirectParents)
        }
    }

/**
 * List containing declarations that have all parents of the specified `KClass` type.
 *
 * @param kClass The Kotlin class representing parent to include.
 * @param kClasses The Kotlin classes representing parents to include.
 * @param indirectParents Whether to include indirect parents.
 * @return A list containing declarations that have all parents of the specified `KClass` type.
 */
fun <T : KoParentProvider> List<T>.withAllParentsOf(
    kClass: KClass<*>,
    vararg kClasses: KClass<*>,
    indirectParents: Boolean = false,
): List<T> = withAllParentsOf(listOf(kClass, *kClasses), indirectParents)

/**
 * List containing declarations that have all parents of the specified `KClass` type.
 *
 * @param kClasses The Kotlin classes representing parents to include.
 * @param indirectParents Whether to include indirect parents.
 * @return A list containing declarations that have all parents of the specified `KClass` type.
 */
fun <T : KoParentProvider> List<T>.withAllParentsOf(
    kClasses: Collection<KClass<*>>,
    indirectParents: Boolean = false,
): List<T> =
    filter {
        when {
            kClasses.isEmpty() -> it.hasParents(indirectParents)
            else -> it.hasAllParentsOf(kClasses, indirectParents = indirectParents)
        }
    }

/**
 * List containing declarations without all specified `KClass` type parents.
 *
 * @param kClass The Kotlin class representing parent to exclude.
 * @param kClasses The Kotlin classes representing parents to exclude.
 * @param indirectParents Whether to include indirect parents.
 * @return A list containing declarations without all specified `KClass` type parents.
 */
fun <T : KoParentProvider> List<T>.withoutAllParentsOf(
    kClass: KClass<*>,
    vararg kClasses: KClass<*>,
    indirectParents: Boolean = false,
): List<T> = withoutAllParentsOf(listOf(kClass, *kClasses), indirectParents)

/**
 * List containing declarations without all specified `KClass` type parents.
 *
 * @param kClasses The Kotlin classes representing parents to exclude.
 * @param indirectParents Whether to include indirect parents.
 * @return A list containing declarations without all specified `KClass` type parents.
 */
fun <T : KoParentProvider> List<T>.withoutAllParentsOf(
    kClasses: Collection<KClass<*>>,
    indirectParents: Boolean = false,
): List<T> =
    filterNot {
        when {
            kClasses.isEmpty() -> it.hasParents(indirectParents)
            else -> it.hasAllParentsOf(kClasses, indirectParents = indirectParents)
        }
    }
