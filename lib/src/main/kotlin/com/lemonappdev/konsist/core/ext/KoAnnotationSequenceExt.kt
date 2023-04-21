package com.lemonappdev.konsist.core.ext

import com.lemonappdev.konsist.core.declaration.KoAnnotation

fun Sequence<KoAnnotation>.withType(vararg types: String) = filter {
    types.any { type -> it.representsType(type) }
}

fun Sequence<KoAnnotation>.withoutType(vararg types: String) = filter {
    types.none { type -> it.representsType(type) }
}

inline fun <reified T> Sequence<KoAnnotation>.withType() = filter { it.representsType<T>() }

inline fun <reified T> Sequence<KoAnnotation>.withoutType() = filterNot { it.representsType<T>() }

fun Sequence<KoAnnotation>.withName(vararg names: String) = filter {
    names.any { name -> it.name == name }
}

fun Sequence<KoAnnotation>.withoutName(vararg names: String) = filter {
    names.none { name -> it.name == name }
}

fun Sequence<KoAnnotation>.withFullyQualifiedClassName(vararg names: String) = filter {
    names.any { fullyQualifiedName -> it.fullyQualifiedName == fullyQualifiedName }
}

fun Sequence<KoAnnotation>.withoutFullyQualifiedClassName(vararg names: String) = filter {
    names.none { fullyQualifiedName -> it.fullyQualifiedName == fullyQualifiedName }
}
