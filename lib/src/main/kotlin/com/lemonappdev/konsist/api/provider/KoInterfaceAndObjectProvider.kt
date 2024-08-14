package com.lemonappdev.konsist.api.provider

import com.lemonappdev.konsist.api.declaration.combined.KoInterfaceAndObjectDeclaration

/**
 * An interface representing a Kotlin declaration that provides information about interfaces and objects.
 */
interface KoInterfaceAndObjectProvider : KoBaseProvider {
    /**
     * The interfaces and objects present in the declaration.
     *
     * @param includeNested specifies whether to include nested interfaces and objects.
     * @return a list of [KoInterfaceAndObjectDeclaration] representing the interfaces and objects in the declaration.
     */
    fun interfacesAndObjects(
        includeNested: Boolean = true,
    ): List<KoInterfaceAndObjectDeclaration>

    /**
     * Returns the number of interfaces and objects present in the declaration.
     *
     * @param includeNested Specifies whether to include nested interfaces and objects in the count (optional, default is `true`).
     * @return The number of interfaces and objects in the declaration.
     */
    fun numInterfacesAndObjects(
        includeNested: Boolean = true,
    ): Int

    /**
     * Returns the number of interfaces and objects that satisfies the specified predicate present in the declaration.
     *
     * @param includeNested Specifies whether to include nested interfaces and objects in the count (optional, default is `true`).
     * @param predicate The predicate function to determine if a interface or object satisfies a condition.
     * @return The number of interfaces and objects in the declaration.
     */
    fun countInterfacesAndObjects(
        includeNested: Boolean = true,
        predicate: (KoInterfaceAndObjectDeclaration) -> Boolean,
    ): Int

    /**
     * Determines whatever the declaration has interfaces and objects.
     *
     * @param includeNested Specifies whether to include nested interfaces and objects in the check (optional, default is `true`).
     * @return `true` if the declaration has any interface or object, `false` otherwise.
     */
    fun hasInterfacesOrObjects(
        includeNested: Boolean = true,
    ): Boolean

    /**
     * Determines whether the declaration has at least one interface or object whose name matches any of the specified names.
     *
     * @param name the name of the interface or object to check.
     * @param names the names of the interfaces and objects to check.
     * @param includeNested Specifies whether to include nested interfaces and objects in the check (optional, default is `true`).
     * @return `true` if there is a matching declaration, `false` otherwise.
     */
    fun hasInterfaceOrObjectWithName(
        name: String,
        vararg names: String,
        includeNested: Boolean = true,
    ): Boolean

    /**
     * Determines whether the declaration has at least one interface or object whose name matches any of the specified names.
     *
     * @param names the names of the interfaces and objects to check.
     * @param includeNested Specifies whether to include nested interfaces and objects in the check (optional, default is `true`).
     * @return `true` if there is a matching declaration, `false` otherwise.
     */
    fun hasInterfaceOrObjectWithName(
        names: Collection<String>,
        includeNested: Boolean = true,
    ): Boolean

    /**
     * Determines whether the declaration has interfaces and objects with all the specified names.
     *
     * @param name The name of the interface or object to check.
     * @param names The names of the interfaces and objects to check.
     * @param includeNested Specifies whether to include nested interfaces and objects in the check (optional, default is `true`).
     * @return `true` if there are declarations with all the specified names, `false` otherwise.
     */
    fun hasInterfacesAndObjectsWithAllNames(
        name: String,
        vararg names: String,
        includeNested: Boolean = true,
    ): Boolean

    /**
     * Determines whether the declaration has interfaces and objects with all the specified names.
     *
     * @param names The names of the interfaces and objects to check.
     * @param includeNested Specifies whether to include nested interfaces and objects in the check (optional, default is `true`).
     * @return `true` if there are declarations with all the specified names, `false` otherwise.
     */
    fun hasInterfacesAndObjectsWithAllNames(
        names: Collection<String>,
        includeNested: Boolean = true,
    ): Boolean

    /**
     * Determines whether the declaration has at least one interface or object that satisfies the provided predicate.
     *
     * @param includeNested Specifies whether to include nested interfaces and objects in the check (optional, default is `true`).
     * @param predicate A function that defines the condition to be met by a interface or object declaration.
     * @return `true` if there is a matching declaration, `false` otherwise.
     */
    fun hasInterfaceOrObject(
        includeNested: Boolean = true,
        predicate: (KoInterfaceAndObjectDeclaration) -> Boolean,
    ): Boolean

    /**
     * Determines whether the declaration has all interfaces and objects that satisfy the provided predicate.
     *
     * Note that if the interfaces and objects contains no elements, the function returns `true` because
     * there are no elements in it that do not match the predicate.
     *
     * @param includeNested Specifies whether to include nested interfaces and objects in the check (optional, default is `true`).
     * @param predicate A function that defines the condition to be met by interface or object declarations.
     * @return `true` if all interface or object declarations satisfy the predicate, `false` otherwise.
     */
    fun hasAllInterfacesAndObjects(
        includeNested: Boolean = true,
        predicate: (KoInterfaceAndObjectDeclaration) -> Boolean,
    ): Boolean
}
