package com.lemonappdev.konsist.api.provider

import com.lemonappdev.konsist.api.declaration.KoExternalDeclaration
import kotlin.reflect.KClass

/**
 * An interface representing a Kotlin declaration that provides access to the external parents.
 * The external parent is a parent defined outside the project codebase (defined inside external library).
 */
interface KoExternalParentProvider : KoBaseProvider {
    /**
     * List containing external parents of the declaration.
     *
     * @param indirectParents specifies whether to include external parents defined in other files such as parent of the parent.
     *                        If `true`, it includes only those external parents defined within our scope and those used
     *                        by our declarations.
     *                        For example:
     *
     *                        // Android
     *                        class AppCompactActivity: Activity
     *                        interface Activity
     *
     *                        // Project
     *                        class BaseActivity: AppCompactActivity() // externalParents(indirectParents = true) returns [AppCompactActivity]
     *                        class MyActivity: BaseActivity() // externalParents(indirectParents = true) returns [AppCompactActivity]
     * @return a list of [KoExternalDeclaration] representing the external parents of the declaration.
     * @see com.lemonappdev.konsist.api.declaration.KoExternalDeclaration
     */
    fun externalParents(indirectParents: Boolean = false): List<KoExternalDeclaration>

    /**
     * Returns the number of external parents.
     *
     * @param indirectParents specifies whether to include external parents defined in other files such as parent of the parent.
     *                        If `true`, it includes only those external parents defined within our scope and those used
     *                        by our declarations.
     *                        For example:
     *
     *                        // Android
     *                        class AppCompactActivity: Activity
     *                        interface Activity
     *
     *                        // Project
     *                        class BaseActivity: AppCompactActivity() // externalParents(indirectParents = true) returns [AppCompactActivity]
     *                        class MyActivity: BaseActivity() // externalParents(indirectParents = true) returns [AppCompactActivity]
     * @return The number of external parents.
     * @see com.lemonappdev.konsist.api.declaration.KoExternalDeclaration
     */
    fun numExternalParents(indirectParents: Boolean = false): Int

    /**
     * Returns the number of external parents that satisfies the specified predicate present in the declaration.
     *
     * @param indirectParents specifies whether to include external parents defined in other files such as parent of the parent.
     *                        If `true`, it includes only those external parents defined within our scope and those used
     *                        by our declarations.
     *                        For example:
     *
     *                        // Android
     *                        class AppCompactActivity: Activity
     *                        interface Activity
     *
     *                        // Project
     *                        class BaseActivity: AppCompactActivity() // externalParents(indirectParents = true) returns [AppCompactActivity]
     *                        class MyActivity: BaseActivity() // externalParents(indirectParents = true) returns [AppCompactActivity]
     * @param predicate The predicate function to determine if an external parent satisfies a condition.
     * @return The number of external parents in the declaration satisfying predicate.
     * @see com.lemonappdev.konsist.api.declaration.KoExternalDeclaration
     */
    fun countExternalParents(
        indirectParents: Boolean = false,
        predicate: (KoExternalDeclaration) -> Boolean,
    ): Int

    /**
     * Determines whatever declaration has any external parents. The external parent is a parent defined outside
     * project codebase (defined inside external library).
     *
     * @param indirectParents specifies whether to include external parents defined in other files such as parent of the parent.
     *                        If `true`, it includes only those external parents defined within our scope and those used
     *                        by our declarations.
     *                        For example:
     *
     *                        // Android
     *                        class AppCompactActivity: Activity
     *                        interface Activity
     *
     *                        // Project
     *                        class BaseActivity: AppCompactActivity() // externalParents(indirectParents = true) returns [AppCompactActivity]
     *                        class MyActivity: BaseActivity() // externalParents(indirectParents = true) returns [AppCompactActivity]
     * @return `true` if the declaration has any external parent, `false` otherwise.
     * @see com.lemonappdev.konsist.api.declaration.KoExternalDeclaration
     */
    fun hasExternalParents(indirectParents: Boolean = false): Boolean

    /**
     * Determines whether the declaration has at least one external parent whose name matches any of the specified.
     *
     * @param name the name of the external parent to check.
     * @param names the names of the external parents to check.
     * @param indirectParents specifies whether to include external parents defined in other files such as parent of the parent.
     *                        If `true`, it includes only those external parents defined within our scope and those used
     *                        by our declarations.
     *                        For example:
     *
     *                        // Android
     *                        class AppCompactActivity: Activity
     *                        interface Activity
     *
     *                        // Project
     *                        class BaseActivity: AppCompactActivity() // externalParents(indirectParents = true) returns [AppCompactActivity]
     *                        class MyActivity: BaseActivity() // externalParents(indirectParents = true) returns [AppCompactActivity]
     * @return `true` if there is a matching declaration, `false` otherwise.
     * @see com.lemonappdev.konsist.api.declaration.KoExternalDeclaration
     */
    fun hasExternalParentWithName(
        name: String,
        vararg names: String,
        indirectParents: Boolean = false,
    ): Boolean

