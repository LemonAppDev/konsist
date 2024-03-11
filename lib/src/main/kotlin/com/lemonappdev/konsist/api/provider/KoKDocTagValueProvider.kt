package com.lemonappdev.konsist.api.provider

/**
 * An interface representing a Kotlin declaration that provides access to KDoc value.
 */
interface KoKDocTagValueProvider : KoBaseProvider {
    /**
     * The value of the tag
     */
    val value: String
}
