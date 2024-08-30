package com.lemonappdev.konsist.api.provider

/**
 * An interface representing a Kotlin declaration that provides information about whether it is a wildcard.
 */
@Deprecated("Will be removed in version 0.18.0", ReplaceWith("KoIsWildcardProvider"))
interface KoWildcardProvider : KoBaseProvider {
    /**
     * Determines whatever this declaration is a wildcard.
     */
    val isWildcard: Boolean
}
