package com.lemonappdev.konsist.api.ext.sequence

import com.lemonappdev.konsist.api.declaration.KoPropertyDeclaration
import kotlin.reflect.KClass

/**
 * Sequence containing properties that have `var` modifier.
 *
 * @return A sequence containing properties with the `var` modifier.
 */
fun Sequence<KoPropertyDeclaration>.withVarModifier(): Sequence<KoPropertyDeclaration> = filter { it.isVar }

/**
 * Sequence containing properties that don't have `var` modifier.
 *
 * @return A sequence containing properties without the `var` modifier.
 */
fun Sequence<KoPropertyDeclaration>.withoutVarModifier(): Sequence<KoPropertyDeclaration> = filterNot { it.isVar }

/**
 * Sequence containing properties that have `val` modifier.
 *
 * @return A sequence containing properties with the `val` modifier.
 */
fun Sequence<KoPropertyDeclaration>.withValModifier(): Sequence<KoPropertyDeclaration> = filter { it.isVal }

/**
 * Sequence containing properties that don't have `val` modifier.
 *
 * @return A sequence containing properties without the `val` modifier.
 */
fun Sequence<KoPropertyDeclaration>.withoutValModifier(): Sequence<KoPropertyDeclaration> = filterNot { it.isVal }

/**
 * Sequence containing properties that have `lateinit` modifier.
 *
 * @return A sequence containing properties with the `lateinit` modifier.
 */
fun Sequence<KoPropertyDeclaration>.withLateinitModifier(): Sequence<KoPropertyDeclaration> = filter { it.hasLateinitModifier() }

/**
 * Sequence containing properties that don't have `lateinit` modifier.
 *
 * @return A sequence containing properties without the `lateinit` modifier.
 */
fun Sequence<KoPropertyDeclaration>.withoutLateinitModifier(): Sequence<KoPropertyDeclaration> = filterNot { it.hasLateinitModifier() }

/**
 * Sequence containing properties that have `override` modifier.
 *
 * @return A sequence containing properties with the `override` modifier.
 */
fun Sequence<KoPropertyDeclaration>.withOverrideModifier(): Sequence<KoPropertyDeclaration> = filter { it.hasOverrideModifier() }

/**
 * Sequence containing properties that don't have `override` modifier.
 *
 * @return A sequence containing properties without the `override` modifier.
 */
fun Sequence<KoPropertyDeclaration>.withoutOverrideModifier(): Sequence<KoPropertyDeclaration> = filterNot { it.hasOverrideModifier() }

/**
 * Sequence containing properties that have `abstract` modifier.
 *
 * @return A sequence containing properties with the `abstract` modifier.
 */
fun Sequence<KoPropertyDeclaration>.withAbstractModifier(): Sequence<KoPropertyDeclaration> = filter { it.hasAbstractModifier() }

/**
 * Sequence containing properties that don't have `abstract` modifier.
 *
 * @return A sequence containing properties without the `abstract` modifier.
 */
fun Sequence<KoPropertyDeclaration>.withoutAbstractModifier(): Sequence<KoPropertyDeclaration> = filterNot { it.hasAbstractModifier() }

/**
 * Sequence containing properties that have `open` modifier.
 *
 * @return A sequence containing properties with the `open` modifier.
 */
fun Sequence<KoPropertyDeclaration>.withOpenModifier(): Sequence<KoPropertyDeclaration> = filter { it.hasOpenModifier() }

/**
 * Sequence containing properties that don't have `open` modifier.
 *
 * @return A sequence containing properties without the `open` modifier.
 */
fun Sequence<KoPropertyDeclaration>.withoutOpenModifier(): Sequence<KoPropertyDeclaration> = filterNot { it.hasOpenModifier() }

/**
 * Sequence containing properties that have `final` modifier.
 *
 * @return A sequence containing properties with the `final` modifier.
 */
fun Sequence<KoPropertyDeclaration>.withFinalModifier(): Sequence<KoPropertyDeclaration> = filter { it.hasFinalModifier() }

/**
 * Sequence containing properties that don't have `final` modifier.
 *
 * @return A sequence containing properties without the `final` modifier.
 */
fun Sequence<KoPropertyDeclaration>.withoutFinalModifier(): Sequence<KoPropertyDeclaration> = filterNot { it.hasFinalModifier() }

/**
 * Sequence containing properties that have `actual` modifier.
 *
 * @return A sequence containing properties with the `actual` modifier.
 */
fun Sequence<KoPropertyDeclaration>.withActualModifier(): Sequence<KoPropertyDeclaration> = filter { it.hasActualModifier() }

/**
 * Sequence containing properties that don't have `actual` modifier.
 *
 * @return A sequence containing properties without the `actual` modifier.
 */
fun Sequence<KoPropertyDeclaration>.withoutActualModifier(): Sequence<KoPropertyDeclaration> = filterNot { it.hasActualModifier() }

/**
 * Sequence containing properties that have `expect` modifier.
 *
 * @return A sequence containing properties with the `expect` modifier.
 */
