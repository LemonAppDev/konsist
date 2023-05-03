package com.lemonappdev.konsist.api.ext.sequence

import com.lemonappdev.konsist.api.declaration.KoAnnotationDeclaration
import com.lemonappdev.konsist.api.ext.declaration.representsTypeOf
import kotlin.reflect.KClass

fun Sequence<KoAnnotationDeclaration>.withType(vararg types: String) = filter {
    types.any { type -> it.representsType(type) }
}

fun Sequence<KoAnnotationDeclaration>.withoutType(vararg types: String) = filter {
    types.none { type -> it.representsType(type) }
}

fun Sequence<KoAnnotationDeclaration>.withTypeOf(vararg types: KClass<*>) = filter {
    types.any { type ->
        type
            .qualifiedName
            ?.let { name -> it.representsType(name) }
            ?: false
    }
}

fun Sequence<KoAnnotationDeclaration>.withoutTypeOf(vararg types: KClass<*>) = filter {
    types.none { type ->
        type
            .qualifiedName
            ?.let { name -> it.representsType(name) }
            ?: false
    }
}

inline fun <reified T> Sequence<KoAnnotationDeclaration>.withTypeOf() = filter { it.representsTypeOf<T>() }

inline fun <reified T> Sequence<KoAnnotationDeclaration>.withoutTypeOf() = filterNot { it.representsTypeOf<T>() }

fun Sequence<KoAnnotationDeclaration>.withName(vararg names: String) = filter {
    names.any { name -> it.name == name }
}

fun Sequence<KoAnnotationDeclaration>.withoutName(vararg names: String) = filter {
    names.none { name -> it.name == name }
}

fun Sequence<KoAnnotationDeclaration>.withFullyQualifiedClassName(vararg names: String) = filter {
    names.any { fullyQualifiedName -> it.fullyQualifiedName == fullyQualifiedName }
}

fun Sequence<KoAnnotationDeclaration>.withoutFullyQualifiedClassName(vararg names: String) = filter {
    names.none { fullyQualifiedName -> it.fullyQualifiedName == fullyQualifiedName }
}
