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
 * List containing declarations with all specified parents.
 *
 * @param name The name of the parent to include.
 * @param names The name(s) of the parent(s) to include.
 * @return A list containing declarations with all specified parent(s).
 */
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
fun <T : KoParentProvider> List<T>.withSomeParents(name: String, vararg names: String): List<T> = filter {
    it.hasParents(name) || names.any { name -> it.hasParents(name) }
}

/**
 * List containing declarations with no parent - class does not extend any class and does not implement any interface.
 *
 * @return A list containing declarations with no parent - class does not extend any class and does not implement any
 * interface.
 */
fun <T : KoParentProvider> List<T>.withoutParents(): List<T> = filterNot { it.hasParents() }

/**
 * List containing declarations without all specified parents.
 *
 * @param name The name of the parent to exclude.
 * @param names The name(s) of the parent(s) to exclude.
 * @return A list containing declarations without all specified parent(s).
 */
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
fun <T : KoParentProvider> List<T>.withoutSomeParents(name: String, vararg names: String): List<T> = filter {
    !it.hasParents(name) && if (names.isNotEmpty()) {
        names.any { name -> !it.hasParents(name) }
    } else {
        true
    }
}

/**
 * List containing declarations with named parents.
 *
 * @param kClass The Kotlin class representing the parent to include.
 * @param kClasses The Kotlin declarations representing the parents to include.
 * @return A list containing declarations with the parents of the specified type(s).
 */
fun <T : KoParentProvider> List<T>.withAllParentsOf(kClass: KClass<*>, vararg kClasses: KClass<*>): List<T> =
    filter {
        it.parents.any { parent -> parent.name == kClass.simpleName } &&
            kClasses.all { kClass ->
                it
                    .parents
                    .any { parent -> parent.name == kClass.simpleName }
            }
    }

/**
 * List containing declarations with some named parents.
 *
 * @param kClass The Kotlin class representing the parent to include.
 * @param kClasses The Kotlin declarations representing the parents to include.
 * @return A list containing declarations with at least one of the specified parent(s).
 */
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
 * List containing declarations without named parents.
 *
 * @param kClass The Kotlin class representing the parent to exclude.
 * @param kClasses The Kotlin declarations representing the parents to exclude.
 * @return A list containing declarations without parents of the specified type(s).
 */
fun <T : KoParentProvider> List<T>.withoutAllParentsOf(kClass: KClass<*>, vararg kClasses: KClass<*>): List<T> =
    filter {
        it.parents.none { parent -> parent.name == kClass.simpleName } &&
            kClasses.none { kClass ->
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
fun <T : KoParentProvider> List<T>.withoutSomeParentsOf(kClass: KClass<*>, vararg kClasses: KClass<*>): List<T> =
    filter {
        it.parents.none { parent -> parent.name == kClass.simpleName } &&
            if (kClasses.isNotEmpty()) {
                kClasses.any { kClass ->
                    it
                        .parents
                        .none { parent -> parent.name == kClass.simpleName }
                }
            } else {
                true
            }
    }
