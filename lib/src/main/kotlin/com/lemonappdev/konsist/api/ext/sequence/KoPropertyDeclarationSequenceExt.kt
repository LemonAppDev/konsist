package com.lemonappdev.konsist.api.ext.sequence

import com.lemonappdev.konsist.api.declaration.KoPropertyDeclaration
import kotlin.reflect.KClass

/**
 * Sequence containing declarations that have 'var' modifier.
 *
 * @return A sequence containing property declarations with the 'var' modifier.
 */
fun Sequence<KoPropertyDeclaration>.withVarModifier(): Sequence<KoPropertyDeclaration> = filter { it.isVar }

/**
 * Sequence containing declarations that don't have 'var' modifier.
 *
 * @return A sequence containing property declarations without the 'var' modifier.
 */
fun Sequence<KoPropertyDeclaration>.withoutVarModifier(): Sequence<KoPropertyDeclaration> = filterNot { it.isVar }

/**
 * Sequence containing declarations that have 'val' modifier.
 *
 * @return A sequence containing property declarations with the 'val' modifier.
 */
fun Sequence<KoPropertyDeclaration>.withValModifier(): Sequence<KoPropertyDeclaration> = filter { it.isVal }

/**
 * Sequence containing declarations that don't have 'val' modifier.
 *
 * @return A sequence containing property declarations without the 'val' modifier.
 */
fun Sequence<KoPropertyDeclaration>.withoutValModifier(): Sequence<KoPropertyDeclaration> = filterNot { it.isVal }

/**
 * Sequence containing declarations that have 'lateinit' modifier.
 *
 * @return A sequence containing property declarations with the 'lateinit' modifier.
 */
fun Sequence<KoPropertyDeclaration>.withLateinitModifier(): Sequence<KoPropertyDeclaration> = filter { it.hasLateinitModifier() }

/**
 * Sequence containing declarations that don't have 'lateinit' modifier.
 *
 * @return A sequence containing property declarations without the 'lateinit' modifier.
 */
fun Sequence<KoPropertyDeclaration>.withoutLateinitModifier(): Sequence<KoPropertyDeclaration> = filterNot { it.hasLateinitModifier() }

/**
 * Sequence containing declarations that have 'override' modifier.
 *
 * @return A sequence containing property declarations with the 'override' modifier.
 */
fun Sequence<KoPropertyDeclaration>.withOverrideModifier(): Sequence<KoPropertyDeclaration> = filter { it.hasOverrideModifier() }

/**
 * Sequence containing declarations that don't have 'override' modifier.
 *
 * @return A sequence containing property declarations without the 'override' modifier.
 */
fun Sequence<KoPropertyDeclaration>.withoutOverrideModifier(): Sequence<KoPropertyDeclaration> = filterNot { it.hasOverrideModifier() }

/**
 * Sequence containing declarations that have 'abstract' modifier.
 *
 * @return A sequence containing property declarations with the 'abstract' modifier.
 */
fun Sequence<KoPropertyDeclaration>.withAbstractModifier(): Sequence<KoPropertyDeclaration> = filter { it.hasAbstractModifier() }

/**
 * Sequence containing declarations that don't have 'abstract' modifier.
 *
 * @return A sequence containing property declarations without the 'abstract' modifier.
 */
fun Sequence<KoPropertyDeclaration>.withoutAbstractModifier(): Sequence<KoPropertyDeclaration> = filterNot { it.hasAbstractModifier() }

/**
 * Sequence containing declarations that have 'open' modifier.
 *
 * @return A sequence containing property declarations with the 'open' modifier.
 */
fun Sequence<KoPropertyDeclaration>.withOpenModifier(): Sequence<KoPropertyDeclaration> = filter { it.hasOpenModifier() }

/**
 * Sequence containing declarations that don't have 'open' modifier.
 *
 * @return A sequence containing property declarations without the 'open' modifier.
 */
fun Sequence<KoPropertyDeclaration>.withoutOpenModifier(): Sequence<KoPropertyDeclaration> = filterNot { it.hasOpenModifier() }

/**
 * Sequence containing declarations that have 'final' modifier.
 *
 * @return A sequence containing property declarations with the 'final' modifier.
 */
fun Sequence<KoPropertyDeclaration>.withFinalModifier(): Sequence<KoPropertyDeclaration> = filter { it.hasFinalModifier() }

/**
 * Sequence containing declarations that don't have 'final' modifier.
 *
 * @return A sequence containing property declarations without the 'final' modifier.
 */
fun Sequence<KoPropertyDeclaration>.withoutFinalModifier(): Sequence<KoPropertyDeclaration> = filterNot { it.hasFinalModifier() }

/**
 * Sequence containing declarations that have 'actual' modifier.
 *
 * @return A sequence containing property declarations with the 'actual' modifier.
 */
fun Sequence<KoPropertyDeclaration>.withActualModifier(): Sequence<KoPropertyDeclaration> = filter { it.hasActualModifier() }

/**
 * Sequence containing declarations that don't have 'actual' modifier.
 *
 * @return A sequence containing property declarations without the 'actual' modifier.
 */
fun Sequence<KoPropertyDeclaration>.withoutActualModifier(): Sequence<KoPropertyDeclaration> = filterNot { it.hasActualModifier() }

