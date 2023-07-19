package com.lemonappdev.konsist.api.provider

interface KoTopLevelProvider: KoProvider {
    /**
     * Whether the declaration is defined at top level.
     *
     * @return `true` if the declaration is defined at top level, `false` otherwise.
     */
    fun isTopLevel(): Boolean
}
