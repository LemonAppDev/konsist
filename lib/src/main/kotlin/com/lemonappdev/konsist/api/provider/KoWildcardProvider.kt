package com.lemonappdev.konsist.api.provider

/**
 * An interface representing a Kotlin declaration that provides information about whether it is a wildcard.
 */
interface KoWildcardProvider : KoBaseProvider {
    /**
     * Determines whatever this declaration is a wildcard.
     */
    val isWildcard: Boolean
}
