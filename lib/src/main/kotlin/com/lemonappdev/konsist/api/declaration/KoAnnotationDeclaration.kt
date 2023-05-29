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
     * Returns `true` if this annotation represents the specified type.
     *
     * @param name the name of the type to compare.
     * @return `true` if this annotation represents the specified type, `false` otherwise.
     */
    fun representsType(name: String): Boolean
}
