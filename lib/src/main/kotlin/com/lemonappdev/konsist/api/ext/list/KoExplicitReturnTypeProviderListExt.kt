package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.provider.KoExplicitReturnTypeProvider
import kotlin.reflect.KClass

/**
 * List containing elements with explicit return type.
 *
 * @param names The return type name(s) to include.
 * @return A list containing elements with the specified return type(s) (or any return type if [names] is empty).
 */
fun <T : KoExplicitReturnTypeProvider> List<T>.withExplicitReturnType(vararg names: String): List<T> = filter {
    when {
        names.isEmpty() -> it.hasExplicitReturnType
        else -> names.any { type -> it.explicitReturnType?.name == type }
    }
}

/**
 * List containing elements without explicit return type.
 *
 * @param names The return type name(s) to exclude.
 * @return A list containing elements without specified return type(s) (or none return type if [names] is empty).
 */
fun <T : KoExplicitReturnTypeProvider> List<T>.withoutExplicitReturnType(vararg names: String): List<T> = filter {
    when {
        names.isEmpty() -> !it.hasExplicitReturnType
        else -> names.none { type -> it.explicitReturnType?.name == type }
    }
}

/**
 * List containing elements with explicit return type.
 *
 * @param kClass The Kotlin class representing the return type to include.
 * @param kClasses The Kotlin class(es) representing the return type(s) to include.
 * @return A list containing elements with the return type of the specified Kotlin class(es).
 */
fun <T : KoExplicitReturnTypeProvider> List<T>.withExplicitReturnTypeOf(
    kClass: KClass<*>,
    vararg kClasses: KClass<*>,
): List<T> = filter {
    it.explicitReturnType?.name == kClass.simpleName ||
        if (kClasses.isNotEmpty()) {
            kClasses.any { kClass -> it.explicitReturnType?.name == kClass.simpleName }
        } else {
            false
        }
}

/**
 * List containing elements without return type.
 *
 * @param kClass The Kotlin class representing the return type to exclude.
 * @param kClasses The Kotlin class(es) representing the return type(s) to exclude.
 * @return A list containing elements without return type of the specified Kotlin class(es).
 */
fun <T : KoExplicitReturnTypeProvider> List<T>.withoutExplicitReturnTypeOf(
    kClass: KClass<*>,
    vararg kClasses: KClass<*>,
): List<T> = filter {
    it.explicitReturnType?.name != kClass.simpleName &&
        if (kClasses.isNotEmpty()) {
            kClasses.none { kClass -> it.explicitReturnType?.name == kClass.simpleName }
        } else {
            true
        }
}
