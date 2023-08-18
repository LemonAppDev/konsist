package com.lemonappdev.konsist.api.provider

import com.lemonappdev.konsist.api.declaration.KoBaseDeclaration

/**
 * An interface representing a Kotlin declaration that provides information about declarations.
 */
interface KoDeclarationProvider : KoBaseProvider {
    /**
     * The declarations present in the declaration.
     *
     * @param includeNested specifies whether to include nested declarations.
     * @param includeLocal specifies whether to include local declarations.
     * @return a list of [KoBaseDeclaration] representing the declarations in the declaration.
     */
    fun declarations(
        includeNested: Boolean = false,
        includeLocal: Boolean = false,
    ): List<KoBaseDeclaration>

    /**
     * Checks whether the declaration contains a declaration that satisfies the specified predicate.
     *
     * @param includeNested Specifies whether to include nested declarations in the check (optional, default is `false`).
     * @param includeLocal Specifies whether to include local declarations in the check (optional, default is `false`).
     * @param predicate The predicate function to determine if a declaration satisfies a condition.
     * @return `true` if the declaration contains a declaration that satisfies the specified predicate, `false` otherwise.
     */
    fun containsDeclaration(
        includeNested: Boolean = false,
        includeLocal: Boolean = false,
        predicate: (KoBaseDeclaration) -> Boolean,
    ): Boolean

    /**
     * Gets the number of declarations present in the declaration.
     *
     * @param includeNested Specifies whether to include nested declarations in the count (optional, default is `false`).
     * @param includeLocal Specifies whether to include local declarations in the count (optional, default is `false`).
     * @return The number of declarations in the declaration.
     */
    fun countDeclarations(includeNested: Boolean = false, includeLocal: Boolean = false): Int

    /**
     * Gets the number of declarations that satisfies the specified predicate present in the declaration.
     *
     * @param includeNested Specifies whether to include nested declarations in the count (optional, default is `false`).
     * @param includeLocal Specifies whether to include local declarations in the count (optional, default is `false`).
     * @param predicate The predicate function to determine if a declaration satisfies a condition.
     * @return The number of declarations that satisfies the specified predicate in the declaration.
     */
    fun countDeclarations(
        includeNested: Boolean = false,
        includeLocal: Boolean = false,
        predicate: (KoBaseDeclaration) -> Boolean,
    ): Int

    /**
     * Gets the number of declarations with public visibility modifier present in the declaration.
     *
     * @param includeNested Specifies whether to include nested declarations in the count (optional, default is `false`).
     * @param includeLocal Specifies whether to include local declarations in the count (optional, default is `false`).
     * @return The number of declarations in the declaration.
     */
    fun countPublic(includeNested: Boolean = false, includeLocal: Boolean = false): Int

    /**
     * Gets the number of declarations with public or default visibility modifier present in the declaration.
     *
     * @param includeNested Specifies whether to include nested declarations in the count (optional, default is `false`).
     * @param includeLocal Specifies whether to include local declarations in the count (optional, default is `false`).
     * @return The number of declarations in the declaration.
     */
    fun countPublicOrDefault(includeNested: Boolean = false, includeLocal: Boolean = false): Int

    /**
     * Gets the number of declarations with private visibility modifier present in the declaration.
     *
     * @param includeNested Specifies whether to include nested declarations in the count (optional, default is `false`).
     * @param includeLocal Specifies whether to include local declarations in the count (optional, default is `false`).
     * @return The number of declarations in the declaration.
     */
    fun countPrivate(includeNested: Boolean = false, includeLocal: Boolean = false): Int

    /**
     * Gets the number of declarations with protected visibility modifier present in the declaration.
     *
     * @param includeNested Specifies whether to include nested declarations in the count (optional, default is `false`).
     * @param includeLocal Specifies whether to include local declarations in the count (optional, default is `false`).
     * @return The number of declarations in the declaration.
     */
    fun countProtected(includeNested: Boolean = false, includeLocal: Boolean = false): Int

    /**
     * Gets the number of declarations with internal visibility modifier present in the declaration.
     *
     * @param includeNested Specifies whether to include nested declarations in the count (optional, default is `false`).
     * @param includeLocal Specifies whether to include local declarations in the count (optional, default is `false`).
     * @return The number of declarations in the declaration.
     */
    fun countInternal(includeNested: Boolean = false, includeLocal: Boolean = false): Int
}
