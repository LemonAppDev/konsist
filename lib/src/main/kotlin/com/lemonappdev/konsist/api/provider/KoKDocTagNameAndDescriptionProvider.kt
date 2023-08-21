package com.lemonappdev.konsist.api.provider

import com.lemonappdev.konsist.api.KoKDocTag

interface KoKDocTagNameAndDescriptionProvider {
    /**
     * Name of the tag.
     */
    val name: KoKDocTag

    /**
     * Description of the tag.
     */
    val description: String
}
