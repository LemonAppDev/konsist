package com.lemonappdev.konsist.api.provider

/**
 * An interface representing a Kotlin declaration that provides access to its KDoc description.
 */
interface KoKDocDescriptionProvider : KoBaseProvider {
    /**
     * Description of the declaration.
     */
    val description: String
}
