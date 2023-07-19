package com.lemonappdev.konsist.api.provider

interface KoTextProvider : KoProvider {
    /**
     * Text of the declaration or file.
     */
    val text: String

    /**
     * Print declaration or file.
     */
    fun print(): Unit
}
