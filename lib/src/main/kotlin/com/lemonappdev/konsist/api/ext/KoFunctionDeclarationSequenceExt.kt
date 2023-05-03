@file:Suppress("detekt.TooManyFunctions")

package com.lemonappdev.konsist.api.ext

import com.lemonappdev.konsist.core.declaration.KoFunctionDeclarationImpl
import kotlin.reflect.KClass

fun Sequence<KoFunctionDeclarationImpl>.withOperatorModifier() = filter { it.hasOperatorModifier() }

fun Sequence<KoFunctionDeclarationImpl>.withoutOperatorModifier() = filterNot { it.hasOperatorModifier() }

fun Sequence<KoFunctionDeclarationImpl>.withInlineModifier() = filter { it.hasInlineModifier() }

fun Sequence<KoFunctionDeclarationImpl>.withoutInlineModifier() = filterNot { it.hasInlineModifier() }

fun Sequence<KoFunctionDeclarationImpl>.withTailrecModifier() = filter { it.hasTailrecModifier() }

fun Sequence<KoFunctionDeclarationImpl>.withoutTailrecModifier() = filterNot { it.hasTailrecModifier() }

fun Sequence<KoFunctionDeclarationImpl>.withInfixModifier() = filter { it.hasInfixModifier() }

fun Sequence<KoFunctionDeclarationImpl>.withoutInfixModifier() = filterNot { it.hasInfixModifier() }

fun Sequence<KoFunctionDeclarationImpl>.withExternalModifier() = filter { it.hasExternalModifier() }

fun Sequence<KoFunctionDeclarationImpl>.withoutExternalModifier() = filterNot { it.hasExternalModifier() }

fun Sequence<KoFunctionDeclarationImpl>.withSuspendModifier() = filter { it.hasSuspendModifier() }

fun Sequence<KoFunctionDeclarationImpl>.withoutSuspendModifier() = filterNot { it.hasSuspendModifier() }

fun Sequence<KoFunctionDeclarationImpl>.withOpenModifier() = filter { it.hasOpenModifier() }

fun Sequence<KoFunctionDeclarationImpl>.withoutOpenModifier() = filterNot { it.hasOpenModifier() }

fun Sequence<KoFunctionDeclarationImpl>.withOverrideModifier() = filter { it.hasOverrideModifier() }

fun Sequence<KoFunctionDeclarationImpl>.withoutOverrideModifier() = filterNot { it.hasOverrideModifier() }

fun Sequence<KoFunctionDeclarationImpl>.withFinalModifier() = filter { it.hasFinalModifier() }

fun Sequence<KoFunctionDeclarationImpl>.withoutFinalModifier() = filterNot { it.hasFinalModifier() }

fun Sequence<KoFunctionDeclarationImpl>.withAbstractModifier() = filter { it.hasAbstractModifier() }

fun Sequence<KoFunctionDeclarationImpl>.withoutAbstractModifier() = filterNot { it.hasAbstractModifier() }

fun Sequence<KoFunctionDeclarationImpl>.withActualModifier() = filter { it.hasActualModifier() }

fun Sequence<KoFunctionDeclarationImpl>.withoutActualModifier() = filterNot { it.hasActualModifier() }

fun Sequence<KoFunctionDeclarationImpl>.withExpectModifier() = filter { it.hasExpectModifier() }

fun Sequence<KoFunctionDeclarationImpl>.withoutExpectModifier() = filterNot { it.hasExpectModifier() }

fun Sequence<KoFunctionDeclarationImpl>.withExtension() = filter { it.isExtension() }

fun Sequence<KoFunctionDeclarationImpl>.withoutExtension() = filterNot { it.isExtension() }

fun Sequence<KoFunctionDeclarationImpl>.withReturnType(vararg types: String) = filter {
    when {
        types.isEmpty() -> it.hasReturnType()
        else -> types.any { type -> it.returnType?.name == type }
    }
}

fun Sequence<KoFunctionDeclarationImpl>.withoutReturnType(vararg types: String) = filter {
    when {
        types.isEmpty() -> !it.hasReturnType()
        else -> types.none { type -> it.returnType?.name == type }
    }
}

inline fun <reified T> Sequence<KoFunctionDeclarationImpl>.withReturnTypeOf() = filter { T::class.simpleName == it.returnType?.name }

inline fun <reified T> Sequence<KoFunctionDeclarationImpl>.withoutReturnTypeOf() =
    filterNot { T::class.simpleName == it.returnType?.name }

fun Sequence<KoFunctionDeclarationImpl>.withReturnTypeOf(vararg types: KClass<*>) = filter {
    types.any { kClass -> it.returnType?.name == kClass.simpleName }
}

fun Sequence<KoFunctionDeclarationImpl>.withoutReturnTypeOf(vararg types: KClass<*>) = filter {
    types.none { kClass -> it.returnType?.name == kClass.simpleName }
}
