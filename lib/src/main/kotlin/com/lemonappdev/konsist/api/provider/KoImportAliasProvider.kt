package com.lemonappdev.konsist.api.provider

import com.lemonappdev.konsist.api.declaration.KoImportAliasDeclaration

/**
 * An interface representing a Kotlin declaration that provides access to import aliases.
 *
 */
interface KoImportAliasProvider : KoBaseProvider {
    /**
     * List of import aliases.
     */
    val importAliases: List<KoImportAliasDeclaration>

    /**
     * The number of import aliases.
     */
    val numImportAliases: Int

    /**
     * Returns the number of import aliases that satisfies the specified predicate present in the declaration.
     *
     * @param predicate The predicate function to determine if a import alias satisfies a condition.
     * @return The number of import aliases in the declaration.
     */
    fun countImportAliases(predicate: (KoImportAliasDeclaration) -> Boolean): Int

    /**
     * Determines whatever declaration has any import alias.
     *
     * @return `true` if the declaration has any import alias, `false` otherwise.
     */
    fun hasImportAliases(): Boolean

    /**
     * Determines whether the declaration has at least one import alias whose name matches any of the specified names.
     *
     * @param name the name of the import alias to check.
     * @param names the names of the import aliases to check.
     * @return `true` if there is a matching declaration, `false` otherwise.
     */
    fun hasImportAliasWithName(name: String, vararg names: String): Boolean

    /**
     * Determines whether the declaration has import aliases with all the specified names.
     *
     * @param name The name of the import alias to check.
     * @param names The names of the import aliases to check.
     * @return `true` if there are declarations with all the specified names, `false` otherwise.
     */
    fun hasImportAliasesWithAllNames(name: String, vararg names: String): Boolean

    /**
     * Determines whether the declaration has at least one import alias that satisfies the provided predicate.
     *
     * @param predicate A function that defines the condition to be met by a import alias declaration.
     * @return `true` if there is a matching declaration, `false` otherwise.
     */
    fun hasImportAlias(predicate: (KoImportAliasDeclaration) -> Boolean): Boolean

    /**
     * Determines whether the declaration has all import aliases that satisfy the provided predicate.
     *
     * Note that if the import aliases contains no elements, the function returns `true` because there are no elements in
     * it that do not match the predicate.
     *
     * @param predicate A function that defines the condition to be met by import alias declarations.
     * @return `true` if all import alias declarations satisfy the predicate, `false` otherwise.
     */
    fun hasAllImportAliases(predicate: (KoImportAliasDeclaration) -> Boolean): Boolean
}