    /**
     * Determines whether the declaration has parents interface defined project codebase (external == false)
     *
     * @param name The name of the external parent to check.
     * @param names The names of the external parents to check.
     * @param indirectParents specifies whether to include external parents defined in other files such as parent of the parent.
     *                        If `true`, it includes only those external parents defined within our scope and those used
     *                        by our declarations.
     *                        For example:
     *
     *                        // Android
     *                        class AppCompactActivity: Activity
     *                        interface Activity
     *
     *                        // Project
     *                        class BaseActivity: AppCompactActivity() // externalParents(indirectParents = true) returns [AppCompactActivity]
     *                        class MyActivity: BaseActivity() // externalParents(indirectParents = true) returns [AppCompactActivity]
     * @return `true` if there are declarations with all the specified names, `false` otherwise.
     * @see com.lemonappdev.konsist.api.declaration.KoExternalDeclaration
     */
    fun hasExternalParentsWithAllNames(
        name: String,
        vararg names: String,
        indirectParents: Boolean = false,
    ): Boolean

    /**
     * Determines whether the declaration has at least one external parent that satisfies the provided predicate.
     *
     * @param indirectParents specifies whether to include external parents defined in other files such as parent of the parent.
     *                        If `true`, it includes only those external parents defined within our scope and those used
     *                        by our declarations.
     *                        For example:
     *
     *                        // Android
     *                        class AppCompactActivity: Activity
     *                        interface Activity
     *
     *                        // Project
     *                        class BaseActivity: AppCompactActivity() // externalParents(indirectParents = true) returns [AppCompactActivity]
     *                        class MyActivity: BaseActivity() // externalParents(indirectParents = true) returns [AppCompactActivity]
     * @param predicate A function that defines the condition to be met by an external parent.
     * @return `true` if there is a matching declaration, `false` otherwise.
     * @see com.lemonappdev.konsist.api.declaration.KoExternalDeclaration
     */
    fun hasExternalParent(
        indirectParents: Boolean = false,
        predicate: (KoExternalDeclaration) -> Boolean,
    ): Boolean

    /**
     * Determines whether the declaration has all external parents that satisfy the provided predicate.
     *
     * Note that if the external parents contains no elements, the function returns `true` because there are no
     * elements in it that do not match the predicate.
     *
     * @param indirectParents specifies whether to include external parents defined in other files such as parent of the parent.
     *                        If `true`, it includes only those external parents defined within our scope and those used
     *                        by our declarations.
     *                        For example:
     *
     *                        // Android
     *                        class AppCompactActivity: Activity
     *                        interface Activity
     *
     *                        // Project
     *                        class BaseActivity: AppCompactActivity() // externalParents(indirectParents = true) returns [AppCompactActivity]
     *                        class MyActivity: BaseActivity() // externalParents(indirectParents = true) returns [AppCompactActivity]
     * @param predicate A function that defines the condition to be met by external parents.
     * @return `true` if all external parents satisfy the predicate, `false` otherwise.
     * @see com.lemonappdev.konsist.api.declaration.KoExternalDeclaration
     */
    fun hasAllExternalParents(
        indirectParents: Boolean = false,
        predicate: (KoExternalDeclaration) -> Boolean,
    ): Boolean

    /**
     * Determines whether the declaration has at least one external parent of the specified `KClass` type.
     *
     * @param name the `KClass` type of the external parent to check.
     * @param names the `KClass` types of the external parents to check.
     * @param indirectParents specifies whether to include external parents defined in other files such as parent of the parent.
     *                        If `true`, it includes only those external parents defined within our scope and those used
     *                        by our declarations.
     *                        For example:
     *
     *                        // Android
     *                        class AppCompactActivity: Activity
     *                        interface Activity
     *
     *                        // Project
     *                        class BaseActivity: AppCompactActivity() // externalParents(indirectParents = true) returns [AppCompactActivity]
     *                        class MyActivity: BaseActivity() // externalParents(indirectParents = true) returns [AppCompactActivity]
     * @return `true` if there is a matching declaration, `false` otherwise.
     * @see com.lemonappdev.konsist.api.declaration.KoExternalDeclaration
     */
    fun hasExternalParentOf(
        name: KClass<*>,
        vararg names: KClass<*>,
        indirectParents: Boolean = false,
    ): Boolean

    /**
     * Determines whether the declaration has external parents with all the specified `KClass` type.
     *
     * @param name the `KClass` type of the external parent to check.
     * @param names the `KClass` types of the external parents to check.
     * @param indirectParents specifies whether to include external parents defined in other files such as parent of the parent.
     *                        If `true`, it includes only those external parents defined within our scope and those used
     *                        by our declarations.
     *                        For example:
     *
     *                        // Android
     *                        class AppCompactActivity: Activity
     *                        interface Activity
     *
     *                        // Project
     *                        class BaseActivity: AppCompactActivity() // externalParents(indirectParents = true) returns [AppCompactActivity]
     *                        class MyActivity: BaseActivity() // externalParents(indirectParents = true) returns [AppCompactActivity]
     * @return `true` if the declaration has external parents of all the specified `KClass` types, `false` otherwise.
     * @see com.lemonappdev.konsist.api.declaration.KoExternalDeclaration
     */
    fun hasAllExternalParentsOf(
        name: KClass<*>,
        vararg names: KClass<*>,
        indirectParents: Boolean = false,
    ): Boolean
}
