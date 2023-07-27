package com.lemonappdev.konsist.api.provider

/**
 * An interface representing a Kotlin declaration or file that provides information about its name.
 */
interface KoNameProvider : KoBaseProvider {
    /**
     * Name of the declaration or file.
     */
    val name: String

    /**
     * Name of the declaration or file with prefix.
     *
     * @param prefix The prefix to check against. It is a non-null string representing the desired prefix.
     * @return `true` if the declaration's or file's name starts with the prefix, `false` otherwise.
     */
    fun hasNameStartingWith(prefix: String): Boolean

    /**
     * Name of the declaration or file with suffix.
     *
     * @param suffix The suffix to check against. It is a non-null string representing the desired suffix.
     * @return `true` if the declaration's or file's name ends with the prefix, `false` otherwise.
     */
    fun hasNameEndingWith(suffix: String): Boolean

    /**
     * Name of the declaration or file containing text.
     *
     * @param text The text to check against. It is a non-null string representing the desired text.
     * @return `true` if the declaration's or file's name contains the text, `false` otherwise.
     */
    fun hasNameContaining(text: String): Boolean

    /**
     * Name of the declaration or file matching regex.
     *
     * @param regex The regex to check against. It is a non-null string representing the desired regex.
     * @return `true` if the declaration's or file's name matching with the regex, `false` otherwise.
     */
    fun hasNameMatching(regex: Regex): Boolean
}
