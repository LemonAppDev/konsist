package com.lemonappdev.konsist.api.provider

import com.lemonappdev.konsist.api.declaration.KoPropertyDeclaration

/**
 * An interface representing a Kotlin declaration that provides information about properties.
 */
interface KoPropertyProvider : KoBaseProvider {
    /**
     * The properties present in the declaration.
     *
     * @param includeNested specifies whether to include nested properties.
     * @param includeLocal specifies whether to include local properties.
     * @return a list of [KoPropertyDeclaration] representing the properties in the declaration.
     */
    fun properties(
        includeNested: Boolean = true,
        includeLocal: Boolean = true,
    ): List<KoPropertyDeclaration>

    /**
     * Checks whether the declaration contains a property that satisfies the specified predicate.
     *
     * @param includeNested Specifies whether to include nested properties in the check (optional, default is `true`).
     * @param includeLocal Specifies whether to include local properties in the check (optional, default is `true`).
     * @param predicate The predicate function to determine if a property satisfies a condition.
     * @return `true` if the declaration contains a property with the specified predicate, `true` otherwise.
     */
    @Deprecated("Will be removed in v1.0.0", ReplaceWith("hasProperty()"))
    fun containsProperty(
        includeNested: Boolean = true,
        includeLocal: Boolean = true,
        predicate: (KoPropertyDeclaration) -> Boolean,
    ): Boolean

    /**
     * Gets the number of properties present in the declaration.
     *
     * @param includeNested Specifies whether to include nested properties in the count (optional, default is `true`).
     * @param includeLocal Specifies whether to include local properties in the count (optional, default is `true`).
     * @return The number of properties in the declaration.
     */
    fun numProperties(includeNested: Boolean = true, includeLocal: Boolean = true): Int

    /**
     * Gets the number of properties that satisfies the specified predicate present in the declaration.
     *
     * @param includeNested Specifies whether to include nested properties in the count (optional, default is `true`).
     * @param includeLocal Specifies whether to include local properties in the count (optional, default is `true`).
     * @param predicate The predicate function to determine if a property satisfies a condition.
     * @return The number of properties in the declaration.
     */
    fun countProperties(
        includeNested: Boolean = true,
        includeLocal: Boolean = true,
        predicate: (KoPropertyDeclaration) -> Boolean,
    ): Int

    /**
     * Whether the declaration has properties.
     *
     * @return `true` if the declaration has any property, `false` otherwise.
     */
    fun hasProperties(
        includeNested: Boolean = true,
        includeLocal: Boolean = true,
    ): Boolean

    /**
     * Determines whether the declaration has at least one property whose name matches any of the specified names.
     *
     * @param name the name of the property to check.
     * @param names the names of the properties to check.
     * @return `true` if there is a matching declaration, `false` otherwise.
     */
    fun hasPropertyWithName(
        name: String,
        vararg names: String,
        includeNested: Boolean = true,
        includeLocal: Boolean = true,
    ): Boolean

    /**
     * Determines whether the declaration has properties with all the specified names.
     *
     * @param name The name of the property to check.
     * @param names The names of the properties to check.
     * @return `true` if there are declarations with all the specified names, `false` otherwise.
     */
    fun hasPropertiesWithAllNames(
        name: String,
        vararg names: String,
        includeNested: Boolean = true,
        includeLocal: Boolean = true,
    ): Boolean

    /**
     * Determines whether the declaration has at least one property that satisfies the provided predicate.
     *
     * @param predicate A function that defines the condition to be met by a property declaration.
     * @return `true` if there is a matching declaration, `false` otherwise.
     */
    fun hasProperty(
        includeNested: Boolean = true,
        includeLocal: Boolean = true,
        predicate: (KoPropertyDeclaration) -> Boolean,
    ): Boolean

    /**
     * Determines whether the declaration has all properties that satisfy the provided predicate.
     *
     * Note that if the properties contains no elements, the function returns `true` because there are no elements in it
     * that do not match the predicate. See a more detailed explanation of this logic concept in
     * ["Vacuous truth"](https://en.wikipedia.org/wiki/Vacuous_truth) article.
     *
     * @param predicate A function that defines the condition to be met by property declarations.
     * @return `true` if all property declarations satisfy the predicate, `false` otherwise.
     */
    fun hasAllProperties(
        includeNested: Boolean = true,
        includeLocal: Boolean = true,
        predicate: (KoPropertyDeclaration) -> Boolean,
    ): Boolean
}
