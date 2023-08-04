package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.provider.KoParentClassProvider
import kotlin.reflect.KClass

/**
 * List containing elements that have parent class.
 *
 * @param names The name(s) of the parent class to include.
 * @return A list containing elements that have the specified parent class (or any parent class if [names] is empty).
 */
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
fun <T : KoParentClassProvider> List<T>.withoutParentClass(vararg names: String): List<T> = filter {
    when {
        names.isEmpty() -> !it.hasParentClass()
        else -> names.none { name -> it.hasParentClass(name) }
    }
}

/**
 * List containing elements that have parent class of type.
 *
 * @param name The Kotlin declaration representing the parent clas to include.
 * @param names The Kotlin declarations representing the parent class to include.
 * @return A list containing elements that have the parent class of the specified type(s).
 */
fun <T : KoParentClassProvider> List<T>.withParentClassOf(name: KClass<*>, vararg names: KClass<*>): List<T> = filter {
    it.hasParentClass(name.simpleName) ||
            if (names.isNotEmpty()) {
                names.any { kClass -> it.hasParentClass(kClass.simpleName) }
            } else {
                false
            }
}

/**
 * List containing elements that have some parent class of type.
 *
 * @param name The Kotlin declaration representing the parent clas to exclude.
 * @param names The declarations representing the parent class to exclude.
 * @return A list containing elements that don't have the parent class of the specified type(s).
 */
fun <T : KoParentClassProvider> List<T>.withoutParentClassOf(name: KClass<*>, vararg names: KClass<*>): List<T> =
    filter {
        !it.hasParentClass(name.simpleName) &&
                if (names.isNotEmpty()) {
                    names.none { kClass -> it.hasParentClass(kClass.simpleName) }
                } else {
                    true
                }
    }
