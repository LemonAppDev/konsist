package com.lemonappdev.konsist.api.provider

import com.lemonappdev.konsist.api.declaration.KoInterfaceDeclaration

/**
 * An interface representing a Kotlin declaration that provides information about interfaces.
 */
interface KoInterfaceProvider : KoBaseProvider {
    /**
     * The interfaces present in the declaration.
     *
     * @param includeNested specifies whether to include nested interfaces.
     * @return a list of [KoInterfaceDeclaration] representing the interfaces in the declaration.
     */
    fun interfaces(includeNested: Boolean = true): List<KoInterfaceDeclaration>

    /**
     * Determines whatever the declaration contains an interface that satisfies the specified predicate.
     *
     * @param includeNested Specifies whether to include nested interfaces in the check (optional, default is `true`).
     * @param predicate The predicate function to determine if an interface satisfies a condition.
     * @return `true` if the declaration contains an interface with the specified predicate, `true` otherwise.
     */
    @Deprecated("Will be removed in v0.16.0", ReplaceWith("hasInterface()"))
    fun containsInterface(
        includeNested: Boolean = true,
        predicate: (KoInterfaceDeclaration) -> Boolean,
    ): Boolean

    /**
     * Returns the number of interfaces present in the declaration.
     *
     * @param includeNested Specifies whether to include nested interfaces in the count (optional, default is `true`).
     * @return The number of interfaces in the declaration.
     */
    fun numInterfaces(includeNested: Boolean = true): Int

    /**
     * Returns the number of interfaces that satisfies the specified predicate present in the declaration.
     *
     * @param includeNested Specifies whether to include nested interfaces in the count (optional, default is `true`).
     * @param predicate The predicate function to determine if an interface satisfies a condition.
     * @return The number of interfaces in the declaration.
     */
    fun countInterfaces(
        includeNested: Boolean = true,
        predicate: (KoInterfaceDeclaration) -> Boolean,
    ): Int

    /**
     * Determines whatever the declaration has interfaces.
     *
     * @param includeNested Specifies whether to include nested interfaces in the check (optional, default is `true`).
     * @return `true` if the declaration has any interface, `false` otherwise.
     */
    fun hasInterfaces(includeNested: Boolean = true): Boolean

    /**
     * Determines whether the declaration has at least one interface whose name matches any of the specified names.
     *
     * @param name the name of the interface to check.
     * @param names the names of the interfaces to check.
     * @param includeNested Specifies whether to include nested interfaces in the check (optional, default is `true`).
     * @return `true` if there is a matching declaration, `false` otherwise.
     */
    fun hasInterfaceWithName(
        name: String,
        vararg names: String,
        includeNested: Boolean = true,
    ): Boolean

    /**
     * Determines whether the declaration has interfaces with all the specified names.
     *
     * @param name The name of the interface to check.
     * @param names The names of the interfaces to check.
     * @param includeNested Specifies whether to include nested interfaces in the check (optional, default is `true`).
     * @return `true` if there are declarations with all the specified names, `false` otherwise.
     */
    fun hasInterfacesWithAllNames(
        name: String,
        vararg names: String,
        includeNested: Boolean = true,
    ): Boolean

    /**
     * Determines whether the declaration has at least one interface that satisfies the provided predicate.
     *
     * @param includeNested Specifies whether to include nested interfaces in the check (optional, default is `true`).
     * @param predicate An interface that defines the condition to be met by a interface declaration.
     * @return `true` if there is a matching declaration, `false` otherwise.
     */
    fun hasInterface(
        includeNested: Boolean = true,
        predicate: (KoInterfaceDeclaration) -> Boolean,
    ): Boolean

    /**
     * Determines whether the declaration has all interfaces that satisfy the provided predicate.
     *
     * Note that if the interfaces contains no elements, the interface returns `true` because there are no elements in it
     * that do not match the predicate.
     *
     * @param includeNested Specifies whether to include nested interfaces in the check (optional, default is `true`).
     * @param predicate An interface that defines the condition to be met by interface declarations.
     * @return `true` if all interface declarations satisfy the predicate, `false` otherwise.
     */
    fun hasAllInterfaces(
        includeNested: Boolean = true,
        predicate: (KoInterfaceDeclaration) -> Boolean,
    ): Boolean
}
