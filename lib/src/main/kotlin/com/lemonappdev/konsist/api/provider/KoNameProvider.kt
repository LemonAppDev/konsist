package com.lemonappdev.konsist.api.provider

/**
 * An interface representing a Kotlin declaration that provides information about its name.
 */
interface KoNameProvider : KoBaseProvider {
    /**
     * Name of the declaration.
     */
    val name: String

    /**
     * Name of the declaration with prefix.
     *
     * @param prefix The prefix to check against. It is a non-null string representing the desired prefix.
     * @param ignoreCase Specifies whether the comparison should ignore case.
     *        If `true`, the prefix comparison will be case-insensitive.
     *        If `false`, the comparison will consider case sensitivity.
     * @return `true` if the declaration's name starts with the prefix, `false` otherwise.
     */
    fun hasNameStartingWith(prefix: String, ignoreCase: Boolean = false): Boolean

    /**
     * Name of the declaration with suffix.
     *
     * @param suffix The suffix to check against. It is a non-null string representing the desired suffix.
     * @param ignoreCase Specifies whether the comparison should ignore case.
     *        If `true`, the prefix comparison will be case-insensitive.
     *        If `false`, the comparison will consider case sensitivity.
     * @return `true` if the declaration's name ends with the prefix, `false` otherwise.
     */
    fun hasNameEndingWith(suffix: String, ignoreCase: Boolean = false): Boolean

    /**
     * Name of the declaration containing text.
     *
     * @param text The text to check against. It is a non-null string representing the desired text.
     * @param ignoreCase Specifies whether the comparison should ignore case.
     *        If `true`, the prefix comparison will be case-insensitive.
     *        If `false`, the comparison will consider case sensitivity.
     * @return `true` if the declaration's name contains the text, `false` otherwise.
     */
    fun hasNameContaining(text: String, ignoreCase: Boolean = false): Boolean

    /**
     * Name of the declaration matching regex.
     *
     * @param regex The regex to check against. It is a non-null string representing the desired regex.
     * @return `true` if the declaration's name matching with the regex, `false` otherwise.
     */
    fun hasNameMatching(regex: Regex): Boolean
}
