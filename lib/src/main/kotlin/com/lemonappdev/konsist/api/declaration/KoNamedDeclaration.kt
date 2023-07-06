package com.lemonappdev.konsist.api.declaration

import com.lemonappdev.konsist.api.provider.KoContainingFileProvider
import com.lemonappdev.konsist.api.provider.KoKDocProvider
import com.lemonappdev.konsist.api.provider.KoLocationProvider
import com.lemonappdev.konsist.api.provider.KoPathProvider
import com.lemonappdev.konsist.api.provider.KoTextProvider

/**
 * Represents a Kotlin named declaration.
 */
interface KoNamedDeclaration :
    KoBaseDeclaration,
    KoPathProvider,
    KoKDocProvider,
    KoContainingFileProvider {
    /**
     * Name of the declaration
     */
    val name: String

    /**
     * Name of the declaration with prefix.
     *
     * @param prefix The prefix to check against. It is a non-null string representing the desired prefix.
     * @return `true` if the declaration's name starts with the prefix, `false` otherwise.
     */
    fun hasNameStartingWith(prefix: String): Boolean

    /**
     * Name of the declaration with suffix.
     *
     * @param suffix The suffix to check against. It is a non-null string representing the desired suffix.
     * @return `true` if the declaration's name ends with the prefix, `false` otherwise.
     */
    fun hasNameEndingWith(suffix: String): Boolean

    /**
     * Name of the declaration containing.
     *
     * @param text The text to check against. It is a non-null string representing the desired text.
     * @return `true` if the declaration's name contains the text, `false` otherwise.
     */
    fun hasNameContaining(text: String): Boolean

    /**
     * Name of the declaration matching regex.
     *
     * @param regex The regex to check against. It is a non-null string representing the desired regex.
     * @return `true` if the declaration's name matching with the regex, `false` otherwise.
     */
    fun hasNameMatching(regex: Regex): Boolean
}
