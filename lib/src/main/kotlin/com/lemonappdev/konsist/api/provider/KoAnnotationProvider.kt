package com.lemonappdev.konsist.api.provider

import com.lemonappdev.konsist.api.declaration.KoAnnotationDeclaration
import kotlin.reflect.KClass

/**
 * An interface representing a Kotlin declaration, file or scope that provides an annotations.
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
     * Whether the declaration, file or scope has annotations.
     *
     * @param names the annotation names to check. It can be either a simple name or a fully qualified name.
     * @return `true` if the declaration or file has annotations with the specified names (or any annotation if [names] is empty),
     * `false` otherwise.
     */
    fun hasAnnotations(vararg names: String): Boolean

    /**
     * Whether the declaration, file or scope has annotations of `KClass` type.
     *
     * @param name the `KClass` type of the annotation to check.
     * @param names the `KClass` types of the annotations to check.
     * @return `true` if the declaration, file or scope has annotations of the specified `KClass` types, `false` otherwise.
     */
    fun hasAnnotationsOf(name: KClass<*>, vararg names: KClass<*>): Boolean
}
