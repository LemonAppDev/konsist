package com.lemonappdev.konsist.api.provider

interface KoWildcardProvider : KoBaseProvider {
    /**
     * Whether this declaration is a wildcard.
     */
    val isWildcard: Boolean
}
