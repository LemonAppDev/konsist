package com.lemonappdev.konsist.api.provider

import com.lemonappdev.konsist.api.declaration.KoExternalParentDeclaration
import kotlin.reflect.KClass

/**
 * An interface representing a Kotlin declaration that provides access to the external parents.
 * The external parent is a parent defined outside the project codebase (defined inside external library).
 */
interface KoExternalParentProvider : KoBaseProvider {
    /**
     * List content external parents of the declaration.
     *
     * @see com.lemonappdev.konsist.api.declaration.KoExternalParentDeclaration
     */
    val externalParents: List<KoExternalParentDeclaration>

    /**
     * Returns the number of external parents.
     */
    val numExternalParents: Int

    /**
     * Returns the number of external parents that satisfies the specified predicate present in the declaration.
     *
     * @param predicate The predicate function to determine if a external parent satisfies a condition.
     * @return The number of external parents in the declaration satisfying predicate.
     * @see com.lemonappdev.konsist.api.declaration.KoExternalParentDeclaration
     */
    fun countExternalParents(predicate: (KoExternalParentDeclaration) -> Boolean): Int

    /**
     * Determines whatever declaration has any external parents. The external parent is a parent defined outside
     * project codebase (defined inside external library).
     *
     * @return `true` if the declaration has any external parent, `false` otherwise.
     * @see com.lemonappdev.konsist.api.declaration.KoExternalParentDeclaration
     */
    fun hasExternalParents(): Boolean

    /**
     * Determines whether the declaration has at least one external parent whose name matches any of the specified
     *
     * @param name the name of the external parent to check.
     * @param names the names of the external parents to check.
     * @return `true` if there is a matching declaration, `false` otherwise.
     * @see com.lemonappdev.konsist.api.declaration.KoExternalParentDeclaration
     */
    fun hasExternalParentWithName(name: String, vararg names: String): Boolean

    /**
     * Determines whether the declaration has parents interface defined project codebase (external == false)
     *
     * @param name The name of the external parent to check.
     * @param names The names of the external parents to check.
     * @return `true` if there are declarations with all the specified names, `false` otherwise.
     * @see com.lemonappdev.konsist.api.declaration.KoExternalParentDeclaration
     */
    fun hasExternalParentsWithAllNames(name: String, vararg names: String): Boolean

    /**
     * Determines whether the declaration has at least one external parent that satisfies the provided predicate.
     *
     * @param predicate A function that defines the condition to be met by a external parent.
     * @return `true` if there is a matching declaration, `false` otherwise.
     * @see com.lemonappdev.konsist.api.declaration.KoExternalParentDeclaration
     */
    fun hasExternalParent(predicate: (KoExternalParentDeclaration) -> Boolean): Boolean

    /**
     * Determines whether the declaration has all external parents that satisfy the provided predicate.
     *
     * Note that if the external parents contains no elements, the function returns `true` because there are no
     * elements in it that do not match the predicate.
     *
     * @param predicate A function that defines the condition to be met by external parents.
     * @return `true` if all external parents satisfy the predicate, `false` otherwise.
     * @see com.lemonappdev.konsist.api.declaration.KoExternalParentDeclaration
     */
    fun hasAllExternalParents(predicate: (KoExternalParentDeclaration) -> Boolean): Boolean

    /**
     * Determines whether the declaration has at least one external parent of the specified `KClass` type.
     *
     * @param name the `KClass` type of the external parent to check.
     * @param names the `KClass` types of the external parents to check.
     * @return `true` if there is a matching declaration, `false` otherwise.
     * @see com.lemonappdev.konsist.api.declaration.KoExternalParentDeclaration
     */
    fun hasExternalParentOf(name: KClass<*>, vararg names: KClass<*>): Boolean

    /**
     * Determines whether the declaration has external parents with all the specified `KClass` type.
     *
     * @param name the `KClass` type of the external parent to check.
     * @param names the `KClass` types of the external parents to check.
     * @return `true` if the declaration has external parents of all the specified `KClass` types, `false` otherwise.
     * @see com.lemonappdev.konsist.api.declaration.KoExternalParentDeclaration
     */
    fun hasAllExternalParentsOf(name: KClass<*>, vararg names: KClass<*>): Boolean
}
