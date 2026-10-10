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
     * Checks whether the declaration's name is equal to the specified text.
     *
     * @param text The text to compare with. Must be a non-null string representing the expected name.
     * @param ignoreCase Specifies whether the comparison should ignore case.
     *        If `true`, the comparison will be case-insensitive.
     *        If `false`, the comparison will consider case sensitivity.
     * @return `true` if the declaration's name equals the specified text, `false` otherwise.
     */
    fun hasName(
        text: String,
        ignoreCase: Boolean = false,
    ): Boolean

    /**
     * Checks whether the declaration's name starts with the specified prefix.
     *
     * @param prefix The prefix to check against. It is a non-null string representing the desired prefix.
     * @param ignoreCase Specifies whether the comparison should ignore case.
     *        If `true`, the comparison will be case-insensitive.
     *        If `false`, the comparison will consider case sensitivity.
     * @return `true` if the declaration's name starts with the prefix, `false` otherwise.
     */
    fun hasNameStartingWith(
        prefix: String,
        ignoreCase: Boolean = false,
    ): Boolean

    /**
     * Checks whether the declaration's name ends with the specified suffix.
     *
     * @param suffix The suffix to check against. It is a non-null string representing the desired suffix.
     * @param ignoreCase Specifies whether the comparison should ignore case.
     *        If `true`, the comparison will be case-insensitive.
     *        If `false`, the comparison will consider case sensitivity.
     * @return `true` if the declaration's name ends with the suffix, `false` otherwise.
     */
    fun hasNameEndingWith(
        suffix: String,
        ignoreCase: Boolean = false,
    ): Boolean

    /**
     * Checks whether the declaration's name contains the specified text.
     *
     * @param text The text to check against. It is a non-null string representing the desired text.
     * @param ignoreCase Specifies whether the comparison should ignore case.
     *        If `true`, the comparison will be case-insensitive.
     *        If `false`, the comparison will consider case sensitivity.
     * @return `true` if the declaration's name contains the text, `false` otherwise.
     */
    fun hasNameContaining(
        text: String,
        ignoreCase: Boolean = false,
    ): Boolean

    /**
     * Checks whether the declaration's name matches the specified regex.
     *
     * @param regex The regex to check against.
     * @return `true` if the declaration's name matches the regex, `false` otherwise.
     */
    fun hasNameMatching(regex: Regex): Boolean
}
