@file:Suppress("detekt.TooManyFunctions")

package com.lemonappdev.konsist.core.ext

import com.lemonappdev.konsist.core.declaration.KoProperty
import kotlin.reflect.KClass

fun Sequence<KoProperty>.withVarModifier() = filter { it.isVar }

fun Sequence<KoProperty>.withoutVarModifier() = filterNot { it.isVar }

fun Sequence<KoProperty>.withValModifier() = filter { it.isVal }

fun Sequence<KoProperty>.withoutValModifier() = filterNot { it.isVal }

fun Sequence<KoProperty>.withLateinitModifier() = filter { it.hasLateinitModifier() }

fun Sequence<KoProperty>.withoutLateinitModifier() = filterNot { it.hasLateinitModifier() }

fun Sequence<KoProperty>.withOverrideModifier() = filter { it.hasOverrideModifier() }

fun Sequence<KoProperty>.withoutOverrideModifier() = filterNot { it.hasOverrideModifier() }

fun Sequence<KoProperty>.withAbstractModifier() = filter { it.hasAbstractModifier() }

fun Sequence<KoProperty>.withoutAbstractModifier() = filterNot { it.hasAbstractModifier() }

fun Sequence<KoProperty>.withOpenModifier() = filter { it.hasOpenModifier() }

fun Sequence<KoProperty>.withoutOpenModifier() = filterNot { it.hasOpenModifier() }

fun Sequence<KoProperty>.withFinalModifier() = filter { it.hasFinalModifier() }

fun Sequence<KoProperty>.withoutFinalModifier() = filterNot { it.hasFinalModifier() }

fun Sequence<KoProperty>.withActualModifier() = filter { it.hasActualModifier() }

fun Sequence<KoProperty>.withoutActualModifier() = filterNot { it.hasActualModifier() }

fun Sequence<KoProperty>.withExpectModifier() = filter { it.hasExpectModifier() }

fun Sequence<KoProperty>.withoutExpectModifier() = filterNot { it.hasExpectModifier() }

fun Sequence<KoProperty>.withConstModifier() = filter { it.hasConstModifier() }

fun Sequence<KoProperty>.withoutConstModifier() = filterNot { it.hasConstModifier() }

fun Sequence<KoProperty>.withExtension() = filter { it.isExtension() }

fun Sequence<KoProperty>.withoutExtension() = filterNot { it.isExtension() }

fun Sequence<KoProperty>.withDelegate(vararg names: String) = filter {
    when {
        names.isEmpty() -> it.hasDelegate()
        else -> names.any { name -> it.hasDelegate(name) }
    }
}

fun Sequence<KoProperty>.withoutDelegate(vararg names: String) = filter {
    when {
        names.isEmpty() -> !it.hasDelegate()
        else -> names.none { name -> it.hasDelegate(name) }
    }
}

fun Sequence<KoProperty>.withType(vararg types: String) = filter {
    when {
        types.isEmpty() -> it.hasType()
        else -> types.any { type -> it.hasType(type) }
    }
}

fun Sequence<KoProperty>.withoutType(vararg types: String) = filter {
    when {
        types.isEmpty() -> !it.hasType()
        else -> types.none { type -> it.hasType(type) }
    }
}

fun Sequence<KoProperty>.withTypeOf(vararg types: KClass<*>) = filter {
    types.any { kClass -> it.type?.name == kClass.simpleName }
}

fun Sequence<KoProperty>.withoutTypeOf(vararg types: KClass<*>) = filter {
    types.none { kClass -> it.type?.name == kClass.simpleName }
}

inline fun <reified T> Sequence<KoProperty>.withTypeOf() =
    filter { T::class.simpleName == it.type?.name }

inline fun <reified T> Sequence<KoProperty>.withoutTypeOf() =
    filterNot { T::class.simpleName == it.type?.name }
