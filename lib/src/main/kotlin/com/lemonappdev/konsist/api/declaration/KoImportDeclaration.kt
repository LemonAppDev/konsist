package com.lemonappdev.konsist.api.declaration

import com.lemonappdev.konsist.api.provider.KoParentProvider

/**
 * Represents a Kotlin import declaration.
 */
interface KoImportDeclaration :
    KoNamedDeclaration,
    KoBaseDeclaration,
    KoParentProvider {
    /**
     * Alias of the import.
     */
    val alias: String

    /**
     * Whether this import is a wildcard.
     */
    val isWildcard: Boolean
}
