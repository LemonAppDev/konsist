package com.lemonappdev.konsist.api.provider

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
