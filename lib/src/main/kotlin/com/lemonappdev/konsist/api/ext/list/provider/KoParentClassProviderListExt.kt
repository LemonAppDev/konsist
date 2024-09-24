@file:Suppress("detekt.TooManyFunctions")

package com.lemonappdev.konsist.api.ext.list.provider

import com.lemonappdev.konsist.api.declaration.KoClassDeclaration
import com.lemonappdev.konsist.api.provider.KoParentClassProvider
import kotlin.reflect.KClass

/**
 * List containing direct parent classes.
 */
val <T : KoParentClassProvider> List<T>.parentClasses: List<KoClassDeclaration>
    get() = mapNotNull { it.parentClass }

/**
 * List containing parent classes.
 *
 * @param indirectParents Whether to include indirect parent classes.
 * @return A list containing parent class declarations.
 */
fun <T : KoParentClassProvider> List<T>.parentClasses(indirectParents: Boolean = false): List<KoClassDeclaration> =
    flatMap { it.parentClasses(indirectParents) }

/**
 * List containing declarations with any direct parent class.
 *
 * @return A list containing declarations with any direct parent class.
 */
fun <T : KoParentClassProvider> List<T>.withParentClass(): List<T> = filter { it.hasParentClass() }

/**
 * List containing declarations without direct parent class.
 *
 * @return A list containing declarations with none direct parent class.
 */
fun <T : KoParentClassProvider> List<T>.withoutParentClass(): List<T> = filterNot { it.hasParentClass() }

/**
 * List containing declarations with any parent class.
 *
 * @param indirectParents Whether to include indirect parent classes.
 * @return A list containing declarations with any parent class.
 */
fun <T : KoParentClassProvider> List<T>.withParentClasses(indirectParents: Boolean = false): List<T> =
    filter { it.hasParentClasses(indirectParents) }

/**
 * List containing declarations with none parent class.
 *
 * @param indirectParents Whether to include indirect parent classes.
 * @return A list containing declarations with no parent class.
 */
fun <T : KoParentClassProvider> List<T>.withoutParentClasses(indirectParents: Boolean = false): List<T> =
    filterNot { it.hasParentClasses(indirectParents) }

/**
 * List containing declarations with the specified parent class.
 *
 * @param indirectParents Whether to include indirect parent classes.
 * @param predicate The predicate function to determine if a declaration parent class satisfies a condition.
 * @return A list containing declarations with the specified parent class.
 */
fun <T : KoParentClassProvider> List<T>.withParentClass(
    indirectParents: Boolean = false,
    predicate: (KoClassDeclaration) -> Boolean,
): List<T> = filter { it.hasParentClass(indirectParents, predicate) }

/**
 * List containing declarations without the specified parent class.
 *
 * @param indirectParents Whether to include indirect parent classes.
 * @param predicate The predicate function to determine if a declaration parent class satisfies a condition.
 * @return A list containing declarations without the specified parent class (or none parent class if [predicate] is null).
 */
fun <T : KoParentClassProvider> List<T>.withoutParentClass(
    indirectParents: Boolean = false,
    predicate: (KoClassDeclaration) -> Boolean,
): List<T> = filterNot { it.hasParentClass(indirectParents, predicate) }

/**
 * List containing declarations that have all parent classes satisfying the provided predicate.
 *
 * @param indirectParents Whether to include indirect parent classes.
 * @param predicate A function that defines the condition to be met by all parent class declarations.
 * @return A filtered list containing declarations with all parent classes satisfying the predicate.
 */
fun <T : KoParentClassProvider> List<T>.withAllParentClasses(
    indirectParents: Boolean = false,
    predicate: (KoClassDeclaration) -> Boolean,
): List<T> = filter { it.hasAllParentClasses(indirectParents, predicate) }

/**
 * List containing declarations that have at least one parent class not satisfying the provided predicate.
 *
 * @param indirectParents Whether to include indirect parent classes.
 * @param predicate A function that defines the condition to be met by all parent class declarations.
 * @return A list containing declarations that have at least one parent class not satisfying the provided predicate.
 */
fun <T : KoParentClassProvider> List<T>.withoutAllParentClasses(
    indirectParents: Boolean = false,
    predicate: (KoClassDeclaration) -> Boolean,
): List<T> = filterNot { it.hasAllParentClasses(indirectParents, predicate) }

/**
 * List containing declarations with parent class declarations satisfying the predicate.
 *
 * @param indirectParents Whether to include indirect parent classes.
 * @param predicate A function that defines the condition to be met by the list of parent class declarations.
 * @return A list containing declarations with parent class declarations satisfying the predicate.
 */
fun <T : KoParentClassProvider> List<T>.withParentClasses(
    indirectParents: Boolean = false,
    predicate: (List<KoClassDeclaration>) -> Boolean,
): List<T> = filter { predicate(it.parentClasses(indirectParents)) }

