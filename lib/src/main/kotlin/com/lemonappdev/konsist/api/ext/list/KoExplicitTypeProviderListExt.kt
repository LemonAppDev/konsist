package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.provider.KoExplicitTypeProvider
import kotlin.reflect.KClass

/**
 * List containing elements with explicit type.
 *
 * @param names The type name(s) to include.
 * @return A list containing elements with the specified type (or any type if [names] is empty).
 */
fun <T : KoExplicitTypeProvider> List<T>.withExplicitType(vararg names: String): List<T> = filter {
    when {
        names.isEmpty() -> it.hasExplicitType()
        else -> names.any { type -> it.hasExplicitType(type) }
    }
}

/**
 * List containing elements without explicit type.
 *
 * @param names The type name(s) to exclude.
 * @return A list containing elements without specified type (or none type if [names] is empty).
 */
fun <T : KoExplicitTypeProvider> List<T>.withoutExplicitType(vararg names: String): List<T> = filter {
    when {
        names.isEmpty() -> !it.hasExplicitType()
        else -> names.none { type -> it.hasExplicitType(type) }
    }
}

/**
 * List containing elements with explicit type of.
 *
 * @param kClasses The Kotlin class representing the type to include.
 * @param kClasses The Kotlin class(es) representing the type(s) to include.
 * @return A list containing elements with the type of the specified Kotlin class(es).
 */
fun <T : KoExplicitTypeProvider> List<T>.withExplicitTypeOf(kClass: KClass<*>, vararg kClasses: KClass<*>): List<T> =
    filter {
        it.explicitType?.name == kClass.simpleName ||
            if (kClasses.isNotEmpty()) {
                kClasses.any { kClass -> it.explicitType?.name == kClass.simpleName }
            } else {
                false
            }
    }

/**
 * List containing elements without explicit type of.
 *
 * @param kClasses The Kotlin class representing the type to exclude.
 * @param kClasses The Kotlin class(es) representing the type(s) to exclude.
 * @return A list containing elements without type of the specified Kotlin class(es).
 */
fun <T : KoExplicitTypeProvider> List<T>.withoutExplicitTypeOf(kClass: KClass<*>, vararg kClasses: KClass<*>): List<T> =
    filter {
        it.explicitType?.name != kClass.simpleName &&
            if (kClasses.isNotEmpty()) {
                kClasses.none { kClass -> it.explicitType?.name == kClass.simpleName }
            } else {
                true
            }
    }
