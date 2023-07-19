package com.lemonappdev.konsist.api.provider

interface KoAliasProvider : KoProvider {
    /**
     * Alias of the declaration.
     */
    val alias: String?
}
