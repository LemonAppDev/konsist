package com.lemonappdev.konsist.api.provider

import com.lemonappdev.konsist.api.declaration.KoObjectDeclaration

/**
 * An interface representing a Kotlin declaration that provides information about objects.
 */
interface KoObjectProvider : KoBaseProvider {
    /**
     * The objects present in the declaration.
     *
     * @param includeNested specifies whether to include nested objects.
     * @return a list of [KoObjectDeclaration] representing the objects in the declaration.
     */
    fun objects(includeNested: Boolean = true): List<KoObjectDeclaration>

    /**
     * Returns the number of objects present in the declaration.
     *
     * @param includeNested Specifies whether to include nested objects in the count (optional, default is `true`).
     * @return The number of objects in the declaration.
     */
    fun numObjects(includeNested: Boolean = true): Int

    /**
     * Returns the number of objects that satisfies the specified predicate present in the declaration.
     *
     * @param includeNested Specifies whether to include nested objects in the count (optional, default is `true`).
     * @param predicate The predicate function to determine if an object satisfies a condition.
     * @return The number of objects in the declaration.
     */
    fun countObjects(
        includeNested: Boolean = true,
        predicate: (KoObjectDeclaration) -> Boolean,
    ): Int

    /**
     * Determines whatever the declaration has objects.
     *
     * @param includeNested Specifies whether to include nested objects in the check (optional, default is `true`).
     * @return `true` if the declaration has any object, `false` otherwise.
     */
    fun hasObjects(includeNested: Boolean = true): Boolean

    /**
     * Determines whether the declaration has at least one object whose name matches any of the specified names.
     *
     * @param name the name of the object to check.
     * @param names the names of the objects to check.
     * @param includeNested Specifies whether to include nested objects in the check (optional, default is `true`).
     * @return `true` if there is a matching declaration, `false` otherwise.
     */
    fun hasObjectWithName(
        name: String,
        vararg names: String,
        includeNested: Boolean = true,
    ): Boolean

    /**
     * Determines whether the declaration has at least one object whose name matches any of the specified names.
     *
     * @param names the names of the objects to check.
     * @param includeNested Specifies whether to include nested objects in the check (optional, default is `true`).
     * @return `true` if there is a matching declaration, `false` otherwise.
     */
    fun hasObjectWithName(
        names: Collection<String>,
        includeNested: Boolean = true,
    ): Boolean

    /**
     * Determines whether the declaration has objects with all the specified names.
     *
     * @param name The name of the object to check.
     * @param names The names of the objects to check.
     * @param includeNested Specifies whether to include nested objects in the check (optional, default is `true`).
     * @return `true` if there are declarations with all the specified names, `false` otherwise.
     */
    fun hasObjectsWithAllNames(
        name: String,
        vararg names: String,
        includeNested: Boolean = true,
    ): Boolean

    /**
     * Determines whether the declaration has objects with all the specified names.
     *
     * @param names The names of the objects to check.
     * @param includeNested Specifies whether to include nested objects in the check (optional, default is `true`).
     * @return `true` if there are declarations with all the specified names, `false` otherwise.
     */
    fun hasObjectsWithAllNames(
        names: Collection<String>,
        includeNested: Boolean = true,
    ): Boolean

    /**
     * Determines whether the declaration has at least one object that satisfies the provided predicate.
     *
     * @param includeNested Specifies whether to include nested objects in the check (optional, default is `true`).
     * @param predicate An object that defines the condition to be met by a object declaration.
     * @return `true` if there is a matching declaration, `false` otherwise.
     */
    fun hasObject(
        includeNested: Boolean = true,
        predicate: (KoObjectDeclaration) -> Boolean,
    ): Boolean

    /**
     * Determines whether the declaration has all objects that satisfy the provided predicate.
     *
     * Note that if the objects contains no elements, the object returns `true` because there are no elements in it
     * that do not match the predicate.
     *
     * @param includeNested Specifies whether to include nested objects in the check (optional, default is `true`).
     * @param predicate An object that defines the condition to be met by object declarations.
     * @return `true` if all object declarations satisfy the predicate, `false` otherwise.
     */
    fun hasAllObjects(
        includeNested: Boolean = true,
        predicate: (KoObjectDeclaration) -> Boolean,
    ): Boolean
}
