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
     * Returns the number of type aliases that satisfies the specified predicate present in the declaration.
     *
     * @param predicate The predicate function to determine if a type alias satisfies a condition.
     * @return The number of type aliases in the declaration.
     */
    fun countTypeAliases(predicate: (KoTypeAliasDeclaration) -> Boolean): Int

    /**
     * Determines whatever the declaration has type aliases.
     *
     * @param names the names of the type aliases to check.
     * @return `true` if the declaration has type aliases with the specified names (or any type alias if [names] is empty),
     * `false` otherwise.
     */
    @Deprecated(
        """
            Will be removed in v0.16.0. 
            If you passed one argument - replace with `hasTypeAliasWithName`, otherwise with `hasTypeAliasesWithAllNames`.
            """,
    )
    fun hasTypeAliases(vararg names: String): Boolean

    /**
     * Determines whatever declaration has any type alias.
     *
     * @return `true` if the declaration has any type alias, `false` otherwise.
     */
    fun hasTypeAliases(): Boolean

    /**
     * Determines whether the declaration has at least one type alias whose name matches any of the specified names.
     *
     * @param name the name of the type alias to check.
     * @param names the names of the type aliases to check.
     * @return `true` if there is a matching declaration, `false` otherwise.
     */
    fun hasTypeAliasWithName(
        name: String,
        vararg names: String,
    ): Boolean

    /**
     * Determines whether the declaration has at least one type alias whose name matches any of the specified names.
     *
     * @param names the names of the type aliases to check.
     * @return `true` if there is a matching declaration, `false` otherwise.
     */
    fun hasTypeAliasWithName(names: Collection<String>): Boolean

    /**
     * Determines whether the declaration has type aliases with all the specified names.
     *
     * @param name The name of the type alias to check.
     * @param names The names of the type aliases to check.
     * @return `true` if there are declarations with all the specified names, `false` otherwise.
     */
    fun hasTypeAliasesWithAllNames(
        name: String,
        vararg names: String,
    ): Boolean

    /**
     * Determines whether the declaration has type aliases with all the specified names.
     *
     * @param names The names of the type aliases to check.
     * @return `true` if there are declarations with all the specified names, `false` otherwise.
     */
    fun hasTypeAliasesWithAllNames(names: Collection<String>): Boolean

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
     * Note that if the type aliases contains no elements, the function returns `true` because there are no elements in
     * it that do not match the predicate.
     *
     * @param predicate A function that defines the condition to be met by type alias declarations.
     * @return `true` if all type alias declarations satisfy the predicate, `false` otherwise.
     */
    fun hasAllTypeAliases(predicate: (KoTypeAliasDeclaration) -> Boolean): Boolean
}
