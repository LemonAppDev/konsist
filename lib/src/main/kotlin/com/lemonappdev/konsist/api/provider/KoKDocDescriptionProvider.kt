package com.lemonappdev.konsist.api.provider

interface KoKDocDescriptionProvider : KoProvider {
    /**
     * Description of the declaration.
     */
    val description: String
}
