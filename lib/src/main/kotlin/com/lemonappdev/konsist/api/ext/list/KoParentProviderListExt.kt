@file:Suppress("detekt.TooManyFunctions")

package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.declaration.KoParentDeclaration
import com.lemonappdev.konsist.api.provider.KoParentProvider
import kotlin.reflect.KClass

/**
 * List containing parent declarations.
 */
val <T : KoParentProvider> List<T>.parents: List<KoParentDeclaration>
    get() = flatMap { it.parents }

/**
 * List containing declarations with class or interface parent.
 *
 * @return A list containing declarations with class or interface parent.
 */
fun <T : KoParentProvider> List<T>.withParents(): List<T> = filter { it.hasParents() }

/**
 * List containing declarations with no parent - class does not extend any class and does not implement any interface.
 *
 * @return A list containing declarations with no parent - class does not extend any class and does not implement any
 * interface.
 */
fun <T : KoParentProvider> List<T>.withoutParents(): List<T> = filterNot { it.hasParents() }

/**
 * List containing declarations that have at least one parent with the specified name(s).
 *
 * @param name The name of the parent to include.
 * @param names The names of additional parents to include.
 * @return A list containing declarations with at least one of the specified parent(s).
 */
fun <T : KoParentProvider> List<T>.withParentNamed(name: String, vararg names: String): List<T> = filter {
    it.hasParentWithName(name, *names)
}

/**
 * List containing declarations without any of specified parents.
 *
 * @param name The name of the parent to exclude.
 * @param names The names of additional parents to exclude.
 * @return A list containing declarations without any of specified parents.
 */
fun <T : KoParentProvider> List<T>.withoutParentNamed(name: String, vararg names: String): List<T> = filterNot {
    it.hasParentWithName(name, *names)
}

/**
 * List containing declarations that have all specified parents.
 *
 * @param name The name of the parent to include.
 * @param names The name(s) of the parent(s) to include.
 * @return A list containing declarations with all specified parent(s).
 */
fun <T : KoParentProvider> List<T>.withAllParentsNamed(name: String, vararg names: String): List<T> = filter {
    it.hasParentsWithAllNames(name, *names)
}

/**
 * List containing declarations without all specified parents.
 *
 * @param name The name of the parent to exclude.
 * @param names The name(s) of the parent(s) to exclude.
 * @return A list containing declarations without all specified parent(s).
 */
fun <T : KoParentProvider> List<T>.withoutAllParentsNamed(name: String, vararg names: String): List<T> = filterNot {
    it.hasParentsWithAllNames(name, *names)
}

/**
 * List containing declarations that have at least one parent satisfying the provided predicate.
 *
 * @param predicate A function that defines the condition to be met by a parent declaration.
 * @return A list containing declarations with at least one parent satisfying the predicate.
 */
fun <T : KoParentProvider> List<T>.withParent(predicate: (KoParentDeclaration) -> Boolean): List<T> = filter {
    it.hasParent(predicate)
}

/**
 * List containing declarations that not have parent satisfying the provided predicate.
 *
 * @param predicate A function that defines the condition to be met by a parent declaration.
 * @return A list containing declarations without parent satisfying the provided predicate.
 */
fun <T : KoParentProvider> List<T>.withoutParent(predicate: (KoParentDeclaration) -> Boolean): List<T> = filterNot {
    it.hasParent(predicate)
}

/**
 * List containing declarations that have all parents satisfying the provided predicate.
 *
 * @param predicate A function that defines the condition to be met by all parent declarations.
 * @return A filtered list containing declarations with all parents satisfying the predicate.
 */
fun <T : KoParentProvider> List<T>.withAllParents(predicate: (KoParentDeclaration) -> Boolean): List<T> = filter {
    it.hasAllParents(predicate)
}

/**
 * List containing declarations that have at least one parent not satisfying the provided predicate.
 *
 * @param predicate A function that defines the condition to be met by all parent declarations.
 * @return A list containing declarations that have at least one parent not satisfying the provided predicate.
 */
fun <T : KoParentProvider> List<T>.withoutAllParents(predicate: (KoParentDeclaration) -> Boolean): List<T> = filterNot {
    it.hasAllParents(predicate)
}

/**
 * List containing declarations with parent declarations satisfying the predicate.
 *
 * @param predicate A function that defines the condition to be met by the list of parent declarations.
 * @return A list containing declarations with parent declarations satisfying the predicate.
 */
fun <T : KoParentProvider> List<T>.withParents(predicate: (List<KoParentDeclaration>) -> Boolean): List<T> = filter {
    predicate(it.parents)
}

/**
 * List containing declarations without parent declarations satisfying the predicate.
 *
 * @param predicate A function that defines the condition to be met by the list of parent declarations.
 * @return A list containing declarations without parent declarations satisfying the predicate.
 */
fun <T : KoParentProvider> List<T>.withoutParents(predicate: (List<KoParentDeclaration>) -> Boolean): List<T> =
    filterNot { predicate(it.parents) }

/**
 * List containing declarations that have at least one parent of the specified `KClass` type.
 *
 * @param kClass The Kotlin class representing parent to include.
 * @param kClasses The Kotlin classes representing parents to include.
 * @return A list containing declarations with at least one parent of the specified `KClass` type.
 */
fun <T : KoParentProvider> List<T>.withParentOf(
    kClass: KClass<*>,
    vararg kClasses: KClass<*>,
): List<T> =
    filter { it.hasParentOf(kClass, *kClasses) }

