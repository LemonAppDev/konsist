package com.lemonappdev.konsist.api.ext.sequence

import com.lemonappdev.konsist.api.declaration.KoAnnotationDeclaration
import com.lemonappdev.konsist.api.ext.declaration.representsTypeOf
import kotlin.reflect.KClass

/**
 * Sequence containing all declarations that represents the type.
 */
fun Sequence<KoAnnotationDeclaration>.withType(vararg types: String): Sequence<KoAnnotationDeclaration> = filter {
    types.any { type -> it.representsType(type) }
}

/**
 * Sequence containing all declarations that do not represent the type.
 */
fun Sequence<KoAnnotationDeclaration>.withoutType(vararg types: String): Sequence<KoAnnotationDeclaration> = filter {
    types.none { type -> it.representsType(type) }
}

/**
 * Sequence containing all declarations that represents the type.
 */
fun Sequence<KoAnnotationDeclaration>.withTypeOf(vararg types: KClass<*>): Sequence<KoAnnotationDeclaration> = filter {
    types.any { type ->
        type
            .qualifiedName
            ?.let { name -> it.representsType(name) }
            ?: false
    }
}

/**
 * Sequence containing all declarations that do not represent the type.
 */
fun Sequence<KoAnnotationDeclaration>.withoutTypeOf(vararg types: KClass<*>): Sequence<KoAnnotationDeclaration> = filter {
    types.none { type ->
        type
            .qualifiedName
            ?.let { name -> it.representsType(name) }
            ?: false
    }
}

/**
 * Sequence containing all declarations that represents the type.
 */
inline fun <reified T> Sequence<KoAnnotationDeclaration>.withTypeOf(): Sequence<KoAnnotationDeclaration> =
    filter { it.representsTypeOf<T>() }

/**
 * Sequence containing all declarations that do not represent the type.
 */
inline fun <reified T> Sequence<KoAnnotationDeclaration>.withoutTypeOf(): Sequence<KoAnnotationDeclaration> =
    filterNot { it.representsTypeOf<T>() }

/**
 * Sequence containing all declarations that have the name.
 */
fun Sequence<KoAnnotationDeclaration>.withName(vararg names: String): Sequence<KoAnnotationDeclaration> = filter {
    names.any { name -> it.name == name }
}

/**
 * Sequence containing all declarations that don't have the name.
 */
fun Sequence<KoAnnotationDeclaration>.withoutName(vararg names: String): Sequence<KoAnnotationDeclaration> = filter {
    names.none { name -> it.name == name }
}

/**
 * Sequence containing all declarations that have the fully qualified name.
 */
fun Sequence<KoAnnotationDeclaration>.withFullyQualifiedClassName(vararg names: String): Sequence<KoAnnotationDeclaration> = filter {
    names.any { fullyQualifiedName -> it.fullyQualifiedName == fullyQualifiedName }
}

/**
 * Sequence containing all declarations that don't have the fully qualified name.
 */
fun Sequence<KoAnnotationDeclaration>.withoutFullyQualifiedClassName(vararg names: String): Sequence<KoAnnotationDeclaration> = filter {
    names.none { fullyQualifiedName -> it.fullyQualifiedName == fullyQualifiedName }
}
