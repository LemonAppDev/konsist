package com.lemonappdev.konsist.api.ext

import com.lemonappdev.konsist.api.KoModifier
import com.lemonappdev.konsist.core.declaration.KoComplexDeclarationImpl
import kotlin.reflect.KClass

fun <T : KoComplexDeclarationImpl> Sequence<T>.withType(vararg types: String) = filter {
    types.any { type -> it.representsType(type) }
}

fun <T : KoComplexDeclarationImpl> Sequence<T>.withoutType(vararg types: String) = filter {
    types.none { type -> it.representsType(type) }
}

fun <T : KoComplexDeclarationImpl> Sequence<T>.withTypeOf(vararg types: KClass<*>) = filter {
    types.any { type ->
        type
            .qualifiedName
            ?.let { name -> it.representsType(name) } ?: false
    }
}

fun <T : KoComplexDeclarationImpl> Sequence<T>.withoutTypeOf(vararg types: KClass<*>) = filter {
    types.none { type ->
        type
            .qualifiedName
            ?.let { name -> it.representsType(name) } ?: false
    }
}

inline fun <reified T> Sequence<KoComplexDeclarationImpl>.withTypeOf() = filter { it.representsTypeOf<T>() }

inline fun <reified T> Sequence<KoComplexDeclarationImpl>.withoutTypeOf() = filterNot { it.representsTypeOf<T>() }

fun <T : KoComplexDeclarationImpl> Sequence<T>.declarations(
    modifiers: List<KoModifier> = emptyList(),
    includeNested: Boolean = false,
    includeLocal: Boolean = false,
) = flatMap { it.declarations(modifiers, includeNested, includeLocal) }

fun <T : KoComplexDeclarationImpl> Sequence<T>.classes(
    modifiers: List<KoModifier> = emptyList(),
    includeNested: Boolean = false,
    includeLocal: Boolean = false,
) = flatMap { it.classes(modifiers, includeNested, includeLocal) }

fun <T : KoComplexDeclarationImpl> Sequence<T>.interfaces(
    modifiers: List<KoModifier> = emptyList(),
    includeNested: Boolean = false,
) = flatMap { it.interfaces(modifiers, includeNested) }

fun <T : KoComplexDeclarationImpl> Sequence<T>.objects(
    modifiers: List<KoModifier> = emptyList(),
    includeNested: Boolean = false,
) = flatMap { it.objects(modifiers, includeNested) }

fun <T : KoComplexDeclarationImpl> Sequence<T>.companionObjects(
    modifiers: List<KoModifier> = emptyList(),
    includeNested: Boolean = false,
) = flatMap { it.companionObjects(modifiers, includeNested) }

fun <T : KoComplexDeclarationImpl> Sequence<T>.properties(
    modifiers: List<KoModifier> = emptyList(),
    includeNested: Boolean = false,
    includeLocal: Boolean = false,
) = flatMap { it.properties(modifiers, includeNested, includeLocal) }

fun <T : KoComplexDeclarationImpl> Sequence<T>.functions(
    modifiers: List<KoModifier> = emptyList(),
    includeNested: Boolean = false,
    includeLocal: Boolean = false,
) = flatMap { it.functions(modifiers, includeNested, includeLocal) }
