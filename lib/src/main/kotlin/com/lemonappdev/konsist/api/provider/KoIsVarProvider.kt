package com.lemonappdev.konsist.api.provider

/**
 * An interface representing a Kotlin declaration that provides information about whether it has a `var` keyword.
 */
interface KoIsVarProvider : KoBaseProvider {
    /**
     * Determines whatever the declaration has `var` keyword.
     */
    val isVar: Boolean
}
