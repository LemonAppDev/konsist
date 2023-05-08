package com.lemonappdev.konsist.api.declaration

/**
 * Represents a documentation tag
 */
interface KoValuedKDocTagDeclaration : KoKDocTagDeclaration {
    /**
     * The value of the tag
     */
    val value: String
}
