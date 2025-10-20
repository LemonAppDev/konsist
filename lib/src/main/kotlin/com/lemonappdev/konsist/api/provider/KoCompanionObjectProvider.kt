package com.lemonappdev.konsist.api.provider

import com.lemonappdev.konsist.api.declaration.KoCompanionObjectDeclaration

/**
 * An interface representing a Kotlin declaration that provides access to its companion object.
 */
interface KoCompanionObjectProvider : KoBaseProvider {
    /**
     * The companion object declared within this declaration, or `null` if none exists.
     */
    val companionObject: KoCompanionObjectDeclaration?

    /**
     * The companion objects of the declaration.
     *
     * @param includeNested Specifies whether to include nested companion objects in the count (optional, default is `true`).
     * @return a list of [KoCompanionObjectDeclaration] representing the companion objects of the declaration.
     */
    fun companionObjects(includeNested: Boolean = true): List<KoCompanionObjectDeclaration>

    /**
     * Returns the number of companion objects.
     *
     * @param includeNested Specifies whether to include nested companion objects in the count (optional, default is `true`).
     * @return The number of companion objects.
     */
    fun numCompanionObjects(includeNested: Boolean = true): Int

    /**
     * Returns the number of companion objects that satisfies the specified predicate present in the declaration.
     *
     * @param includeNested Specifies whether to include nested companion objects in the count (optional, default is `true`).
     * @param predicate The predicate function to determine if a companion object satisfies a condition.
     * @return The number of companion objects in the declaration satisfying predicate.
     */
    fun countCompanionObjects(
        includeNested: Boolean = true,
        predicate: (KoCompanionObjectDeclaration) -> Boolean,
    ): Int

    /**
     * Determines whether the declaration has a direct companion object.
     *
     * @return `true` if the declaration has any direct companion object, `false` otherwise.
     */
    fun hasCompanionObject(): Boolean

    /**
     * Determines whatever declaration has any companion object.
     *
     * @param includeNested Specifies whether to include nested companion objects in the count (optional, default is `true`).
     * @return `true` if the declaration has any companion object, `false` otherwise.
     */
    fun hasCompanionObjects(includeNested: Boolean = true): Boolean

    /**
     * Determines whether the declaration has a specified companion object.
     *
     * @param includeNested Specifies whether to include nested companion objects in the count (optional, default is `true`).
     * @param predicate A function that defines the condition to be met by a companion object.
     * @return `true` if there is a matching declaration, `false` otherwise.
     */
    fun hasCompanionObject(
        includeNested: Boolean = true,
        predicate: (KoCompanionObjectDeclaration) -> Boolean,
    ): Boolean

    /**
     * Determines whether the declaration has all companion objects that satisfy the provided predicate.
     *
     * Note that if the companion objects contain no elements, the function returns `true` because there are no elements in it
     * that do not match the predicate.
     *
     * @param includeNested Specifies whether to include nested companion objects in the count (optional, default is `true`).
     * @param predicate A function that defines the condition to be met by companion objects.
     * @return `true` if all companion objects satisfy the predicate, `false` otherwise.
     */
    fun hasAllCompanionObjects(
        includeNested: Boolean = true,
        predicate: (KoCompanionObjectDeclaration) -> Boolean,
    ): Boolean

    /**
     * Determines whether the declaration has a companion object whose name matches any of the specified names.
     *
     * @param name the name of the companion object to check.
     * @param names the names of the companion objects to check.
     * @param includeNested Specifies whether to include nested companion objects in the count (optional, default is `true`).
     * @param ignoreCase Specifies whether the comparison should ignore case.
     *        If `true`, the prefix comparison will be case-insensitive.
     *        If `false`, the comparison will consider case sensitivity.
     * @return `true` if there is a matching declaration, `false` otherwise.
     */
    fun hasCompanionObjectWithName(
        name: String,
        vararg names: String,
        includeNested: Boolean = true,
        ignoreCase: Boolean = false,
    ): Boolean

    /**
     * Determines whether the declaration has a companion object whose name matches any of the specified names.
     *
     * @param names the names of the companion objects to check.
     * @param includeNested Specifies whether to include nested companion objects in the count (optional, default is `true`).
     * @param ignoreCase Specifies whether the comparison should ignore case.
     *        If `true`, the prefix comparison will be case-insensitive.
     *        If `false`, the comparison will consider case sensitivity.
     * @return `true` if there is a matching declaration, `false` otherwise.
     */
    fun hasCompanionObjectWithName(
        names: Collection<String>,
        includeNested: Boolean = true,
        ignoreCase: Boolean = false,
    ): Boolean

    /**
     * Determines whether the declaration has companion objects with all the specified names.
     *
     * @param name The name of the companion object to check.
     * @param names The names of the companion objects to check.
     * @param includeNested Specifies whether to include nested companion objects in the count (optional, default is `true`).
     * @param ignoreCase Specifies whether the comparison should ignore case.
     *        If `true`, the prefix comparison will be case-insensitive.
     *        If `false`, the comparison will consider case sensitivity.
     * @return `true` if there are declarations with all the specified names, `false` otherwise.
     */
    fun hasCompanionObjectsWithAllNames(
        name: String,
        vararg names: String,
        includeNested: Boolean = true,
        ignoreCase: Boolean = false,
    ): Boolean

    /**
     * Determines whether the declaration has companion objects with all the specified names.
     *
     * @param names The names of the companion objects to check.
     * @param includeNested Specifies whether to include nested companion objects in the count (optional, default is `true`).
     * @param ignoreCase Specifies whether the comparison should ignore case.
     *        If `true`, the prefix comparison will be case-insensitive.
     *        If `false`, the comparison will consider case sensitivity.
     * @return `true` if there are declarations with all the specified names, `false` otherwise.
     */
    fun hasCompanionObjectsWithAllNames(
        names: Collection<String>,
        includeNested: Boolean = true,
        ignoreCase: Boolean = false,
    ): Boolean
}
