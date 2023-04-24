package com.lemonappdev.konsist.core.ext

import com.lemonappdev.konsist.core.declaration.KoAnnotation
import kotlin.reflect.KClass

fun Sequence<KoAnnotation>.withType(vararg types: String) = filter {
    types.any { type -> it.representsType(type) }
}

fun Sequence<KoAnnotation>.withoutType(vararg types: String) = filter {
    types.none { type -> it.representsType(type) }
}

fun Sequence<KoAnnotation>.withTypeOf(vararg types: KClass<*>) = filter {
    types.any { type ->
        type
            .qualifiedName
            ?.let { name -> it.representsType(name) }
            ?: false
    }
}

fun Sequence<KoAnnotation>.withoutTypeOf(vararg types: KClass<*>) = filter {
    types.none { type ->
        type
            .qualifiedName
            ?.let { name -> it.representsType(name) }
            ?: false
    }
}

inline fun <reified T> Sequence<KoAnnotation>.withTypeOf() = filter { it.representsTypeOf<T>() }

inline fun <reified T> Sequence<KoAnnotation>.withoutTypeOf() = filterNot { it.representsTypeOf<T>() }

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
