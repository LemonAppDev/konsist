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
     * Text of the declaration with prefix.
     *
     * @param prefix The prefix to check against. It is a non-null string representing the desired prefix.
     * @return `true` if the declaration's text starts with the prefix, `false` otherwise.
     */
    fun hasTextStartingWith(prefix: String): Boolean

    /**
     * Text of the declaration with suffix.
     *
     * @param suffix The suffix to check against. It is a non-null string representing the desired suffix.
     * @return `true` if the declaration's text ends with the prefix, `false` otherwise.
     */
    fun hasTextEndingWith(suffix: String): Boolean

    /**
     * Text of the declaration containing text.
     *
     * @param str The text to check against. It is a non-null string representing the desired text.
     * @return `true` if the declaration's text contains the text, `false` otherwise.
     */
    fun hasTextContaining(str: String): Boolean

    /**
     * Text of the declaration matching regex.
     *
     * @param regex The regex to check against. It is a non-null string representing the desired regex.
     * @return `true` if the declaration's text matching with the regex, `false` otherwise.
     */
    fun hasTextMatching(regex: Regex): Boolean
}
