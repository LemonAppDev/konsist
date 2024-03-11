package com.lemonappdev.konsist.api.provider

/**
 * An interface representing a Kotlin declaration that provides an alias.
 */
interface KoAliasProvider : KoBaseProvider {
    /**
     * Alias of the declaration.
     */
    val alias: String?
}
