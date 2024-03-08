package com.lemonappdev.konsist.api.provider

import com.lemonappdev.konsist.api.declaration.KoParentDeclaration
import kotlin.reflect.KClass

/**
 * An interface representing a Kotlin declaration that provides access to its parent declarations.
 */
interface KoParentProvider : KoBaseProvider {
    /**
     * The parents (parent class and parent interfaces) defined directly in the Kotlin file.
     */
    @Deprecated("Will be removed in v0.16.0.", ReplaceWith("parents()"))
    val parents: List<KoParentDeclaration>

    /**
     * Gets the number of parents.
     */
    @Deprecated("Will be removed in v0.16.0.", ReplaceWith("numParents()"))
    val numParents: Int

    /**
     * The parents (parent class and parent interfaces) of the declaration.
     *
     * @param indirectParents specifies whether to include parents defined in other files such as parent of the parent.
     *                        If `true`, it includes only those parents defined within our scope and those used
     *                        by our declarations.
     *                        For example:
     *
     *                        // Android
     *                        class AppCompactActivity: Activity
     *                        interface Activity
     *
     *                        // Project
     *                        class BaseActivity: AppCompactActivity() // parents(indirectParents = true) returns [AppCompactActivity]
     *                        class MyActivity: BaseActivity() // parents(indirectParents = true) returns [BaseActivity, AppCompactActivity]
     * @return a list of [KoParentDeclaration] representing the parents of the declaration.
     */
    fun parents(indirectParents: Boolean = false): List<KoParentDeclaration>

    /**
     * Returns the number of parents.
     *
     * @param indirectParents specifies whether to include parents defined in other files such as parent of the parent.
     *                        If `true`, it includes only those parents defined within our scope and those used
     *                        by our declarations.
     *                        For example:
     *
     *                        // Android
     *                        class AppCompactActivity: Activity
     *                        interface Activity
     *
     *                        // Project
     *                        class BaseActivity: AppCompactActivity() // parents(indirectParents = true) returns [AppCompactActivity]
     *                        class MyActivity: BaseActivity() // parents(indirectParents = true) returns [BaseActivity, AppCompactActivity]
     * @return The number of parents.
     */
    fun numParents(indirectParents: Boolean = false): Int

    /**
     * Returns the number of parents that satisfies the specified predicate present in the declaration.
     *
     * @param indirectParents specifies whether to include parents defined in other files such as parent of the parent.
     *                        If `true`, it includes only those parents defined within our scope and those used
     *                        by our declarations.
     *                        For example:
     *
     *                        // Android
     *                        class AppCompactActivity: Activity
     *                        interface Activity
     *
     *                        // Project
     *                        class BaseActivity: AppCompactActivity() // parents(indirectParents = true) returns [AppCompactActivity]
     *                        class MyActivity: BaseActivity() // parents(indirectParents = true) returns [BaseActivity, AppCompactActivity]
     * @param predicate The predicate function to determine if a parent satisfies a condition.
     * @return The number of parents in the declaration satisfying predicate.
     */
    fun countParents(indirectParents: Boolean = false, predicate: (KoParentDeclaration) -> Boolean): Int

    /**
     * Determines whatever declaration has parents (parent class and parent interfaces) defined directly in the Kotlin file.
     * Does not include parents defined in other files such as parent of the parent.
     *
     * @param names the names of the parents to check.
     * @return `true` if the declaration has parents with the specified names (or any parent if [names] is empty), `false` otherwise.
     */
    @Deprecated(
        """
            Will be removed in v0.16.0. 
            If you passed one argument - replace with `hasParentWithName`, otherwise with `hasParentsWithAllNames`.
            """,
    )
    fun hasParents(vararg names: String): Boolean

    /**
     * Determines whatever declaration has any parent (parent class and parent interfaces).
     *
     * @param indirectParents specifies whether to include parents defined in other files such as parent of the parent.
     *                        If `true`, it includes only those parents defined within our scope and those used
     *                        by our declarations.
     *                        For example:
     *
     *                        // Android
     *                        class AppCompactActivity: Activity
     *                        interface Activity
     *
     *                        // Project
     *                        class BaseActivity: AppCompactActivity() // parents(indirectParents = true) returns [AppCompactActivity]
     *                        class MyActivity: BaseActivity() // parents(indirectParents = true) returns [BaseActivity, AppCompactActivity]
     * @return `true` if the declaration has any parent, `false` otherwise.
     */
    fun hasParents(indirectParents: Boolean = false): Boolean

    /**
     * Determines whether the declaration has at least one parent (parent class and parent interfaces) whose name
     * matches any of the specified names.
     *
     * @param name the name of the parent to check.
     * @param names the names of the parents to check.
     * @param indirectParents specifies whether to include parents defined in other files such as parent of the parent.
     *                        If `true`, it includes only those parents defined within our scope and those used
     *                        by our declarations.
     *                        For example:
     *
     *                        // Android
     *                        class AppCompactActivity: Activity
     *                        interface Activity
     *
     *                        // Project
     *                        class BaseActivity: AppCompactActivity() // parents(indirectParents = true) returns [AppCompactActivity]
     *                        class MyActivity: BaseActivity() // parents(indirectParents = true) returns [BaseActivity, AppCompactActivity]
     * @return `true` if there is a matching declaration, `false` otherwise.
     */
    fun hasParentWithName(name: String, vararg names: String, indirectParents: Boolean = false): Boolean

