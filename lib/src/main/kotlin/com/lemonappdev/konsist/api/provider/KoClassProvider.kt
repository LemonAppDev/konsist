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
        includeNested: Boolean = true,
        includeLocal: Boolean = true,
    ): List<KoClassDeclaration>

    /**
     * Checks whether the declaration contains a class that satisfies the specified predicate.
     *
     * @param includeNested Specifies whether to include nested classes in the check (optional, default is `true`).
     * @param includeLocal Specifies whether to include local classes in the check (optional, default is `true`).
     * @param predicate The predicate function to determine if a class satisfies a condition.
     * @return `true` if the declaration contains a class with the specified predicate, `true` otherwise.
     */
    @Deprecated("Will be removed in v1.0.0", ReplaceWith("hasClass()"))
    fun containsClass(
        includeNested: Boolean = true,
        includeLocal: Boolean = true,
        predicate: (KoClassDeclaration) -> Boolean,
    ): Boolean

    /**
     * Gets the number of classes present in the declaration.
     *
     * @param includeNested Specifies whether to include nested classes in the count (optional, default is `true`).
     * @param includeLocal Specifies whether to include local classes in the count (optional, default is `true`).
     * @return The number of classes in the declaration.
     */
    fun numClasses(includeNested: Boolean = true, includeLocal: Boolean = true): Int

    /**
     * Gets the number of classes that satisfies the specified predicate present in the declaration.
     *
     * @param includeNested Specifies whether to include nested classes in the count (optional, default is `true`).
     * @param includeLocal Specifies whether to include local classes in the count (optional, default is `true`).
     * @param predicate The predicate function to determine if a class satisfies a condition.
     * @return The number of classes in the declaration.
     */
    fun countClasses(
        includeNested: Boolean = true,
        includeLocal: Boolean = true,
        predicate: (KoClassDeclaration) -> Boolean,
    ): Int

    /**
     * Whether the declaration has classes.
     *
     * @param includeNested Specifies whether to include nested classes in the check (optional, default is `true`).
     * @param includeLocal Specifies whether to include local classes in the check (optional, default is `true`).
     * @return `true` if the declaration has any class, `false` otherwise.
     */
    fun hasClasses(
        includeNested: Boolean = true,
        includeLocal: Boolean = true,
    ): Boolean

    /**
     * Determines whether the declaration has at least one class whose name matches any of the specified names.
     *
     * @param name the name of the class to check.
     * @param names the names of the classes to check.
     * @param includeNested Specifies whether to include nested classes in the check (optional, default is `true`).
     * @param includeLocal Specifies whether to include local classes in the check (optional, default is `true`).
     * @return `true` if there is a matching declaration, `false` otherwise.
     */
    fun hasClassWithName(
        name: String,
        vararg names: String,
        includeNested: Boolean = true,
        includeLocal: Boolean = true,
    ): Boolean

    /**
     * Determines whether the declaration has classes with all the specified names.
     *
     * @param name The name of the class to check.
     * @param names The names of the classes to check.
     * @param includeNested Specifies whether to include nested classes in the check (optional, default is `true`).
     * @param includeLocal Specifies whether to include local classes in the check (optional, default is `true`).
     * @return `true` if there are declarations with all the specified names, `false` otherwise.
     */
    fun hasClassesWithAllNames(
        name: String,
        vararg names: String,
        includeNested: Boolean = true,
        includeLocal: Boolean = true,
    ): Boolean

    /**
     * Determines whether the declaration has at least one class that satisfies the provided predicate.
     *
     * @param includeNested Specifies whether to include nested classes in the check (optional, default is `true`).
     * @param includeLocal Specifies whether to include local classes in the check (optional, default is `true`).
     * @param predicate A function that defines the condition to be met by a class declaration.
     * @return `true` if there is a matching declaration, `false` otherwise.
     */
    fun hasClass(
        includeNested: Boolean = true,
        includeLocal: Boolean = true,
        predicate: (KoClassDeclaration) -> Boolean,
    ): Boolean

    /**
     * Determines whether the declaration has all classes that satisfy the provided predicate.
     *
     * Note that if the classes contains no elements, the function returns `true` because there are no elements in it
     * that do not match the predicate. See a more detailed explanation of this logic concept in
     * ["Vacuous truth"](https://en.wikipedia.org/wiki/Vacuous_truth) article.
     *
     * @param includeNested Specifies whether to include nested classes in the check (optional, default is `true`).
     * @param includeLocal Specifies whether to include local classes in the check (optional, default is `true`).
     * @param predicate A function that defines the condition to be met by class declarations.
     * @return `true` if all class declarations satisfy the predicate, `false` otherwise.
     */
    fun hasAllClasses(
        includeNested: Boolean = true,
        includeLocal: Boolean = true,
        predicate: (KoClassDeclaration) -> Boolean,
    ): Boolean
}
