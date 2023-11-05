package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.declaration.KoExternalParentDeclaration
import com.lemonappdev.konsist.api.provider.KoExternalParentProvider
import kotlin.reflect.KClass

/**
 * List containing external parent declarations.
 * The external parent is a parent defined outside the project codebase (defined inside external library).
 *
 * @param indirectParents Whether to include indirect external parents.
 * @return A list containing external parent declarations.
 */
fun <T : KoExternalParentProvider> List<T>.externalParents(indirectParents: Boolean = false): List<KoExternalParentDeclaration> =
    flatMap { it.externalParents(indirectParents) }

/**
 * List containing declarations with any external parent.
 * The external parent is a parent defined outside the project codebase (defined inside external library).
 *
 * @param indirectParents Whether to include indirect external parents.
 * @return A list containing declarations with any external parent.
 */
fun <T : KoExternalParentProvider> List<T>.withExternalParents(indirectParents: Boolean = false): List<T> =
    filter { it.hasExternalParents(indirectParents) }

/**
 * List containing declarations with none external parent.
 * The external parent is a parent defined outside the project codebase (defined inside external library).
 *
 * @param indirectParents Whether to include indirect external parents.
 * @return A list containing declarations with no external parent.
 */
fun <T : KoExternalParentProvider> List<T>.withoutExternalParents(indirectParents: Boolean = false): List<T> =
    filterNot { it.hasExternalParents(indirectParents) }

/**
 * List containing declarations that have at least one external parent with the specified name(s).
 * The external parent is a parent defined outside the project codebase (defined inside external library).
 *
 * @param name The name of the external parent to include.
 * @param names The names of additional external parents to include.
 * @param indirectParents Whether to include indirect external parents.
 * @return A list containing declarations with at least one of the specified external parent(s).
 */
fun <T : KoExternalParentProvider> List<T>.withExternalParentNamed(
    name: String,
    vararg names: String,
    indirectParents: Boolean = false,
): List<T> =
    filter { it.hasExternalParentWithName(name, *names, indirectParents = indirectParents) }

/**
 * List containing declarations without any of specified external parents.
 * The external parent is a parent defined outside the project codebase (defined inside external library).
 *
 * @param name The name of the external parent to exclude.
 * @param names The names of additional external parents to exclude.
 * @param indirectParents Whether to include indirect external parents.
 * @return A list containing declarations without any of specified external parents.
 */
fun <T : KoExternalParentProvider> List<T>.withoutExternalParentNamed(
    name: String,
    vararg names: String,
    indirectParents: Boolean = false,
): List<T> =
    filterNot { it.hasExternalParentWithName(name, *names, indirectParents = indirectParents) }

/**
 * List containing declarations that have all specified external parents.
 * The external parent is a parent defined outside the project codebase (defined inside external library).
 *
 * @param name The name of the external parent to include.
 * @param names The name(s) of the external parent(s) to include.
 * @param indirectParents Whether to include indirect external parents.
 * @return A list containing declarations with all specified external parent(s).
 */
fun <T : KoExternalParentProvider> List<T>.withAllExternalParentsNamed(
    name: String,
    vararg names: String,
    indirectParents: Boolean = false,
): List<T> =
    filter { it.hasExternalParentsWithAllNames(name, *names, indirectParents = indirectParents) }

/**
 * List containing declarations without all specified external parents.
 * The external parent is a parent defined outside the project codebase (defined inside external library).
 *
 * @param name The name of the external parent to exclude.
 * @param names The name(s) of the external parent(s) to exclude.
 * @param indirectParents Whether to include indirect external parents.
 * @return A list containing declarations without all specified external parent(s).
 */
fun <T : KoExternalParentProvider> List<T>.withoutAllExternalParentsNamed(
    name: String,
    vararg names: String,
    indirectParents: Boolean = false,
): List<T> =
    filterNot { it.hasExternalParentsWithAllNames(name, *names, indirectParents = indirectParents) }

/**
 * List containing declarations that have at least one external parent satisfying the provided predicate.
 * The external parent is a parent defined outside the project codebase (defined inside external library).
 *
 * @param indirectParents Whether to include indirect external parents.
 * @param predicate A function that defines the condition to be met by an external parent declaration.
 * @return A list containing declarations with at least one external parent satisfying the predicate.
 */
fun <T : KoExternalParentProvider> List<T>.withExternalParent(
    indirectParents: Boolean = false,
    predicate: (KoExternalParentDeclaration) -> Boolean,
): List<T> =
    filter { it.hasExternalParent(indirectParents, predicate) }

/**
 * List containing declarations that not have external parent satisfying the provided predicate.
 * The external parent is a parent defined outside the project codebase (defined inside external library).
 *
 * @param indirectParents Whether to include indirect external parents.
 * @param predicate A function that defines the condition to be met by an external parent declaration.
 * @return A list containing declarations without external parent satisfying the provided predicate.
 */
fun <T : KoExternalParentProvider> List<T>.withoutExternalParent(
    indirectParents: Boolean = false,
    predicate: (KoExternalParentDeclaration) -> Boolean,
): List<T> =
    filterNot { it.hasExternalParent(indirectParents, predicate) }

