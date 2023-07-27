package com.lemonappdev.konsist.api.declaration

/**
 * Represents a Kotlin primary constructor declaration.
 */
interface KoPrimaryConstructorDeclaration : KoConstructorDeclaration {
    /**
     * String representing the primary constructor.
     *
     * @return a string representing the primary constructor.
     */
    override fun toString(): String
}
