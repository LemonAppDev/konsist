package com.lemonappdev.konsist.api.ext.sequence

import com.lemonappdev.konsist.api.declaration.KoFunctionDeclaration
import com.lemonappdev.konsist.api.ext.declaration.hasReceiverOf
import kotlin.reflect.KClass

/**
 * Sequence containing functions that have `operator` modifier.
 *
 * @return A sequence containing functions with the `operator` modifier.
 */
fun Sequence<KoFunctionDeclaration>.withOperatorModifier(): Sequence<KoFunctionDeclaration> = filter { it.hasOperatorModifier() }

/**
 * Sequence containing functions that don't have `operator` modifier.
 *
 * @return A sequence containing functions without the `operator` modifier.
 */
fun Sequence<KoFunctionDeclaration>.withoutOperatorModifier(): Sequence<KoFunctionDeclaration> = filterNot { it.hasOperatorModifier() }

/**
 * Sequence containing functions that have `inline` modifier.
 *
 * @return A sequence containing functions with the `inline` modifier.
 */
fun Sequence<KoFunctionDeclaration>.withInlineModifier(): Sequence<KoFunctionDeclaration> = filter { it.hasInlineModifier() }

/**
 * Sequence containing functions that don't have `inline` modifier.
 *
 * @return A sequence containing functions without the `inline` modifier.
 */
fun Sequence<KoFunctionDeclaration>.withoutInlineModifier(): Sequence<KoFunctionDeclaration> = filterNot { it.hasInlineModifier() }

/**
 * Sequence containing functions that have `tailrec` modifier.
 *
 * @return A sequence containing functions with the `tailrec` modifier.
 */
fun Sequence<KoFunctionDeclaration>.withTailrecModifier(): Sequence<KoFunctionDeclaration> = filter { it.hasTailrecModifier() }

/**
 * Sequence containing functions that don't have `tailrec` modifier.
 *
 * @return A sequence containing functions without the `tailrec` modifier.
 */
fun Sequence<KoFunctionDeclaration>.withoutTailrecModifier(): Sequence<KoFunctionDeclaration> = filterNot { it.hasTailrecModifier() }

/**
 * Sequence containing functions that have `infix` modifier.
 *
 * @return A sequence containing functions with the `infix` modifier.
 */
fun Sequence<KoFunctionDeclaration>.withInfixModifier(): Sequence<KoFunctionDeclaration> = filter { it.hasInfixModifier() }

/**
 * Sequence containing functions that don't have `infix` modifier.
 *
 * @return A sequence containing functions without the `infix` modifier.
 */
fun Sequence<KoFunctionDeclaration>.withoutInfixModifier(): Sequence<KoFunctionDeclaration> = filterNot { it.hasInfixModifier() }

/**
 * Sequence containing functions that have `external` modifier.
 *
 * @return A sequence containing functions with the `external` modifier.
 */
fun Sequence<KoFunctionDeclaration>.withExternalModifier(): Sequence<KoFunctionDeclaration> = filter { it.hasExternalModifier() }

/**
 * Sequence containing functions that don't have `external` modifier.
 *
 * @return A sequence containing functions without the `external` modifier.
 */
fun Sequence<KoFunctionDeclaration>.withoutExternalModifier(): Sequence<KoFunctionDeclaration> = filterNot { it.hasExternalModifier() }

/**
 * Sequence containing functions that have `suspend` modifier.
 *
 * @return A sequence containing functions with the `suspend` modifier.
 */
fun Sequence<KoFunctionDeclaration>.withSuspendModifier(): Sequence<KoFunctionDeclaration> = filter { it.hasSuspendModifier() }

/**
 * Sequence containing functions that don't have `suspend` modifier.
 *
 * @return A sequence containing functions without the `suspend` modifier.
 */
fun Sequence<KoFunctionDeclaration>.withoutSuspendModifier(): Sequence<KoFunctionDeclaration> = filterNot { it.hasSuspendModifier() }

/**
 * Sequence containing functions that have `open` modifier.
 *
 * @return A sequence containing functions with the `open` modifier.
 */
fun Sequence<KoFunctionDeclaration>.withOpenModifier(): Sequence<KoFunctionDeclaration> = filter { it.hasOpenModifier() }

/**
 * Sequence containing functions that don't have `open` modifier.
 *
 * @return A sequence containing functions without the `open` modifier.
 */
