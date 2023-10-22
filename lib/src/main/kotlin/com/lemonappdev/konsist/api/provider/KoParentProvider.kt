package com.lemonappdev.konsist.api.provider

import com.lemonappdev.konsist.api.declaration.KoParentDeclaration
import kotlin.reflect.KClass

/**
 * An interface representing a Kotlin declaration that provides access to its parent declarations.
 */
interface KoParentProvider : KoBaseProvider {
    /**
     * The parents (parent class and parent interfaces) defined directly in the Kotlin file.
     * Does not include parents defined in other files such as parent of the parent.
     */
    val parents: List<KoParentDeclaration>

    /**
     * The number of parents.
     */
    val numParents: Int

    /**
     * Returns the number of parents that satisfies the specified predicate present in the declaration.
     *
     * @param predicate The predicate function to determine if a parent satisfies a condition.
     * @return The number of parents in the declaration satisfying predicate.
     */
    fun countParents(predicate: (KoParentDeclaration) -> Boolean): Int

    /**
     * Determines whatever declaration has parents (parent class and parent interfaces) defined directly in the Kotlin file.
     * Does not include parents defined in other files such as parent of the parent.
     *
     * @param names the names of the parents to check.
     * @return `true` if the declaration has parents with the specified names (or any parent if [names] is empty), `false` otherwise.
     */
    @Deprecated(
        """
            Will be removed in v1.0.0. 
            If you passed one argument - replace with `hasParentWithName`, otherwise with `hasParentsWithAllNames`.
            """,
    )
    fun hasParents(vararg names: String): Boolean

    /**
     * Determines whatever declaration has any parent (parent class and parent interfaces) defined directly in the Kotlin file.
     * Does not include parents defined in other files such as parent of the parent.
     *
     * @return `true` if the declaration has any parent, `false` otherwise.
     */
    fun hasParents(): Boolean

    /**
     * Determines whether the declaration has at least one parent (parent class and parent interfaces) defined directly
     * in the Kotlin file whose name matches any of the specified names.
     * This method does not include parents defined in other files, such as parents of the parent.
     *
     * @param name the name of the parent to check.
     * @param names the names of the parents to check.
     * @return `true` if there is a matching declaration, `false` otherwise.
     */
    fun hasParentWithName(name: String, vararg names: String): Boolean

    /**
     * Determines whether the declaration has parents (parent classes and parent interfaces) defined directly in the Kotlin
     * file with all the specified names.
     * This method does not include parents defined in other files, such as parents of the parent.
     *
     * @param name The name of the parent to check.
     * @param names The names of the parents to check.
     * @return `true` if there are declarations with all the specified names, `false` otherwise.
     */
    fun hasParentsWithAllNames(name: String, vararg names: String): Boolean

    /**
     * Determines whether the declaration has at least one parent (parent class or parent interface) defined directly
     * in the Kotlin file that satisfies the provided predicate.
     * This method does not include parents defined in other files, such as parents of the parent.
     *
     * @param predicate A function that defines the condition to be met by a parent declaration.
     * @return `true` if there is a matching declaration, `false` otherwise.
     */
    fun hasParent(predicate: (KoParentDeclaration) -> Boolean): Boolean

    /**
     * Determines whether the declaration has all parents (parent classes and parent interfaces) defined directly
     * in the Kotlin file that satisfy the provided predicate.
     * This method does not include parents defined in other files, such as parents of the parent.
     *
     * Note that if the parents contains no elements, the function returns `true` because there are no elements in it
     * that do not match the predicate.
     *
     * @param predicate A function that defines the condition to be met by parent declarations.
     * @return `true` if all parent declarations satisfy the predicate, `false` otherwise.
     */
    fun hasAllParents(predicate: (KoParentDeclaration) -> Boolean): Boolean

    /**
     * Determines whether the declaration has at least one parent of the specified `KClass` type.
     *
     * @param name the `KClass` type of the parent to check.
     * @param names the `KClass` types of the parents to check.
     * @return `true` if there is a matching declaration, `false` otherwise.
     */
    fun hasParentOf(name: KClass<*>, vararg names: KClass<*>): Boolean

    /**
     * Determines whether the declaration has parents with all the specified `KClass` type.
     *
     * @param name the `KClass` type of the parent to check.
     * @param names the `KClass` types of the parents to check.
     * @return `true` if the declaration has parents of all the specified `KClass` types, `false` otherwise.
     */
    fun hasAllParentsOf(name: KClass<*>, vararg names: KClass<*>): Boolean
}
