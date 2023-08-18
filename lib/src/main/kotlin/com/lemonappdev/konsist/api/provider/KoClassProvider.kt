package com.lemonappdev.konsist.api.provider

import com.lemonappdev.konsist.api.declaration.KoClassDeclaration

/**
 * An interface representing a Kotlin declaration that provides information about classes.
 */
interface KoClassProvider : KoBaseProvider {
    /**
     * The classes present in the declaration.
     *
     * @param includeNested specifies whether to include nested classes.
     * @param includeLocal specifies whether to include local classes.
     * @return a list of [KoClassDeclaration] representing the classes in the declaration.
     */
    fun classes(
        includeNested: Boolean = false,
        includeLocal: Boolean = false,
    ): List<KoClassDeclaration>

    /**
     * Checks whether the declaration contains a class that satisfies the specified predicate.
     *
     * @param includeNested Specifies whether to include nested classes in the check (optional, default is `false`).
     * @param includeLocal Specifies whether to include local classes in the check (optional, default is `false`).
     * @param predicate The predicate function to determine if a class satisfies a condition.
     * @return `true` if the declaration contains a class with the specified predicate, `false` otherwise.
     */
    fun containsClass(
        includeNested: Boolean = false,
        includeLocal: Boolean = false,
        predicate: (KoClassDeclaration) -> Boolean,
    ): Boolean

    /**
     * Gets the number of classes present in the declaration.
     *
     * @param includeNested Specifies whether to include nested classes in the count (optional, default is `false`).
     * @param includeLocal Specifies whether to include local classes in the count (optional, default is `false`).
     * @return The number of classes in the declaration.
     */
    fun countClasses(includeNested: Boolean = false, includeLocal: Boolean = false): Int

    /**
     * Gets the number of classes that satisfies the specified predicate present in the declaration.
     *
     * @param includeNested Specifies whether to include nested classes in the count (optional, default is `false`).
     * @param includeLocal Specifies whether to include local classes in the count (optional, default is `false`).
     * @param predicate The predicate function to determine if a class satisfies a condition.
     * @return The number of classes in the declaration.
     */
    fun countClasses(
        includeNested: Boolean = false,
        includeLocal: Boolean = false,
        predicate: (KoClassDeclaration) -> Boolean,
    ): Int
}
