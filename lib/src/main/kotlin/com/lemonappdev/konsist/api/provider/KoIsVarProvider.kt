package com.lemonappdev.konsist.api.provider

/**
 * An interface representing a Kotlin declaration that provides information about whether it has a `var` keyword.
 */
interface KoIsVarProvider : KoBaseProvider {
    /**
     * Determines whether the declaration has the `var` keyword.
     */
    val isVar: Boolean
}
