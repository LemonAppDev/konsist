package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.provider.KoExplicitReturnTypeProvider
import kotlin.reflect.KClass

/**
 * List containing elements with explicit return type.
 *
 * @param types The return type(s) to include.
 * @return A list containing elements with the specified return type(s) (or any return type if [types] is empty).
 */
fun <T : KoExplicitReturnTypeProvider> List<T>.withExplicitReturnType(vararg types: String): List<T> = filter {
    when {
        types.isEmpty() -> it.hasExplicitReturnType
        else -> types.any { type -> it.explicitReturnType?.name == type }
    }
}

/**
 * List containing elements without explicit return type.
 *
 * @param types The return type(s) to exclude.
 * @return A list containing elements without specified return type(s) (or none return type if [types] is empty).
 */
fun <T : KoExplicitReturnTypeProvider> List<T>.withoutExplicitReturnType(vararg types: String): List<T> = filter {
    when {
        types.isEmpty() -> !it.hasExplicitReturnType
        else -> types.none { type -> it.explicitReturnType?.name == type }
    }
}

/**
 * List containing elements with explicit return type.
 *
 * @param type The Kotlin class representing the return type to include.
 * @param types The Kotlin class(es) representing the return type(s) to include.
 * @return A list containing elements with the return type of the specified Kotlin class(es).
 */
fun <T : KoExplicitReturnTypeProvider> List<T>.withExplicitReturnTypeOf(
    type: KClass<*>,
    vararg types: KClass<*>,
): List<T> = filter {
    it.explicitReturnType?.name == type.simpleName ||
        if (types.isNotEmpty()) {
            types.any { kClass -> it.explicitReturnType?.name == kClass.simpleName }
        } else {
            false
        }
}

/**
 * List containing elements without return type.
 *
 * @param type The Kotlin class representing the return type to exclude.
 * @param types The Kotlin class(es) representing the return type(s) to exclude.
 * @return A list containing elements without return type of the specified Kotlin class(es).
 */
fun <T : KoExplicitReturnTypeProvider> List<T>.withoutExplicitReturnTypeOf(
    type: KClass<*>,
    vararg types: KClass<*>,
): List<T> = filter {
    it.explicitReturnType?.name != type.simpleName &&
        if (types.isNotEmpty()) {
            types.none { kClass -> it.explicitReturnType?.name == kClass.simpleName }
        } else {
            true
        }
}