fun Sequence<KoFunctionDeclaration>.withoutOpenModifier(): Sequence<KoFunctionDeclaration> = filterNot { it.hasOpenModifier() }

/**
 * Sequence containing functions that have `override` modifier.
 *
 * @return A sequence containing functions with the `override` modifier.
 */
fun Sequence<KoFunctionDeclaration>.withOverrideModifier(): Sequence<KoFunctionDeclaration> = filter { it.hasOverrideModifier() }

/**
 * Sequence containing functions that don't have `override` modifier.
 *
 * @return A sequence containing functions without the `override` modifier.
 */
fun Sequence<KoFunctionDeclaration>.withoutOverrideModifier(): Sequence<KoFunctionDeclaration> = filterNot { it.hasOverrideModifier() }

/**
 * Sequence containing functions that have `final` modifier.
 *
 * @return A sequence containing functions with the `final` modifier.
 */
fun Sequence<KoFunctionDeclaration>.withFinalModifier(): Sequence<KoFunctionDeclaration> = filter { it.hasFinalModifier() }

/**
 * Sequence containing functions that don't have `final` modifier.
 *
 * @return A sequence containing functions without the `final` modifier.
 */
fun Sequence<KoFunctionDeclaration>.withoutFinalModifier(): Sequence<KoFunctionDeclaration> = filterNot { it.hasFinalModifier() }

/**
 * Sequence containing functions that have `abstract` modifier.
 *
 * @return A sequence containing functions with the `abstract` modifier.
 */
fun Sequence<KoFunctionDeclaration>.withAbstractModifier(): Sequence<KoFunctionDeclaration> = filter { it.hasAbstractModifier() }

/**
 * Sequence containing functions that don't have `abstract` modifier.
 *
 * @return A sequence containing functions without the `abstract` modifier.
 */
fun Sequence<KoFunctionDeclaration>.withoutAbstractModifier(): Sequence<KoFunctionDeclaration> = filterNot { it.hasAbstractModifier() }

/**
 * Sequence containing functions that have `actual` modifier.
 *
 * @return A sequence containing functions with the `actual` modifier.
 */
fun Sequence<KoFunctionDeclaration>.withActualModifier(): Sequence<KoFunctionDeclaration> = filter { it.hasActualModifier() }

/**
 * Sequence containing functions that don't have `actual` modifier.
 *
 * @return A sequence containing functions without the `actual` modifier.
 */
fun Sequence<KoFunctionDeclaration>.withoutActualModifier(): Sequence<KoFunctionDeclaration> = filterNot { it.hasActualModifier() }

/**
 * Sequence containing functions that have `expect` modifier.
 *
 * @return A sequence containing functions with the `expect` modifier.
 */
fun Sequence<KoFunctionDeclaration>.withExpectModifier(): Sequence<KoFunctionDeclaration> = filter { it.hasExpectModifier() }

/**
 * Sequence containing functions that don't have `expect` modifier.
 *
 * @return A sequence containing functions without the `expect` modifier.
 */
fun Sequence<KoFunctionDeclaration>.withoutExpectModifier(): Sequence<KoFunctionDeclaration> = filterNot { it.hasExpectModifier() }

/**
 * Sequence containing functions that are extensions.
 *
 * @return A sequence containing functions that are extensions.
 */
fun Sequence<KoFunctionDeclaration>.withExtension(): Sequence<KoFunctionDeclaration> = filter { it.isExtension() }

/**
 * Sequence containing functions that are not extensions.
 *
 * @return A sequence containing functions that are not extensions.
 */
fun Sequence<KoFunctionDeclaration>.withoutExtension(): Sequence<KoFunctionDeclaration> = filterNot { it.isExtension() }

/**
 * Sequence containing functions that have receiver.
 *
 * @param types The receiver(s) to include.
 * @return A sequence containing functions that have the specified receiver(s) (or any receiver if [types] is empty).
 */
fun Sequence<KoFunctionDeclaration>.withReceiver(vararg types: String): Sequence<KoFunctionDeclaration> = filter {
    when {
        types.isEmpty() -> it.hasReceiver()
        else -> types.any { type -> it.hasReceiver(type) }
    }
}

/**
 * Sequence containing functions that don't have receiver.
 *
 * @param types The receiver(s) to exclude.
 * @return A sequence containing functions that don't have the specified receiver(s) (or none receiver if [types] is empty).
 */
