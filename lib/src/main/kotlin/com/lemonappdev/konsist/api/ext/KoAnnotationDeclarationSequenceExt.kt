package com.lemonappdev.konsist.api.ext

import com.lemonappdev.konsist.core.declaration.KoAnnotationDeclarationImpl
import kotlin.reflect.KClass

fun Sequence<KoAnnotationDeclarationImpl>.withType(vararg types: String) = filter {
    types.any { type -> it.representsType(type) }
}

fun Sequence<KoAnnotationDeclarationImpl>.withoutType(vararg types: String) = filter {
    types.none { type -> it.representsType(type) }
}

fun Sequence<KoAnnotationDeclarationImpl>.withTypeOf(vararg types: KClass<*>) = filter {
    types.any { type ->
        type
            .qualifiedName
            ?.let { name -> it.representsType(name) }
            ?: false
    }
}

fun Sequence<KoAnnotationDeclarationImpl>.withoutTypeOf(vararg types: KClass<*>) = filter {
    types.none { type ->
        type
            .qualifiedName
            ?.let { name -> it.representsType(name) }
            ?: false
    }
}

inline fun <reified T> Sequence<KoAnnotationDeclarationImpl>.withTypeOf() = filter { it.representsTypeOf<T>() }

inline fun <reified T> Sequence<KoAnnotationDeclarationImpl>.withoutTypeOf() = filterNot { it.representsTypeOf<T>() }

fun Sequence<KoAnnotationDeclarationImpl>.withName(vararg names: String) = filter {
    names.any { name -> it.name == name }
}

fun Sequence<KoAnnotationDeclarationImpl>.withoutName(vararg names: String) = filter {
    names.none { name -> it.name == name }
}

fun Sequence<KoAnnotationDeclarationImpl>.withFullyQualifiedClassName(vararg names: String) = filter {
    names.any { fullyQualifiedName -> it.fullyQualifiedName == fullyQualifiedName }
}

fun Sequence<KoAnnotationDeclarationImpl>.withoutFullyQualifiedClassName(vararg names: String) = filter {
    names.none { fullyQualifiedName -> it.fullyQualifiedName == fullyQualifiedName }
}
