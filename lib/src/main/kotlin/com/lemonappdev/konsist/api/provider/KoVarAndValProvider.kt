package com.lemonappdev.konsist.api.provider

interface KoVarAndValProvider {
    /**
     * Whatever declaration is `var`.
     */
    val isVar: Boolean

    /**
     * Whatever declaration is `val`.
     */
    val isVal: Boolean
}
