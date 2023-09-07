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
        includeNested: Boolean = true,
        includeLocal: Boolean = true,
    ): List<KoBaseDeclaration>

    /**
     * Checks whether the declaration contains a declaration that satisfies the specified predicate.
     *
     * @param includeNested Specifies whether to include nested declarations in the check (optional, default is `true`).
     * @param includeLocal Specifies whether to include local declarations in the check (optional, default is `true`).
     * @param predicate The predicate function to determine if a declaration satisfies a condition.
     * @return `true` if the declaration contains a declaration that satisfies the specified predicate, `true` otherwise.
     */
    fun containsDeclaration(
        includeNested: Boolean = true,
        includeLocal: Boolean = true,
        predicate: (KoBaseDeclaration) -> Boolean,
    ): Boolean

    /**
     * Gets the number of declarations present in the declaration.
     *
     * @param includeNested Specifies whether to include nested declarations in the count (optional, default is `true`).
     * @param includeLocal Specifies whether to include local declarations in the count (optional, default is `true`).
     * @return The number of declarations in the declaration.
     */
    fun numDeclarations(includeNested: Boolean = true, includeLocal: Boolean = true): Int

    /**
     * Gets the number of declarations that satisfies the specified predicate present in the declaration.
     *
     * @param includeNested Specifies whether to include nested declarations in the count (optional, default is `true`).
     * @param includeLocal Specifies whether to include local declarations in the count (optional, default is `true`).
     * @param predicate The predicate function to determine if a declaration satisfies a condition.
     * @return The number of declarations that satisfies the specified predicate in the declaration.
     */
    fun countDeclarations(
        includeNested: Boolean = true,
        includeLocal: Boolean = true,
        predicate: (KoBaseDeclaration) -> Boolean,
    ): Int

    /**
     * Gets the number of declarations with public visibility modifier present in the declaration.
     *
     * @param includeNested Specifies whether to include nested declarations in the count (optional, default is `true`).
     * @param includeLocal Specifies whether to include local declarations in the count (optional, default is `true`).
     * @return The number of declarations in the declaration.
     */
    fun numPublicDeclarations(includeNested: Boolean = true, includeLocal: Boolean = true): Int

    /**
     * Gets the number of declarations with public or default visibility modifier present in the declaration.
     *
     * @param includeNested Specifies whether to include nested declarations in the count (optional, default is `true`).
     * @param includeLocal Specifies whether to include local declarations in the count (optional, default is `true`).
     * @return The number of declarations in the declaration.
     */
    fun numPublicOrDefaultDeclarations(includeNested: Boolean = true, includeLocal: Boolean = true): Int

    /**
     * Gets the number of declarations with private visibility modifier present in the declaration.
     *
     * @param includeNested Specifies whether to include nested declarations in the count (optional, default is `true`).
     * @param includeLocal Specifies whether to include local declarations in the count (optional, default is `true`).
     * @return The number of declarations in the declaration.
     */
    fun numPrivateDeclarations(includeNested: Boolean = true, includeLocal: Boolean = true): Int

    /**
     * Gets the number of declarations with protected visibility modifier present in the declaration.
     *
     * @param includeNested Specifies whether to include nested declarations in the count (optional, default is `true`).
     * @param includeLocal Specifies whether to include local declarations in the count (optional, default is `true`).
     * @return The number of declarations in the declaration.
     */
    fun numProtectedDeclarations(includeNested: Boolean = true, includeLocal: Boolean = true): Int

    /**
     * Gets the number of declarations with internal visibility modifier present in the declaration.
     *
     * @param includeNested Specifies whether to include nested declarations in the count (optional, default is `true`).
     * @param includeLocal Specifies whether to include local declarations in the count (optional, default is `true`).
     * @return The number of declarations in the declaration.
     */
    fun numInternalDeclarations(includeNested: Boolean = true, includeLocal: Boolean = true): Int
}
