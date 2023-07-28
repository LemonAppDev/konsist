package com.lemonappdev.konsist.api.provider

/**
 * An interface representing a Kotlin declaration that provides information about whether it is a `var` or `val`.
 */
interface KoVarAndValProvider : KoBaseProvider {
    /**
     * Whatever declaration is `var`.
     */
    val isVar: Boolean

    /**
     * Whatever declaration is `val`.
     */
    val isVal: Boolean
}
