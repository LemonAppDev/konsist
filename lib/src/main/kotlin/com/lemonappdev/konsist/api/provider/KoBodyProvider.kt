package com.lemonappdev.konsist.api.provider

/**
 * An interface representing a Kotlin declaration that provides information about the body.
 */
interface KoBodyProvider : KoBaseProvider {
    /**
     * Determines whether the declaration has an expression body.
     */
    val hasExpressionBody: Boolean

    /**
     * Determines whether the declaration has a block body.
     */
    val hasBlockBody: Boolean

    /**
     * The body of the declaration without the enclosing braces, or `null` if the declaration has no body.
     * For an expression body, the expression is returned.
     */
    val body: String?
}
