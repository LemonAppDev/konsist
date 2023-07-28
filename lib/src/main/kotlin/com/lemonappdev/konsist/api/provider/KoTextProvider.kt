package com.lemonappdev.konsist.api.provider

/**
 * An interface representing a Kotlin declaration or file that provides access to its text content.
 */
interface KoTextProvider : KoBaseProvider {
    /**
     * Text of the declaration or file.
     */
    val text: String

    /**
     * Print declaration or file.
     */
    fun print(): Unit
}
