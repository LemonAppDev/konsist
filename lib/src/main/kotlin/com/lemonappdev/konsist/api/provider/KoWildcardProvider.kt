package com.lemonappdev.konsist.api.provider

interface KoWildcardProvider : KoProvider {
    /**
     * Whether this declaration is a wildcard.
     */
    val isWildcard: Boolean
}
