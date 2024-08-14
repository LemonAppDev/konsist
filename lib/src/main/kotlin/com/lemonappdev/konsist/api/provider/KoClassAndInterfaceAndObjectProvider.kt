package com.lemonappdev.konsist.api.provider

import com.lemonappdev.konsist.api.declaration.combined.KoClassAndInterfaceAndObjectDeclaration

/**
 * An interface representing a Kotlin declaration that provides information about classes and interfaces.
 */
interface KoClassAndInterfaceAndObjectProvider : KoBaseProvider {
    /**
     * The classes and interfaces present in the declaration.
     *
     * @param includeNested specifies whether to include nested classes, interfaces and objects.
     * @param includeLocal specifies whether to include local classes, interfaces and objects.
     * @return a list of [KoClassAndInterfaceAndObjectDeclaration] representing the classes, interfaces and objects in the declaration.
     */
    fun classesAndInterfacesAndObjects(
        includeNested: Boolean = true,
        includeLocal: Boolean = true,
    ): List<KoClassAndInterfaceAndObjectDeclaration>

    /**
     * Returns the number of classes, interfaces and objects present in the declaration.
     *
     * @param includeNested Specifies whether to include nested classes, interfaces and objects in the count (optional, default is `true`).
     * @param includeLocal Specifies whether to include local classes in the count (optional, default is `true`).
     * @return The number of classes, interfaces and objects in the declaration.
     */
    fun numClassesAndInterfacesAndObjects(
        includeNested: Boolean = true,
        includeLocal: Boolean = true,
    ): Int

    /**
     * Returns the number of classes, interfaces and objects that satisfies the specified predicate present in the declaration.
     *
     * @param includeNested Specifies whether to include nested classes, interfaces and objects in the count (optional, default is `true`).
     * @param includeLocal Specifies whether to include local classes in the count (optional, default is `true`).
     * @param predicate The predicate function to determine if a class or interface satisfies a condition.
     * @return The number of classes, interfaces and objects in the declaration.
     */
    fun countClassesAndInterfacesAndObjects(
        includeNested: Boolean = true,
        includeLocal: Boolean = true,
        predicate: (KoClassAndInterfaceAndObjectDeclaration) -> Boolean,
    ): Int

    /**
     * Determines whatever the declaration has classes, interfaces and objects.
     *
     * @param includeNested Specifies whether to include nested classes, interfaces and objects in the check (optional, default is `true`).
     * @param includeLocal Specifies whether to include local classes in the check (optional, default is `true`).
     * @return `true` if the declaration has any class or interface, `false` otherwise.
     */
    fun hasClassesOrInterfacesOrObjects(
        includeNested: Boolean = true,
        includeLocal: Boolean = true,
    ): Boolean

    /**
     * Determines whether the declaration has at least one class or interface whose name matches any of the specified names.
     *
     * @param name the name of the class or interface to check.
     * @param names the names of the classes, interfaces and objects to check.
     * @param includeNested Specifies whether to include nested classes, interfaces and objects in the check (optional, default is `true`).
     * @param includeLocal Specifies whether to include local classes in the check (optional, default is `true`).
     * @return `true` if there is a matching declaration, `false` otherwise.
     */
    fun hasClassOrInterfaceOrObjectWithName(
        name: String,
        vararg names: String,
        includeNested: Boolean = true,
        includeLocal: Boolean = true,
    ): Boolean

    /**
     * Determines whether the declaration has at least one class or interface whose name matches any of the specified names.
     *
     * @param names the names of the classes, interfaces and objects to check.
     * @param includeNested Specifies whether to include nested classes, interfaces and objects in the check (optional, default is `true`).
     * @param includeLocal Specifies whether to include local classes in the check (optional, default is `true`).
     * @return `true` if there is a matching declaration, `false` otherwise.
     */
    fun hasClassOrInterfaceOrObjectWithName(
        names: Collection<String>,
        includeNested: Boolean = true,
        includeLocal: Boolean = true,
    ): Boolean

    /**
     * Determines whether the declaration has classes, interfaces and objects with all the specified names.
     *
     * @param name The name of the class or interface to check.
     * @param names The names of the classes, interfaces and objects to check.
     * @param includeNested Specifies whether to include nested classes, interfaces and objects in the check (optional, default is `true`).
     * @param includeLocal Specifies whether to include local classes in the check (optional, default is `true`).
     * @return `true` if there are declarations with all the specified names, `false` otherwise.
     */
    fun hasClassesAndInterfacesAndObjectsWithAllNames(
        name: String,
        vararg names: String,
        includeNested: Boolean = true,
        includeLocal: Boolean = true,
    ): Boolean

    /**
     * Determines whether the declaration has classes, interfaces and objects with all the specified names.
     *
     * @param names The names of the classes, interfaces and objects to check.
     * @param includeNested Specifies whether to include nested classes, interfaces and objects in the check (optional, default is `true`).
     * @param includeLocal Specifies whether to include local classes in the check (optional, default is `true`).
     * @return `true` if there are declarations with all the specified names, `false` otherwise.
     */
    fun hasClassesAndInterfacesAndObjectsWithAllNames(
        names: Collection<String>,
        includeNested: Boolean = true,
        includeLocal: Boolean = true,
    ): Boolean

    /**
     * Determines whether the declaration has at least one class or interface that satisfies the provided predicate.
     *
     * @param includeNested Specifies whether to include nested classes, interfaces and objects in the check (optional, default is `true`).
     * @param includeLocal Specifies whether to include local classes in the check (optional, default is `true`).
     * @param predicate A function that defines the condition to be met by a class or interface declaration.
     * @return `true` if there is a matching declaration, `false` otherwise.
     */
    fun hasClassOrInterfaceOrObject(
        includeNested: Boolean = true,
        includeLocal: Boolean = true,
        predicate: (KoClassAndInterfaceAndObjectDeclaration) -> Boolean,
    ): Boolean

    /**
     * Determines whether the declaration has all classes, interfaces and objects that satisfy the provided predicate.
     *
     * Note that if the classes, interfaces and objects contains no elements, the function returns `true` because
     * there are no elements in it that do not match the predicate.
     *
     * @param includeNested Specifies whether to include nested classes, interfaces and objects in the check (optional, default is `true`).
     * @param includeLocal Specifies whether to include local classes in the check (optional, default is `true`).
     * @param predicate A function that defines the condition to be met by class or interface declarations.
     * @return `true` if all class or interface declarations satisfy the predicate, `false` otherwise.
     */
    fun hasAllClassesAndInterfacesAndObjects(
        includeNested: Boolean = true,
        includeLocal: Boolean = true,
        predicate: (KoClassAndInterfaceAndObjectDeclaration) -> Boolean,
    ): Boolean
}
