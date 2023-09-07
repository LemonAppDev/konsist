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
    fun objects(
        includeNested: Boolean = true,
    ): List<KoObjectDeclaration>

    /**
     * Checks whether the declaration contains an object that satisfies the specified predicate.
     *
     * @param includeNested Specifies whether to include nested objects in the check (optional, default is `true`).
     * @param predicate The predicate function to determine if an object satisfies a condition.
     * @return `true` if the declaration contains an object with the specified predicate, `true` otherwise.
     */
    fun containsObject(
        includeNested: Boolean = true,
        predicate: (KoObjectDeclaration) -> Boolean,
    ): Boolean

    /**
     * Gets the number of objects present in the declaration.
     *
     * @param includeNested Specifies whether to include nested objects in the count (optional, default is `true`).
     * @return The number of objects in the declaration.
     */
    fun numObjects(includeNested: Boolean = true): Int

    /**
     * Gets the number of objects that satisfies the specified predicate present in the declaration.
     *
     * @param includeNested Specifies whether to include nested objects in the count (optional, default is `true`).
     * @param predicate The predicate function to determine if an object satisfies a condition.
     * @return The number of objects in the declaration.
     */
    fun countObjects(
        includeNested: Boolean = true,
        predicate: (KoObjectDeclaration) -> Boolean,
    ): Int
}
