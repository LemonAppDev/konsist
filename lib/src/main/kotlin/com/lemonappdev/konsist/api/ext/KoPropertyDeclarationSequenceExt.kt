@file:Suppress("detekt.TooManyFunctions")

package com.lemonappdev.konsist.api.ext

import com.lemonappdev.konsist.core.declaration.KoPropertyDeclarationImpl
import kotlin.reflect.KClass

fun Sequence<KoPropertyDeclarationImpl>.withVarModifier() = filter { it.isVar }

fun Sequence<KoPropertyDeclarationImpl>.withoutVarModifier() = filterNot { it.isVar }

fun Sequence<KoPropertyDeclarationImpl>.withValModifier() = filter { it.isVal }

fun Sequence<KoPropertyDeclarationImpl>.withoutValModifier() = filterNot { it.isVal }

fun Sequence<KoPropertyDeclarationImpl>.withLateinitModifier() = filter { it.hasLateinitModifier() }

fun Sequence<KoPropertyDeclarationImpl>.withoutLateinitModifier() = filterNot { it.hasLateinitModifier() }

fun Sequence<KoPropertyDeclarationImpl>.withOverrideModifier() = filter { it.hasOverrideModifier() }

fun Sequence<KoPropertyDeclarationImpl>.withoutOverrideModifier() = filterNot { it.hasOverrideModifier() }

fun Sequence<KoPropertyDeclarationImpl>.withAbstractModifier() = filter { it.hasAbstractModifier() }

fun Sequence<KoPropertyDeclarationImpl>.withoutAbstractModifier() = filterNot { it.hasAbstractModifier() }

fun Sequence<KoPropertyDeclarationImpl>.withOpenModifier() = filter { it.hasOpenModifier() }

fun Sequence<KoPropertyDeclarationImpl>.withoutOpenModifier() = filterNot { it.hasOpenModifier() }

fun Sequence<KoPropertyDeclarationImpl>.withFinalModifier() = filter { it.hasFinalModifier() }

fun Sequence<KoPropertyDeclarationImpl>.withoutFinalModifier() = filterNot { it.hasFinalModifier() }

fun Sequence<KoPropertyDeclarationImpl>.withActualModifier() = filter { it.hasActualModifier() }

fun Sequence<KoPropertyDeclarationImpl>.withoutActualModifier() = filterNot { it.hasActualModifier() }

fun Sequence<KoPropertyDeclarationImpl>.withExpectModifier() = filter { it.hasExpectModifier() }

fun Sequence<KoPropertyDeclarationImpl>.withoutExpectModifier() = filterNot { it.hasExpectModifier() }

fun Sequence<KoPropertyDeclarationImpl>.withConstModifier() = filter { it.hasConstModifier() }

fun Sequence<KoPropertyDeclarationImpl>.withoutConstModifier() = filterNot { it.hasConstModifier() }

fun Sequence<KoPropertyDeclarationImpl>.withExtension() = filter { it.isExtension() }

fun Sequence<KoPropertyDeclarationImpl>.withoutExtension() = filterNot { it.isExtension() }

fun Sequence<KoPropertyDeclarationImpl>.withDelegate(vararg names: String) = filter {
    when {
        names.isEmpty() -> it.hasDelegate()
        else -> names.any { name -> it.hasDelegate(name) }
    }
}

fun Sequence<KoPropertyDeclarationImpl>.withoutDelegate(vararg names: String) = filter {
    when {
        names.isEmpty() -> !it.hasDelegate()
        else -> names.none { name -> it.hasDelegate(name) }
    }
}

fun Sequence<KoPropertyDeclarationImpl>.withType(vararg types: String) = filter {
    when {
        types.isEmpty() -> it.hasType()
        else -> types.any { type -> it.hasType(type) }
    }
}

fun Sequence<KoPropertyDeclarationImpl>.withoutType(vararg types: String) = filter {
    when {
        types.isEmpty() -> !it.hasType()
        else -> types.none { type -> it.hasType(type) }
    }
}

fun Sequence<KoPropertyDeclarationImpl>.withTypeOf(vararg types: KClass<*>) = filter {
    types.any { kClass -> it.type?.name == kClass.simpleName }
}

fun Sequence<KoPropertyDeclarationImpl>.withoutTypeOf(vararg types: KClass<*>) = filter {
    types.none { kClass -> it.type?.name == kClass.simpleName }
}

inline fun <reified T> Sequence<KoPropertyDeclarationImpl>.withTypeOf() =
    filter { T::class.simpleName == it.type?.name }

inline fun <reified T> Sequence<KoPropertyDeclarationImpl>.withoutTypeOf() =
    filterNot { T::class.simpleName == it.type?.name }
