package com.lemonappdev.konsist.api.ext.sequence

import com.lemonappdev.konsist.api.declaration.KoFunctionDeclaration
import kotlin.reflect.KClass

/**
 * Sequence containing declarations that have 'operator' modifier.
 */
fun Sequence<KoFunctionDeclaration>.withOperatorModifier(): Sequence<KoFunctionDeclaration> = filter { it.hasOperatorModifier() }

/**
 * Sequence containing declarations that don't have 'operator' modifier.
 */
fun Sequence<KoFunctionDeclaration>.withoutOperatorModifier(): Sequence<KoFunctionDeclaration> = filterNot { it.hasOperatorModifier() }

/**
 * Sequence containing declarations that have 'inline' modifier.
 */
fun Sequence<KoFunctionDeclaration>.withInlineModifier(): Sequence<KoFunctionDeclaration> = filter { it.hasInlineModifier() }

/**
 * Sequence containing declarations that don't have 'inline' modifier.
 */
fun Sequence<KoFunctionDeclaration>.withoutInlineModifier(): Sequence<KoFunctionDeclaration> = filterNot { it.hasInlineModifier() }

/**
 * Sequence containing declarations that have 'tailrec' modifier.
 */
fun Sequence<KoFunctionDeclaration>.withTailrecModifier(): Sequence<KoFunctionDeclaration> = filter { it.hasTailrecModifier() }

/**
 * Sequence containing declarations that don't have 'tailrec' modifier.
 */
fun Sequence<KoFunctionDeclaration>.withoutTailrecModifier(): Sequence<KoFunctionDeclaration> = filterNot { it.hasTailrecModifier() }

/**
 * Sequence containing declarations that have 'infix' modifier.
 */
fun Sequence<KoFunctionDeclaration>.withInfixModifier(): Sequence<KoFunctionDeclaration> = filter { it.hasInfixModifier() }

/**
 * Sequence containing declarations that don't have 'infix' modifier.
 */
fun Sequence<KoFunctionDeclaration>.withoutInfixModifier(): Sequence<KoFunctionDeclaration> = filterNot { it.hasInfixModifier() }

/**
 * Sequence containing declarations that have 'external' modifier.
 */
fun Sequence<KoFunctionDeclaration>.withExternalModifier(): Sequence<KoFunctionDeclaration> = filter { it.hasExternalModifier() }

/**
 * Sequence containing declarations that don't have 'external' modifier.
 */
fun Sequence<KoFunctionDeclaration>.withoutExternalModifier(): Sequence<KoFunctionDeclaration> = filterNot { it.hasExternalModifier() }

/**
 * Sequence containing declarations that have 'suspend' modifier.
 */
fun Sequence<KoFunctionDeclaration>.withSuspendModifier(): Sequence<KoFunctionDeclaration> = filter { it.hasSuspendModifier() }

/**
 * Sequence containing declarations that don't have 'suspend' modifier.
 */
fun Sequence<KoFunctionDeclaration>.withoutSuspendModifier(): Sequence<KoFunctionDeclaration> = filterNot { it.hasSuspendModifier() }

/**
 * Sequence containing declarations that have 'open' modifier.
 */
fun Sequence<KoFunctionDeclaration>.withOpenModifier(): Sequence<KoFunctionDeclaration> = filter { it.hasOpenModifier() }

/**
 * Sequence containing declarations that don't have 'open' modifier.
 */
fun Sequence<KoFunctionDeclaration>.withoutOpenModifier(): Sequence<KoFunctionDeclaration> = filterNot { it.hasOpenModifier() }

/**
 * Sequence containing declarations that have 'override' modifier.
 */
fun Sequence<KoFunctionDeclaration>.withOverrideModifier(): Sequence<KoFunctionDeclaration> = filter { it.hasOverrideModifier() }

/**
 * Sequence containing declarations that don't have 'override' modifier.
 */
fun Sequence<KoFunctionDeclaration>.withoutOverrideModifier(): Sequence<KoFunctionDeclaration> = filterNot { it.hasOverrideModifier() }

/**
 * Sequence containing declarations that have 'final' modifier.
 */
