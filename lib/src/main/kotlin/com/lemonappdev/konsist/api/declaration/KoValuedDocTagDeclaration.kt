package com.lemonappdev.konsist.api.declaration

/**
 * Represents a documentation tag
 */
interface KoValuedDocTagDeclaration : KoDocTagDeclaration {
    /**
     * The value of the tag
     */
    val value: String
}
