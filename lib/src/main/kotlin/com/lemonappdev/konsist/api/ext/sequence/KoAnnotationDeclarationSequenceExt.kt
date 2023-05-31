package com.lemonappdev.konsist.api.ext.sequence

import com.lemonappdev.konsist.api.declaration.KoAnnotationDeclaration
import com.lemonappdev.konsist.api.ext.declaration.representsTypeOf
import kotlin.reflect.KClass

/**
 * Sequence containing all annotations that represents the type.
 *
 * @param types The types to include.
 * @return A sequence containing annotations with the specified types.
 */
fun Sequence<KoAnnotationDeclaration>.withRepresentedType(vararg types: String): Sequence<KoAnnotationDeclaration> = filter {
    types.any { type -> it.representsType(type) }
}

/**
 * Sequence containing all annotations that do not represent the type.
 *
 * @param types The types to exclude.
 * @return A sequence containing annotations without the specified types.
 */
fun Sequence<KoAnnotationDeclaration>.withoutRepresentedType(vararg types: String): Sequence<KoAnnotationDeclaration> = filter {
    types.none { type -> it.representsType(type) }
}

/**
 * Sequence containing all annotations that represents the type.
 *
 * @param types The Kotlin classes representing the types to include.
 * @return A sequence containing annotations with types matching the specified Kotlin classes.
 */
fun Sequence<KoAnnotationDeclaration>.withRepresentedTypeOf(vararg types: KClass<*>): Sequence<KoAnnotationDeclaration> = filter {
    types.any { type ->
        type
            .qualifiedName
            ?.let { name -> it.representsType(name) }
            ?: false
    }
}

/**
 * Sequence containing all annotations that do not represent the type.
 *
 * @param types The Kotlin classes representing the types to exclude.
 * @return A sequence containing annotations without types matching the specified Kotlin classes.
 */
fun Sequence<KoAnnotationDeclaration>.withoutRepresentedTypeOf(vararg types: KClass<*>): Sequence<KoAnnotationDeclaration> = filter {
    types.none { type ->
        type
            .qualifiedName
            ?.let { name -> it.representsType(name) }
            ?: false
    }
}

/**
 * Sequence containing all annotations that represents the type.
 *
 * @return A sequence containing annotations with types matching the specified reified type parameter.
 */
inline fun <reified T> Sequence<KoAnnotationDeclaration>.withRepresentedTypeOf(): Sequence<KoAnnotationDeclaration> =
    filter { it.representsTypeOf<T>() }

/**
 * Sequence containing all annotations that do not represent the type.
 *
 * @return A sequence containing annotations without types matching the specified reified type parameter.
 */
inline fun <reified T> Sequence<KoAnnotationDeclaration>.withoutRepresentedTypeOf(): Sequence<KoAnnotationDeclaration> =
    filterNot { it.representsTypeOf<T>() }

/**
 * Sequence containing all annotations that have the name.
 *
 * @param names The names to include.
 * @return A sequence containing annotations with the specified names.
 */
fun Sequence<KoAnnotationDeclaration>.withName(vararg names: String): Sequence<KoAnnotationDeclaration> = filter {
    names.any { name -> it.name == name }
}

/**
 * Sequence containing all annotations that don't have the name.
 *
 * @param names The names to exclude.
 * @return A sequence containing annotations without the specified names.
 */
fun Sequence<KoAnnotationDeclaration>.withoutName(vararg names: String): Sequence<KoAnnotationDeclaration> = filter {
    names.none { name -> it.name == name }
}

/**
 * Sequence containing all annotations that have the fully qualified name.
 *
 * @param names The names to include.
 * @return A sequence containing annotations with the specified fully qualified names.
 */
fun Sequence<KoAnnotationDeclaration>.withFullyQualifiedClassName(vararg names: String): Sequence<KoAnnotationDeclaration> = filter {
    names.any { fullyQualifiedName -> it.fullyQualifiedName == fullyQualifiedName }
}

/**
 * Sequence containing all annotations that don't have the fully qualified name.
 *
 * @param names The names to exclude.
 * @return A sequence containing annotations without the specified fully qualified names.
 */
fun Sequence<KoAnnotationDeclaration>.withoutFullyQualifiedClassName(vararg names: String): Sequence<KoAnnotationDeclaration> = filter {
    names.none { fullyQualifiedName -> it.fullyQualifiedName == fullyQualifiedName }
}