fun Sequence<KoFunctionDeclaration>.withoutReceiver(vararg types: String): Sequence<KoFunctionDeclaration> = filter {
    when {
        types.isEmpty() -> !it.hasReceiver()
        else -> types.none { type -> it.hasReceiver(type) }
    }
}

/**
 * Sequence containing functions that have receiver.
 *
 * @return A sequence containing functions that have the receiver of the specified type.
 */
inline fun <reified T> Sequence<KoFunctionDeclaration>.withReceiverOf(): Sequence<KoFunctionDeclaration> =
    filter { it.hasReceiverOf<T>() }

/**
 * Sequence containing functions that don't have receiver.
 *
 * @return A sequence containing functions that don't have the receiver of the specified type.
 */
inline fun <reified T> Sequence<KoFunctionDeclaration>.withoutReceiverOf(): Sequence<KoFunctionDeclaration> =
    filterNot { it.hasReceiverOf<T>() }

/**
 * Sequence containing functions that have receiver.
 *
 * @param types The Kotlin class(es) representing the receiver(s) to include.
 * @return A sequence containing functions that have the receiver of the specified Kotlin class(es).
 */
fun Sequence<KoFunctionDeclaration>.withReceiverOf(vararg types: KClass<*>): Sequence<KoFunctionDeclaration> = filter {
    types.any { kClass -> it.hasReceiver(kClass.simpleName) }
}

/**
 * Sequence containing functions that don't have receiver.
 *
 * @param types The Kotlin class(es) representing the receiver(s) to exclude.
 * @return A sequence containing functions that don't have the receiver of the specified Kotlin class(es).
 */
fun Sequence<KoFunctionDeclaration>.withoutReceiverOf(vararg types: KClass<*>): Sequence<KoFunctionDeclaration> = filter {
    types.none { kClass -> it.hasReceiver(kClass.simpleName) }
}

/**
 * Sequence containing functions that have return type.
 *
 * @param types The return type(s) to include.
 * @return A sequence containing functions that have the specified return type(s) (or any return type if [types] is empty).
 */
fun Sequence<KoFunctionDeclaration>.withReturnType(vararg types: String): Sequence<KoFunctionDeclaration> = filter {
    when {
        types.isEmpty() -> it.hasReturnType()
        else -> types.any { type -> it.returnType?.name == type }
    }
}

/**
 * Sequence containing functions that don't have return type.
 *
 * @param types The return type(s) to exclude.
 * @return A sequence containing functions that don't have the specified return type(s) (or none return type if [types] is empty).
 */
fun Sequence<KoFunctionDeclaration>.withoutReturnType(vararg types: String): Sequence<KoFunctionDeclaration> = filter {
    when {
        types.isEmpty() -> !it.hasReturnType()
        else -> types.none { type -> it.returnType?.name == type }
    }
}

/**
 * Sequence containing functions that have return type.
 *
 * @return A sequence containing functions that have the return type of the specified type.
 */
inline fun <reified T> Sequence<KoFunctionDeclaration>.withReturnTypeOf(): Sequence<KoFunctionDeclaration> =
    filter { T::class.simpleName == it.returnType?.name }

/**
 * Sequence containing functions that don't have return type.
 *
 * @return A sequence containing functions that don't have the return type of the specified type.
 */
inline fun <reified T> Sequence<KoFunctionDeclaration>.withoutReturnTypeOf(): Sequence<KoFunctionDeclaration> =
    filterNot { T::class.simpleName == it.returnType?.name }

/**
 * Sequence containing functions that have return type.
 *
 * @param types The Kotlin class(es) representing the return type(s) to include.
 * @return A sequence containing functions that have the return type of the specified Kotlin class(es).
 */
fun Sequence<KoFunctionDeclaration>.withReturnTypeOf(vararg types: KClass<*>): Sequence<KoFunctionDeclaration> = filter {
    types.any { kClass -> it.returnType?.name == kClass.simpleName }
}

/**
 * Sequence containing functions that don't have return type.
 *
 * @param types The Kotlin class(es) representing the return type(s) to exclude.
 * @return A sequence containing functions that don't have the return type of the specified Kotlin class(es).
 */
fun Sequence<KoFunctionDeclaration>.withoutReturnTypeOf(vararg types: KClass<*>): Sequence<KoFunctionDeclaration> = filter {
    types.none { kClass -> it.returnType?.name == kClass.simpleName }
}