/**
 * List containing declarations without parent class declarations satisfying the predicate.
 *
 * @param indirectParents Whether to include indirect parent classes.
 * @param predicate A function that defines the condition to be met by the list of parent class declarations.
 * @return A list containing declarations without parent class declarations satisfying the predicate.
 */
fun <T : KoParentClassProvider> List<T>.withoutParentClasses(
    indirectParents: Boolean = false,
    predicate: (List<KoClassDeclaration>) -> Boolean,
): List<T> = filterNot { predicate(it.parentClasses(indirectParents)) }

/**
 * List containing declarations that have parent class with the specified name(s).
 *
 * @param name The name of the parent class to include.
 * @param names The names of additional parent classes to include.
 * @param indirectParents Whether to include indirect parent classes.
 * @return A list containing declarations with the specified parent class(es).
 */
fun <T : KoParentClassProvider> List<T>.withParentClassNamed(
    name: String,
    vararg names: String,
    indirectParents: Boolean = false,
): List<T> = withParentClassNamed(listOf(name, *names), indirectParents)

/**
 * List containing declarations that have parent class with the specified name(s).
 *
 * @param names The names of additional parent classes to include.
 * @param indirectParents Whether to include indirect parent classes.
 * @return A list containing declarations with the specified parent class(es).
 */
fun <T : KoParentClassProvider> List<T>.withParentClassNamed(
    names: Collection<String>,
    indirectParents: Boolean = false,
): List<T> =
    filter {
        when {
            names.isEmpty() -> it.hasParentClasses(indirectParents)
            else -> it.hasParentClassWithName(names, indirectParents = indirectParents)
        }
    }

/**
 * List containing declarations without any of specified parent classes.
 *
 * @param name The name of the parent class to exclude.
 * @param names The names of additional parent classes to exclude.
 * @param indirectParents Whether to include indirect parent classes.
 * @return A list containing declarations without any of specified parent classes.
 */
fun <T : KoParentClassProvider> List<T>.withoutParentClassNamed(
    name: String,
    vararg names: String,
    indirectParents: Boolean = false,
): List<T> = withoutParentClassNamed(listOf(name, *names), indirectParents)

/**
 * List containing declarations without any of specified parent classes.
 *
 * @param names The names of additional parent classes to exclude.
 * @param indirectParents Whether to include indirect parent classes.
 * @return A list containing declarations without any of specified parent classes.
 */
fun <T : KoParentClassProvider> List<T>.withoutParentClassNamed(
    names: Collection<String>,
    indirectParents: Boolean = false,
): List<T> =
    filterNot {
        when {
            names.isEmpty() -> it.hasParentClasses(indirectParents)
            else -> it.hasParentClassWithName(names, indirectParents = indirectParents)
        }
    }

/**
 * List containing declarations that have all specified parent classes.
 *
 * @param name The name of the parent class to include.
 * @param names The name(s) of the parent class(es) to include.
 * @param indirectParents Whether to include indirect parent classes.
 * @return A list containing declarations with all specified parent class(es).
 */
fun <T : KoParentClassProvider> List<T>.withAllParentClassesNamed(
    name: String,
    vararg names: String,
    indirectParents: Boolean = false,
): List<T> = withAllParentClassesNamed(listOf(name, *names), indirectParents)

/**
 * List containing declarations that have all specified parent classes.
 *
 * @param names The name(s) of the parent class(es) to include.
 * @param indirectParents Whether to include indirect parent classes.
 * @return A list containing declarations with all specified parent class(es).
 */
fun <T : KoParentClassProvider> List<T>.withAllParentClassesNamed(
    names: Collection<String>,
    indirectParents: Boolean = false,
): List<T> =
    filter {
        when {
            names.isEmpty() -> it.hasParentClasses(indirectParents)
            else -> it.hasParentClassesWithAllNames(names, indirectParents = indirectParents)
        }
    }

/**
 * List containing declarations without all specified parent classes.
 *
 * @param name The name of the parent class to exclude.
 * @param names The name(s) of the parent class(es) to exclude.
 * @param indirectParents Whether to include indirect parent classes.
 * @return A list containing declarations without all specified parent class(es).
 */
fun <T : KoParentClassProvider> List<T>.withoutAllParentClassesNamed(
    name: String,
    vararg names: String,
    indirectParents: Boolean = false,
): List<T> = withoutAllParentClassesNamed(listOf(name, *names), indirectParents)

/**
 * List containing declarations without all specified parent classes.
 *
 * @param names The name(s) of the parent class(es) to exclude.
 * @param indirectParents Whether to include indirect parent classes.
 * @return A list containing declarations without all specified parent class(es).
 */
fun <T : KoParentClassProvider> List<T>.withoutAllParentClassesNamed(
    names: Collection<String>,
    indirectParents: Boolean = false,
): List<T> =
    filterNot {
        when {
            names.isEmpty() -> it.hasParentClasses(indirectParents)
            else -> it.hasParentClassesWithAllNames(names, indirectParents = indirectParents)
        }
    }

