package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.provider.KoParentProvider
import kotlin.reflect.KClass

/**
 * List containing all declarations with class or interface parent.
 *
 * @return A list containing declarations with class or interface parent.
 */
fun <T : KoParentProvider> List<T>.withParents(): List<T> = filter { it.hasParents() }

/**
 * List containing all declarations with all specified parents.
 *
 * @param name The name of the parent to include.
 * @param names The name(s) of the parent(s) to include.
 * @return A list containing declarations with all specified parent(s).
 */
fun <T : KoParentProvider> List<T>.withAllParents(name: String, vararg names: String): List<T> = filter {
    it.hasParents(name, *names)
}

/**
 * List containing all declarations with some parents.
 *
 * @param name The name of the parent to include.
 * @param names The names of the parents to include.
 * @return A list containing declarations with at least one of the specified parent(s).
 */
fun <T : KoParentProvider> List<T>.withSomeParents(name: String, vararg names: String): List<T> = filter {
    it.hasParents(name) || names.any { name -> it.hasParents(name) }
}

/**
 * List containing all declarations with no parent - class does not extend any class and does not implement any interface.
 *
 * @return A list containing declarations with no parent - class does not extend any class and does not implement any
 * interface.
 */
fun <T : KoParentProvider> List<T>.withoutParents(): List<T> = filterNot { it.hasParents() }

/**
 * List containing all declarations without all specified parents.
 *
 * @param name The name of the parent to exclude.
 * @param names The name(s) of the parent(s) to exclude.
 * @return A list containing declarations without all specified parent(s).
 */
fun <T : KoParentProvider> List<T>.withoutAllParents(name: String, vararg names: String): List<T> = filter {
    !it.hasParents(name, *names)
}

/**
 * List containing all declarations without some parents represented by name.
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
 * List containing all declarations with named parents.
 *
 * @return A list containing declarations with the parent of the specified type.
 */
inline fun <reified T> List<KoParentProvider>.withParentOf(): List<KoParentProvider> =
    withAllParentsOf(T::class)

/**
 * List containing all declarations without named parents.
 *
 * @return A list containing declarations without parent of the specified type.
 */
inline fun <reified T> List<KoParentProvider>.withoutParentOf(): List<KoParentProvider> =
    withoutAllParentsOf(T::class)

/**
 * List containing all declarations with named parents.
 *
 * @param name The Kotlin class representing the parent to include.
 * @param names The Kotlin declarations representing the parents to include.
 * @return A list containing declarations with the parents of the specified type(s).
 */
fun <T : KoParentProvider> List<T>.withAllParentsOf(name: KClass<*>, vararg names: KClass<*>): List<T> =
    filter {
        it.parents.any { parent -> parent.name == name.simpleName } &&
            names.all { kClass ->
                it
                    .parents
                    .any { parent -> parent.name == kClass.simpleName }
            }
    }

/**
 * List containing all declarations with some named parents.
 *
 * @param name The Kotlin class representing the parent to include.
 * @param names The Kotlin declarations representing the parents to include.
 * @return A list containing declarations with at least one of the specified parent(s).
 */
fun <T : KoParentProvider> List<T>.withSomeParentsOf(name: KClass<*>, vararg names: KClass<*>): List<T> =
    filter {
        it.parents.any { parent -> parent.name == name.simpleName } ||
            names.any { kClass ->
                it
                    .parents
                    .any { parent -> parent.name == kClass.simpleName }
            }
    }

/**
 * List containing all declarations without named parents.
 *
 * @param name The Kotlin class representing the parent to exclude.
 * @param names The Kotlin declarations representing the parents to exclude.
 * @return A list containing declarations without parents of the specified type(s).
 */
fun <T : KoParentProvider> List<T>.withoutAllParentsOf(name: KClass<*>, vararg names: KClass<*>): List<T> =
    filter {
        it.parents.none { parent -> parent.name == name.simpleName } &&
            names.none { kClass ->
                it
                    .parents
                    .any { parent -> parent.name == kClass.simpleName }
            }
    }

/**
 * List containing all declarations without some named parents.
 *
 * @param name The Kotlin class representing the parent to exclude.
 * @param names The Kotlin declarations representing the parents to exclude.
 * @return A list containing declarations without at least one of the specified parent(s).
 */
fun <T : KoParentProvider> List<T>.withoutSomeParentsOf(name: KClass<*>, vararg names: KClass<*>): List<T> =
    filter {
        it.parents.none { parent -> parent.name == name.simpleName } &&
            if (names.isNotEmpty()) {
                names.any { kClass ->
                    it
                        .parents
                        .none { parent -> parent.name == kClass.simpleName }
                }
            } else {
                true
            }
    }
