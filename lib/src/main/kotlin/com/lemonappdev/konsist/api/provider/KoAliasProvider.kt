package com.lemonappdev.konsist.api.provider

import com.lemonappdev.konsist.api.declaration.KoImportAliasDeclaration

/**
 * An interface representing a Kotlin declaration that provides an alias.
 */
interface KoAliasProvider : KoBaseProvider {
    /**
     * Alias of the declaration.
     */
    val alias: KoImportAliasDeclaration?
}
