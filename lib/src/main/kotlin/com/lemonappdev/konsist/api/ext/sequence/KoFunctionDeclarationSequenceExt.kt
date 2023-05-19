package com.lemonappdev.konsist.api.ext.sequence

import com.lemonappdev.konsist.api.declaration.KoFunctionDeclaration
import kotlin.reflect.KClass

/**
 * Sequence containing declarations that have 'operator' modifier.
 *
 * @return A sequence containing function declarations with the 'operator' modifier.
 */
fun Sequence<KoFunctionDeclaration>.withOperatorModifier(): Sequence<KoFunctionDeclaration> = filter { it.hasOperatorModifier() }

/**
 * Sequence containing declarations that don't have 'operator' modifier.
 *
 * @return A sequence containing function declarations without the 'operator' modifier.
 */
fun Sequence<KoFunctionDeclaration>.withoutOperatorModifier(): Sequence<KoFunctionDeclaration> = filterNot { it.hasOperatorModifier() }

/**
 * Sequence containing declarations that have 'inline' modifier.
 *
 * @return A sequence containing function declarations with the 'inline' modifier.
 */
fun Sequence<KoFunctionDeclaration>.withInlineModifier(): Sequence<KoFunctionDeclaration> = filter { it.hasInlineModifier() }

/**
 * Sequence containing declarations that don't have 'inline' modifier.
 *
 * @return A sequence containing function declarations without the 'inline' modifier.
 */
fun Sequence<KoFunctionDeclaration>.withoutInlineModifier(): Sequence<KoFunctionDeclaration> = filterNot { it.hasInlineModifier() }

/**
 * Sequence containing declarations that have 'tailrec' modifier.
 *
 * @return A sequence containing function declarations with the 'tailrec' modifier.
 */
fun Sequence<KoFunctionDeclaration>.withTailrecModifier(): Sequence<KoFunctionDeclaration> = filter { it.hasTailrecModifier() }

/**
 * Sequence containing declarations that don't have 'tailrec' modifier.
 *
 * @return A sequence containing function declarations without the 'tailrec' modifier.
 */
fun Sequence<KoFunctionDeclaration>.withoutTailrecModifier(): Sequence<KoFunctionDeclaration> = filterNot { it.hasTailrecModifier() }

/**
 * Sequence containing declarations that have 'infix' modifier.
 *
 * @return A sequence containing function declarations with the 'infix' modifier.
 */
fun Sequence<KoFunctionDeclaration>.withInfixModifier(): Sequence<KoFunctionDeclaration> = filter { it.hasInfixModifier() }

/**
 * Sequence containing declarations that don't have 'infix' modifier.
 *
 * @return A sequence containing function declarations without the 'infix' modifier.
 */
fun Sequence<KoFunctionDeclaration>.withoutInfixModifier(): Sequence<KoFunctionDeclaration> = filterNot { it.hasInfixModifier() }

/**
 * Sequence containing declarations that have 'external' modifier.
 *
 * @return A sequence containing function declarations with the 'external' modifier.
 */
fun Sequence<KoFunctionDeclaration>.withExternalModifier(): Sequence<KoFunctionDeclaration> = filter { it.hasExternalModifier() }

/**
 * Sequence containing declarations that don't have 'external' modifier.
 *
 * @return A sequence containing function declarations without the 'external' modifier.
 */
fun Sequence<KoFunctionDeclaration>.withoutExternalModifier(): Sequence<KoFunctionDeclaration> = filterNot { it.hasExternalModifier() }

/**
 * Sequence containing declarations that have 'suspend' modifier.
 *
 * @return A sequence containing function declarations with the 'suspend' modifier.
 */
fun Sequence<KoFunctionDeclaration>.withSuspendModifier(): Sequence<KoFunctionDeclaration> = filter { it.hasSuspendModifier() }

/**
 * Sequence containing declarations that don't have 'suspend' modifier.
 *
 * @return A sequence containing function declarations without the 'suspend' modifier.
 */
fun Sequence<KoFunctionDeclaration>.withoutSuspendModifier(): Sequence<KoFunctionDeclaration> = filterNot { it.hasSuspendModifier() }

/**
 * Sequence containing declarations that have 'open' modifier.
 *
 * @return A sequence containing function declarations with the 'open' modifier.
 */
fun Sequence<KoFunctionDeclaration>.withOpenModifier(): Sequence<KoFunctionDeclaration> = filter { it.hasOpenModifier() }

/**
 * Sequence containing declarations that don't have 'open' modifier.
 *
 * @return A sequence containing function declarations without the 'open' modifier.
 */
fun Sequence<KoFunctionDeclaration>.withoutOpenModifier(): Sequence<KoFunctionDeclaration> = filterNot { it.hasOpenModifier() }

/**
 * Sequence containing declarations that have 'override' modifier.
 *
 * @return A sequence containing function declarations with the 'override' modifier.
 */
fun Sequence<KoFunctionDeclaration>.withOverrideModifier(): Sequence<KoFunctionDeclaration> = filter { it.hasOverrideModifier() }

/**
 * Sequence containing declarations that don't have 'override' modifier.
 *
 * @return A sequence containing function declarations without the 'override' modifier.
 */
fun Sequence<KoFunctionDeclaration>.withoutOverrideModifier(): Sequence<KoFunctionDeclaration> = filterNot { it.hasOverrideModifier() }

/**
 * Sequence containing declarations that have 'final' modifier.
 *
 * @return A sequence containing function declarations with the 'final' modifier.
 */