fun Sequence<KoFunctionDeclaration>.withFinalModifier(): Sequence<KoFunctionDeclaration> = filter { it.hasFinalModifier() }

/**
 * Sequence containing declarations that don't have 'final' modifier.
 */
fun Sequence<KoFunctionDeclaration>.withoutFinalModifier(): Sequence<KoFunctionDeclaration> = filterNot { it.hasFinalModifier() }

/**
 * Sequence containing declarations that have 'abstract' modifier.
 */
fun Sequence<KoFunctionDeclaration>.withAbstractModifier(): Sequence<KoFunctionDeclaration> = filter { it.hasAbstractModifier() }

/**
 * Sequence containing declarations that don't have 'abstract' modifier.
 */
fun Sequence<KoFunctionDeclaration>.withoutAbstractModifier(): Sequence<KoFunctionDeclaration> = filterNot { it.hasAbstractModifier() }

/**
 * Sequence containing declarations that have 'actual' modifier.
 */
fun Sequence<KoFunctionDeclaration>.withActualModifier(): Sequence<KoFunctionDeclaration> = filter { it.hasActualModifier() }

/**
 * Sequence containing declarations that don't have 'actual' modifier.
 */
fun Sequence<KoFunctionDeclaration>.withoutActualModifier(): Sequence<KoFunctionDeclaration> = filterNot { it.hasActualModifier() }

/**
 * Sequence containing declarations that have 'expect' modifier.
 */
fun Sequence<KoFunctionDeclaration>.withExpectModifier(): Sequence<KoFunctionDeclaration> = filter { it.hasExpectModifier() }

/**
 * Sequence containing declarations that don't have 'expect' modifier.
 */
fun Sequence<KoFunctionDeclaration>.withoutExpectModifier(): Sequence<KoFunctionDeclaration> = filterNot { it.hasExpectModifier() }

/**
 * Sequence containing declarations that are extensions.
 */
fun Sequence<KoFunctionDeclaration>.withExtension(): Sequence<KoFunctionDeclaration> = filter { it.isExtension() }

/**
 * Sequence containing declarations that are not extensions.
 */
fun Sequence<KoFunctionDeclaration>.withoutExtension(): Sequence<KoFunctionDeclaration> = filterNot { it.isExtension() }

/**
 * Sequence containing declarations that have return type.
 */
fun Sequence<KoFunctionDeclaration>.withReturnType(vararg types: String): Sequence<KoFunctionDeclaration> = filter {
    when {
        types.isEmpty() -> it.hasReturnType()
        else -> types.any { type -> it.returnType?.name == type }
    }
}

/**
 * Sequence containing declarations that don't have return type.
 */
fun Sequence<KoFunctionDeclaration>.withoutReturnType(vararg types: String): Sequence<KoFunctionDeclaration> = filter {
    when {
        types.isEmpty() -> !it.hasReturnType()
        else -> types.none { type -> it.returnType?.name == type }
    }
}

/**
 * Sequence containing declarations that have return type.
 */
inline fun <reified T> Sequence<KoFunctionDeclaration>.withReturnTypeOf(): Sequence<KoFunctionDeclaration> =
    filter { T::class.simpleName == it.returnType?.name }

/**
 * Sequence containing declarations that don't have return type.
 */
inline fun <reified T> Sequence<KoFunctionDeclaration>.withoutReturnTypeOf(): Sequence<KoFunctionDeclaration> =
    filterNot { T::class.simpleName == it.returnType?.name }

/**
 * Sequence containing declarations that have return type.
 */
fun Sequence<KoFunctionDeclaration>.withReturnTypeOf(vararg types: KClass<*>): Sequence<KoFunctionDeclaration> = filter {
    types.any { kClass -> it.returnType?.name == kClass.simpleName }
}

/**
 * Sequence containing declarations that don't have return type.
 */
fun Sequence<KoFunctionDeclaration>.withoutReturnTypeOf(vararg types: KClass<*>): Sequence<KoFunctionDeclaration> = filter {
    types.none { kClass -> it.returnType?.name == kClass.simpleName }
}
