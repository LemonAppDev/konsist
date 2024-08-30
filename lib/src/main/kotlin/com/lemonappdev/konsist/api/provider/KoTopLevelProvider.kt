package com.lemonappdev.konsist.api.provider

/**
 * An interface representing a Kotlin declaration that provides information about whether it is defined at top level.
 */
@Deprecated("Will be removed in version 0.18.0", ReplaceWith("KoIsTopLevelProvider"))
interface KoTopLevelProvider : KoBaseProvider {
    /**
     * Determines whatever the declaration is defined at top level.
     */
    val isTopLevel: Boolean
}
