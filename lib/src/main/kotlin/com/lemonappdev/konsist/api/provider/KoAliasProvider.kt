package com.lemonappdev.konsist.api.provider

interface KoAliasProvider : KoBaseProvider {
    /**
     * Alias of the declaration.
     */
    val alias: String?
}
