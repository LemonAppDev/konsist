package com.lemonappdev.konsist.core.ext

import com.lemonappdev.konsist.core.declaration.KoComplexDeclaration

fun Sequence<KoComplexDeclaration>.withType(type: String) = filter { it.representsType(type) }

fun Sequence<KoComplexDeclaration>.withoutType(type: String) = filterNot { it.representsType(type) }

inline fun <reified T> Sequence<KoComplexDeclaration>.withType() = filter { it.representsType<T>() }

inline fun <reified T> Sequence<KoComplexDeclaration>.withoutType() = filterNot { it.representsType<T>() }
