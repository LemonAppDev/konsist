package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.declaration.KoAnnotationDeclaration
import com.lemonappdev.konsist.api.provider.KoAnnotationProvider
import kotlin.reflect.KClass

/**
 * List containing annotation declarations.
 */
val <T : KoAnnotationProvider> List<T>.annotations: List<KoAnnotationDeclaration>
    get() = flatMap { it.annotations }

/**
 * List containing elements with any annotation.
 *
 * @return A list containing elements with any annotation.
 */
fun <T : KoAnnotationProvider> List<T>.withAnnotations(): List<T> = filter { it.hasAnnotations() }

/**
 * List containing elements with all annotations.
 *
 * @param name The annotation name to include.
 * @param names The annotation name(s) to include.
 * @return A list containing elements with all the specified annotations.
 */
fun <T : KoAnnotationProvider> List<T>.withAllAnnotations(
    name: String,
    vararg names: String,
): List<T> = filter {
    it.hasAnnotations(name, *names)
}

/**
 * List containing elements with some annotations.
 *
 * @param name The annotation name to include.
 * @param names The annotation name(s) to include.
 * @return A list containing elements with at least one of the specified annotations.
 */
fun <T : KoAnnotationProvider> List<T>.withSomeAnnotations(name: String, vararg names: String): List<T> =
    filter {
        it.hasAnnotations(name) || names.any { annotation -> it.hasAnnotations(annotation) }
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
 * @param name The annotation name to exclude.
 * @param names The annotation name(s) to exclude.
 * @return A list containing elements without any of the specified annotations.
 */
fun <T : KoAnnotationProvider> List<T>.withoutAllAnnotations(
    name: String,
    vararg names: String,
): List<T> = filterNot {
    it.hasAnnotations(name, *names)
}

/**
 * List containing elements without some annotations.
 *
 * @param name The annotation name to exclude.
 * @param names The annotation name(s) to exclude.
 * @return A list containing elements without at least one of the specified annotations.
 */
fun <T : KoAnnotationProvider> List<T>.withoutSomeAnnotations(
    name: String,
    vararg names: String,
): List<T> = filter {
    !it.hasAnnotations(name) && if (names.isNotEmpty()) {
        names.any { annotation -> !it.hasAnnotations(annotation) }
    } else {
        true
    }
}

/**
 * List containing elements with all annotations of type.
 *
 * @param kClass The Kotlin class representing annotation to include.
 * @param kClasses The Kotlin classes representing annotations to include.
 * @return A list containing elements with all the specified annotations.
 */
fun <T : KoAnnotationProvider> List<T>.withAllAnnotationsOf(
    kClass: KClass<*>,
    vararg kClasses: KClass<*>,
): List<T> =
    filter { it.hasAnnotationsOf(kClass, *kClasses) }

/**
 * List containing elements with some annotations of type.
 *
 * @param kClass The Kotlin class representing annotation to include.
 * @param kClasses The Kotlin classes representing annotations to include.
 * @return A list containing elements with at least one of the specified the annotations.
 */
fun <T : KoAnnotationProvider> List<T>.withSomeAnnotationsOf(
    kClass: KClass<*>,
    vararg kClasses: KClass<*>,
): List<T> = filter {
    it.hasAnnotationsOf(kClass) || kClasses.any { annotation -> it.hasAnnotationsOf(annotation) }
}

/**
 * List containing elements without specified annotations.
 *
 * @param kClass The Kotlin class representing annotation to exclude.
 * @param kClasses The Kotlin classes representing annotations to exclude.
 * @return A list containing elements without any of the specified annotations.
 */
fun <T : KoAnnotationProvider> List<T>.withoutAllAnnotationsOf(
    kClass: KClass<*>,
    vararg kClasses: KClass<*>,
): List<T> =
    filterNot { it.hasAnnotationsOf(kClass, *kClasses) }

/**
 * List containing elements without some annotations.
 *
 * @param kClass The Kotlin class representing annotation to exclude.
 * @param kClasses The Kotlin classes representing annotations to exclude.
 * @return A list containing elements without at least one of the specified annotations.
 */
fun <T : KoAnnotationProvider> List<T>.withoutSomeAnnotationsOf(
    kClass: KClass<*>,
    vararg kClasses: KClass<*>,
): List<T> = filter {
    !it.hasAnnotationsOf(kClass) && if (kClasses.isNotEmpty()) {
        kClasses.any { annotation -> !it.hasAnnotationsOf(annotation) }
    } else {
        true
    }
}
