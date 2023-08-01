package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.provider.KoParentClassProvider
import kotlin.reflect.KClass

/**
 * List containing all declarations that have parent class.
 *
 * @param names The name(s) of the parent class to include.
 * @return A list containing declarations that have the specified parent class (or any parent class if [names] is empty).
 */
fun <T : KoParentClassProvider> List<T>.withParentClass(vararg names: String): List<T> = filter {
    when {
        names.isEmpty() -> it.hasParentClass()
        else -> names.any { name -> it.hasParentClass(name) }
    }
}

/**
 * List containing all declarations that have some parent class.
 *
 * @param names The name(s) of the parent class to exclude.
 * @return A list containing declarations that don't have the specified parent class (or none parent class if [names] is empty).
 */
fun <T : KoParentClassProvider> List<T>.withoutParentClass(vararg names: String): List<T> = filter {
    when {
        names.isEmpty() -> !it.hasParentClass()
        else -> names.none { name -> it.hasParentClass(name) }
    }
}

/**
 * List containing all declarations that have parent class of type.
 *
 * @return A list containing declarations that have the parent class of the specified type.
 */
inline fun <reified T> List<KoParentClassProvider>.withParentClassOf(): List<KoParentClassProvider> = withParentClassOf(T::class)

/**
 * List containing all declarations that don't have some parent class of type.
 *
 * @return A list containing declarations that don't have the parent class of the specified type.
 */
inline fun <reified T> List<KoParentClassProvider>.withoutParentClassOf(): List<KoParentClassProvider> =
    withoutParentClassOf(T::class)

/**
 * List containing all declarations that have parent class of type.
 *
 * @param names The Kotlin declarations representing the parent declarations to include.
 * @return A list containing declarations that have the parent class of the specified type(s).
 */
fun <T : KoParentClassProvider> List<T>.withParentClassOf(vararg names: KClass<*>): List<T> = filter {
    names.any { kClass -> it.hasParentClass(kClass.simpleName) }
}

/**
 * List containing all declarations that have some parent class of type.
 *
 * @param names The declarations representing the parent declarations to exclude.
 * @return A list containing declarations that don't have the parent class of the specified type(s).
 */
fun <T : KoParentClassProvider> List<T>.withoutParentClassOf(vararg names: KClass<*>): List<T> = filter {
    names.none { kClass -> it.hasParentClass(kClass.simpleName) }
}
