@file:Suppress("detekt.TooManyFunctions")

package com.lemonappdev.konsist.core.ext

import com.lemonappdev.konsist.core.declaration.KoFunction

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

fun Sequence<KoFunction>.withExplicitReturnType(type: String? = null) = filter {
    if (type == null) {
        it.hasExplicitReturnType()
    } else {
        it.explicitReturnType?.name == type
    }
}

fun Sequence<KoFunction>.withoutExplicitReturnType(type: String? = null) = this - withExplicitReturnType(type).toSet()

inline fun <reified T> Sequence<KoFunction>.withExplicitReturnTypeOf() = filter { T::class.simpleName == it.explicitReturnType?.name }

inline fun <reified T> Sequence<KoFunction>.withoutExplicitReturnTypeOf() =
    filterNot { T::class.simpleName == it.explicitReturnType?.name }