/**
 * Sequence containing declarations that have 'expect' modifier.
 *
 * @return A sequence containing property declarations with the 'expect' modifier.
 */
fun Sequence<KoPropertyDeclaration>.withExpectModifier(): Sequence<KoPropertyDeclaration> = filter { it.hasExpectModifier() }

/**
 * Sequence containing declarations that don't have 'expect' modifier.
 *
 * @return A sequence containing property declarations without the 'expect' modifier.
 */
fun Sequence<KoPropertyDeclaration>.withoutExpectModifier(): Sequence<KoPropertyDeclaration> = filterNot { it.hasExpectModifier() }

/**
 * Sequence containing declarations that have 'const' modifier.
 *
 * @return A sequence containing property declarations with the 'const' modifier.
 */
fun Sequence<KoPropertyDeclaration>.withConstModifier(): Sequence<KoPropertyDeclaration> = filter { it.hasConstModifier() }

/**
 * Sequence containing declarations that don't have 'const' modifier.
 *
 * @return A sequence containing property declarations without the 'const' modifier.
 */
fun Sequence<KoPropertyDeclaration>.withoutConstModifier(): Sequence<KoPropertyDeclaration> = filterNot { it.hasConstModifier() }

/**
 * Sequence containing declarations that have extension.
 *
 * @return A sequence containing property declarations that have extensions.
 */
fun Sequence<KoPropertyDeclaration>.withExtension(): Sequence<KoPropertyDeclaration> = filter { it.isExtension() }

/**
 * Sequence containing declarations that don't have extension.
 *
 * @return A sequence containing property declarations that don't have extensions.
 */
fun Sequence<KoPropertyDeclaration>.withoutExtension(): Sequence<KoPropertyDeclaration> = filterNot { it.isExtension() }

/**
 * Sequence containing declarations that have named delegate.
 *
 * @param names The delegate names to include. If empty, all property declarations with delegate are included.
 * @return A sequence containing property declarations with the specified delegate name(s).
 */
fun Sequence<KoPropertyDeclaration>.withDelegate(vararg names: String): Sequence<KoPropertyDeclaration> = filter {
    when {
        names.isEmpty() -> it.hasDelegate()
        else -> names.any { name -> it.hasDelegate(name) }
    }
}

/**
 * Sequence containing declarations that don't have named delegate.
 *
 * @param names The delegate names to exclude. If empty, all property declarations without delegate are included.
 * @return A sequence containing property declarations without the specified delegate name(s).
 */
fun Sequence<KoPropertyDeclaration>.withoutDelegate(vararg names: String): Sequence<KoPropertyDeclaration> = filter {
    when {
        names.isEmpty() -> !it.hasDelegate()
        else -> names.none { name -> it.hasDelegate(name) }
    }
}

/**
 * Sequence containing declarations that have type.
 *
 * @param types The type(s) to include. If empty, all property declarations with specified type are included.
 * @return A sequence containing property declarations that have the specified type.
 */
fun Sequence<KoPropertyDeclaration>.withType(vararg types: String): Sequence<KoPropertyDeclaration> = filter {
    when {
        types.isEmpty() -> it.hasType()
        else -> types.any { type -> it.hasType(type) }
    }
}

/**
 * Sequence containing declarations that don't have type.
 *
 * @param types The type(s) to exclude. If empty, all property declarations without specified type are included.
 * @return A sequence containing property declarations that don't have the specified type.
 */
fun Sequence<KoPropertyDeclaration>.withoutType(vararg types: String): Sequence<KoPropertyDeclaration> = filter {
    when {
        types.isEmpty() -> !it.hasType()
        else -> types.none { type -> it.hasType(type) }
    }
}

/**
 * Sequence containing declarations that have type of.
 *
 * @param types The Kotlin class(es) representing the type(s) to include.
 * @return A sequence containing property declarations that have the type of the specified Kotlin class(es).
 */
fun Sequence<KoPropertyDeclaration>.withTypeOf(vararg types: KClass<*>): Sequence<KoPropertyDeclaration> = filter {
    types.any { kClass -> it.type?.name == kClass.simpleName }
}

/**
 * Sequence containing declarations that don't have type of.
 *
 * @param types The Kotlin class(es) representing the type(s) to exclude.
 * @return A sequence containing property declarations that don't have the type of the specified Kotlin class(es).
 */
fun Sequence<KoPropertyDeclaration>.withoutTypeOf(vararg types: KClass<*>): Sequence<KoPropertyDeclaration> = filter {
    types.none { kClass -> it.type?.name == kClass.simpleName }
}

/**
 * Sequence containing declarations that have type of.
 *
 * @return A sequence containing property declarations that have the specified type.
 */
inline fun <reified T> Sequence<KoPropertyDeclaration>.withTypeOf(): Sequence<KoPropertyDeclaration> =
    filter { T::class.simpleName == it.type?.name }

/**
 * Sequence containing declarations that don't have type of.
 *
 * @return A sequence containing property declarations that don't have the specified type.
 */
inline fun <reified T> Sequence<KoPropertyDeclaration>.withoutTypeOf(): Sequence<KoPropertyDeclaration> =
    filterNot { T::class.simpleName == it.type?.name }
