package com.lemon.konsist.core.ext

import com.lemon.konsist.core.declaration.KoAnnotation

fun Sequence<KoAnnotation>.withType(type: String) = filter { it.type == type }

fun Sequence<KoAnnotation>.withoutType(type: String) = filterNot { it.type == type }

fun Sequence<KoAnnotation>.withName(name: String) = filter { it.name == name }

inline fun <reified T> Sequence<KoAnnotation>.withName() = filter { it.name == T::class.simpleName }

fun Sequence<KoAnnotation>.withoutName(name: String) = filterNot { it.name == name }

inline fun <reified T> Sequence<KoAnnotation>.withoutName() = filterNot { it.name == T::class.simpleName }

fun Sequence<KoAnnotation>.withFullyQualifiedClassName(name: String) =
    filter { it.fullyQualifiedName == name }

inline fun <reified T> Sequence<KoAnnotation>.withFullyQualifiedClassName() =
    filter { it.fullyQualifiedName == T::class.qualifiedName }

fun Sequence<KoAnnotation>.withoutFullyQualifiedClassName(name: String) = filterNot { it.fullyQualifiedName == name }

inline fun <reified T> Sequence<KoAnnotation>.withoutFullyQualifiedClassName() = filterNot { it.fullyQualifiedName == T::class.qualifiedName }
