package com.lemonappdev.konsist.core.ext

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
