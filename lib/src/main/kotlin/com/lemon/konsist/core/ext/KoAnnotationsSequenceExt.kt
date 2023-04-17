package com.lemon.konsist.core.ext

import com.lemon.konsist.core.declaration.KoAnnotation

fun Sequence<KoAnnotation>.withType(type: String) = filter { it.type == type }

fun Sequence<KoAnnotation>.withoutType(type: String) = this - withType(type).toSet()

fun Sequence<KoAnnotation>.withName(name: String) = filter { it.name == name }

inline fun <reified T> Sequence<KoAnnotation>.withName() = filter { it.name == T::class.simpleName }

fun Sequence<KoAnnotation>.withoutName(name: String) = this - withName(name).toSet()

inline fun <reified T> Sequence<KoAnnotation>.withoutName() = this - withName<T>().toSet()

fun Sequence<KoAnnotation>.withFullyQualifiedClassName(name: String) =
    filter { it.fullyQualifiedName == name }

inline fun <reified T> Sequence<KoAnnotation>.withFullyQualifiedClassName() =
    filter { it.fullyQualifiedName == T::class.qualifiedName }

fun Sequence<KoAnnotation>.withoutFullyQualifiedClassName(name: String) =
    this - withFullyQualifiedClassName(name).toSet()

inline fun <reified T> Sequence<KoAnnotation>.withoutFullyQualifiedClassName() =
    this - withFullyQualifiedClassName<T>().toSet()
