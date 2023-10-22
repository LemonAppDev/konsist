package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.declaration.KoExternalParentDeclaration
import com.lemonappdev.konsist.api.provider.KoExternalParentProvider
import kotlin.reflect.KClass

/**
 * List containing external parents.
 */
val <T : KoExternalParentProvider> List<T>.externalParents: List<KoExternalParentDeclaration>
    get() = flatMap { it.externalParents }

/**
 * List containing declarations with external parent.
 *
 * @return A list containing declarations with any external parent.
 */
fun <T : KoExternalParentProvider> List<T>.withExternalParents(): List<T> = filter { it.hasExternalParents() }

/**
 * List containing declarations with no external parent.
 *
 * @return A list containing declarations with no external parent.
 */
fun <T : KoExternalParentProvider> List<T>.withoutExternalParents(): List<T> = filterNot { it.hasExternalParents() }

/**
 * List containing declarations that have at least one external parent with the specified name(s).
 *
 * @param name The name of the external parent to include.
 * @param names The names of additional external parents to include.
 * @return A list containing declarations with at least one of the specified external parent(s).
 */
fun <T : KoExternalParentProvider> List<T>.withExternalParentNamed(name: String, vararg names: String): List<T> =
    filter { it.hasExternalParentWithName(name, *names) }

/**
 * List containing declarations without any of specified external parents.
 *
 * @param name The name of the external parent to exclude.
 * @param names The names of additional external parents to exclude.
 * @return A list containing declarations without any of specified external parents.
 */
fun <T : KoExternalParentProvider> List<T>.withoutExternalParentNamed(name: String, vararg names: String): List<T> =
    filterNot { it.hasExternalParentWithName(name, *names) }

/**
 * List containing declarations that have all specified external parents.
 *
 * @param name The name of the external parent to include.
 * @param names The name(s) of the external parent(s) to include.
 * @return A list containing declarations with all specified external parent(s).
 */
fun <T : KoExternalParentProvider> List<T>.withAllExternalParentsNamed(name: String, vararg names: String): List<T> =
    filter { it.hasExternalParentsWithAllNames(name, *names) }

/**
 * List containing declarations without all specified external parents.
 *
 * @param name The name of the external parent to exclude.
 * @param names The name(s) of the external parent(s) to exclude.
 * @return A list containing declarations without all specified external parent(s).
 */
fun <T : KoExternalParentProvider> List<T>.withoutAllExternalParentsNamed(name: String, vararg names: String): List<T> =
    filterNot { it.hasExternalParentsWithAllNames(name, *names) }

/**
 * List containing declarations that have at least one external parent satisfying the provided predicate.
 *
 * @param predicate A function that defines the condition to be met by an external parent declaration.
 * @return A list containing declarations with at least one external parent satisfying the predicate.
 */
fun <T : KoExternalParentProvider> List<T>.withExternalParent(predicate: (KoExternalParentDeclaration) -> Boolean): List<T> =
    filter { it.hasExternalParent(predicate) }

/**
 * List containing declarations that not have external parent satisfying the provided predicate.
 *
 * @param predicate A function that defines the condition to be met by an external parent declaration.
 * @return A list containing declarations without external parent satisfying the provided predicate.
 */
fun <T : KoExternalParentProvider> List<T>.withoutExternalParent(predicate: (KoExternalParentDeclaration) -> Boolean): List<T> =
    filterNot { it.hasExternalParent(predicate) }

/**
 * List containing declarations that have all external parents satisfying the provided predicate.
 *
 * @param predicate A function that defines the condition to be met by all external parent declarations.
 * @return A filtered list containing declarations with all external parents satisfying the predicate.
 */
fun <T : KoExternalParentProvider> List<T>.withAllExternalParents(predicate: (KoExternalParentDeclaration) -> Boolean): List<T> =
    filter { it.hasAllExternalParents(predicate) }

/**
 * List containing declarations that have at least one external parent not satisfying the provided predicate.
 *
 * @param predicate A function that defines the condition to be met by all external parent declarations.
 * @return A list containing declarations that have at least one external parent not satisfying the provided predicate.
 */
fun <T : KoExternalParentProvider> List<T>.withoutAllExternalParents(
    predicate: (KoExternalParentDeclaration) -> Boolean,
): List<T> = filterNot { it.hasAllExternalParents(predicate) }

/**
 * List containing declarations with external parent declarations satisfying the predicate.
 *
 * @param predicate A function that defines the condition to be met by the list of external parent declarations.
 * @return A list containing declarations with external parent declarations satisfying the predicate.
 */
fun <T : KoExternalParentProvider> List<T>.withExternalParents(predicate: (List<KoExternalParentDeclaration>) -> Boolean): List<T> =
    filter { predicate(it.externalParents) }

/**
 * List containing declarations without external parent declarations satisfying the predicate.
 *
 * @param predicate A function that defines the condition to be met by the list of external parent declarations.
 * @return A list containing declarations without external parent declarations satisfying the predicate.
 */
fun <T : KoExternalParentProvider> List<T>.withoutExternalParents(predicate: (List<KoExternalParentDeclaration>) -> Boolean): List<T> =
    filterNot { predicate(it.externalParents) }

/**
 * List containing declarations that have at least one external parent of the specified `KClass` type.
 *
 * @param kClass The Kotlin class representing external parent to include.
 * @param kClasses The Kotlin classes representing external parents to include.
 * @return A list containing declarations with at least one external parent of the specified `KClass` type.
 */
fun <T : KoExternalParentProvider> List<T>.withExternalParentOf(
    kClass: KClass<*>,
    vararg kClasses: KClass<*>,
): List<T> =
    filter { it.hasExternalParentOf(kClass, *kClasses) }

/**
 * List containing declarations without any external parent of the specified `KClass` type.
 *
 * @param kClass The Kotlin class representing external parent to exclude.
 * @param kClasses The Kotlin classes representing external parents to exclude.
 * @return A list containing declarations without any of the specified external parents.
 */
fun <T : KoExternalParentProvider> List<T>.withoutExternalParentOf(
    kClass: KClass<*>,
    vararg kClasses: KClass<*>,
): List<T> =
    filterNot { it.hasExternalParentOf(kClass, *kClasses) }

/**
 * List containing declarations that have all external parents of the specified `KClass` type.
 *
 * @param kClass The Kotlin class representing external parent to include.
 * @param kClasses The Kotlin classes representing external parents to include.
 * @return A list containing declarations that have all external parents of the specified `KClass` type.
 */
fun <T : KoExternalParentProvider> List<T>.withAllExternalParentsOf(
    kClass: KClass<*>,
    vararg kClasses: KClass<*>,
): List<T> =
    filter { it.hasAllExternalParentsOf(kClass, *kClasses) }

/**
 * List containing declarations without all specified `KClass` type external parents.
 *
 * @param kClass The Kotlin class representing external parent to exclude.
 * @param kClasses The Kotlin classes representing external parents to exclude.
 * @return A list containing declarations without all specified `KClass` type external parents.
 */
fun <T : KoExternalParentProvider> List<T>.withoutAllExternalParentsOf(
    kClass: KClass<*>,
    vararg kClasses: KClass<*>,
): List<T> =
    filterNot { it.hasAllExternalParentsOf(kClass, *kClasses) }
