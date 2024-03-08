@file:Suppress("detekt.TooManyFunctions")

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
 * List containing declarations with any annotation.
 *
 * @return A list containing declarations with any annotation.
 */
fun <T : KoAnnotationProvider> List<T>.withAnnotations(): List<T> = filter { it.hasAnnotations() }

/**
 * List containing declarations with no annotation.
 *
 * @return A list containing declarations with no annotation.
 */
fun <T : KoAnnotationProvider> List<T>.withoutAnnotations(): List<T> = filterNot { it.hasAnnotations() }

/**
 * List containing declarations that have at least one annotation with the specified name(s).
 *
 * @param name The name of the annotation to include.
 * @param names The names of additional annotations to include.
 * @return A list containing declarations with at least one of the specified annotation(s).
 */
fun <T : KoAnnotationProvider> List<T>.withAnnotationNamed(name: String, vararg names: String): List<T> = filter {
    it.hasAnnotationWithName(name, *names)
}

/**
 * List containing declarations without any of specified annotations.
 *
 * @param name The name of the annotation to exclude.
 * @param names The names of additional annotations to exclude.
 * @return A list containing declarations without any of specified annotations.
 */
fun <T : KoAnnotationProvider> List<T>.withoutAnnotationNamed(name: String, vararg names: String): List<T> = filterNot {
    it.hasAnnotationWithName(name, *names)
}

/**
 * List containing declarations that have all specified annotations.
 *
 * @param name The name of the annotation to include.
 * @param names The name(s) of the annotation(s) to include.
 * @return A list containing declarations with all specified annotation(s).
 */
fun <T : KoAnnotationProvider> List<T>.withAllAnnotationsNamed(name: String, vararg names: String): List<T> = filter {
    it.hasAnnotationsWithAllNames(name, *names)
}

/**
 * List containing declarations without all specified annotations.
 *
 * @param name The name of the annotation to exclude.
 * @param names The name(s) of the annotation(s) to exclude.
 * @return A list containing declarations without all specified annotation(s).
 */
fun <T : KoAnnotationProvider> List<T>.withoutAllAnnotationsNamed(name: String, vararg names: String): List<T> =
    filterNot { it.hasAnnotationsWithAllNames(name, *names) }

/**
 * List containing declarations that have at least one annotation satisfying the provided predicate.
 *
 * @param predicate A function that defines the condition to be met by an annotation declaration.
 * @return A list containing declarations with at least one annotation satisfying the predicate.
 */
fun <T : KoAnnotationProvider> List<T>.withAnnotation(predicate: (KoAnnotationDeclaration) -> Boolean): List<T> =
    filter {
        it.hasAnnotation(predicate)
    }

/**
 * List containing declarations that not have annotation satisfying the provided predicate.
 *
 * @param predicate A function that defines the condition to be met by an annotation declaration.
 * @return A list containing declarations without annotation satisfying the provided predicate.
 */
fun <T : KoAnnotationProvider> List<T>.withoutAnnotation(predicate: (KoAnnotationDeclaration) -> Boolean): List<T> =
    filterNot { it.hasAnnotation(predicate) }

/**
 * List containing declarations that have all annotations satisfying the provided predicate.
 *
 * @param predicate A function that defines the condition to be met by all annotation declarations.
 * @return A filtered list containing declarations with all annotations satisfying the predicate.
 */
fun <T : KoAnnotationProvider> List<T>.withAllAnnotations(predicate: (KoAnnotationDeclaration) -> Boolean): List<T> =
    filter { it.hasAllAnnotations(predicate) }

/**
 * List containing declarations that have at least one annotation not satisfying the provided predicate.
 *
 * @param predicate A function that defines the condition to be met by all annotation declarations.
 * @return A list containing declarations that have at least one annotation not satisfying the provided predicate.
 */
fun <T : KoAnnotationProvider> List<T>.withoutAllAnnotations(predicate: (KoAnnotationDeclaration) -> Boolean): List<T> =
    filterNot { it.hasAllAnnotations(predicate) }

/**
 * List containing declarations with annotation declarations satisfying the predicate.
 *
 * @param predicate A function that defines the condition to be met by the list of annotation declarations.
 * @return A list containing declarations with annotation declarations satisfying the predicate.
 */
fun <T : KoAnnotationProvider> List<T>.withAnnotations(predicate: (List<KoAnnotationDeclaration>) -> Boolean): List<T> =
    filter { predicate(it.annotations) }

/**
 * List containing declarations without annotation declarations satisfying the predicate.
 *
 * @param predicate A function that defines the condition to be met by the list of annotation declarations.
 * @return A list containing declarations without annotation declarations satisfying the predicate.
 */
fun <T : KoAnnotationProvider> List<T>.withoutAnnotations(predicate: (List<KoAnnotationDeclaration>) -> Boolean): List<T> =
    filterNot { predicate(it.annotations) }

/**
 * List containing declarations that have at least one annotation of the specified `KClass` type.
 *
 * @param kClass The Kotlin class representing annotation to include.
 * @param kClasses The Kotlin classes representing annotations to include.
 * @return A list containing declarations with at least one annotation of the specified `KClass` type.
 */
fun <T : KoAnnotationProvider> List<T>.withAnnotationOf(
    kClass: KClass<*>,
    vararg kClasses: KClass<*>,
): List<T> =
    filter { it.hasAnnotationOf(kClass, *kClasses) }

/**
 * List containing declarations without any annotation of the specified `KClass` type.
 *
 * @param kClass The Kotlin class representing annotation to exclude.
 * @param kClasses The Kotlin classes representing annotations to exclude.
 * @return A list containing declarations without any of the specified annotations.
 */
