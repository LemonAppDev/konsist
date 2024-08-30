package com.lemonappdev.konsist.api.provider

import com.lemonappdev.konsist.api.declaration.combined.KoClassAndObjectDeclaration

/**
 * An interface representing a Kotlin declaration that provides information about classes and objects.
 */
interface KoClassAndObjectProvider : KoBaseProvider {
    /**
     * The classes and objects present in the declaration.
     *
     * @param includeNested specifies whether to include nested classes and objects.
     * @param includeLocal specifies whether to include local classes.
     * @return a list of [KoClassAndObjectDeclaration] representing the classes and objects in the declaration.
     */
    fun classesAndObjects(
        includeNested: Boolean = true,
        includeLocal: Boolean = true,
    ): List<KoClassAndObjectDeclaration>

    /**
     * Returns the number of classes and objects present in the declaration.
     *
     * @param includeNested Specifies whether to include nested classes and objects in the count (optional, default is `true`).
     * @param includeLocal Specifies whether to include local classes in the count (optional, default is `true`).
     * @return The number of classes and objects in the declaration.
     */
    fun numClassesAndObjects(
        includeNested: Boolean = true,
        includeLocal: Boolean = true,
    ): Int

    /**
     * Returns the number of classes and objects that satisfies the specified predicate present in the declaration.
     *
     * @param includeNested Specifies whether to include nested classes and objects in the count (optional, default is `true`).
     * @param includeLocal Specifies whether to include local classes in the count (optional, default is `true`).
     * @param predicate The predicate function to determine if a class or object satisfies a condition.
     * @return The number of classes and objects in the declaration.
     */
    fun countClassesAndObjects(
        includeNested: Boolean = true,
        includeLocal: Boolean = true,
        predicate: (KoClassAndObjectDeclaration) -> Boolean,
    ): Int

    /**
     * Determines whatever the declaration has classes and objects.
     *
     * @param includeNested Specifies whether to include nested classes and objects in the check (optional, default is `true`).
     * @param includeLocal Specifies whether to include local classes in the check (optional, default is `true`).
     * @return `true` if the declaration has any class or object, `false` otherwise.
     */
    fun hasClassesOrObjects(
        includeNested: Boolean = true,
        includeLocal: Boolean = true,
    ): Boolean

    /**
     * Determines whether the declaration has at least one class or object whose name matches any of the specified names.
     *
     * @param name the name of the class or object to check.
     * @param names the names of the classes and objects to check.
     * @param includeNested Specifies whether to include nested classes and objects in the check (optional, default is `true`).
     * @param includeLocal Specifies whether to include local classes in the check (optional, default is `true`).
     * @return `true` if there is a matching declaration, `false` otherwise.
     */
    fun hasClassOrObjectWithName(
        name: String,
        vararg names: String,
        includeNested: Boolean = true,
        includeLocal: Boolean = true,
    ): Boolean

    /**
     * Determines whether the declaration has at least one class or object whose name matches any of the specified names.
     *
     * @param names the names of the classes and objects to check.
     * @param includeNested Specifies whether to include nested classes and objects in the check (optional, default is `true`).
     * @param includeLocal Specifies whether to include local classes in the check (optional, default is `true`).
     * @return `true` if there is a matching declaration, `false` otherwise.
     */
    fun hasClassOrObjectWithName(
        names: Collection<String>,
        includeNested: Boolean = true,
        includeLocal: Boolean = true,
    ): Boolean

    /**
     * Determines whether the declaration has classes and objects with all the specified names.
     *
     * @param name The name of the class or object to check.
     * @param names The names of the classes and objects to check.
     * @param includeNested Specifies whether to include nested classes and objects in the check (optional, default is `true`).
     * @param includeLocal Specifies whether to include local classes in the check (optional, default is `true`).
     * @return `true` if there are declarations with all the specified names, `false` otherwise.
     */
    fun hasClassesAndObjectsWithAllNames(
        name: String,
        vararg names: String,
        includeNested: Boolean = true,
        includeLocal: Boolean = true,
    ): Boolean

    /**
     * Determines whether the declaration has classes and objects with all the specified names.
     *
     * @param names The names of the classes and objects to check.
     * @param includeNested Specifies whether to include nested classes and objects in the check (optional, default is `true`).
     * @param includeLocal Specifies whether to include local classes in the check (optional, default is `true`).
     * @return `true` if there are declarations with all the specified names, `false` otherwise.
     */
    fun hasClassesAndObjectsWithAllNames(
        names: Collection<String>,
        includeNested: Boolean = true,
        includeLocal: Boolean = true,
    ): Boolean

    /**
     * Determines whether the declaration has at least one class or object that satisfies the provided predicate.
     *
     * @param includeNested Specifies whether to include nested classes and objects in the check (optional, default is `true`).
     * @param includeLocal Specifies whether to include local classes in the check (optional, default is `true`).
     * @param predicate A function that defines the condition to be met by a class or object declaration.
     * @return `true` if there is a matching declaration, `false` otherwise.
     */
    fun hasClassOrObject(
        includeNested: Boolean = true,
        includeLocal: Boolean = true,
        predicate: (KoClassAndObjectDeclaration) -> Boolean,
    ): Boolean

    /**
     * Determines whether the declaration has all classes and objects that satisfy the provided predicate.
     *
     * Note that if the classes and objects contains no elements, the function returns `true` because there are no elements in it
     * that do not match the predicate.
     *
     * @param includeNested Specifies whether to include nested classes and objects in the check (optional, default is `true`).
     * @param includeLocal Specifies whether to include local classes in the check (optional, default is `true`).
     * @param predicate A function that defines the condition to be met by class or object declarations.
     * @return `true` if all class and object declarations satisfy the predicate, `false` otherwise.
     */
    fun hasAllClassesAndObjects(
        includeNested: Boolean = true,
        includeLocal: Boolean = true,
        predicate: (KoClassAndObjectDeclaration) -> Boolean,
    ): Boolean
}
