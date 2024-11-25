package com.lemonappdev.konsist.api.provider

/**
 * An interface representing a Kotlin declaration that provides information about whether it has a `val` keyword.
 */
interface KoIsValProvider : KoBaseProvider {
    /**
     * Determines whatever the declaration has `val` keyword.
     */
    val isVal: Boolean
}