fun Sequence<KoPropertyDeclaration>.withExpectModifier(): Sequence<KoPropertyDeclaration> = filter { it.hasExpectModifier() }

/**
 * Sequence containing properties that don't have `expect` modifier.
 *
 * @return A sequence containing properties without the `expect` modifier.
 */
fun Sequence<KoPropertyDeclaration>.withoutExpectModifier(): Sequence<KoPropertyDeclaration> = filterNot { it.hasExpectModifier() }

/**
 * Sequence containing properties that have `const` modifier.
 *
 * @return A sequence containing properties with the `const` modifier.
 */
fun Sequence<KoPropertyDeclaration>.withConstModifier(): Sequence<KoPropertyDeclaration> = filter { it.hasConstModifier() }

/**
 * Sequence containing properties that don't have `const` modifier.
 *
 * @return A sequence containing properties without the `const` modifier.
 */
fun Sequence<KoPropertyDeclaration>.withoutConstModifier(): Sequence<KoPropertyDeclaration> = filterNot { it.hasConstModifier() }

/**
 * Sequence containing properties that have extension.
 *
 * @return A sequence containing properties that have extensions.
 */
fun Sequence<KoPropertyDeclaration>.withExtension(): Sequence<KoPropertyDeclaration> = filter { it.isExtension() }

/**
 * Sequence containing properties that don't have extension.
 *
 * @return A sequence containing properties that don't have extensions.
 */
fun Sequence<KoPropertyDeclaration>.withoutExtension(): Sequence<KoPropertyDeclaration> = filterNot { it.isExtension() }

/**
 * Sequence containing properties that have named delegate.
 *
 * @param names The delegate names to include.
 * @return A sequence containing properties with the specified delegate name(s) (or any delegate if [names] is empty).
 */
fun Sequence<KoPropertyDeclaration>.withDelegate(vararg names: String): Sequence<KoPropertyDeclaration> = filter {
    when {
        names.isEmpty() -> it.hasDelegate()
        else -> names.any { name -> it.hasDelegate(name) }
    }
}

/**
 * Sequence containing properties that don't have named delegate.
 *
 * @param names The delegate names to exclude.
 * @return A sequence containing properties without the specified delegate name(s) (or none delegate if [names] is empty).
 */
fun Sequence<KoPropertyDeclaration>.withoutDelegate(vararg names: String): Sequence<KoPropertyDeclaration> = filter {
    when {
        names.isEmpty() -> !it.hasDelegate()
        else -> names.none { name -> it.hasDelegate(name) }
    }
}

/**
 * Sequence containing properties that have the explicit type.
 *
 * @param types The type(s) to include.
 * @return A sequence containing properties that have the specified type (or any type if [types] is empty).
 */
fun Sequence<KoPropertyDeclaration>.withExplicitType(vararg types: String): Sequence<KoPropertyDeclaration> = filter {
    when {
        types.isEmpty() -> it.hasExplicitType()
        else -> types.any { type -> it.hasExplicitType(type) }
    }
}

/**
 * Sequence containing properties that don't have the explicit type.
 *
 * @param types The type(s) to exclude.
 * @return A sequence containing properties that don't have the specified type (or none type if [types] is empty).
 */
fun Sequence<KoPropertyDeclaration>.withoutExplicitType(vararg types: String): Sequence<KoPropertyDeclaration> = filter {
    when {
        types.isEmpty() -> !it.hasExplicitType()
        else -> types.none { type -> it.hasExplicitType(type) }
    }
}

/**
 * Sequence containing properties that have explicit type of.
 *
 * @param types The Kotlin class(es) representing the type(s) to include.
 * @return A sequence containing properties that have the type of the specified Kotlin class(es).
 */
fun Sequence<KoPropertyDeclaration>.withExplicitTypeOf(vararg types: KClass<*>): Sequence<KoPropertyDeclaration> = filter {
    types.any { kClass -> it.explicitType?.name == kClass.simpleName }
}

/**
 * Sequence containing properties that don't have explicit type of.
 *
 * @param types The Kotlin class(es) representing the type(s) to exclude.
 * @return A sequence containing properties that don't have the type of the specified Kotlin class(es).
 */
fun Sequence<KoPropertyDeclaration>.withoutExplicitTypeOf(vararg types: KClass<*>): Sequence<KoPropertyDeclaration> = filter {
    types.none { kClass -> it.explicitType?.name == kClass.simpleName }
}

/**
 * Sequence containing properties that have explicit type of.
 *
 * @return A sequence containing properties that have the specified type.
 */
inline fun <reified T> Sequence<KoPropertyDeclaration>.withExplicitTypeOf(): Sequence<KoPropertyDeclaration> =
    filter { T::class.simpleName == it.explicitType?.name }

/**
 * Sequence containing properties that don't have explicit type of.
 *
 * @return A sequence containing properties that don't have the specified type.
 */
inline fun <reified T> Sequence<KoPropertyDeclaration>.withoutExplicitTypeOf(): Sequence<KoPropertyDeclaration> =
    filterNot { T::class.simpleName == it.explicitType?.name }