/**
 * List containing declarations that have all external parents satisfying the provided predicate.
 * The external parent is a parent defined outside the project codebase (defined inside external library).
 *
 * @param indirectParents Whether to include indirect external parents.
 * @param predicate A function that defines the condition to be met by all external parent declarations.
 * @return A filtered list containing declarations with all external parents satisfying the predicate.
 */
fun <T : KoExternalParentProvider> List<T>.withAllExternalParents(
    indirectParents: Boolean = false,
    predicate: (KoExternalParentDeclaration) -> Boolean,
): List<T> =
    filter { it.hasAllExternalParents(indirectParents, predicate) }

/**
 * List containing declarations that have at least one external parent not satisfying the provided predicate.
 * The external parent is a parent defined outside the project codebase (defined inside external library).
 *
 * @param indirectParents Whether to include indirect external parents.
 * @param predicate A function that defines the condition to be met by all external parent declarations.
 * @return A list containing declarations that have at least one external parent not satisfying the provided predicate.
 */
fun <T : KoExternalParentProvider> List<T>.withoutAllExternalParents(
    indirectParents: Boolean = false,
    predicate: (KoExternalParentDeclaration) -> Boolean,
): List<T> = filterNot { it.hasAllExternalParents(indirectParents, predicate) }

/**
 * List containing declarations with external parent declarations satisfying the predicate.
 * The external parent is a parent defined outside the project codebase (defined inside external library).
 *
 * @param indirectParents Whether to include indirect external parents.
 * @param predicate A function that defines the condition to be met by the list of external parent declarations.
 * @return A list containing declarations with external parent declarations satisfying the predicate.
 */
fun <T : KoExternalParentProvider> List<T>.withExternalParents(
    indirectParents: Boolean = false,
    predicate: (List<KoExternalParentDeclaration>) -> Boolean,
): List<T> =
    filter { predicate(it.externalParents(indirectParents)) }

/**
 * List containing declarations without external parent declarations satisfying the predicate.
 * The external parent is a parent defined outside the project codebase (defined inside external library).
 *
 * @param indirectParents Whether to include indirect external parents.
 * @param predicate A function that defines the condition to be met by the list of external parent declarations.
 * @return A list containing declarations without external parent declarations satisfying the predicate.
 */
fun <T : KoExternalParentProvider> List<T>.withoutExternalParents(
    indirectParents: Boolean = false,
    predicate: (List<KoExternalParentDeclaration>) -> Boolean,
): List<T> =
    filterNot { predicate(it.externalParents(indirectParents)) }

/**
 * List containing declarations that have at least one external parent of the specified `KClass` type.
 * The external parent is a parent defined outside the project codebase (defined inside external library).
 *
 * @param kClass The Kotlin class representing external parent to include.
 * @param kClasses The Kotlin classes representing external parents to include.
 * @param indirectParents Whether to include indirect external parents.
 * @return A list containing declarations with at least one external parent of the specified `KClass` type.
 */
fun <T : KoExternalParentProvider> List<T>.withExternalParentOf(
    kClass: KClass<*>,
    vararg kClasses: KClass<*>,
    indirectParents: Boolean = false,
): List<T> =
    filter { it.hasExternalParentOf(kClass, *kClasses, indirectParents = indirectParents) }

/**
 * List containing declarations without any external parent of the specified `KClass` type.
 * The external parent is a parent defined outside the project codebase (defined inside external library).
 *
 * @param kClass The Kotlin class representing external parent to exclude.
 * @param kClasses The Kotlin classes representing external parents to exclude.
 * @param indirectParents Whether to include indirect external parents.
 * @return A list containing declarations without any of the specified external parents.
 */
fun <T : KoExternalParentProvider> List<T>.withoutExternalParentOf(
    kClass: KClass<*>,
    vararg kClasses: KClass<*>,
    indirectParents: Boolean = false,
): List<T> =
    filterNot { it.hasExternalParentOf(kClass, *kClasses, indirectParents = indirectParents) }

/**
 * List containing declarations that have all external parents of the specified `KClass` type.
 * The external parent is a parent defined outside the project codebase (defined inside external library).
 *
 * @param kClass The Kotlin class representing external parent to include.
 * @param kClasses The Kotlin classes representing external parents to include.
 * @param indirectParents Whether to include indirect external parents.
 * @return A list containing declarations that have all external parents of the specified `KClass` type.
 */
fun <T : KoExternalParentProvider> List<T>.withAllExternalParentsOf(
    kClass: KClass<*>,
    vararg kClasses: KClass<*>,
    indirectParents: Boolean = false,
): List<T> =
    filter { it.hasAllExternalParentsOf(kClass, *kClasses, indirectParents = indirectParents) }

/**
 * List containing declarations without all specified `KClass` type external parents.
 * The external parent is a parent defined outside the project codebase (defined inside external library).
 *
 * @param kClass The Kotlin class representing external parent to exclude.
 * @param kClasses The Kotlin classes representing external parents to exclude.
 * @param indirectParents Whether to include indirect external parents.
 * @return A list containing declarations without all specified `KClass` type external parents.
 */
fun <T : KoExternalParentProvider> List<T>.withoutAllExternalParentsOf(
    kClass: KClass<*>,
    vararg kClasses: KClass<*>,
    indirectParents: Boolean = false,
): List<T> =
    filterNot { it.hasAllExternalParentsOf(kClass, *kClasses, indirectParents = indirectParents) }
