package com.lemonappdev.konsist.core.ext

import com.lemonappdev.konsist.core.declaration.KoComplexDeclaration

fun Sequence<KoComplexDeclaration>.withType(vararg types: String) = filter { koComplexDeclaration ->
    types.any { koComplexDeclaration.representsType(it) }
}

fun Sequence<KoComplexDeclaration>.withoutType(vararg types: String) = filter { koComplexDeclaration ->
    types.none { koComplexDeclaration.representsType(it) }
}

inline fun <reified T> Sequence<KoComplexDeclaration>.withType() = filter { it.representsType<T>() }

inline fun <reified T> Sequence<KoComplexDeclaration>.withoutType() = filterNot { it.representsType<T>() }
