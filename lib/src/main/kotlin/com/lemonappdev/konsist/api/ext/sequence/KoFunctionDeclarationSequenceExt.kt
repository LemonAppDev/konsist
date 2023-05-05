package com.lemonappdev.konsist.api.ext.sequence

import com.lemonappdev.konsist.api.declaration.KoFunctionDeclaration
import kotlin.reflect.KClass

/**
 * Sequence containing declarations that have 'operator' modifier.
 */
fun Sequence<KoFunctionDeclaration>.withOperatorModifier() = filter { it.hasOperatorModifier() }

/**
 * Sequence containing declarations that don't have 'operator' modifier.
 */
fun Sequence<KoFunctionDeclaration>.withoutOperatorModifier() = filterNot { it.hasOperatorModifier() }

/**
 * Sequence containing declarations that have 'inline' modifier.
 */
fun Sequence<KoFunctionDeclaration>.withInlineModifier() = filter { it.hasInlineModifier() }

/**
 * Sequence containing declarations that don't have 'inline' modifier.
 */
fun Sequence<KoFunctionDeclaration>.withoutInlineModifier() = filterNot { it.hasInlineModifier() }

/**
 * Sequence containing declarations that have 'tailrec' modifier.
 */
fun Sequence<KoFunctionDeclaration>.withTailrecModifier() = filter { it.hasTailrecModifier() }

/**
 * Sequence containing declarations that don't have 'tailrec' modifier.
 */
fun Sequence<KoFunctionDeclaration>.withoutTailrecModifier() = filterNot { it.hasTailrecModifier() }

/**
 * Sequence containing declarations that have 'infix' modifier.
 */
fun Sequence<KoFunctionDeclaration>.withInfixModifier() = filter { it.hasInfixModifier() }

/**
 * Sequence containing declarations that don't have 'infix' modifier.
 */
fun Sequence<KoFunctionDeclaration>.withoutInfixModifier() = filterNot { it.hasInfixModifier() }

/**
 * Sequence containing declarations that have 'external' modifier.
 */
fun Sequence<KoFunctionDeclaration>.withExternalModifier() = filter { it.hasExternalModifier() }

/**
 * Sequence containing declarations that don't have 'external' modifier.
 */
fun Sequence<KoFunctionDeclaration>.withoutExternalModifier() = filterNot { it.hasExternalModifier() }

/**
 * Sequence containing declarations that have 'suspend' modifier.
 */
fun Sequence<KoFunctionDeclaration>.withSuspendModifier() = filter { it.hasSuspendModifier() }

/**
 * Sequence containing declarations that don't have 'suspend' modifier.
 */
fun Sequence<KoFunctionDeclaration>.withoutSuspendModifier() = filterNot { it.hasSuspendModifier() }

/**
 * Sequence containing declarations that have 'open' modifier.
 */
fun Sequence<KoFunctionDeclaration>.withOpenModifier() = filter { it.hasOpenModifier() }

/**
 * Sequence containing declarations that don't have 'open' modifier.
 */
fun Sequence<KoFunctionDeclaration>.withoutOpenModifier() = filterNot { it.hasOpenModifier() }

/**
 * Sequence containing declarations that have 'override' modifier.
 */
fun Sequence<KoFunctionDeclaration>.withOverrideModifier() = filter { it.hasOverrideModifier() }

/**
 * Sequence containing declarations that don't have 'override' modifier.
 */
fun Sequence<KoFunctionDeclaration>.withoutOverrideModifier() = filterNot { it.hasOverrideModifier() }

/**
 * Sequence containing declarations that have 'final' modifier.
 */
fun Sequence<KoFunctionDeclaration>.withFinalModifier() = filter { it.hasFinalModifier() }

/**
 * Sequence containing declarations that don't have 'final' modifier.
 */
fun Sequence<KoFunctionDeclaration>.withoutFinalModifier() = filterNot { it.hasFinalModifier() }

/**
 * Sequence containing declarations that have 'abstract' modifier.
 */
fun Sequence<KoFunctionDeclaration>.withAbstractModifier() = filter { it.hasAbstractModifier() }

/**
 * Sequence containing declarations that don't have 'abstract' modifier.
 */
fun Sequence<KoFunctionDeclaration>.withoutAbstractModifier() = filterNot { it.hasAbstractModifier() }

/**
 * Sequence containing declarations that have 'actual' modifier.
 */
fun Sequence<KoFunctionDeclaration>.withActualModifier() = filter { it.hasActualModifier() }

/**
 * Sequence containing declarations that don't have 'actual' modifier.
 */
fun Sequence<KoFunctionDeclaration>.withoutActualModifier() = filterNot { it.hasActualModifier() }

/**
 * Sequence containing declarations that have 'expect' modifier.
 */
fun Sequence<KoFunctionDeclaration>.withExpectModifier() = filter { it.hasExpectModifier() }

/**
 * Sequence containing declarations that don't have 'expect' modifier.
 */
fun Sequence<KoFunctionDeclaration>.withoutExpectModifier() = filterNot { it.hasExpectModifier() }

/**
 * Sequence containing declarations that are extensions.
 */
fun Sequence<KoFunctionDeclaration>.withExtension() = filter { it.isExtension() }

/**
 * Sequence containing declarations that are not extensions.
 */
fun Sequence<KoFunctionDeclaration>.withoutExtension() = filterNot { it.isExtension() }

/**
 * Sequence containing declarations that have return type.
 */
fun Sequence<KoFunctionDeclaration>.withReturnType(vararg types: String) = filter {
    when {
        types.isEmpty() -> it.hasReturnType()
        else -> types.any { type -> it.returnType?.name == type }
    }
}

/**
 * Sequence containing declarations that don't have return type.
 */
fun Sequence<KoFunctionDeclaration>.withoutReturnType(vararg types: String) = filter {
    when {
        types.isEmpty() -> !it.hasReturnType()
        else -> types.none { type -> it.returnType?.name == type }
    }
}

/**
 * Sequence containing declarations that have return type.
 */
inline fun <reified T> Sequence<KoFunctionDeclaration>.withReturnTypeOf() = filter { T::class.simpleName == it.returnType?.name }

/**
 * Sequence containing declarations that don't have return type.
 */
inline fun <reified T> Sequence<KoFunctionDeclaration>.withoutReturnTypeOf() =
    filterNot { T::class.simpleName == it.returnType?.name }

/**
 * Sequence containing declarations that have return type.
 */
fun Sequence<KoFunctionDeclaration>.withReturnTypeOf(vararg types: KClass<*>) = filter {
    types.any { kClass -> it.returnType?.name == kClass.simpleName }
}

/**
 * Sequence containing declarations that don't have return type.
 */
fun Sequence<KoFunctionDeclaration>.withoutReturnTypeOf(vararg types: KClass<*>) = filter {
    types.none { kClass -> it.returnType?.name == kClass.simpleName }
}
