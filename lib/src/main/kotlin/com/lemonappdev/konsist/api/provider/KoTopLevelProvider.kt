package com.lemonappdev.konsist.api.provider

/**
 * An interface representing a Kotlin declaration that provides information about whether it is defined at top level.
 */
interface KoTopLevelProvider : KoBaseProvider {
    /**
     * Whether the declaration is defined at top level.
     *
     * @return `true` if the declaration is defined at top level, `false` otherwise.
     */
    fun isTopLevel(): Boolean
}
