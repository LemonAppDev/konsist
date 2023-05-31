package com.lemonappdev.konsist.api.declaration

/**
 * Represents a Kotlin annotation.
 */
interface KoAnnotationDeclaration : KoNamedDeclaration {
    /**
     * The fully qualified name of the annotation.
     */
    val fullyQualifiedName: String

    /**
     * Whether this annotation represents the specified type.
     *
     * @param name the name of type to compare. It can be either a simple name or a fully qualified name.
     * @return `true` if this annotation represents the specified type, `false` otherwise.
     */
    fun representsType(name: String): Boolean
}
