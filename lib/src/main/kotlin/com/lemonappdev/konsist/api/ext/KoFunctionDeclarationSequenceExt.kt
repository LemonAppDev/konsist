@file:Suppress("detekt.TooManyFunctions")

package com.lemonappdev.konsist.api.ext

import com.lemonappdev.konsist.core.declaration.KoFunctionDeclaration
import kotlin.reflect.KClass

fun Sequence<KoFunctionDeclaration>.withOperatorModifier() = filter { it.hasOperatorModifier() }

fun Sequence<KoFunctionDeclaration>.withoutOperatorModifier() = filterNot { it.hasOperatorModifier() }

fun Sequence<KoFunctionDeclaration>.withInlineModifier() = filter { it.hasInlineModifier() }

fun Sequence<KoFunctionDeclaration>.withoutInlineModifier() = filterNot { it.hasInlineModifier() }

fun Sequence<KoFunctionDeclaration>.withTailrecModifier() = filter { it.hasTailrecModifier() }

fun Sequence<KoFunctionDeclaration>.withoutTailrecModifier() = filterNot { it.hasTailrecModifier() }

fun Sequence<KoFunctionDeclaration>.withInfixModifier() = filter { it.hasInfixModifier() }

fun Sequence<KoFunctionDeclaration>.withoutInfixModifier() = filterNot { it.hasInfixModifier() }

fun Sequence<KoFunctionDeclaration>.withExternalModifier() = filter { it.hasExternalModifier() }

fun Sequence<KoFunctionDeclaration>.withoutExternalModifier() = filterNot { it.hasExternalModifier() }

fun Sequence<KoFunctionDeclaration>.withSuspendModifier() = filter { it.hasSuspendModifier() }

fun Sequence<KoFunctionDeclaration>.withoutSuspendModifier() = filterNot { it.hasSuspendModifier() }

fun Sequence<KoFunctionDeclaration>.withOpenModifier() = filter { it.hasOpenModifier() }

fun Sequence<KoFunctionDeclaration>.withoutOpenModifier() = filterNot { it.hasOpenModifier() }

fun Sequence<KoFunctionDeclaration>.withOverrideModifier() = filter { it.hasOverrideModifier() }

fun Sequence<KoFunctionDeclaration>.withoutOverrideModifier() = filterNot { it.hasOverrideModifier() }

fun Sequence<KoFunctionDeclaration>.withFinalModifier() = filter { it.hasFinalModifier() }

fun Sequence<KoFunctionDeclaration>.withoutFinalModifier() = filterNot { it.hasFinalModifier() }

fun Sequence<KoFunctionDeclaration>.withAbstractModifier() = filter { it.hasAbstractModifier() }

fun Sequence<KoFunctionDeclaration>.withoutAbstractModifier() = filterNot { it.hasAbstractModifier() }

fun Sequence<KoFunctionDeclaration>.withActualModifier() = filter { it.hasActualModifier() }

fun Sequence<KoFunctionDeclaration>.withoutActualModifier() = filterNot { it.hasActualModifier() }

fun Sequence<KoFunctionDeclaration>.withExpectModifier() = filter { it.hasExpectModifier() }

fun Sequence<KoFunctionDeclaration>.withoutExpectModifier() = filterNot { it.hasExpectModifier() }

fun Sequence<KoFunctionDeclaration>.withExtension() = filter { it.isExtension() }

fun Sequence<KoFunctionDeclaration>.withoutExtension() = filterNot { it.isExtension() }

fun Sequence<KoFunctionDeclaration>.withReturnType(vararg types: String) = filter {
    when {
        types.isEmpty() -> it.hasReturnType()
        else -> types.any { type -> it.returnType?.name == type }
    }
}

fun Sequence<KoFunctionDeclaration>.withoutReturnType(vararg types: String) = filter {
    when {
        types.isEmpty() -> !it.hasReturnType()
        else -> types.none { type -> it.returnType?.name == type }
    }
}

inline fun <reified T> Sequence<KoFunctionDeclaration>.withReturnTypeOf() = filter { T::class.simpleName == it.returnType?.name }

inline fun <reified T> Sequence<KoFunctionDeclaration>.withoutReturnTypeOf() =
    filterNot { T::class.simpleName == it.returnType?.name }

fun Sequence<KoFunctionDeclaration>.withReturnTypeOf(vararg types: KClass<*>) = filter {
    types.any { kClass -> it.returnType?.name == kClass.simpleName }
}

fun Sequence<KoFunctionDeclaration>.withoutReturnTypeOf(vararg types: KClass<*>) = filter {
    types.none { kClass -> it.returnType?.name == kClass.simpleName }
}
