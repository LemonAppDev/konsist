package com.lemonappdev.konsist.api.provider

/**
 * An interface representing a Kotlin declaration that provides access to its text content.
 */
interface KoTextProvider : KoBaseProvider {
    /**
     * Text of the declaration.
     */
    val text: String

    /**
     * Print declaration.
     *
     * @param prefix An optional string to be printed before the declaration content. Default is null.
     */
    fun print(prefix: String? = null): Unit
}
