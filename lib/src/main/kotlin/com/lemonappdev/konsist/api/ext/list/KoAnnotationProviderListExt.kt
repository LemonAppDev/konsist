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
 * @param ignoreCase Specifies whether the comparison should ignore case.
 *        If `true`, the prefix comparison will be case-insensitive.
 *        If `false`, the comparison will consider case sensitivity.
 * @return A list containing declarations with at least one of the specified annotation(s).
 */
fun <T : KoAnnotationProvider> List<T>.withAnnotationNamed(
    name: String,
    vararg names: String,
    ignoreCase: Boolean = false,
): List<T> = withAnnotationNamed(listOf(name, *names), ignoreCase)

/**
 * List containing declarations that have at least one annotation with the specified name(s).
 *
 * @param names The names of additional annotations to include.
 * @param ignoreCase Specifies whether the comparison should ignore case.
 *        If `true`, the prefix comparison will be case-insensitive.
 *        If `false`, the comparison will consider case sensitivity.
 * @return A list containing declarations with at least one of the specified annotation(s).
 */
fun <T : KoAnnotationProvider> List<T>.withAnnotationNamed(
    names: Collection<String>,
    ignoreCase: Boolean = false,
): List<T> =
    filter {
        when {
            names.isEmpty() -> it.hasAnnotations()
            else -> it.hasAnnotationWithName(names, ignoreCase = ignoreCase)
        }
    }

/**
 * List containing declarations without any of specified annotations.
 *
 * @param name The name of the annotation to exclude.
 * @param names The names of additional annotations to exclude.
 * @param ignoreCase Specifies whether the comparison should ignore case.
 *        If `true`, the prefix comparison will be case-insensitive.
 *        If `false`, the comparison will consider case sensitivity.
 * @return A list containing declarations without any of specified annotations.
 */
fun <T : KoAnnotationProvider> List<T>.withoutAnnotationNamed(
    name: String,
    vararg names: String,
    ignoreCase: Boolean = false,
): List<T> = withoutAnnotationNamed(listOf(name, *names), ignoreCase)

/**
 * List containing declarations without any of specified annotations.
 *
 * @param names The names of additional annotations to exclude.
 * @param ignoreCase Specifies whether the comparison should ignore case.
 *        If `true`, the prefix comparison will be case-insensitive.
 *        If `false`, the comparison will consider case sensitivity.
 * @return A list containing declarations without any of specified annotations.
 */
fun <T : KoAnnotationProvider> List<T>.withoutAnnotationNamed(
    names: Collection<String>,
    ignoreCase: Boolean = false,
): List<T> =
    filterNot {
        when {
            names.isEmpty() -> it.hasAnnotations()
            else -> it.hasAnnotationWithName(names, ignoreCase = ignoreCase)
        }
    }

/**
 * List containing declarations that have all specified annotations.
 *
 * @param name The name of the annotation to include.
 * @param names The name(s) of the annotation(s) to include.
 * @param ignoreCase Specifies whether the comparison should ignore case.
 *        If `true`, the prefix comparison will be case-insensitive.
 *        If `false`, the comparison will consider case sensitivity.
 * @return A list containing declarations with all specified annotation(s).
 */
fun <T : KoAnnotationProvider> List<T>.withAllAnnotationsNamed(
    name: String,
    vararg names: String,
    ignoreCase: Boolean = false,
): List<T> = withAllAnnotationsNamed(listOf(name, *names), ignoreCase)

/**
 * List containing declarations that have all specified annotations.
 *
 * @param names The name(s) of the annotation(s) to include.
 * @param ignoreCase Specifies whether the comparison should ignore case.
 *        If `true`, the prefix comparison will be case-insensitive.
 *        If `false`, the comparison will consider case sensitivity.
 * @return A list containing declarations with all specified annotation(s).
 */
fun <T : KoAnnotationProvider> List<T>.withAllAnnotationsNamed(
    names: Collection<String>,
    ignoreCase: Boolean = false,
): List<T> =
    filter {
        when {
            names.isEmpty() -> it.hasAnnotations()
            else -> it.hasAnnotationsWithAllNames(names, ignoreCase = ignoreCase)
        }
    }

