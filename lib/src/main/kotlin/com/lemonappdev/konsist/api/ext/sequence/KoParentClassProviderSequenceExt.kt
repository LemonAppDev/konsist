package com.lemonappdev.konsist.api.ext.sequence

import com.lemonappdev.konsist.api.declaration.KoClassDeclaration
import com.lemonappdev.konsist.api.provider.KoParentClassProvider
import kotlin.reflect.KClass


/**
 * Sequence containing all declarations that have parent class.
 *
 * @param names The name(s) of the parent class to include.
 * @return A sequence containing declarations that have the specified parent class (or any parent class if [names] is empty).
 */
fun <T: KoParentClassProvider> Sequence<T>.withParentClass(vararg names: String): Sequence<KoParentClassProvider> = filter {
    when {
        names.isEmpty() -> it.hasParentClass()
        else -> names.any { name -> it.hasParentClass(name) }
    }
}

/**
 * Sequence containing all declarations that have some parent class.
 *
 * @param names The name(s) of the parent class to exclude.
 * @return A sequence containing declarations that don't have the specified parent class (or none parent class if [names] is empty).
 */
fun <T: KoParentClassProvider> Sequence<T>.withoutParentClass(vararg names: String): Sequence<KoParentClassProvider> = filter {
    when {
        names.isEmpty() -> !it.hasParentClass()
        else -> names.none { name -> it.hasParentClass(name) }
    }
}

/**
 * Sequence containing all declarations that have parent class of type.
 *
 * @return A sequence containing declarations that have the parent class of the specified type.
 */
inline fun <reified T> Sequence<KoParentClassProvider>.withParentClassOf(): Sequence<KoParentClassProvider> = filter {
    it
        .parentClass
        ?.name == T::class.simpleName
}

/**
 * Sequence containing all declarations that don't have some parent class of type.
 *
 * @return A sequence containing declarations that don't have the parent class of the specified type.
 */
inline fun <reified T> Sequence<KoParentClassProvider>.withoutParentClassOf(): Sequence<KoParentClassProvider> =
    this - withParentClassOf<T>().toSet()

/**
 * Sequence containing all declarations that have parent class of type.
 *
 * @param names The Kotlin declarations representing the parent declarations to include.
 * @return A sequence containing declarations that have the parent class of the specified type(s).
 */
fun <T: KoParentClassProvider> Sequence<T>.withParentClassOf(vararg names: KClass<*>): Sequence<KoParentClassProvider> = filter {
    names.any { kClass -> it.hasParentClass(kClass.simpleName) }
}

/**
 * Sequence containing all declarations that have some parent class of type.
 *
 * @param names The declarations representing the parent declarations to exclude.
 * @return A sequence containing declarations that don't have the parent class of the specified type(s).
 */
fun <T: KoParentClassProvider> Sequence<T>.withoutParentClassOf(vararg names: KClass<*>): Sequence<KoParentClassProvider> = filter {
    names.none { kClass -> it.hasParentClass(kClass.simpleName) }
}
