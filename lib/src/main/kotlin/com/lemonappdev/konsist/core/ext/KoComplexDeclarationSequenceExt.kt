package com.lemonappdev.konsist.core.ext

import com.lemonappdev.konsist.core.declaration.KoComplexDeclaration

fun Sequence<KoComplexDeclaration>.withType(vararg types: String) = filter {
    types.any { type -> it.representsType(type) }
}

fun Sequence<KoComplexDeclaration>.withoutType(vararg types: String) = filter {
    types.none { type -> it.representsType(type) }
}

inline fun <reified T> Sequence<KoComplexDeclaration>.withType() = filter { it.representsType<T>() }

inline fun <reified T> Sequence<KoComplexDeclaration>.withoutType() = filterNot { it.representsType<T>() }
