package com.lemonappdev.konsist.api.ext.sequence

import com.lemonappdev.konsist.api.declaration.KoAnnotationDeclaration
import com.lemonappdev.konsist.api.ext.declaration.representsTypeOf
import kotlin.reflect.KClass

/**
 * Sequence containing all annotations that represents the type.
 *
 * @param type The type to include.
 * @param types The types to include.
 * @return A sequence containing annotations with the specified types.
 */
fun Sequence<KoAnnotationDeclaration>.withRepresentedType(type: String, vararg types: String): Sequence<KoAnnotationDeclaration> = filter {
    it.representsType(type) || types.any { type -> it.representsType(type) }
}

/**
 * Sequence containing all annotations that do not represent the type.
 *
 * @param type The type to exclude.
 * @param types The types to exclude.
 * @return A sequence containing annotations without the specified types.
 */
fun Sequence<KoAnnotationDeclaration>.withoutRepresentedType(type: String, vararg types: String): Sequence<KoAnnotationDeclaration> =
    filter {
        !it.representsType(type) && types.none { type -> it.representsType(type) }
    }

/**
 * Sequence containing all annotations that represents the type.
 *
 * @param type The Kotlin class representing the type to include.
 * @param types The Kotlin classes representing the types to include.
 * @return A sequence containing annotations with types matching the specified Kotlin classes.
 */
fun Sequence<KoAnnotationDeclaration>.withRepresentedTypeOf(type: KClass<*>, vararg types: KClass<*>): Sequence<KoAnnotationDeclaration> =
    filter {
        type.qualifiedName?.let { name -> it.representsType(name) } ?: false
                ||
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
 * @param type The Kotlin class representing the type to exclude.
 * @param types The Kotlin classes representing the types to exclude.
 * @return A sequence containing annotations without types matching the specified Kotlin classes.
 */
fun Sequence<KoAnnotationDeclaration>.withoutRepresentedTypeOf(
    type: KClass<*>,
    vararg types: KClass<*>
): Sequence<KoAnnotationDeclaration> = filter {
    type.qualifiedName?.let { name -> !it.representsType(name) } ?: false
            &&
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
 * @param name The name to include.
 * @param names The names to include.
 * @return A sequence containing annotations with the specified names.
 */
fun Sequence<KoAnnotationDeclaration>.withName(name: String, vararg names: String): Sequence<KoAnnotationDeclaration> = filter {
    it.name == name || names.any { name -> it.name == name }
}

/**
 * Sequence containing all annotations that don't have the name.
 *
 * @param name The name to exclude.
 * @param names The names to exclude.
 * @return A sequence containing annotations without the specified names.
 */
fun Sequence<KoAnnotationDeclaration>.withoutName(name: String, vararg names: String): Sequence<KoAnnotationDeclaration> = filter {
    it.name != name && names.none { name -> it.name == name }
}

/**
 * Sequence containing all annotations that have the fully qualified name.
 *
 * @param name The name to include.
 * @param names The names to include.
 * @return A sequence containing annotations with the specified fully qualified names.
 */
fun Sequence<KoAnnotationDeclaration>.withFullyQualifiedClassName(name: String, vararg names: String): Sequence<KoAnnotationDeclaration> = filter {
    it.fullyQualifiedName == name || names.any { fullyQualifiedName -> it.fullyQualifiedName == fullyQualifiedName }
}

/**
 * Sequence containing all annotations that don't have the fully qualified name.
 *
 * @param name The name to exclude.
 * @param names The names to exclude.
 * @return A sequence containing annotations without the specified fully qualified names.
 */
fun Sequence<KoAnnotationDeclaration>.withoutFullyQualifiedClassName(name: String, vararg names: String): Sequence<KoAnnotationDeclaration> = filter {
    it.fullyQualifiedName != name && names.none { fullyQualifiedName -> it.fullyQualifiedName == fullyQualifiedName }
}
