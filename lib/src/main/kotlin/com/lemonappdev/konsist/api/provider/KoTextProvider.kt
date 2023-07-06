package com.lemonappdev.konsist.api.provider

interface KoTextProvider {
    /**
     * Text of the declaration
     */
    val text: String

    /**
     * Print declaration.
     */
    fun print(): Unit
}
