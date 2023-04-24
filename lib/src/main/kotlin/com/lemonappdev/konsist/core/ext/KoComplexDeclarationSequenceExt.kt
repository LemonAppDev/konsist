package com.lemonappdev.konsist.core.ext

import com.lemonappdev.konsist.core.const.KoModifier
import com.lemonappdev.konsist.core.declaration.KoComplexDeclaration
import kotlin.reflect.KClass

fun Sequence<KoComplexDeclaration>.withType(vararg types: String) = filter {
    types.any { type -> it.representsType(type) }
}

fun Sequence<KoComplexDeclaration>.withoutType(vararg types: String) = filter {
    types.none { type -> it.representsType(type) }
}

fun Sequence<KoComplexDeclaration>.withTypeOf(vararg types: KClass<*>) = filter {
    types.any { type ->
        type
            .qualifiedName
            ?.let { name -> it.representsType(name) } ?: false
    }
}

fun Sequence<KoComplexDeclaration>.withoutTypeOf(vararg types: KClass<*>) = filter {
    types.none { type ->
        type
            .qualifiedName
            ?.let { name -> it.representsType(name) } ?: false
    }
}

inline fun <reified T> Sequence<KoComplexDeclaration>.withTypeOf() = filter { it.representsType<T>() }

inline fun <reified T> Sequence<KoComplexDeclaration>.withoutTypeOf() = filterNot { it.representsType<T>() }

fun Sequence<KoComplexDeclaration>.declarations(
    modifiers: List<KoModifier> = emptyList(),
    includeNested: Boolean = false,
    includeLocal: Boolean = false,
) = flatMap { it.declarations(modifiers, includeNested, includeLocal) }

fun Sequence<KoComplexDeclaration>.classes(
    modifiers: List<KoModifier> = emptyList(),
    includeNested: Boolean = false,
    includeLocal: Boolean = false,
) = flatMap { it.classes(modifiers, includeNested, includeLocal) }

fun Sequence<KoComplexDeclaration>.interfaces(
    modifiers: List<KoModifier> = emptyList(),
    includeNested: Boolean = false,
) = flatMap { it.interfaces(modifiers, includeNested) }

fun Sequence<KoComplexDeclaration>.objects(
    modifiers: List<KoModifier> = emptyList(),
    includeNested: Boolean = false,
) = flatMap { it.objects(modifiers, includeNested) }

fun Sequence<KoComplexDeclaration>.companionObjects(
    modifiers: List<KoModifier> = emptyList(),
    includeNested: Boolean = false,
) = flatMap { it.companionObjects(modifiers, includeNested) }

fun Sequence<KoComplexDeclaration>.properties(
    modifiers: List<KoModifier> = emptyList(),
    includeNested: Boolean = false,
    includeLocal: Boolean = false,
) = flatMap { it.properties(modifiers, includeNested, includeLocal) }

fun Sequence<KoComplexDeclaration>.functions(
    modifiers: List<KoModifier> = emptyList(),
    includeNested: Boolean = false,
    includeLocal: Boolean = false,
) = flatMap { it.functions(modifiers, includeNested, includeLocal) }
