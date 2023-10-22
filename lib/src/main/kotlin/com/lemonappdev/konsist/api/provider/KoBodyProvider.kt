package com.lemonappdev.konsist.api.provider

/**
 * An interface representing a Kotlin declaration that provides an information about the body.
 */
interface KoBodyProvider : KoBaseProvider {
    /**
     * Determines whatever declaration has expression body.
     */
    val hasExpressionBody: Boolean

    /**
     * Determines whatever declaration has block body.
     */
    val hasBlockBody: Boolean
}
