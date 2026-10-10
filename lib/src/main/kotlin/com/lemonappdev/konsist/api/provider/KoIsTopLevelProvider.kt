package com.lemonappdev.konsist.api.provider

/**
 * An interface representing a Kotlin declaration that provides information about whether it is defined at the top level.
 */
interface KoIsTopLevelProvider : KoBaseProvider {
    /**
     * Determines whether the declaration is defined at the top level.
     */
    val isTopLevel: Boolean
}
