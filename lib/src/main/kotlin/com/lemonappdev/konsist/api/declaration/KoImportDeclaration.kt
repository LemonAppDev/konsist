package com.lemonappdev.konsist.api.declaration

/**
 * Represents a Kotlin import declaration.
 */
interface KoImportDeclaration : KoNamedDeclaration {
    /**
     * Alias of the import.
     */
    val alias: String
}
