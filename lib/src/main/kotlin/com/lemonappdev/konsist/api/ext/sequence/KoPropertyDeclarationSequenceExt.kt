@file:Suppress("detekt.TooManyFunctions")

package com.lemonappdev.konsist.api.ext.sequence

import com.lemonappdev.konsist.api.declaration.KoPropertyDeclaration
import kotlin.reflect.KClass

fun Sequence<KoPropertyDeclaration>.withVarModifier() = filter { it.isVar }

fun Sequence<KoPropertyDeclaration>.withoutVarModifier() = filterNot { it.isVar }

fun Sequence<KoPropertyDeclaration>.withValModifier() = filter { it.isVal }

fun Sequence<KoPropertyDeclaration>.withoutValModifier() = filterNot { it.isVal }

fun Sequence<KoPropertyDeclaration>.withLateinitModifier() = filter { it.hasLateinitModifier() }

fun Sequence<KoPropertyDeclaration>.withoutLateinitModifier() = filterNot { it.hasLateinitModifier() }

fun Sequence<KoPropertyDeclaration>.withOverrideModifier() = filter { it.hasOverrideModifier() }

fun Sequence<KoPropertyDeclaration>.withoutOverrideModifier() = filterNot { it.hasOverrideModifier() }

fun Sequence<KoPropertyDeclaration>.withAbstractModifier() = filter { it.hasAbstractModifier() }

fun Sequence<KoPropertyDeclaration>.withoutAbstractModifier() = filterNot { it.hasAbstractModifier() }

fun Sequence<KoPropertyDeclaration>.withOpenModifier() = filter { it.hasOpenModifier() }

fun Sequence<KoPropertyDeclaration>.withoutOpenModifier() = filterNot { it.hasOpenModifier() }

fun Sequence<KoPropertyDeclaration>.withFinalModifier() = filter { it.hasFinalModifier() }

fun Sequence<KoPropertyDeclaration>.withoutFinalModifier() = filterNot { it.hasFinalModifier() }

fun Sequence<KoPropertyDeclaration>.withActualModifier() = filter { it.hasActualModifier() }

fun Sequence<KoPropertyDeclaration>.withoutActualModifier() = filterNot { it.hasActualModifier() }

fun Sequence<KoPropertyDeclaration>.withExpectModifier() = filter { it.hasExpectModifier() }

fun Sequence<KoPropertyDeclaration>.withoutExpectModifier() = filterNot { it.hasExpectModifier() }

fun Sequence<KoPropertyDeclaration>.withConstModifier() = filter { it.hasConstModifier() }

fun Sequence<KoPropertyDeclaration>.withoutConstModifier() = filterNot { it.hasConstModifier() }

fun Sequence<KoPropertyDeclaration>.withExtension() = filter { it.isExtension() }

fun Sequence<KoPropertyDeclaration>.withoutExtension() = filterNot { it.isExtension() }

fun Sequence<KoPropertyDeclaration>.withDelegate(vararg names: String) = filter {
    when {
        names.isEmpty() -> it.hasDelegate()
        else -> names.any { name -> it.hasDelegate(name) }
    }
}

fun Sequence<KoPropertyDeclaration>.withoutDelegate(vararg names: String) = filter {
    when {
        names.isEmpty() -> !it.hasDelegate()
        else -> names.none { name -> it.hasDelegate(name) }
    }
}

fun Sequence<KoPropertyDeclaration>.withType(vararg types: String) = filter {
    when {
        types.isEmpty() -> it.hasType()
        else -> types.any { type -> it.hasType(type) }
    }
}

fun Sequence<KoPropertyDeclaration>.withoutType(vararg types: String) = filter {
    when {
        types.isEmpty() -> !it.hasType()
        else -> types.none { type -> it.hasType(type) }
    }
}

fun Sequence<KoPropertyDeclaration>.withTypeOf(vararg types: KClass<*>) = filter {
    types.any { kClass -> it.type?.name == kClass.simpleName }
}

fun Sequence<KoPropertyDeclaration>.withoutTypeOf(vararg types: KClass<*>) = filter {
    types.none { kClass -> it.type?.name == kClass.simpleName }
}

inline fun <reified T> Sequence<KoPropertyDeclaration>.withTypeOf() =
    filter { T::class.simpleName == it.type?.name }

inline fun <reified T> Sequence<KoPropertyDeclaration>.withoutTypeOf() =
    filterNot { T::class.simpleName == it.type?.name }
