package com.lemonappdev.konsist.api.provider

import com.lemonappdev.konsist.api.declaration.KoAnnotationDeclaration
import com.lemonappdev.konsist.api.declaration.KoClassDeclaration
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
    fun hasAnnotations(vararg names: String): Boolean

    /**
     * Whether the declaration has annotations of `KClass` type.
     *
     * @param name the `KClass` type of the annotation to check.
     * @param names the `KClass` types of the annotations to check.
     * @return `true` if the declaration has annotations of the specified `KClass` types, `false` otherwise.
     */
    fun hasAnnotationsOf(name: KClass<*>, vararg names: KClass<*>): Boolean
}