    /**
     * Determines whether the declaration has parents (parent classes and parent interfaces) with all the specified names.
     *
     * @param name The name of the parent to check.
     * @param names The names of the parents to check.
     * @param indirectParents specifies whether to include parents defined in other files such as parent of the parent.
     *                        If `true`, it includes only those parents defined within our scope and those used
     *                        by our declarations.
     *                        For example:
     *
     *                        // Android
     *                        class AppCompactActivity: Activity
     *                        interface Activity
     *
     *                        // Project
     *                        class BaseActivity: AppCompactActivity() // parents(indirectParents = true) returns [AppCompactActivity]
     *                        class MyActivity: BaseActivity() // parents(indirectParents = true) returns [BaseActivity, AppCompactActivity]
     * @return `true` if there are declarations with all the specified names, `false` otherwise.
     */
    fun hasParentsWithAllNames(name: String, vararg names: String, indirectParents: Boolean = false): Boolean

    /**
     * Determines whether the declaration has at least one parent (parent class or parent interface) that satisfies the
     * provided predicate.
     *
     * @param indirectParents specifies whether to include parents defined in other files such as parent of the parent.
     *                        If `true`, it includes only those parents defined within our scope and those used
     *                        by our declarations.
     *                        For example:
     *
     *                        // Android
     *                        class AppCompactActivity: Activity
     *                        interface Activity
     *
     *                        // Project
     *                        class BaseActivity: AppCompactActivity() // parents(indirectParents = true) returns [AppCompactActivity]
     *                        class MyActivity: BaseActivity() // parents(indirectParents = true) returns [BaseActivity, AppCompactActivity]
     * @param predicate A function that defines the condition to be met by a parent declaration.
     * @return `true` if there is a matching declaration, `false` otherwise.
     */
    fun hasParent(indirectParents: Boolean = false, predicate: (KoParentDeclaration) -> Boolean): Boolean

    /**
     * Determines whether the declaration has all parents (parent classes and parent interfaces) that satisfy the
     * provided predicate.
     *
     * Note that if the parents contains no elements, the function returns `true` because there are no elements in it
     * that do not match the predicate.
     *
     * @param indirectParents specifies whether to include parents defined in other files such as parent of the parent.
     *                        If `true`, it includes only those parents defined within our scope and those used
     *                        by our declarations.
     *                        For example:
     *
     *                        // Android
     *                        class AppCompactActivity: Activity
     *                        interface Activity
     *
     *                        // Project
     *                        class BaseActivity: AppCompactActivity() // parents(indirectParents = true) returns [AppCompactActivity]
     *                        class MyActivity: BaseActivity() // parents(indirectParents = true) returns [BaseActivity, AppCompactActivity]
     * @param predicate A function that defines the condition to be met by parent declarations.
     * @return `true` if all parent declarations satisfy the predicate, `false` otherwise.
     */
    fun hasAllParents(indirectParents: Boolean = false, predicate: (KoParentDeclaration) -> Boolean): Boolean

    /**
     * Determines whether the declaration has at least one parent of the specified `KClass` type.
     *
     * @param name the `KClass` type of the parent to check.
     * @param names the `KClass` types of the parents to check.
     * @param indirectParents specifies whether to include parents defined in other files such as parent of the parent.
     *                        If `true`, it includes only those parents defined within our scope and those used
     *                        by our declarations.
     *                        For example:
     *
     *                        // Android
     *                        class AppCompactActivity: Activity
     *                        interface Activity
     *
     *                        // Project
     *                        class BaseActivity: AppCompactActivity() // parents(indirectParents = true) returns [AppCompactActivity]
     *                        class MyActivity: BaseActivity() // parents(indirectParents = true) returns [BaseActivity, AppCompactActivity]
     * @return `true` if there is a matching declaration, `false` otherwise.
     */
    fun hasParentOf(name: KClass<*>, vararg names: KClass<*>, indirectParents: Boolean = false): Boolean

    /**
     * Determines whether the declaration has parents with all the specified `KClass` type.
     *
     * @param name the `KClass` type of the parent to check.
     * @param names the `KClass` types of the parents to check.
     * @param indirectParents specifies whether to include parents defined in other files such as parent of the parent.
     *                        If `true`, it includes only those parents defined within our scope and those used
     *                        by our declarations.
     *                        For example:
     *
     *                        // Android
     *                        class AppCompactActivity: Activity
     *                        interface Activity
     *
     *                        // Project
     *                        class BaseActivity: AppCompactActivity() // parents(indirectParents = true) returns [AppCompactActivity]
     *                        class MyActivity: BaseActivity() // parents(indirectParents = true) returns [BaseActivity, AppCompactActivity]
     * @return `true` if the declaration has parents of all the specified `KClass` types, `false` otherwise.
     */
    fun hasAllParentsOf(name: KClass<*>, vararg names: KClass<*>, indirectParents: Boolean = false): Boolean
}
