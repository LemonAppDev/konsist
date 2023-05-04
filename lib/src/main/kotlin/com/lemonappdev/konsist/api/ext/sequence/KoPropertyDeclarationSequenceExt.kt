package com.lemonappdev.konsist.api.ext.sequence

import com.lemonappdev.konsist.api.declaration.KoPropertyDeclaration
import kotlin.reflect.KClass

/**
 * Sequence containing declarations that have 'var' modifier.
 */
fun Sequence<KoPropertyDeclaration>.withVarModifier() = filter { it.isVar }

/**
 * Sequence containing declarations that don't have 'var' modifier.
 */
fun Sequence<KoPropertyDeclaration>.withoutVarModifier() = filterNot { it.isVar }

/**
 * Sequence containing declarations that have 'val' modifier.
 */
fun Sequence<KoPropertyDeclaration>.withValModifier() = filter { it.isVal }

/**
 * Sequence containing declarations that don't have 'val' modifier.
 */
fun Sequence<KoPropertyDeclaration>.withoutValModifier() = filterNot { it.isVal }

/**
 * Sequence containing declarations that have 'lateinit' modifier.
 */
fun Sequence<KoPropertyDeclaration>.withLateinitModifier() = filter { it.hasLateinitModifier() }

/**
 * Sequence containing declarations that don't have 'lateinit' modifier.
 */
fun Sequence<KoPropertyDeclaration>.withoutLateinitModifier() = filterNot { it.hasLateinitModifier() }

/**
 * Sequence containing declarations that have 'override' modifier.
 */
fun Sequence<KoPropertyDeclaration>.withOverrideModifier() = filter { it.hasOverrideModifier() }

/**
 * Sequence containing declarations that don't have 'override' modifier.
 */
fun Sequence<KoPropertyDeclaration>.withoutOverrideModifier() = filterNot { it.hasOverrideModifier() }

/**
 * Sequence containing declarations that have 'abstract' modifier.
 */
fun Sequence<KoPropertyDeclaration>.withAbstractModifier() = filter { it.hasAbstractModifier() }

/**
 * Sequence containing declarations that don't have 'abstract' modifier.
 */
fun Sequence<KoPropertyDeclaration>.withoutAbstractModifier() = filterNot { it.hasAbstractModifier() }

/**
 * Sequence containing declarations that have 'open' modifier.
 */
fun Sequence<KoPropertyDeclaration>.withOpenModifier() = filter { it.hasOpenModifier() }

/**
 * Sequence containing declarations that don't have 'open' modifier.
 */
fun Sequence<KoPropertyDeclaration>.withoutOpenModifier() = filterNot { it.hasOpenModifier() }

/**
 * Sequence containing declarations that have 'final' modifier.
 */
fun Sequence<KoPropertyDeclaration>.withFinalModifier() = filter { it.hasFinalModifier() }

/**
 * Sequence containing declarations that don't have 'final' modifier.
 */
fun Sequence<KoPropertyDeclaration>.withoutFinalModifier() = filterNot { it.hasFinalModifier() }

/**
 * Sequence containing declarations that have 'actual' modifier.
 */
fun Sequence<KoPropertyDeclaration>.withActualModifier() = filter { it.hasActualModifier() }

/**
 * Sequence containing declarations that don't have 'actual' modifier.
 */
fun Sequence<KoPropertyDeclaration>.withoutActualModifier() = filterNot { it.hasActualModifier() }

/**
 * Sequence containing declarations that have 'expect' modifier.
 */
fun Sequence<KoPropertyDeclaration>.withExpectModifier() = filter { it.hasExpectModifier() }

/**
 * Sequence containing declarations that don't have 'expect' modifier.
 */
fun Sequence<KoPropertyDeclaration>.withoutExpectModifier() = filterNot { it.hasExpectModifier() }

/**
 * Sequence containing declarations that have 'const' modifier.
 */
fun Sequence<KoPropertyDeclaration>.withConstModifier() = filter { it.hasConstModifier() }

/**
 * Sequence containing declarations that don't have 'const' modifier.
 */
fun Sequence<KoPropertyDeclaration>.withoutConstModifier() = filterNot { it.hasConstModifier() }

/**
 * Sequence containing declarations that have extension.
 */
fun Sequence<KoPropertyDeclaration>.withExtension() = filter { it.isExtension() }

/**
 * Sequence containing declarations that don't have extension.
 */
fun Sequence<KoPropertyDeclaration>.withoutExtension() = filterNot { it.isExtension() }

/**
 * Sequence containing declarations that have named delegate.
 */
fun Sequence<KoPropertyDeclaration>.withDelegate(vararg names: String) = filter {
    when {
        names.isEmpty() -> it.hasDelegate()
        else -> names.any { name -> it.hasDelegate(name) }
    }
}

/**
 * Sequence containing declarations that don't have named delegate.
 */
fun Sequence<KoPropertyDeclaration>.withoutDelegate(vararg names: String) = filter {
    when {
        names.isEmpty() -> !it.hasDelegate()
        else -> names.none { name -> it.hasDelegate(name) }
    }
}

/**
 * Sequence containing declarations that have type.
 */
fun Sequence<KoPropertyDeclaration>.withType(vararg types: String) = filter {
    when {
        types.isEmpty() -> it.hasType()
        else -> types.any { type -> it.hasType(type) }
    }
}

/**
 * Sequence containing declarations that don't have type.
 */
fun Sequence<KoPropertyDeclaration>.withoutType(vararg types: String) = filter {
    when {
        types.isEmpty() -> !it.hasType()
        else -> types.none { type -> it.hasType(type) }
    }
}

/**
 * Sequence containing declarations that have type of.
 */
fun Sequence<KoPropertyDeclaration>.withTypeOf(vararg types: KClass<*>) = filter {
    types.any { kClass -> it.type?.name == kClass.simpleName }
}

/**
 * Sequence containing declarations that don't have type of.
 */
fun Sequence<KoPropertyDeclaration>.withoutTypeOf(vararg types: KClass<*>) = filter {
    types.none { kClass -> it.type?.name == kClass.simpleName }
}

/**
 * Sequence containing declarations that have type of.
 */
inline fun <reified T> Sequence<KoPropertyDeclaration>.withTypeOf() =
    filter { T::class.simpleName == it.type?.name }

/**
 * Sequence containing declarations that don't have type of.
 */
inline fun <reified T> Sequence<KoPropertyDeclaration>.withoutTypeOf() =
    filterNot { T::class.simpleName == it.type?.name }
