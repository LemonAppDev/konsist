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
     * Determines whether the declaration's text starts with the specified prefix.
     *
     * @param prefix The prefix to check against. It is a non-null string representing the desired prefix.
     * @return `true` if the declaration's text starts with the prefix, `false` otherwise.
     */
    fun hasTextStartingWith(prefix: String): Boolean

    /**
     * Determines whether the declaration's text ends with the specified suffix.
     *
     * @param suffix The suffix to check against. It is a non-null string representing the desired suffix.
     * @return `true` if the declaration's text ends with the suffix, `false` otherwise.
     */
    fun hasTextEndingWith(suffix: String): Boolean

    /**
     * Determines whether the declaration's text contains the specified text.
     *
     * @param str The text to check against. It is a non-null string representing the desired text.
     * @return `true` if the declaration's text contains the text, `false` otherwise.
     */
    fun hasTextContaining(str: String): Boolean

    /**
     * Determines whether the declaration's text matches the specified regex.
     *
     * @param regex The regex to check against. It is a non-null regular expression.
     * @return `true` if the declaration's text matches the regex, `false` otherwise.
     */
    fun hasTextMatching(regex: Regex): Boolean
}