/**
 * List containing declarations that have parent class of type.
 *
 * @param kClass The Kotlin declaration representing the parent class to include.
 * @param kClasses The Kotlin declarations representing the parent class to include.
 * @param indirectParents Whether to include indirect parent classes.
 * @return A list containing declarations that have the parent class of the specified type(s).
 */
fun <T : KoParentClassProvider> List<T>.withParentClassOf(
    kClass: KClass<*>,
    vararg kClasses: KClass<*>,
    indirectParents: Boolean = false,
): List<T> = withParentClassOf(listOf(kClass, *kClasses), indirectParents)

/**
 * List containing declarations that have parent class of type.
 *
 * @param kClasses The Kotlin declarations representing the parent class to include.
 * @param indirectParents Whether to include indirect parent classes.
 * @return A list containing declarations that have the parent class of the specified type(s).
 */
fun <T : KoParentClassProvider> List<T>.withParentClassOf(
    kClasses: Collection<KClass<*>>,
    indirectParents: Boolean = false,
): List<T> =
    filter {
        when {
            kClasses.isEmpty() -> it.hasParentClasses(indirectParents)
            else -> it.hasParentClassOf(kClasses, indirectParents = indirectParents)
        }
    }

/**
 * List containing declarations without parent class.
 *
 * @param kClass The Kotlin class representing the parent class to exclude.
 * @param kClasses The Kotlin class(es) representing the parent class(es) to exclude.
 * @param indirectParents Whether to include indirect parent classes.
 * @return A list containing declarations without parent class of the specified Kotlin class(es).
 */
fun <T : KoParentClassProvider> List<T>.withoutParentClassOf(
    kClass: KClass<*>,
    vararg kClasses: KClass<*>,
    indirectParents: Boolean = false,
): List<T> = withoutParentClassOf(listOf(kClass, *kClasses), indirectParents)

/**
 * List containing declarations without parent class.
 *
 * @param kClasses The Kotlin class(es) representing the parent class(es) to exclude.
 * @param indirectParents Whether to include indirect parent classes.
 * @return A list containing declarations without parent class of the specified Kotlin class(es).
 */
fun <T : KoParentClassProvider> List<T>.withoutParentClassOf(
    kClasses: Collection<KClass<*>>,
    indirectParents: Boolean = false,
): List<T> =
    filterNot {
        when {
            kClasses.isEmpty() -> it.hasParentClasses(indirectParents)
            else -> it.hasParentClassOf(kClasses, indirectParents = indirectParents)
        }
    }

/**
 * List containing declarations that have all parent classes of the specified `KClass` type.
 *
 * @param kClass The Kotlin class representing parent class to include.
 * @param kClasses The Kotlin classes representing parent classes to include.
 * @param indirectParents Whether to include indirect parent classes.
 * @return A list containing declarations that have all parent classes of the specified `KClass` type.
 */
fun <T : KoParentClassProvider> List<T>.withAllParentClassesOf(
    kClass: KClass<*>,
    vararg kClasses: KClass<*>,
    indirectParents: Boolean = false,
): List<T> = withAllParentClassesOf(listOf(kClass, *kClasses), indirectParents)

/**
 * List containing declarations that have all parent classes of the specified `KClass` type.
 *
 * @param kClasses The Kotlin classes representing parent classes to include.
 * @param indirectParents Whether to include indirect parent classes.
 * @return A list containing declarations that have all parent classes of the specified `KClass` type.
 */
fun <T : KoParentClassProvider> List<T>.withAllParentClassesOf(
    kClasses: Collection<KClass<*>>,
    indirectParents: Boolean = false,
): List<T> =
    filter {
        when {
            kClasses.isEmpty() -> it.hasParentClasses(indirectParents)
            else -> it.hasAllParentClassesOf(kClasses, indirectParents = indirectParents)
        }
    }

/**
 * List containing declarations without all specified `KClass` type parent classes.
 *
 * @param kClass The Kotlin class representing parent class to exclude.
 * @param kClasses The Kotlin classes representing parent classes to exclude.
 * @param indirectParents Whether to include indirect parent classes.
 * @return A list containing declarations without all specified `KClass` type parent classes.
 */
fun <T : KoParentClassProvider> List<T>.withoutAllParentClassesOf(
    kClass: KClass<*>,
    vararg kClasses: KClass<*>,
    indirectParents: Boolean = false,
): List<T> = withoutAllParentClassesOf(listOf(kClass, *kClasses), indirectParents)

/**
 * List containing declarations without all specified `KClass` type parent classes.
 *
 * @param kClasses The Kotlin classes representing parent classes to exclude.
 * @param indirectParents Whether to include indirect parent classes.
 * @return A list containing declarations without all specified `KClass` type parent classes.
 */
fun <T : KoParentClassProvider> List<T>.withoutAllParentClassesOf(
    kClasses: Collection<KClass<*>>,
    indirectParents: Boolean = false,
): List<T> =
    filterNot {
        when {
            kClasses.isEmpty() -> it.hasParentClasses(indirectParents)
            else -> it.hasAllParentClassesOf(kClasses, indirectParents = indirectParents)
        }
    }
