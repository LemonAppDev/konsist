package com.lemonappdev.konsist.api.provider

import com.lemonappdev.konsist.api.declaration.combined.KoClassAndInterfaceDeclaration

/**
 * An interface representing a Kotlin declaration that provides information about classes and interfaces.
 */
interface KoClassAndInterfaceProvider : KoBaseProvider {
    /**
     * The classes and interfaces present in the declaration.
     *
     * @param includeNested specifies whether to include nested classes and interfaces.
     * @param includeLocal specifies whether to include local classes and interfaces.
     * @return a list of [KoClassAndInterfaceDeclaration] representing the classes and interfaces in the declaration.
     */
    fun classesAndInterfaces(
        includeNested: Boolean = true,
        includeLocal: Boolean = true,
    ): List<KoClassAndInterfaceDeclaration>

    /**
     * Returns the number of classes and interfaces present in the declaration.
     *
     * @param includeNested Specifies whether to include nested classes and interfaces in the count (optional, default is `true`).
     * @param includeLocal Specifies whether to include local classes in the count (optional, default is `true`).
     * @return The number of classes and interfaces in the declaration.
     */
    fun numClassesAndInterfaces(
        includeNested: Boolean = true,
        includeLocal: Boolean = true,
    ): Int

    /**
     * Returns the number of classes and interfaces that satisfies the specified predicate present in the declaration.
     *
     * @param includeNested Specifies whether to include nested classes and interfaces in the count (optional, default is `true`).
     * @param includeLocal Specifies whether to include local classes in the count (optional, default is `true`).
     * @param predicate The predicate function to determine if a class or interface satisfies a condition.
     * @return The number of classes and interfaces in the declaration.
     */
    fun countClassesAndInterfaces(
        includeNested: Boolean = true,
        includeLocal: Boolean = true,
        predicate: (KoClassAndInterfaceDeclaration) -> Boolean,
    ): Int

    /**
     * Determines whatever the declaration has classes and interfaces.
     *
     * @param includeNested Specifies whether to include nested classes and interfaces in the check (optional, default is `true`).
     * @param includeLocal Specifies whether to include local classes in the check (optional, default is `true`).
     * @return `true` if the declaration has any class or interface, `false` otherwise.
     */
    fun hasClassesOrInterfaces(
        includeNested: Boolean = true,
        includeLocal: Boolean = true,
    ): Boolean

    /**
     * Determines whether the declaration has at least one class or interface whose name matches any of the specified names.
     *
     * @param name the name of the class or interface to check.
     * @param names the names of the classes and interfaces to check.
     * @param includeNested Specifies whether to include nested classes and interfaces in the check (optional, default is `true`).
     * @param includeLocal Specifies whether to include local classes in the check (optional, default is `true`).
     * @return `true` if there is a matching declaration, `false` otherwise.
     */
    fun hasClassOrInterfaceWithName(
        name: String,
        vararg names: String,
        includeNested: Boolean = true,
        includeLocal: Boolean = true,
    ): Boolean

    /**
     * Determines whether the declaration has at least one class or interface whose name matches any of the specified names.
     *
     * @param names the names of the classes and interfaces to check.
     * @param includeNested Specifies whether to include nested classes and interfaces in the check (optional, default is `true`).
     * @param includeLocal Specifies whether to include local classes in the check (optional, default is `true`).
     * @return `true` if there is a matching declaration, `false` otherwise.
     */
    fun hasClassOrInterfaceWithName(
        names: Collection<String>,
        includeNested: Boolean = true,
        includeLocal: Boolean = true,
    ): Boolean

    /**
     * Determines whether the declaration has classes and interfaces with all the specified names.
     *
     * @param name The name of the class or interface to check.
     * @param names The names of the classes and interfaces to check.
     * @param includeNested Specifies whether to include nested classes and interfaces in the check (optional, default is `true`).
     * @param includeLocal Specifies whether to include local classes in the check (optional, default is `true`).
     * @return `true` if there are declarations with all the specified names, `false` otherwise.
     */
    fun hasClassesAndInterfacesWithAllNames(
        name: String,
        vararg names: String,
        includeNested: Boolean = true,
        includeLocal: Boolean = true,
    ): Boolean

    /**
     * Determines whether the declaration has classes and interfaces with all the specified names.
     *
     * @param names The names of the classes and interfaces to check.
     * @param includeNested Specifies whether to include nested classes and interfaces in the check (optional, default is `true`).
     * @param includeLocal Specifies whether to include local classes in the check (optional, default is `true`).
     * @return `true` if there are declarations with all the specified names, `false` otherwise.
     */
    fun hasClassesAndInterfacesWithAllNames(
        names: Collection<String>,
        includeNested: Boolean = true,
        includeLocal: Boolean = true,
    ): Boolean

    /**
     * Determines whether the declaration has at least one class or interface that satisfies the provided predicate.
     *
     * @param includeNested Specifies whether to include nested classes and interfaces in the check (optional, default is `true`).
     * @param includeLocal Specifies whether to include local classes in the check (optional, default is `true`).
     * @param predicate A function that defines the condition to be met by a class or interface declaration.
     * @return `true` if there is a matching declaration, `false` otherwise.
     */
    fun hasClassOrInterface(
        includeNested: Boolean = true,
        includeLocal: Boolean = true,
        predicate: (KoClassAndInterfaceDeclaration) -> Boolean,
    ): Boolean

    /**
     * Determines whether the declaration has all classes and interfaces that satisfy the provided predicate.
     *
     * Note that if the classes and interfaces contains no elements, the function returns `true` because there are no elements in it
     * that do not match the predicate.
     *
     * @param includeNested Specifies whether to include nested classes and interfaces in the check (optional, default is `true`).
     * @param includeLocal Specifies whether to include local classes in the check (optional, default is `true`).
     * @param predicate A function that defines the condition to be met by class or interface declarations.
     * @return `true` if all class or interface declarations satisfy the predicate, `false` otherwise.
     */
    fun hasAllClassesAndInterfaces(
        includeNested: Boolean = true,
        includeLocal: Boolean = true,
        predicate: (KoClassAndInterfaceDeclaration) -> Boolean,
    ): Boolean
}
