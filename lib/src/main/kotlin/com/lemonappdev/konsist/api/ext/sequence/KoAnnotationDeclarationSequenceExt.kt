package com.lemonappdev.konsist.api.ext.sequence

import com.lemonappdev.konsist.api.declaration.KoAnnotationDeclaration
import com.lemonappdev.konsist.api.ext.provider.representsTypeOf
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
 * @param types The Kotlin classes representing the types to include.
 * @return A sequence containing annotations with types matching the specified Kotlin classes.
 */
fun Sequence<KoAnnotationDeclaration>.withRepresentedTypeOf(vararg types: KClass<*>): Sequence<KoAnnotationDeclaration> =
    filter {
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
fun Sequence<KoAnnotationDeclaration>.withoutRepresentedTypeOf(vararg types: KClass<*>): Sequence<KoAnnotationDeclaration> =
    filter {
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

