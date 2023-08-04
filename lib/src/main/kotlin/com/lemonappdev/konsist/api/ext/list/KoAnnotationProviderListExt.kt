package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.provider.KoAnnotationProvider
import kotlin.reflect.KClass

/**
 * List containing elements with any annotation.
 *
 * @return A list containing elements with any annotation.
 */
fun <T : KoAnnotationProvider> List<T>.withAnnotations(): List<T> = filter { it.hasAnnotations() }

/**
 * List containing elements with all annotations.
 *
 * @param annotation The annotation to include.
 * @param annotations The annotations to include.
 * @return A list containing elements with all the specified annotations.
 */
fun <T : KoAnnotationProvider> List<T>.withAllAnnotations(
    annotation: String,
    vararg annotations: String,
): List<T> = filter {
    it.hasAnnotations(annotation, *annotations)
}

/**
 * List containing elements with some annotations.
 *
 * @param annotation The annotation to include.
 * @param annotations The annotations to include.
 * @return A list containing elements with at least one of the specified annotations.
 */
fun <T : KoAnnotationProvider> List<T>.withSomeAnnotations(annotation: String, vararg annotations: String): List<T> =
    filter {
        it.hasAnnotations(annotation) || annotations.any { annotation -> it.hasAnnotations(annotation) }
    }

/**
 * List containing elements with no annotation.
 *
 * @return A list containing elements with no annotation.
 */
fun <T : KoAnnotationProvider> List<T>.withoutAnnotations(): List<T> = filterNot { it.hasAnnotations() }

/**
 * List containing elements without all the specified annotations.
 *
 * @param annotation The annotation to exclude.
 * @param annotations The annotations to exclude.
 * @return A list containing elements without any of the specified annotations.
 */
fun <T : KoAnnotationProvider> List<T>.withoutAllAnnotations(
    annotation: String,
    vararg annotations: String,
): List<T> = filterNot {
    it.hasAnnotations(annotation, *annotations)
}

/**
 * List containing elements without some annotations.
 *
 * @param annotation The annotation to exclude.
 * @param annotations The annotations to exclude.
 * @return A list containing elements without at least one of the specified annotations.
 */
fun <T : KoAnnotationProvider> List<T>.withoutSomeAnnotations(
    annotation: String,
    vararg annotations: String,
): List<T> = filter {
    !it.hasAnnotations(annotation) && if (annotations.isNotEmpty()) {
        annotations.any { annotation -> !it.hasAnnotations(annotation) }
    } else {
        true
    }
}

/**
 * List containing elements with all annotations of type.
 *
 * @param annotation The Kotlin class representing annotation to include.
 * @param annotations The Kotlin classes representing annotations to include.
 * @return A list containing elements with all the specified annotations.
 */
fun <T : KoAnnotationProvider> List<T>.withAllAnnotationsOf(
    annotation: KClass<*>,
    vararg annotations: KClass<*>,
): List<T> =
    filter { it.hasAnnotationsOf(annotation, *annotations) }

/**
 * List containing elements with some annotations of type.
 *
 * @param annotation The Kotlin class representing annotation to include.
 * @param annotations The Kotlin classes representing annotations to include.
 * @return A list containing elements with at least one of the specified the annotations.
 */
fun <T : KoAnnotationProvider> List<T>.withSomeAnnotationsOf(
    annotation: KClass<*>,
    vararg annotations: KClass<*>,
): List<T> = filter {
    it.hasAnnotationsOf(annotation) || annotations.any { annotation -> it.hasAnnotationsOf(annotation) }
}

/**
 * List containing elements without specified annotations.
 *
 * @param annotation The Kotlin class representing annotation to exclude.
 * @param annotations The Kotlin classes representing annotations to exclude.
 * @return A list containing elements without any of the specified annotations.
 */
fun <T : KoAnnotationProvider> List<T>.withoutAllAnnotationsOf(
    annotation: KClass<*>,
    vararg annotations: KClass<*>,
): List<T> =
    filterNot { it.hasAnnotationsOf(annotation, *annotations) }

/**
 * List containing elements without some annotations.
 *
 * @param annotation The Kotlin class representing annotation to exclude.
 * @param annotations The Kotlin classes representing annotations to exclude.
 * @return A list containing elements without at least one of the specified annotations.
 */
fun <T : KoAnnotationProvider> List<T>.withoutSomeAnnotationsOf(
    annotation: KClass<*>,
    vararg annotations: KClass<*>,
): List<T> = filter {
    !it.hasAnnotationsOf(annotation) && if (annotations.isNotEmpty()) {
        annotations.any { annotation -> !it.hasAnnotationsOf(annotation) }
    } else {
        true
    }
}

/**
 * List containing elements with all annotations of type.
 *
 * @return A list containing elements with the specified annotation.
 */
inline fun <reified T> List<KoAnnotationProvider>.withAnnotationOf(): List<KoAnnotationProvider> =
    withAllAnnotationsOf(T::class)

/**
 * List containing elements without annotations of type.
 *
 * @return A list containing elements without specified annotation.
 */
inline fun <reified T> List<KoAnnotationProvider>.withoutAnnotationOf(): List<KoAnnotationProvider> =
    withoutAllAnnotationsOf(T::class)
