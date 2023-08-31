package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.provider.KoParentClassProvider
import kotlin.reflect.KClass

/**
 * List containing elements that have parent class.
 *
 * @param names The name(s) of the parent class to include.
 * @return A list containing elements that have the specified parent class (or any parent class if [names] is empty).
 */
@Deprecated("Will be removed in v1.0.0")
fun <T : KoParentClassProvider> List<T>.withParentClass(vararg names: String): List<T> = filter {
    when {
        names.isEmpty() -> it.hasParentClass()
        else -> names.any { name -> it.hasParentClass(name) }
    }
}

/**
 * List containing elements that have some parent class.
 *
 * @param names The name(s) of the parent class to exclude.
 * @return A list containing elements that don't have the specified parent class (or none parent class if [names] is empty).
 */
@Deprecated("Will be removed in v1.0.0")
fun <T : KoParentClassProvider> List<T>.withoutParentClass(vararg names: String): List<T> = filter {
    when {
        names.isEmpty() -> !it.hasParentClass()
        else -> names.none { name -> it.hasParentClass(name) }
    }
}

/**
 * List containing elements that have parent class of type.
 *
 * @param kClass The Kotlin declaration representing the parent class to include.
 * @param kClasses The Kotlin declarations representing the parent class to include.
 * @return A list containing elements that have the parent class of the specified type(s).
 */
@Deprecated("Will be removed in v1.0.0")
fun <T : KoParentClassProvider> List<T>.withParentClassOf(kClass: KClass<*>, vararg kClasses: KClass<*>): List<T> =
    filter {
        it.hasParentClass(kClass.simpleName) ||
            if (kClasses.isNotEmpty()) {
                kClasses.any { kClass -> it.hasParentClass(kClass.simpleName) }
            } else {
                false
            }
    }

/**
 * List containing elements that have some parent class of type.
 *
 * @param kClass The Kotlin declaration representing the parent class to exclude.
 * @param kClasses The declarations representing the parent class to exclude.
 * @return A list containing elements that don't have the parent class of the specified type(s).
 */
@Deprecated("Will be removed in v1.0.0")
fun <T : KoParentClassProvider> List<T>.withoutParentClassOf(kClass: KClass<*>, vararg kClasses: KClass<*>): List<T> =
    filter {
        !it.hasParentClass(kClass.simpleName) &&
            if (kClasses.isNotEmpty()) {
                kClasses.none { kClass -> it.hasParentClass(kClass.simpleName) }
            } else {
                true
            }
    }
