package com.lemonappdev.konsist.api.ext.sequence

import com.lemonappdev.konsist.api.declaration.KoPropertyDeclaration
import kotlin.reflect.KClass

/**
 * Sequence containing properties with `var` modifier.
 *
 * @return A sequence containing properties with the `var` modifier.
 */
fun Sequence<KoPropertyDeclaration>.withVarModifier(): Sequence<KoPropertyDeclaration> = filter { it.isVar }

/**
 * Sequence containing properties without `var` modifier.
 *
 * @return A sequence containing properties without the `var` modifier.
 */
fun Sequence<KoPropertyDeclaration>.withoutVarModifier(): Sequence<KoPropertyDeclaration> = filterNot { it.isVar }

/**
 * Sequence containing properties with `val` modifier.
 *
 * @return A sequence containing properties with the `val` modifier.
 */
fun Sequence<KoPropertyDeclaration>.withValModifier(): Sequence<KoPropertyDeclaration> = filter { it.isVal }

/**
 * Sequence containing properties without `val` modifier.
 *
 * @return A sequence containing properties without the `val` modifier.
 */
fun Sequence<KoPropertyDeclaration>.withoutValModifier(): Sequence<KoPropertyDeclaration> = filterNot { it.isVal }

/**
 * Sequence containing properties with extension.
 *
 * @return A sequence containing properties with extensions.
 */
fun Sequence<KoPropertyDeclaration>.withExtension(): Sequence<KoPropertyDeclaration> = filter { it.isExtension() }

/**
 * Sequence containing properties without extension.
 *
 * @return A sequence containing properties without extensions.
 */
fun Sequence<KoPropertyDeclaration>.withoutExtension(): Sequence<KoPropertyDeclaration> = filterNot { it.isExtension() }

/**
 * Sequence containing properties with named delegate.
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
 * Sequence containing properties without named delegate.
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
 * Sequence containing properties with explicit type.
 *
 * @param types The type(s) to include.
 * @return A sequence containing properties with the specified type (or any type if [types] is empty).
 */
fun Sequence<KoPropertyDeclaration>.withExplicitType(vararg types: String): Sequence<KoPropertyDeclaration> = filter {
    when {
        types.isEmpty() -> it.hasExplicitType()
        else -> types.any { type -> it.hasExplicitType(type) }
    }
}

/**
 * Sequence containing properties without explicit type.
 *
 * @param types The type(s) to exclude.
 * @return A sequence containing properties without specified type (or none type if [types] is empty).
 */
fun Sequence<KoPropertyDeclaration>.withoutExplicitType(vararg types: String): Sequence<KoPropertyDeclaration> = filter {
    when {
        types.isEmpty() -> !it.hasExplicitType()
        else -> types.none { type -> it.hasExplicitType(type) }
    }
}

/**
 * Sequence containing properties with explicit type of.
 *
 * @param types The Kotlin class(es) representing the type(s) to include.
 * @return A sequence containing properties with the type of the specified Kotlin class(es).
 */
fun Sequence<KoPropertyDeclaration>.withExplicitTypeOf(vararg types: KClass<*>): Sequence<KoPropertyDeclaration> = filter {
    types.any { kClass -> it.explicitType?.name == kClass.simpleName }
}

/**
 * Sequence containing properties without explicit type of.
 *
 * @param types The Kotlin class(es) representing the type(s) to exclude.
 * @return A sequence containing properties without type of the specified Kotlin class(es).
 */
fun Sequence<KoPropertyDeclaration>.withoutExplicitTypeOf(vararg types: KClass<*>): Sequence<KoPropertyDeclaration> = filter {
    types.none { kClass -> it.explicitType?.name == kClass.simpleName }
}

/**
 * Sequence containing properties with explicit type of.
 *
 * @return A sequence containing properties with the specified type.
 */
inline fun <reified T> Sequence<KoPropertyDeclaration>.withExplicitTypeOf(): Sequence<KoPropertyDeclaration> =
    filter { T::class.simpleName == it.explicitType?.name }

/**
 * Sequence containing properties without explicit type of.
 *
 * @return A sequence containing properties without specified type.
 */
inline fun <reified T> Sequence<KoPropertyDeclaration>.withoutExplicitTypeOf(): Sequence<KoPropertyDeclaration> =
    filterNot { T::class.simpleName == it.explicitType?.name }
