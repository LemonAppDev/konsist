package com.lemonappdev.konsist.api.ext.sequence

import com.lemonappdev.konsist.api.declaration.KoFunctionDeclaration
import com.lemonappdev.konsist.api.ext.provider.hasReceiverTypeOf
import kotlin.reflect.KClass

/**
 * Sequence containing functions with receiver type.
 *
 * @param types The receiver type(s) to include.
 * @return A sequence containing functions with the specified receiver type(s) (or any receiver type if [types] is empty).
 */
fun Sequence<KoFunctionDeclaration>.withReceiverType(vararg types: String): Sequence<KoFunctionDeclaration> = filter {
    when {
        types.isEmpty() -> it.hasReceiverType()
        else -> types.any { type -> it.hasReceiverType(type) }
    }
}

/**
 * Sequence containing functions without receiver type.
 *
 * @param types The receiver type(s) to exclude.
 * @return A sequence containing functions without specified receiver type(s) (or none receiver type if [types] is empty).
 */
fun Sequence<KoFunctionDeclaration>.withoutReceiverType(vararg types: String): Sequence<KoFunctionDeclaration> = filter {
    when {
        types.isEmpty() -> !it.hasReceiverType()
        else -> types.none { type -> it.hasReceiverType(type) }
    }
}

/**
 * Sequence containing functions with receiver type.
 *
 * @return A sequence containing functions with the receiver type of the specified type.
 */
inline fun <reified T> Sequence<KoFunctionDeclaration>.withReceiverTypeOf(): Sequence<KoFunctionDeclaration> =
    filter { it.hasReceiverTypeOf<T>() }

/**
 * Sequence containing functions without receiver type.
 *
 * @return A sequence containing functions without receiver type of the specified type.
 */
inline fun <reified T> Sequence<KoFunctionDeclaration>.withoutReceiverTypeOf(): Sequence<KoFunctionDeclaration> =
    filterNot { it.hasReceiverTypeOf<T>() }

/**
 * Sequence containing functions with receiver type.
 *
 * @param types The Kotlin class(es) representing the receiver type(s) to include.
 * @return A sequence containing functions with the receiver type of the specified Kotlin class(es).
 */
fun Sequence<KoFunctionDeclaration>.withReceiverTypeOf(vararg types: KClass<*>): Sequence<KoFunctionDeclaration> = filter {
    types.any { kClass -> it.hasReceiverType(kClass.simpleName) }
}

/**
 * Sequence containing functions without receiver type.
 *
 * @param types The Kotlin class(es) representing the receiver type(s) to exclude.
 * @return A sequence containing functions without receiver type of the specified Kotlin class(es).
 */
fun Sequence<KoFunctionDeclaration>.withoutReceiverTypeOf(vararg types: KClass<*>): Sequence<KoFunctionDeclaration> = filter {
    types.none { kClass -> it.hasReceiverType(kClass.simpleName) }
}

/**
 * Sequence containing functions with explicit return type.
 *
 * @param types The return type(s) to include.
 * @return A sequence containing functions with the specified return type(s) (or any return type if [types] is empty).
 */
fun Sequence<KoFunctionDeclaration>.withExplicitReturnType(vararg types: String): Sequence<KoFunctionDeclaration> = filter {
    when {
        types.isEmpty() -> it.hasExplicitReturnType()
        else -> types.any { type -> it.explicitReturnType?.name == type }
    }
}

/**
 * Sequence containing functions without explicit return type.
 *
 * @param types The return type(s) to exclude.
 * @return A sequence containing functions without specified return type(s) (or none return type if [types] is empty).
 */
fun Sequence<KoFunctionDeclaration>.withoutExplicitReturnType(vararg types: String): Sequence<KoFunctionDeclaration> = filter {
    when {
        types.isEmpty() -> !it.hasExplicitReturnType()
        else -> types.none { type -> it.explicitReturnType?.name == type }
    }
}

/**
 * Sequence containing functions with explicit return type.
 *
 * @return A sequence containing functions with the return type of the specified type.
 */
inline fun <reified T> Sequence<KoFunctionDeclaration>.withExplicitReturnTypeOf(): Sequence<KoFunctionDeclaration> =
    filter { T::class.simpleName == it.explicitReturnType?.name }

/**
 * Sequence containing functions without explicit return type.
 *
 * @return A sequence containing functions without return type of the specified type.
 */
inline fun <reified T> Sequence<KoFunctionDeclaration>.withoutExplicitReturnTypeOf(): Sequence<KoFunctionDeclaration> =
    filterNot { T::class.simpleName == it.explicitReturnType?.name }

/**
 * Sequence containing functions with explicit return type.
 *
 * @param types The Kotlin class(es) representing the return type(s) to include.
 * @return A sequence containing functions with the return type of the specified Kotlin class(es).
 */
fun Sequence<KoFunctionDeclaration>.withExplicitReturnTypeOf(vararg types: KClass<*>): Sequence<KoFunctionDeclaration> = filter {
    types.any { kClass -> it.explicitReturnType?.name == kClass.simpleName }
}

/**
 * Sequence containing functions without return type.
 *
 * @param types The Kotlin class(es) representing the return type(s) to exclude.
 * @return A sequence containing functions without return type of the specified Kotlin class(es).
 */
fun Sequence<KoFunctionDeclaration>.withoutExplicitReturnTypeOf(vararg types: KClass<*>): Sequence<KoFunctionDeclaration> = filter {
    types.none { kClass -> it.explicitReturnType?.name == kClass.simpleName }
}

/**
 * Sequence containing functions that have implementation.
 *
 * @return A sequence containing functions with the implementation.
 */
fun Sequence<KoFunctionDeclaration>.withImplementation(): Sequence<KoFunctionDeclaration> = filter { it.hasImplementation() }

/**
 * Sequence containing functions without implementation.
 *
 * @return A sequence containing functions without the implementation.
 */
fun Sequence<KoFunctionDeclaration>.withoutImplementation(): Sequence<KoFunctionDeclaration> = filterNot { it.hasImplementation() }
