package com.lemonappdev.konsist.api.declaration

/**
 * Represents a Kotlin named declaration.
 */
interface KoNamedDeclaration : KoBaseDeclaration {
    /**
     * Name of the declaration
     */
    val name: String

    /**
     * Name of the declaration with prefix.
     */
    fun hasNameWithPrefix(prefix: String): Boolean

    /**
     * Name of the declaration with suffix.
     */
    fun hasNameWithSuffix(suffix: String): Boolean

    /**
     * Name of the declaration containing.
     */
    fun hasNameContaining(text: String): Boolean

    /**
     * Name of the declaration matching regex.
     */
    fun hasNameMatching(regex: Regex): Boolean
}
