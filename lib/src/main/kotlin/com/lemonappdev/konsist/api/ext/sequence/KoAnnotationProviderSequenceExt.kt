package com.lemonappdev.konsist.api.ext.sequence

import com.lemonappdev.konsist.api.provider.KoAnnotationProvider
import kotlin.reflect.KClass

/**
 * Sequence containing declarations with any annotation.
 *
 * @return A sequence containing declarations with any annotation.
 */
fun <T : KoAnnotationProvider> Sequence<T>.withAnnotations(): Sequence<T> = filter { it.hasAnnotations() }

/**
 * Sequence containing declarations with all annotations.
 *
 * @param annotation The annotation to include.
 * @param annotations The annotations to include.
 * @return A sequence containing declarations with all the specified annotations.
 */
fun <T : KoAnnotationProvider> Sequence<T>.withAllAnnotations(
    annotation: String,
    vararg annotations: String,
): Sequence<T> = filter {
    it.hasAnnotations(annotation, *annotations)
}

/**
 * Sequence containing declarations with some annotations.
 *
 * @param annotation The annotation to include.
 * @param annotations The annotations to include.
 * @return A sequence containing declarations with at least one of the specified annotations.
 */
fun <T : KoAnnotationProvider> Sequence<T>.withSomeAnnotations(annotation: String, vararg annotations: String): Sequence<T> =
    filter {
        it.hasAnnotations(annotation) || annotations.any { annotation -> it.hasAnnotations(annotation) }
    }

/**
 * Sequence containing declarations with no annotation.
 *
 * @return A sequence containing declarations with no annotation.
 */
fun <T : KoAnnotationProvider> Sequence<T>.withoutAnnotations(): Sequence<T> = filterNot { it.hasAnnotations() }

/**
 * Sequence containing declarations without all the specified annotations.
 *
 * @param annotation The annotation to exclude.
 * @param annotations The annotations to exclude.
 * @return A sequence containing declarations without any of the specified annotations.
 */
fun <T : KoAnnotationProvider> Sequence<T>.withoutAllAnnotations(
    annotation: String,
    vararg annotations: String,
): Sequence<T> = filterNot {
    it.hasAnnotations(annotation, *annotations)
}

/**
 * Sequence containing declarations without some annotations.
 *
 * @param annotation The annotation to exclude.
 * @param annotations The annotations to exclude.
 * @return A sequence containing declarations without at least one of the specified annotations.
 */
fun <T : KoAnnotationProvider> Sequence<T>.withoutSomeAnnotations(
    annotation: String,
    vararg annotations: String,
): Sequence<T> = filter {
    !it.hasAnnotations(annotation) && if (annotations.isNotEmpty()) {
        annotations.any { annotation -> !it.hasAnnotations(annotation) }
    } else {
        true
    }
}

/**
 * Sequence containing declarations with all annotations of type.
 *
 * @param annotation The Kotlin class representing annotation to include.
 * @param annotations The Kotlin classes representing annotations to include.
 * @return A sequence containing declarations with all the specified annotations.
 */
fun <T : KoAnnotationProvider> Sequence<T>.withAllAnnotationsOf(
    annotation: KClass<*>,
    vararg annotations: KClass<*>,
): Sequence<T> =
    filter { it.hasAnnotationsOf(annotation, *annotations) }

/**
 * Sequence containing declarations with some annotations of type.
 *
 * @param annotation The Kotlin class representing annotation to include.
 * @param annotations The Kotlin classes representing annotations to include.
 * @return A sequence containing declarations with at least one of the specified the annotations.
 */
fun <T : KoAnnotationProvider> Sequence<T>.withSomeAnnotationsOf(
    annotation: KClass<*>,
    vararg annotations: KClass<*>,
): Sequence<T> = filter {
    it.hasAnnotationsOf(annotation) || annotations.any { annotation -> it.hasAnnotationsOf(annotation) }
}

/**
 * Sequence containing declarations without specified annotations.
 *
 * @param annotation The Kotlin class representing annotation to exclude.
 * @param annotations The Kotlin classes representing annotations to exclude.
 * @return A sequence containing declarations without any of the specified annotations.
 */
fun <T : KoAnnotationProvider> Sequence<T>.withoutAllAnnotationsOf(
    annotation: KClass<*>,
    vararg annotations: KClass<*>,
): Sequence<T> =
    filterNot { it.hasAnnotationsOf(annotation, *annotations) }

/**
 * Sequence containing declarations without some annotations.
 *
 * @param annotation The Kotlin class representing annotation to exclude.
 * @param annotations The Kotlin classes representing annotations to exclude.
 * @return A sequence containing declarations without at least one of the specified annotations.
 */
fun <T : KoAnnotationProvider> Sequence<T>.withoutSomeAnnotationsOf(
    annotation: KClass<*>,
    vararg annotations: KClass<*>,
): Sequence<T> = filter {
    !it.hasAnnotationsOf(annotation) && if (annotations.isNotEmpty()) {
        annotations.any { annotation -> !it.hasAnnotationsOf(annotation) }
    } else {
        true
    }
}

/**
 * Sequence containing declarations with all annotations of type.
 *
 * @return A sequence containing declarations with the specified annotation.
 */
inline fun <reified T> Sequence<KoAnnotationProvider>.withAnnotationOf(): Sequence<KoAnnotationProvider> =
    withAllAnnotationsOf(T::class)

/**
 * Sequence containing declarations without annotations of type.
 *
 * @return A sequence containing declarations without specified annotation.
 */
inline fun <reified T> Sequence<KoAnnotationProvider>.withoutAnnotationOf(): Sequence<KoAnnotationProvider> =
    withoutAllAnnotationsOf(T::class)
