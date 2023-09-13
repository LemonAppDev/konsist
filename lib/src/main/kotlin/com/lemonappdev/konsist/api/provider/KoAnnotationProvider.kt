package com.lemonappdev.konsist.api.provider

import com.lemonappdev.konsist.api.declaration.KoAnnotationDeclaration
import kotlin.reflect.KClass

/**
 * An interface representing a Kotlin declaration that provides an annotations.
 */
interface KoAnnotationProvider : KoBaseProvider {
    /**
     * List of annotations.
     */
    val annotations: List<KoAnnotationDeclaration>

    /**
     * The number of annotations.
     */
    val numAnnotations: Int

    /**
     * Gets the number of annotations that satisfies the specified predicate present in the declaration.
     *
     * @param predicate The predicate function to determine if an annotation satisfies a condition.
     * @return The number of annotations in the declaration.
     */
    fun countAnnotations(predicate: (KoAnnotationDeclaration) -> Boolean): Int

    /**
     * Whether the declaration has annotations.
     *
     * @param names the annotation names to check. It can be either a simple name or a fully qualified name.
     * @return `true` if the declaration has annotations with the specified names (or any annotation if [names] is empty),
     * `false` otherwise.
     */
    @Deprecated(
        """
            Will be removed in v1.0.0. 
            If you passed one argument - replace with `hasAnnotationWithName`, otherwise with `hasAnnotationsWithAllNames`.
            """,
    )
    fun hasAnnotations(vararg names: String): Boolean

    /**
     * Whether the declaration has annotations of `KClass` type.
     *
     * @param name the `KClass` type of the annotation to check.
     * @param names the `KClass` types of the annotations to check.
     * @return `true` if the declaration has annotations of the specified `KClass` types, `false` otherwise.
     */
    fun hasAnnotationsOf(name: KClass<*>, vararg names: KClass<*>): Boolean

    /**
     * Whatever declaration has any annotation.
     *
     * @return `true` if the declaration has any annotation, `false` otherwise.
     */
    fun hasAnnotations(): Boolean

    /**
     * Determines whether the declaration has at least one annotation whose name matches any of the specified names.
     *
     * @param names the names of the annotations to check. It can be either a simple name or a fully qualified name.
     * @return `true` if there is a matching declaration, `false` otherwise.
     */
    fun hasAnnotationWithName(vararg names: String): Boolean

    /**
     * Determines whether the declaration has annotations with all the specified names.
     *
     * @param names The names of the annotations to check. It can be either a simple name or a fully qualified name.
     * @return `true` if there are declarations with all the specified names, `false` otherwise.
     */
    fun hasAnnotationsWithAllNames(vararg names: String): Boolean

    /**
     * Determines whether the declaration has at least one annotation that satisfies the provided predicate.
     *
     * @param predicate A function that defines the condition to be met by an annotation declaration.
     * @return `true` if there is a matching declaration, `false` otherwise.
     */
    fun hasAnnotation(predicate: (KoAnnotationDeclaration) -> Boolean): Boolean

    /**
     * Determines whether the declaration has all annotations that satisfy the provided predicate.
     *
     * @param predicate A function that defines the condition to be met by annotation declarations.
     * @return `true` if all annotation declarations satisfy the predicate, `false` otherwise.
     */
    fun hasAllAnnotations(predicate: (KoAnnotationDeclaration) -> Boolean): Boolean
}
