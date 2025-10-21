package com.lemonappdev.konsist.api.provider

import com.lemonappdev.konsist.api.declaration.KoCompanionObjectDeclaration

/**
 * An interface representing a Kotlin declaration that provides access to its companion objects.
 *
 * A *companion object* is a special singleton object declared inside a declaration using
 * the `companion` keyword. Each declaration can contain **at most one direct companion object**,
 * but nested declarations may each have their own companion objects.
 *
 * The `includeNested` parameter in several functions determines whether companion objects
 * from nested declarations should be included in the results.
 *
 * ### Example
 * ```
 * class A {
 *     class B {
 *         companion object C { }
 *     }
 * }
 *
 * For class A:
 * companionObject               // null
 * companionObjects(false)       // []
 * companionObjects(true)        // [C]
 *
 * For class B:
 * companionObject               // C
 * companionObjects(false)       // [C]
 * companionObjects(true)        // [C]
 * ```
 */
interface KoCompanionObjectProvider : KoBaseProvider {
    /**
     * The direct companion object declared within this declaration, or `null` if none exists.
     *
     * A *direct* companion object is one declared in the declaration itself:
     * ```
     * class A {
     *     companion object { }
     * }
     * ```
     * Nested companion objects declared in inner declarations are **not** included.
     */
    val companionObject: KoCompanionObjectDeclaration?

    /**
     * Returns a list of companion objects declared within this declaration.
     *
     * @param includeNested Determines whether to include companion objects declared in nested declarations.
     *  - If `false`, returns only the *direct* companion object of this declaration (if any).
     *  - If `true`, also includes companion objects from all nested declarations.
     *
     * ### Example
     * ```
     * class A {
     *     class B { companion object AB { } }
     *     class C { companion object AC { } }
     * }
     *
     * For class A:
     * companionObjects(false) // []
     * companionObjects(true)  // [AB, AC]
     * ```
     *
     * @return A list of [KoCompanionObjectDeclaration] representing companion objects found in this declaration.
     */
    fun companionObjects(includeNested: Boolean = true): List<KoCompanionObjectDeclaration>

    /**
     * Returns the number of companion objects declared in this declaration.
     *
     * Behaves consistently with [companionObjects] — if [includeNested] is `true`,
     * nested companion objects from inner declarations are also counted.
     *
     * @param includeNested Whether to include nested companion objects.
     * @return The total number of companion objects, including nested ones if [includeNested] is `true`.
     */
    fun numCompanionObjects(includeNested: Boolean = true): Int

    /**
     * Returns the number of companion objects that satisfy the specified [predicate].
     *
     * Behaves consistently with [companionObjects]:
     * - When [includeNested] is `false`, checks only the direct companion object (if any).
     * - When [includeNested] is `true`, includes nested companion objects as well.
     *
     * @param includeNested Whether to include nested companion objects.
     * @param predicate A function defining the condition that each companion object must satisfy.
     * @return The number of companion objects matching the predicate.
     */
    fun countCompanionObjects(
        includeNested: Boolean = true,
        predicate: (KoCompanionObjectDeclaration) -> Boolean,
    ): Int

    /**
     * Determines whether this declaration has a direct companion object.
     *
     * Returns `true` only if a companion object is declared **directly** within this declaration.
     * Nested companion objects are not considered.
     *
     * @return `true` if this declaration directly contains a companion object, otherwise `false`.
     */
    fun hasCompanionObject(): Boolean

    /**
     * Determines whether this declaration has any companion objects.
     *
     * Behaves consistently with [companionObjects]:
     * - When [includeNested] is `false`, checks only for a direct companion object.
     * - When [includeNested] is `true`, also considers companion objects in nested declarations.
     *
     * @param includeNested Whether to include nested companion objects in the check.
     * @return `true` if this declaration has at least one companion object (direct or nested, depending on
     * [includeNested]), otherwise `false`.
     */
    fun hasCompanionObjects(includeNested: Boolean = true): Boolean

