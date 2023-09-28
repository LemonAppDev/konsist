package com.lemonappdev.konsist.api.provider

/**
 * An interface representing a Kotlin declaration that provides an information whether it body.
 */
interface KoBodyProvider : KoBaseProvider {
    /**
     * Whatever declaration has expression body.
     */
    val hasExpressionBody: Boolean

    /**
     * Whatever declaration has block body.
     */
    val hasBlockBody: Boolean
}
