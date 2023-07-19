package com.lemonappdev.konsist.api.provider

import com.lemonappdev.konsist.api.declaration.KoAnnotationDeclaration
import kotlin.reflect.KClass

interface KoAnnotationProvider {
    /**
     * List of annotations.
     */
    val annotations: List<KoAnnotationDeclaration>

    /**
     * Whether the declaration or file has annotations.
     *
     * @param names the annotation names to check. It can be either a simple name or a fully qualified name.
     * @return `true` if the declaration or file has annotations with the specified names (or any annotation if [names] is empty),
     * `false` otherwise.
     */
    fun hasAnnotations(vararg names: String): Boolean

    /**
     * Whether the declaration or file has annotations of `KClass` type.
     *
     * @param names the `KClass` types of the annotations to check.
     * @return `true` if the declaration or file has annotations of the specified `KClass` types, `false` otherwise.
     */
    fun hasAnnotationsOf(vararg names: KClass<*>): Boolean
}
