package com.lemonappdev.konsist.api.provider

import com.lemonappdev.konsist.api.declaration.KoSourceDeclaration

/**
 * An interface representing a Kotlin declaration that provides access to its upper bounds.
 */
interface KoUpperBoundsProvider : KoBaseProvider {
    /**
     * Upper bounds of the declaration.
     */
    val upperBounds: List<KoSourceDeclaration>

    /**
     * The number of upper bounds.
     */
    val numUpperBounds: Int

    /**
     * Returns the number of upper bounds that satisfies the specified predicate present in the declaration.
     *
     * @param predicate The predicate function to determine if an upper bound satisfies a condition.
     * @return The number of upper bounds in the declaration.
     */
    fun countUpperBounds(predicate: (KoSourceDeclaration) -> Boolean): Int

    /**
     * Determines whatever declaration has any upper bound.
     *
     * @return `true` if the declaration has any upper bound, `false` otherwise.
     */
    fun hasUpperBounds(): Boolean

    /**
     * Determines whether the declaration has at least one upper bound whose name matches any of the specified names.
     *
     * @param name the name of the upper bound to check.
     * @param names the names of the upper bounds to check.
     * @return `true` if there is a matching declaration, `false` otherwise.
     */
    fun hasUpperBoundWithName(
        name: String,
        vararg names: String,
    ): Boolean

    /**
     * Determines whether the declaration has at least one upper bound whose name matches any of the specified names.
     *
     * @param names the names of the upper bounds to check.
     * @return `true` if there is a matching declaration, `false` otherwise.
     */
    fun hasUpperBoundWithName(names: Collection<String>): Boolean

    /**
     * Determines whether the declaration has upper bounds with all the specified names.
     *
     * @param name The name of the upper bound to check.
     * @param names The names of the upper bounds to check.
     * @return `true` if there are declarations with all the specified names, `false` otherwise.
     */
    fun hasUpperBoundsWithAllNames(
        name: String,
        vararg names: String,
    ): Boolean

    /**
     * Determines whether the declaration has upper bounds with all the specified names.
     *
     * @param names The names of the upper bounds to check.
     * @return `true` if there are declarations with all the specified names, `false` otherwise.
     */
    fun hasUpperBoundsWithAllNames(names: Collection<String>): Boolean

    /**
     * Determines whether the declaration has at least one upper bound that satisfies the provided predicate.
     *
     * @param predicate A function that defines the condition to be met by an upper bound declaration.
     * @return `true` if there is a matching declaration, `false` otherwise.
     */
    fun hasUpperBound(predicate: (KoSourceDeclaration) -> Boolean): Boolean

    /**
     * Determines whether the declaration has all upper bounds that satisfy the provided predicate.
     *
     * Note that if the upper bounds contains no elements, the function returns `true` because there are no elements in it
     * that do not match the predicate.
     *
     * @param predicate A function that defines the condition to be met by upper bound declarations.
     * @return `true` if all upper bound declarations satisfy the predicate, `false` otherwise.
     */
    fun hasAllUpperBounds(predicate: (KoSourceDeclaration) -> Boolean): Boolean
}
