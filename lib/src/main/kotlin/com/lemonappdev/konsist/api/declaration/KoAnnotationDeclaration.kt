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
     */
    fun representsType(name: String): Boolean
}