fun Sequence<KoFunctionDeclaration>.withFinalModifier(): Sequence<KoFunctionDeclaration> = filter { it.hasFinalModifier() }

/**
 * Sequence containing declarations that don't have 'final' modifier.
 *
 * @return A sequence containing function declarations without the 'final' modifier.
 */
fun Sequence<KoFunctionDeclaration>.withoutFinalModifier(): Sequence<KoFunctionDeclaration> = filterNot { it.hasFinalModifier() }

/**
 * Sequence containing declarations that have 'abstract' modifier.
 *
 * @return A sequence containing function declarations with the 'abstract' modifier.
 */
fun Sequence<KoFunctionDeclaration>.withAbstractModifier(): Sequence<KoFunctionDeclaration> = filter { it.hasAbstractModifier() }

/**
 * Sequence containing declarations that don't have 'abstract' modifier.
 *
 * @return A sequence containing function declarations without the 'abstract' modifier.
 */
fun Sequence<KoFunctionDeclaration>.withoutAbstractModifier(): Sequence<KoFunctionDeclaration> = filterNot { it.hasAbstractModifier() }

/**
 * Sequence containing declarations that have 'actual' modifier.
 *
 * @return A sequence containing function declarations with the 'actual' modifier.
 */
fun Sequence<KoFunctionDeclaration>.withActualModifier(): Sequence<KoFunctionDeclaration> = filter { it.hasActualModifier() }

/**
 * Sequence containing declarations that don't have 'actual' modifier.
 *
 * @return A sequence containing function declarations without the 'actual' modifier.
 */
fun Sequence<KoFunctionDeclaration>.withoutActualModifier(): Sequence<KoFunctionDeclaration> = filterNot { it.hasActualModifier() }

/**
 * Sequence containing declarations that have 'expect' modifier.
 *
 * @return A sequence containing function declarations with the 'expect' modifier.
 */
fun Sequence<KoFunctionDeclaration>.withExpectModifier(): Sequence<KoFunctionDeclaration> = filter { it.hasExpectModifier() }

/**
 * Sequence containing declarations that don't have 'expect' modifier.
 *
 * @return A sequence containing function declarations without the 'expect' modifier.
 */
fun Sequence<KoFunctionDeclaration>.withoutExpectModifier(): Sequence<KoFunctionDeclaration> = filterNot { it.hasExpectModifier() }

/**
 * Sequence containing declarations that are extensions.
 *
 * @return A sequence containing function declarations that are extensions.
 */
fun Sequence<KoFunctionDeclaration>.withExtension(): Sequence<KoFunctionDeclaration> = filter { it.isExtension() }

/**
 * Sequence containing declarations that are not extensions.
 *
 * @return A sequence containing function declarations that are not extensions.
 */
fun Sequence<KoFunctionDeclaration>.withoutExtension(): Sequence<KoFunctionDeclaration> = filterNot { it.isExtension() }

/**
 * Sequence containing declarations that have return type.
 *
 * @param types The return type(s) to include. If empty, all function declarations with return type are included.
 * @return A sequence containing function declarations that have the specified return type(s).
 */
fun Sequence<KoFunctionDeclaration>.withReturnType(vararg types: String): Sequence<KoFunctionDeclaration> = filter {
    when {
        types.isEmpty() -> it.hasReturnType()
        else -> types.any { type -> it.returnType?.name == type }
    }
}

/**
 * Sequence containing declarations that don't have return type.
 *
 * @param types The return type(s) to exclude. If empty, all function declarations without return type are included.
 * @return A sequence containing function declarations that don't have the specified return type(s).
 */
fun Sequence<KoFunctionDeclaration>.withoutReturnType(vararg types: String): Sequence<KoFunctionDeclaration> = filter {
    when {
        types.isEmpty() -> !it.hasReturnType()
        else -> types.none { type -> it.returnType?.name == type }
    }
}

/**
 * Sequence containing declarations that have return type.
 *
 * @return A sequence containing function declarations that have the return type of the specified type.
 */
inline fun <reified T> Sequence<KoFunctionDeclaration>.withReturnTypeOf(): Sequence<KoFunctionDeclaration> =
    filter { T::class.simpleName == it.returnType?.name }

/**
 * Sequence containing declarations that don't have return type.
 *
 * @return A sequence containing function declarations that don't have the return type of the specified type.
 */
inline fun <reified T> Sequence<KoFunctionDeclaration>.withoutReturnTypeOf(): Sequence<KoFunctionDeclaration> =
    filterNot { T::class.simpleName == it.returnType?.name }

/**
 * Sequence containing declarations that have return type.
 *
 * @param types The Kotlin class(es) representing the return type(s) to include.
 * @return A sequence containing function declarations that have the return type of the specified Kotlin class(es).
 */
fun Sequence<KoFunctionDeclaration>.withReturnTypeOf(vararg types: KClass<*>): Sequence<KoFunctionDeclaration> = filter {
    types.any { kClass -> it.returnType?.name == kClass.simpleName }
}

/**
 * Sequence containing declarations that don't have return type.
 *
 * @param types The Kotlin class(es) representing the return type(s) to exclude.
 * @return A sequence containing function declarations that don't have the return type of the specified Kotlin class(es).
 */
fun Sequence<KoFunctionDeclaration>.withoutReturnTypeOf(vararg types: KClass<*>): Sequence<KoFunctionDeclaration> = filter {
    types.none { kClass -> it.returnType?.name == kClass.simpleName }
}
