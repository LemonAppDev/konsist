package com.lemonappdev.konsist.api.declaration

import com.lemonappdev.konsist.api.KoDocTag

/**
 * Represents a Kotlin documentation tag declaration.
 */
interface KoDocTagDeclaration {
    /**
     * Name of the tag.
     */
    val name: KoDocTag

    /**
     * Description of the tag.
     */
    val description: String
}
