package com.lemonappdev.konsist.api.provider

/**
 * An interface representing a Kotlin declaration that provides information about whether it is defined at top level.
 */
interface KoTopLevelProvider : KoBaseProvider {
    /**
     * Determines whatever the declaration is defined at top level.
     */
    val isTopLevel: Boolean
}