/**
 * List containing declarations without any parent of the specified `KClass` type.
 *
 * @param kClass The Kotlin class representing parent to exclude.
 * @param kClasses The Kotlin classes representing parents to exclude.
 * @return A list containing declarations without any of the specified parents.
 */
fun <T : KoParentProvider> List<T>.withoutParentOf(
    kClass: KClass<*>,
    vararg kClasses: KClass<*>,
): List<T> =
    filterNot { it.hasParentOf(kClass, *kClasses) }

/**
 * List containing declarations that have all parents of the specified `KClass` type.
 *
 * @param kClass The Kotlin class representing parent to include.
 * @param kClasses The Kotlin classes representing parents to include.
 * @return A list containing declarations that have all parents of the specified `KClass` type.
 */
fun <T : KoParentProvider> List<T>.withAllParentsOf(kClass: KClass<*>, vararg kClasses: KClass<*>): List<T> =
    filter { it.hasAllParentsOf(kClass, *kClasses) }

/**
 * List containing declarations without all specified `KClass` type parents.
 *
 * @param kClass The Kotlin class representing parent to exclude.
 * @param kClasses The Kotlin classes representing parents to exclude.
 * @return A list containing declarations without all specified `KClass` type parents.
 */
fun <T : KoParentProvider> List<T>.withoutAllParentsOf(kClass: KClass<*>, vararg kClasses: KClass<*>): List<T> =
    filterNot { it.hasAllParentsOf(kClass, *kClasses) }

/**
 * List containing declarations with some named parents.
 *
 * @param kClass The Kotlin class representing the parent to include.
 * @param kClasses The Kotlin declarations representing the parents to include.
 * @return A list containing declarations with at least one of the specified parent(s).
 */
@Deprecated("Will be removed in v1.0.0.", ReplaceWith("withParentOf(*kClasses"))
fun <T : KoParentProvider> List<T>.withSomeParentsOf(kClass: KClass<*>, vararg kClasses: KClass<*>): List<T> =
    filter {
        it.parents.any { parent -> parent.name == kClass.simpleName } ||
            kClasses.any { kClass ->
                it
                    .parents
                    .any { parent -> parent.name == kClass.simpleName }
            }
    }

/**
 * List containing declarations without some named parents.
 *
 * @param kClass The Kotlin class representing the parent to exclude.
 * @param kClasses The Kotlin declarations representing the parents to exclude.
 * @return A list containing declarations without at least one of the specified parent(s).
 */
@Deprecated("Will be removed in v1.0.0.", ReplaceWith("withoutParentOf(*kClasses"))
fun <T : KoParentProvider> List<T>.withoutSomeParentsOf(kClass: KClass<*>, vararg kClasses: KClass<*>): List<T> =
    filter {
        val missesAtLeastOneParent = if (kClasses.isNotEmpty()) {
            kClasses.any { kClass ->
                it
                    .parents
                    .none { parent -> parent.name == kClass.simpleName }
            }
        } else {
            true
        }
        it.parents.none { parent -> parent.name == kClass.simpleName } &&
            missesAtLeastOneParent
    }

/**
 * List containing declarations with all specified parents.
 *
 * @param name The name of the parent to include.
 * @param names The name(s) of the parent(s) to include.
 * @return A list containing declarations with all specified parent(s).
 */
@Deprecated(
    """
            Will be removed in v1.0.0. 
            If you passed one argument - replace with `withParentNamed`, otherwise with `withAllParentsNamed`.
            """,
    ReplaceWith("withParentNamed/withAllParentsNamed"),
)
fun <T : KoParentProvider> List<T>.withAllParents(name: String, vararg names: String): List<T> = filter {
    it.hasParents(name, *names)
}

/**
 * List containing declarations with some parents.
 *
 * @param name The name of the parent to include.
 * @param names The names of the parents to include.
 * @return A list containing declarations with at least one of the specified parent(s).
 */
@Deprecated("Will be removed in v1.0.0.", ReplaceWith("withParentNamed(*names"))
fun <T : KoParentProvider> List<T>.withSomeParents(name: String, vararg names: String): List<T> = filter {
    it.hasParents(name) || names.any { name -> it.hasParents(name) }
}

/**
 * List containing declarations without all specified parents.
 *
 * @param name The name of the parent to exclude.
 * @param names The name(s) of the parent(s) to exclude.
 * @return A list containing declarations without all specified parent(s).
 */
@Deprecated(
    """
            Will be removed in v1.0.0. 
            If you passed one argument - replace with `withoutParentNamed`, otherwise with `withoutAllParentsNamed`.
            """,
    ReplaceWith("withoutParentNamed/withoutAllParentsNamed"),
)
fun <T : KoParentProvider> List<T>.withoutAllParents(name: String, vararg names: String): List<T> = filter {
    !it.hasParents(name, *names)
}

/**
 * List containing declarations without some parents represented by name.
 *
 * @param name The name of the parent to exclude.
 * @param names The names of the parents to exclude.
 * @return A list containing declarations without at least one of the specified parent(s).
 */
@Deprecated("Will be removed in v1.0.0.", ReplaceWith("withoutParentNamed(*names"))
fun <T : KoParentProvider> List<T>.withoutSomeParents(name: String, vararg names: String): List<T> = filter {
    val hasNoMatchingParents = if (names.isNotEmpty()) {
        names.any { name -> !it.hasParents(name) }
    } else {
        true
    }
    !it.hasParents(name) && hasNoMatchingParents
}
