package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.declaration.KoClassDeclaration
import com.lemonappdev.konsist.api.provider.KoParentClassProvider
import kotlin.reflect.KClass

/**
 * List containing parent classes.
 */
val <T : KoParentClassProvider> List<T>.parentClasses: List<KoClassDeclaration>
    get() = mapNotNull { it.parentClass }

/**
 * List containing declarations with the specified parent class.
 *
 * @param predicate The predicate function to determine if a declaration parent class satisfies a condition.
 * @return A list containing declarations with the specified parent class (or any parent class if [predicate] is null).
 */
fun <T : KoParentClassProvider> List<T>.withParentClass(predicate: ((KoClassDeclaration) -> Boolean)? = null): List<T> =
    filter {
        when (predicate) {
            null -> it.hasParentClass()
            else -> it.parentClass?.let { parentClass -> predicate(parentClass) } ?: false
        }
    }

/**
 * List containing declarations without the specified parent class.
 *
 * @param predicate The predicate function to determine if a declaration parent class satisfies a condition.
 * @return A list containing declarations without the specified parent class (or none parent class if [predicate] is null).
 */
fun <T : KoParentClassProvider> List<T>.withoutParentClass(predicate: ((KoClassDeclaration) -> Boolean)? = null): List<T> =
    filterNot {
        when (predicate) {
            null -> it.hasParentClass()
            else -> it.parentClass?.let { parentClass -> predicate(parentClass) } ?: false
        }
    }

/**
 * List containing declarations that have parent class with the specified name(s).
 *
 * @param name The name of the parent class to include.
 * @param names The names of additional parent classes to include.
 * @return A list containing declarations with the specified parent class(es).
 */
fun <T : KoParentClassProvider> List<T>.withParentClassNamed(name: String, vararg names: String): List<T> = filter {
    it.hasParentClassWithName(name, *names)
}

/**
 * List containing declarations without any of specified parent classes.
 *
 * @param name The name of the parent class to exclude.
 * @param names The names of additional parent classes to exclude.
 * @return A list containing declarations without any of specified parent classes.
 */
fun <T : KoParentClassProvider> List<T>.withoutParentClassNamed(name: String, vararg names: String): List<T> =
    filterNot {
        it.hasParentClassWithName(name, *names)
    }

/**
 * List containing declarations that have parent class of type.
 *
 * @param kClass The Kotlin declaration representing the parent class to include.
 * @param kClasses The Kotlin declarations representing the parent class to include.
 * @return A list containing declarations that have the parent class of the specified type(s).
 */
fun <T : KoParentClassProvider> List<T>.withParentClassOf(kClass: KClass<*>, vararg kClasses: KClass<*>): List<T> =
    filter { it.hasParentClassOf(kClass, *kClasses) }

/**
 * List containing declarations without parent class.
 *
 * @param kClass The Kotlin class representing the parent class to exclude.
 * @param kClasses The Kotlin class(es) representing the parent class(s) to exclude.
 * @return A list containing declarations without parent class of the specified Kotlin class(es).
 */
fun <T : KoParentClassProvider> List<T>.withoutParentClassOf(kClass: KClass<*>, vararg kClasses: KClass<*>): List<T> =
    filterNot { it.hasParentClassOf(kClass, *kClasses) }

/**
 * List containing declarations that have parent class.
 *
 * @param names The name(s) of the parent class to include.
 * @return A list containing declarations that have the specified parent class (or any parent class if [names] is empty).
 */
@Deprecated("Will be removed in v1.0.0. Replace with `withAllParents` if you pass any parameter, `withParents` otherwise.")
fun <T : KoParentClassProvider> List<T>.withParentClass(vararg names: String): List<T> = filter {
    when {
        names.isEmpty() -> it.hasParentClass()
        else -> names.any { name -> it.hasParentClass(name) }
    }
}

/**
 * List containing declarations that have some parent class.
 *
 * @param names The name(s) of the parent class to exclude.
 * @return A list containing declarations that don't have the specified parent class (or none parent class if [names] is empty).
 */
@Deprecated("Will be removed in v1.0.0. Replace with `withoutSomeParents` if you pass any parameter, `withoutParents` otherwise.")
fun <T : KoParentClassProvider> List<T>.withoutParentClass(vararg names: String): List<T> = filter {
    when {
        names.isEmpty() -> !it.hasParentClass()
        else -> names.none { name -> it.hasParentClass(name) }
    }
}