/**
 * List containing declarations without all specified annotations.
 *
 * @param name The name of the annotation to exclude.
 * @param names The name(s) of the annotation(s) to exclude.
 * @param ignoreCase Specifies whether the comparison should ignore case.
 *        If `true`, the prefix comparison will be case-insensitive.
 *        If `false`, the comparison will consider case sensitivity.
 * @return A list containing declarations without all specified annotation(s).
 */
fun <T : KoAnnotationProvider> List<T>.withoutAllAnnotationsNamed(
    name: String,
    vararg names: String,
    ignoreCase: Boolean = false,
): List<T> = withoutAllAnnotationsNamed(listOf(name, *names), ignoreCase)

/**
 * List containing declarations without all specified annotations.
 *
 * @param names The name(s) of the annotation(s) to exclude.
 * @param ignoreCase Specifies whether the comparison should ignore case.
 *        If `true`, the prefix comparison will be case-insensitive.
 *        If `false`, the comparison will consider case sensitivity.
 * @return A list containing declarations without all specified annotation(s).
 */
fun <T : KoAnnotationProvider> List<T>.withoutAllAnnotationsNamed(
    names: Collection<String>,
    ignoreCase: Boolean = false,
): List<T> =
    filterNot {
        when {
            names.isEmpty() -> it.hasAnnotations()
            else -> it.hasAnnotationsWithAllNames(names, ignoreCase = ignoreCase)
        }
    }

/**
 * List containing declarations that have at least one annotation satisfying the provided predicate.
 *
 * @param predicate A function that defines the condition to be met by an annotation declaration.
 * @return A list containing declarations with at least one annotation satisfying the predicate.
 */
fun <T : KoAnnotationProvider> List<T>.withAnnotation(predicate: (KoAnnotationDeclaration) -> Boolean): List<T> =
    filter { it.hasAnnotation(predicate) }

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
): List<T> = withAnnotationOf(listOf(kClass, *kClasses))

/**
 * List containing declarations that have at least one annotation of the specified `KClass` type.
 *
 * @param kClasses The Kotlin classes representing annotations to include.
 * @return A list containing declarations with at least one annotation of the specified `KClass` type.
 */
fun <T : KoAnnotationProvider> List<T>.withAnnotationOf(kClasses: Collection<KClass<*>>): List<T> =
    filter {
        when {
            kClasses.isEmpty() -> it.hasAnnotations()
            else -> it.hasAnnotationOf(kClasses)
        }
    }

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
): List<T> = withoutAnnotationOf(listOf(kClass, *kClasses))

/**
 * List containing declarations without any annotation of the specified `KClass` type.
 *
 * @param kClasses The Kotlin classes representing annotations to exclude.
 * @return A list containing declarations without any of the specified annotations.
 */
fun <T : KoAnnotationProvider> List<T>.withoutAnnotationOf(kClasses: Collection<KClass<*>>): List<T> =
    filterNot {
        when {
            kClasses.isEmpty() -> it.hasAnnotations()
            else -> it.hasAnnotationOf(kClasses)
        }
    }

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
): List<T> = withAllAnnotationsOf(listOf(kClass, *kClasses))

/**
 * List containing declarations that have all annotations of the specified `KClass` type.
 *
 * @param kClasses The Kotlin classes representing annotations to include.
 * @return A list containing declarations that have all annotations of the specified `KClass` type.
 */
fun <T : KoAnnotationProvider> List<T>.withAllAnnotationsOf(kClasses: Collection<KClass<*>>): List<T> =
    filter {
        when {
            kClasses.isEmpty() -> it.hasAnnotations()
            else -> it.hasAllAnnotationsOf(kClasses)
        }
    }

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
): List<T> = withoutAllAnnotationsOf(listOf(kClass, *kClasses))

/**
 * List containing declarations without all specified `KClass` type annotations.
 *
 * @param kClasses The Kotlin classes representing annotations to exclude.
 * @return A list containing declarations without all specified `KClass` type annotations.
 */
fun <T : KoAnnotationProvider> List<T>.withoutAllAnnotationsOf(kClasses: Collection<KClass<*>>): List<T> =
    filterNot {
        when {
            kClasses.isEmpty() -> it.hasAnnotations()
            else -> it.hasAllAnnotationsOf(kClasses)
        }
    }
