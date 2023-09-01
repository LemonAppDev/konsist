package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.declaration.KoTypeDeclaration
import com.lemonappdev.konsist.api.provider.KoReturnTypeProvider
import kotlin.reflect.KClass

/**
 * List containing return type declarations.
 */
val <T : KoReturnTypeProvider> List<T>.returnTypes: List<KoTypeDeclaration>
    get() = mapNotNull { it.returnType }

/**
 * List containing elements with return type.
 *
 * @param names The return type name(s) to include.
 * @return A list containing elements with the specified return type(s) (or any return type if [names] is empty).
 */
fun <T : KoReturnTypeProvider> List<T>.withReturnType(vararg names: String): List<T> = filter {
    when {
        names.isEmpty() -> it.hasReturnType
        else -> names.any { type -> it.returnType?.name == type }
    }
}

/**
 * List containing elements without return type.
 *
 * @param names The return type name(s) to exclude.
 * @return A list containing elements without specified return type(s) (or none return type if [names] is empty).
 */
fun <T : KoReturnTypeProvider> List<T>.withoutReturnType(vararg names: String): List<T> = filter {
    when {
        names.isEmpty() -> !it.hasReturnType
        else -> names.none { type -> it.returnType?.name == type }
    }
}

/**
 * List containing elements with return type.
 *
 * @param kClass The Kotlin class representing the return type to include.
 * @param kClasses The Kotlin class(es) representing the return type(s) to include.
 * @return A list containing elements with the return type of the specified Kotlin class(es).
 */
fun <T : KoReturnTypeProvider> List<T>.withReturnTypeOf(
    kClass: KClass<*>,
    vararg kClasses: KClass<*>,
): List<T> = filter {
    it.returnType?.name == kClass.simpleName ||
        if (kClasses.isNotEmpty()) {
            kClasses.any { kClass -> it.returnType?.name == kClass.simpleName }
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
fun <T : KoReturnTypeProvider> List<T>.withoutReturnTypeOf(
    kClass: KClass<*>,
    vararg kClasses: KClass<*>,
): List<T> = filter {
    it.returnType?.name != kClass.simpleName &&
        if (kClasses.isNotEmpty()) {
            kClasses.none { kClass -> it.returnType?.name == kClass.simpleName }
        } else {
            true
        }
}
