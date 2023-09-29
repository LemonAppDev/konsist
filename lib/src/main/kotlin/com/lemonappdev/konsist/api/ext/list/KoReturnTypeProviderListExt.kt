package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.declaration.KoTypeDeclaration
import com.lemonappdev.konsist.api.provider.KoReturnTypeProvider
import com.lemonappdev.konsist.api.provider.KoTopLevelProvider
import kotlin.reflect.KClass

/**
 * List containing return type declarations.
 */
val <T : KoReturnTypeProvider> List<T>.returnTypes: List<KoTypeDeclaration>
    get() = mapNotNull { it.returnType }

/**
 * List containing declarations with return type.
 *
 * @param names The return type name(s) to include.
 * @return A list containing declarations with the specified return type(s) (or any return type if [names] is empty).
 */
@Deprecated("Will be removed in v1.0.0", ReplaceWith("withReturnType { it.name == ... }"))
fun <T : KoReturnTypeProvider> List<T>.withReturnType(vararg names: String): List<T> = filter {
    when {
        names.isEmpty() -> it.hasReturnType
        else -> names.any { type -> it.returnType?.name == type }
    }
}

/**
 * List containing declarations without return type.
 *
 * @param names The return type name(s) to exclude.
 * @return A list containing declarations without specified return type(s) (or none return type if [names] is empty).
 */
@Deprecated("Will be removed in v1.0.0", ReplaceWith("withoutReturnType { it.name != ... }"))
fun <T : KoReturnTypeProvider> List<T>.withoutReturnType(vararg names: String): List<T> = filter {
    when {
        names.isEmpty() -> !it.hasReturnType
        else -> names.none { type -> it.returnType?.name == type }
    }
}

/**
 * List containing declarations with non-Unit return values, which may or may not have explicitly defined return types.
 *
 * @return A list containing declarations with the return value other than `Unit`.
 */
fun <T : KoReturnTypeProvider> List<T>.withReturnValue(): List<T> = filter { it.hasReturnValue }

/**
 * List containing declarations with the `Unit` return value, which may or may not have explicitly defined return types.
 *
 * @return A list containing declarations with the `Unit` return value.
 */
fun <T : KoReturnTypeProvider> List<T>.withoutReturnValue(): List<T> = filterNot { it.hasReturnValue }


/**
 * List containing declarations with the specified return type.
 *
 * @param predicate The predicate function to determine if a declaration return type satisfies a condition.
 * @return A list containing declarations with the specified return type (or any return type if [predicate] is null).
 */
fun <T : KoReturnTypeProvider> List<T>.withReturnType(predicate: ((KoTypeDeclaration) -> Boolean)? = null): List<T> =
    filter {
        when (predicate) {
            null -> it.hasReturnType()
            else -> it.returnType?.let { returnType -> predicate(returnType) } ?: false
        }
    }

/**
 * List containing declarations without the specified return type.
 *
 * @param predicate The predicate function to determine if a declaration return type satisfies a condition.
 * @return A list containing declarations without the specified return type (or none return type if [predicate] is null).
 */
fun <T : KoReturnTypeProvider> List<T>.withoutReturnType(predicate: ((KoTypeDeclaration) -> Boolean)? = null): List<T> =
    filterNot {
        when (predicate) {
            null -> it.hasReturnType()
            else -> it.returnType?.let { returnType -> predicate(returnType) } ?: false
        }
    }

/**
 * List containing declarations with return type.
 *
 * @param kClass The Kotlin class representing the return type to include.
 * @param kClasses The Kotlin class(es) representing the return type(s) to include.
 * @return A list containing declarations with the return type of the specified Kotlin class(es).
 */
fun <T : KoReturnTypeProvider> List<T>.withReturnTypeOf(kClass: KClass<*>, vararg kClasses: KClass<*>): List<T> =
    filter {
        it.hasReturnTypeOf(kClass) ||
            if (kClasses.isNotEmpty()) {
                kClasses.any { kClass -> it.hasReturnTypeOf(kClass) }
            } else {
                false
            }
    }

/**
 * List containing declarations without return type.
 *
 * @param kClass The Kotlin class representing the return type to exclude.
 * @param kClasses The Kotlin class(es) representing the return type(s) to exclude.
 * @return A list containing declarations without return type of the specified Kotlin class(es).
 */
fun <T : KoReturnTypeProvider> List<T>.withoutReturnTypeOf(kClass: KClass<*>, vararg kClasses: KClass<*>): List<T> =
    filterNot {
        it.hasReturnTypeOf(kClass) ||
            if (kClasses.isNotEmpty()) {
                kClasses.any { kClass -> it.hasReturnTypeOf(kClass) }
            } else {
                false
            }
    }
