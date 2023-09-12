package com.lemonappdev.konsist.api.provider

import com.lemonappdev.konsist.api.declaration.KoTypeAliasDeclaration

/**
 * An interface representing a Kotlin declaration that provides access to type aliases.
 *
 */
interface KoTypeAliasProvider : KoBaseProvider {
    /**
     * List of type aliases.
     */
    val typeAliases: List<KoTypeAliasDeclaration>

    /**
     * The number of type aliases.
     */
    val numTypeAliases: Int

    /**
     * Gets the number of type aliases that satisfies the specified predicate present in the declaration.
     *
     * @param predicate The predicate function to determine if a type alias satisfies a condition.
     * @return The number of type aliases in the declaration.
     */
    fun countTypeAliases(predicate: (KoTypeAliasDeclaration) -> Boolean): Int

    /**
     * Whether the declaration has type aliases.
     *
     * @param names the names of the type aliases to check.
     * @return `true` if the declaration has type aliases with the specified names (or any type alias if [names] is empty),
     * `false` otherwise.
     */
    @Deprecated(
        """
            Will be removed in v1.0.0. 
            If you passed one argument - replace with `hasTypeAliasWithName`, otherwise with `hasTypeAliasesWithAllNames`.
            """,
    )
    fun hasTypeAliases(vararg names: String): Boolean

    /**
     * Whatever declaration has any type alias.
     *
     * @return `true` if the declaration has any type alias, `false` otherwise.
     */
    fun hasTypeAliases(): Boolean

    /**
     * Determines whether the declaration has at least one type alias whose name matches any of the specified names.
     *
     * @param names the names of the type aliases to check.
     * @return `true` if there is a matching declaration, `false` otherwise.
     */
    fun hasTypeAliasWithName(vararg names: String): Boolean

    /**
     * Determines whether the declaration has type aliases with all the specified names.
     *
     * @param names The names of the type aliases to check.
     * @return `true` if there are declarations with all the specified names, `false` otherwise.
     */
    fun hasTypeAliasesWithAllNames(vararg names: String): Boolean

    /**
     * Determines whether the declaration has at least one type alias that satisfies the provided predicate.
     *
     * @param predicate A function that defines the condition to be met by a type alias declaration.
     * @return `true` if there is a matching declaration, `false` otherwise.
     */
    fun hasTypeAlias(predicate: (KoTypeAliasDeclaration) -> Boolean): Boolean

    /**
     * Determines whether the declaration has all type aliases that satisfy the provided predicate.
     *
     * @param predicate A function that defines the condition to be met by type alias declarations.
     * @return `true` if all type alias declarations satisfy the predicate, `false` otherwise.
     */
    fun hasAllTypeAliases(predicate: (KoTypeAliasDeclaration) -> Boolean): Boolean
}
