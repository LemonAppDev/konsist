package com.lemonappdev.konsist.api.declaration

import com.lemonappdev.konsist.api.KoKDocTag

/**
 * Represents a Kotlin documentation tag declaration.
 */
interface KoKDocTagDeclaration {
    /**
     * Name of the tag.
     */
    val name: KoKDocTag

    /**
     * Description of the tag.
     */
    val description: String
}