    /**
     * Determines whether the declaration has a companion object that satisfies the given [predicate].
     *
     * Behaves consistently with [companionObjects]:
     * - When [includeNested] is `false`, tests only the direct companion object.
     * - When [includeNested] is `true`, includes nested companion objects as well.
     *
     * @param includeNested Whether to include nested companion objects.
     * @param predicate A function that defines the condition to be met by a companion object.
     * @return `true` if a companion object satisfying the condition exists, `false` otherwise.
     */
    fun hasCompanionObject(
        includeNested: Boolean = true,
        predicate: (KoCompanionObjectDeclaration) -> Boolean,
    ): Boolean

    /**
     * Determines whether all companion objects of this declaration satisfy the given [predicate].
     *
     * If no companion objects are present (according to [includeNested]), this function returns `true`
     * because there are no elements that violate the predicate.
     *
     * Behaves consistently with [companionObjects]:
     * - When [includeNested] is `false`, tests only the direct companion object (if any).
     * - When [includeNested] is `true`, includes nested companion objects as well.
     *
     * @param includeNested Whether to include nested companion objects.
     * @param predicate The condition each companion object must satisfy.
     * @return `true` if all companion objects satisfy the [predicate] (or if none exist), otherwise `false`.
     */
    fun hasAllCompanionObjects(
        includeNested: Boolean = true,
        predicate: (KoCompanionObjectDeclaration) -> Boolean,
    ): Boolean

    /**
     * Determines whether this declaration has a companion object whose name matches any of the specified names.
     *
     * Behaves consistently with [companionObjects]:
     * - When [includeNested] is `false`, tests only the direct companion object.
     * - When [includeNested] is `true`, includes nested companion objects as well.
     *
     * @param name The name to check.
     * @param names Additional names to check.
     * @param includeNested Whether to include nested companion objects.
     * @param ignoreCase Whether name matching should ignore case.
     * @return `true` if a matching companion object exists, `false` otherwise.
     */
    fun hasCompanionObjectWithName(
        name: String,
        vararg names: String,
        includeNested: Boolean = true,
        ignoreCase: Boolean = false,
    ): Boolean

    /**
     * Determines whether this declaration has a companion object whose name matches any of the specified [names].
     *
     * Behaves consistently with [companionObjects]:
     * - When [includeNested] is `false`, tests only the direct companion object.
     * - When [includeNested] is `true`, includes nested companion objects as well.
     *
     * @param names The names to check.
     * @param includeNested Whether to include nested companion objects.
     * @param ignoreCase Whether name matching should ignore case.
     * @return `true` if a matching companion object exists, `false` otherwise.
     */
    fun hasCompanionObjectWithName(
        names: Collection<String>,
        includeNested: Boolean = true,
        ignoreCase: Boolean = false,
    ): Boolean

    /**
     * Determines whether this declaration has companion objects with all the specified names.
     *
     * Behaves consistently with [companionObjects]:
     * - When [includeNested] is `false`, tests only the direct companion object.
     * - When [includeNested] is `true`, includes nested companion objects as well.
     *
     * @param name The first name to check.
     * @param names Additional names to check.
     * @param includeNested Whether to include nested companion objects.
     * @param ignoreCase Whether name matching should ignore case.
     * @return `true` if all specified names are present among companion objects, `false` otherwise.
     */
    fun hasCompanionObjectsWithAllNames(
        name: String,
        vararg names: String,
        includeNested: Boolean = true,
        ignoreCase: Boolean = false,
    ): Boolean

    /**
     * Determines whether this declaration has companion objects with all the specified [names].
     *
     * Behaves consistently with [companionObjects]:
     * - When [includeNested] is `false`, tests only the direct companion object.
     * - When [includeNested] is `true`, includes nested companion objects as well.
     *
     * @param names The names to check.
     * @param includeNested Whether to include nested companion objects.
     * @param ignoreCase Whether name matching should ignore case.
     * @return `true` if all specified names are present among companion objects, `false` otherwise.
     */
    fun hasCompanionObjectsWithAllNames(
        names: Collection<String>,
        includeNested: Boolean = true,
        ignoreCase: Boolean = false,
    ): Boolean
}