fun <T : KoAnnotationProvider> List<T>.withoutAnnotationOf(
    kClass: KClass<*>,
    vararg kClasses: KClass<*>,
): List<T> =
    filterNot { it.hasAnnotationOf(kClass, *kClasses) }

/**
 * List containing declarations that have all annotations of the specified `KClass` type.
 *
 * @param kClass The Kotlin class representing annotation to include.
 * @param kClasses The Kotlin classes representing annotations to include.
 * @return A list containing declarations that have all annotations of the specified `KClass` type.
 */
fun <T : KoAnnotationProvider> List<T>.withAllAnnotationsOf(
    kClass: KClass<*>,
    vararg kClasses: KClass<*>,
): List<T> =
    filter { it.hasAllAnnotationsOf(kClass, *kClasses) }

/**
 * List containing declarations without all specified `KClass` type annotations.
 *
 * @param kClass The Kotlin class representing annotation to exclude.
 * @param kClasses The Kotlin classes representing annotations to exclude.
 * @return A list containing declarations without all specified `KClass` type annotations.
 */
fun <T : KoAnnotationProvider> List<T>.withoutAllAnnotationsOf(
    kClass: KClass<*>,
    vararg kClasses: KClass<*>,
): List<T> =
    filterNot { it.hasAllAnnotationsOf(kClass, *kClasses) }

/**
 * List containing declarations with all annotations.
 *
 * @param name The annotation name to include.
 * @param names The annotation name(s) to include.
 * @return A list containing declarations with all the specified annotations.
 */
@Deprecated(
    """
            Will be removed in v0.16.0. 
            If you passed one argument - replace with `withAnnotationNamed`, otherwise with `withAllAnnotationsNamed`.
            """,
    ReplaceWith("withAnnotationNamed/withAllAnnotationsNamed"),
)
fun <T : KoAnnotationProvider> List<T>.withAllAnnotations(
    name: String,
    vararg names: String,
): List<T> = filter {
    it.hasAnnotations(name, *names)
}

/**
 * List containing declarations with some annotations.
 *
 * @param name The annotation name to include.
 * @param names The annotation name(s) to include.
 * @return A list containing declarations with at least one of the specified annotations.
 */
@Deprecated("Will be removed in v0.16.0.", ReplaceWith("withAnnotationNamed(*names"))
fun <T : KoAnnotationProvider> List<T>.withSomeAnnotations(name: String, vararg names: String): List<T> =
    filter {
        it.hasAnnotations(name) || names.any { annotation -> it.hasAnnotations(annotation) }
    }

/**
 * List containing declarations without all the specified annotations.
 *
 * @param name The annotation name to exclude.
 * @param names The annotation name(s) to exclude.
 * @return A list containing declarations without any of the specified annotations.
 */
@Deprecated(
    """
            Will be removed in v0.16.0. 
            If you passed one argument - replace with `withoutAnnotationNamed`, otherwise with `withoutAllAnnotationsNamed`.
            """,
    ReplaceWith("withoutAnnotationNamed/withoutAllAnnotationsNamed"),
)
fun <T : KoAnnotationProvider> List<T>.withoutAllAnnotations(
    name: String,
    vararg names: String,
): List<T> = filterNot {
    it.hasAnnotations(name, *names)
}

/**
 * List containing declarations without some annotations.
 *
 * @param name The annotation name to exclude.
 * @param names The annotation name(s) to exclude.
 * @return A list containing declarations without at least one of the specified annotations.
 */
@Deprecated("Will be removed in v0.16.0.", ReplaceWith("withoutAnnotationNamed(*names"))
fun <T : KoAnnotationProvider> List<T>.withoutSomeAnnotations(
    name: String,
    vararg names: String,
): List<T> = filter {
    val missesAtLeastOneAnnotation = if (names.isNotEmpty()) {
        names.any { annotation -> !it.hasAnnotations(annotation) }
    } else {
        true
    }

    !it.hasAnnotations(name) && missesAtLeastOneAnnotation
}

/**
 * List containing declarations with some annotations of type.
 *
 * @param kClass The Kotlin class representing annotation to include.
 * @param kClasses The Kotlin classes representing annotations to include.
 * @return A list containing declarations with at least one of the specified the annotations.
 */
@Deprecated("Will be removed in v0.16.0.", ReplaceWith("withAnnotationOf(*kClasses"))
fun <T : KoAnnotationProvider> List<T>.withSomeAnnotationsOf(
    kClass: KClass<*>,
    vararg kClasses: KClass<*>,
): List<T> = filter {
    it.hasAnnotationsOf(kClass) || kClasses.any { annotation -> it.hasAnnotationsOf(annotation) }
}

/**
 * List containing declarations without some annotations.
 *
 * @param kClass The Kotlin class representing annotation to exclude.
 * @param kClasses The Kotlin classes representing annotations to exclude.
 * @return A list containing declarations without at least one of the specified annotations.
 */
@Deprecated("Will be removed in v0.16.0.", ReplaceWith("withoutAnnotationOf(*kClasses"))
fun <T : KoAnnotationProvider> List<T>.withoutSomeAnnotationsOf(
    kClass: KClass<*>,
    vararg kClasses: KClass<*>,
): List<T> = filter {
    val missesAtLeastOneAnnotation = if (kClasses.isNotEmpty()) {
        kClasses.any { annotation -> !it.hasAnnotationsOf(annotation) }
    } else {
        true
    }

    !it.hasAnnotationsOf(kClass) && missesAtLeastOneAnnotation
}
