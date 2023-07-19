package com.lemonappdev.konsist.api.provider

interface KoVarAndValProvider : KoProvider {
    /**
     * Whatever declaration is `var`.
     */
    val isVar: Boolean

    /**
     * Whatever declaration is `val`.
     */
    val isVal: Boolean
}
