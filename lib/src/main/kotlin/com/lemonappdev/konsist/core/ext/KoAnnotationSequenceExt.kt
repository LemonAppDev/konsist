package com.lemonappdev.konsist.core.ext

import com.lemonappdev.konsist.core.declaration.KoAnnotation

fun Sequence<KoAnnotation>.withType(type: String) = filter { it.representsType(type) }

fun Sequence<KoAnnotation>.withoutType(type: String) = filterNot { it.representsType(type) }

inline fun <reified T> Sequence<KoAnnotation>.withType() = filter { it.representsType<T>() }

inline fun <reified T> Sequence<KoAnnotation>.withoutType() = filterNot { it.representsType<T>() }

fun Sequence<KoAnnotation>.withName(name: String) = filter { it.name == name }

fun Sequence<KoAnnotation>.withoutName(name: String) = filterNot { it.name == name }

fun Sequence<KoAnnotation>.withFullyQualifiedClassName(name: String) =
    filter { it.fullyQualifiedName == name }

fun Sequence<KoAnnotation>.withoutFullyQualifiedClassName(name: String) =
    filterNot { it.fullyQualifiedName == name }
