package com.lemonappdev.konsist.api.ext.sequence

import com.lemonappdev.konsist.api.KoModifier
import com.lemonappdev.konsist.api.declaration.KoClassDeclaration
import com.lemonappdev.konsist.api.declaration.KoCompanionObjectDeclaration
import com.lemonappdev.konsist.api.declaration.KoComplexDeclaration
import com.lemonappdev.konsist.api.declaration.KoDeclaration
import com.lemonappdev.konsist.api.declaration.KoFunctionDeclaration
import com.lemonappdev.konsist.api.declaration.KoInterfaceDeclaration
import com.lemonappdev.konsist.api.declaration.KoObjectDeclaration
import com.lemonappdev.konsist.api.declaration.KoPropertyDeclaration
import com.lemonappdev.konsist.api.ext.declaration.representsTypeOf
import kotlin.reflect.KClass

/**
 * Sequence containing declarations that have type of.
 */
fun <T : KoComplexDeclaration> Sequence<T>.withType(vararg types: String): Sequence<T> = filter {
    types.any { type -> it.representsType(type) }
}

/**
 * Sequence containing declarations that don't have type of.
 */
fun <T : KoComplexDeclaration> Sequence<T>.withoutType(vararg types: String): Sequence<T> = filter {
    types.none { type -> it.representsType(type) }
}

/**
 * Sequence containing declarations that have type of.
 */
fun <T : KoComplexDeclaration> Sequence<T>.withTypeOf(vararg types: KClass<*>): Sequence<T> = filter {
    types.any { type ->
        type
            .qualifiedName
            ?.let { name -> it.representsType(name) } ?: false
    }
}

/**
 * Sequence containing declarations that don't have type of.
 */
fun <T : KoComplexDeclaration> Sequence<T>.withoutTypeOf(vararg types: KClass<*>): Sequence<T> = filter {
    types.none { type ->
        type
            .qualifiedName
            ?.let { name -> it.representsType(name) } ?: false
    }
}

/**
 * Sequence containing declarations that have the type of.
 */
inline fun <reified T> Sequence<KoComplexDeclaration>.withTypeOf(): Sequence<KoComplexDeclaration> = filter { it.representsTypeOf<T>() }

/**
 * Sequence containing declarations that don't have the type of.
 */
inline fun <reified T> Sequence<KoComplexDeclaration>.withoutTypeOf(): Sequence<KoComplexDeclaration> =
    filterNot { it.representsTypeOf<T>() }

/**
 * Sequence containing declarations of all types.
 */
fun <T : KoComplexDeclaration> Sequence<T>.declarations(
    modifiers: List<KoModifier> = emptyList(),
    includeNested: Boolean = false,
    includeLocal: Boolean = false,
): Sequence<KoDeclaration> = flatMap { it.declarations(modifiers, includeNested, includeLocal) }

/**
 * Sequence containing class declarations.
 */
fun <T : KoComplexDeclaration> Sequence<T>.classes(
    modifiers: List<KoModifier> = emptyList(),
    includeNested: Boolean = false,
    includeLocal: Boolean = false,
): Sequence<KoClassDeclaration> = flatMap { it.classes(modifiers, includeNested, includeLocal) }

/**
 * Sequence containing interface declarations.
 */
fun <T : KoComplexDeclaration> Sequence<T>.interfaces(
    modifiers: List<KoModifier> = emptyList(),
    includeNested: Boolean = false,
): Sequence<KoInterfaceDeclaration> = flatMap { it.interfaces(modifiers, includeNested) }

/**
 * Sequence containing object declarations.
 */
fun <T : KoComplexDeclaration> Sequence<T>.objects(
    modifiers: List<KoModifier> = emptyList(),
    includeNested: Boolean = false,
): Sequence<KoObjectDeclaration> = flatMap { it.objects(modifiers, includeNested) }

/**
 * Sequence containing companion object declarations.
 */
fun <T : KoComplexDeclaration> Sequence<T>.companionObjects(
    modifiers: List<KoModifier> = emptyList(),
    includeNested: Boolean = false,
): Sequence<KoCompanionObjectDeclaration> = flatMap { it.companionObjects(modifiers, includeNested) }

/**
 * Sequence containing properties declarations.
 */
fun <T : KoComplexDeclaration> Sequence<T>.properties(
    modifiers: List<KoModifier> = emptyList(),
    includeNested: Boolean = false,
    includeLocal: Boolean = false,
): Sequence<KoPropertyDeclaration> = flatMap { it.properties(modifiers, includeNested, includeLocal) }

/**
 * Sequence containing functions declarations.
 */
fun <T : KoComplexDeclaration> Sequence<T>.functions(
    modifiers: List<KoModifier> = emptyList(),
    includeNested: Boolean = false,
    includeLocal: Boolean = false,
): Sequence<KoFunctionDeclaration> = flatMap { it.functions(modifiers, includeNested, includeLocal) }
