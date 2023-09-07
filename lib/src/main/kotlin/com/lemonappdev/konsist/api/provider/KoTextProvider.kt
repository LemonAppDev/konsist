package com.lemonappdev.konsist.api.provider

/**
 * An interface representing a Kotlin declaration that provides access to its text content.
 */
interface KoTextProvider : KoBaseProvider {
    /**
     * Text of the declaration.
     */
    val text: String
}
