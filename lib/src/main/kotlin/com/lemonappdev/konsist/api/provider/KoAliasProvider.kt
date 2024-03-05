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

    /**
     * Determines whatever declaration has a specified alias.
     *
     * @param predicate The predicate function used to determine if a declaration alias satisfies a condition.
     * @return `true` if the declaration has the specified alias (or any alias if [predicate] is `null`),
     * `false` otherwise.
     */
    fun hasAlias(predicate: ((KoImportAliasDeclaration) -> Boolean)? = null): Boolean
}
