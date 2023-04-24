@file:Suppress("detekt.TooManyFunctions")

package com.lemonappdev.konsist.core.ext

import com.lemonappdev.konsist.core.declaration.KoFunction
import kotlin.reflect.KClass

fun Sequence<KoFunction>.withOperatorModifier() = filter { it.hasOperatorModifier() }

fun Sequence<KoFunction>.withoutOperatorModifier() = filterNot { it.hasOperatorModifier() }

fun Sequence<KoFunction>.withInlineModifier() = filter { it.hasInlineModifier() }

fun Sequence<KoFunction>.withoutInlineModifier() = filterNot { it.hasInlineModifier() }

fun Sequence<KoFunction>.withTailrecModifier() = filter { it.hasTailrecModifier() }

fun Sequence<KoFunction>.withoutTailrecModifier() = filterNot { it.hasTailrecModifier() }

fun Sequence<KoFunction>.withInfixModifier() = filter { it.hasInfixModifier() }

fun Sequence<KoFunction>.withoutInfixModifier() = filterNot { it.hasInfixModifier() }

fun Sequence<KoFunction>.withExternalModifier() = filter { it.hasExternalModifier() }

fun Sequence<KoFunction>.withoutExternalModifier() = filterNot { it.hasExternalModifier() }

fun Sequence<KoFunction>.withSuspendModifier() = filter { it.hasSuspendModifier() }

fun Sequence<KoFunction>.withoutSuspendModifier() = filterNot { it.hasSuspendModifier() }

fun Sequence<KoFunction>.withOpenModifier() = filter { it.hasOpenModifier() }

fun Sequence<KoFunction>.withoutOpenModifier() = filterNot { it.hasOpenModifier() }

fun Sequence<KoFunction>.withOverrideModifier() = filter { it.hasOverrideModifier() }

fun Sequence<KoFunction>.withoutOverrideModifier() = filterNot { it.hasOverrideModifier() }

fun Sequence<KoFunction>.withFinalModifier() = filter { it.hasFinalModifier() }

fun Sequence<KoFunction>.withoutFinalModifier() = filterNot { it.hasFinalModifier() }

fun Sequence<KoFunction>.withAbstractModifier() = filter { it.hasAbstractModifier() }

fun Sequence<KoFunction>.withoutAbstractModifier() = filterNot { it.hasAbstractModifier() }

fun Sequence<KoFunction>.withActualModifier() = filter { it.hasActualModifier() }

fun Sequence<KoFunction>.withoutActualModifier() = filterNot { it.hasActualModifier() }

fun Sequence<KoFunction>.withExpectModifier() = filter { it.hasExpectModifier() }

fun Sequence<KoFunction>.withoutExpectModifier() = filterNot { it.hasExpectModifier() }

fun Sequence<KoFunction>.withExtension() = filter { it.isExtension() }

fun Sequence<KoFunction>.withoutExtension() = filterNot { it.isExtension() }

fun Sequence<KoFunction>.withReturnType(vararg types: String) = filter {
    when {
        types.isEmpty() -> it.hasReturnType()
        else -> types.any { type -> it.returnType?.name == type }
    }
}

fun Sequence<KoFunction>.withoutReturnType(vararg types: String) = filter {
    when {
        types.isEmpty() -> !it.hasReturnType()
        else -> types.none { type -> it.returnType?.name == type }
    }
}

inline fun <reified T> Sequence<KoFunction>.withReturnTypeOf() = filter { T::class.simpleName == it.returnType?.name }

inline fun <reified T> Sequence<KoFunction>.withoutReturnTypeOf() =
    filterNot { T::class.simpleName == it.returnType?.name }

fun Sequence<KoFunction>.withReturnTypeOf(vararg types: KClass<*>) = filter {
    types.any { kClass -> it.returnType?.name == kClass.simpleName }
}

fun Sequence<KoFunction>.withoutReturnTypeOf(vararg types: KClass<*>) = filter {
    types.none { kClass -> it.returnType?.name == kClass.simpleName }
}
