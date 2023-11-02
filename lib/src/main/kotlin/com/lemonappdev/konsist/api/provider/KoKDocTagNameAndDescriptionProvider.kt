package com.lemonappdev.konsist.api.provider

import com.lemonappdev.konsist.api.KoKDocTag

/**
 * An interface representing a Kotlin declaration that provides access to KDoc name and description.
 */
interface KoKDocTagNameAndDescriptionProvider : KoBaseProvider {
    /**
     * Name of the tag.
     */
    val name: KoKDocTag

    /**
     * Description of the tag.
     */
    val description: String
}
