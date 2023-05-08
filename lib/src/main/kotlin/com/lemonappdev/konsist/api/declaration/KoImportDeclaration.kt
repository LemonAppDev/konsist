package com.lemonappdev.konsist.api.declaration

/**
 * Represents a Kotlin import declaration.
 */
interface KoImportDeclaration : KoNamedDeclaration {
    /**
     * Alias of the import.
     */
    val alias: String

    /**
     * Whether this import is a wildcard.
     */
    val isWildcard: Boolean
}
