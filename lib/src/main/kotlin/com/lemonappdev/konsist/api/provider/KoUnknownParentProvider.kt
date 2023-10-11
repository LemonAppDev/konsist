package com.lemonappdev.konsist.api.provider

import com.lemonappdev.konsist.api.declaration.KoParentDeclaration
import kotlin.reflect.KClass

/**
 * An interface representing a Kotlin declaration that provides access to its unknown parents.
 */
interface KoUnknownParentProvider : KoBaseProvider {
    /**
     * The unknown parents of the declaration.
     * Does not include unknown parents defined in other files such as parent of the parent.
     */
    val unknownParents: List<KoParentDeclaration>

//    /**
//     * The number of unknown parents.
//     */
//    val numUnknownParents: Int
//
//    /**
//     * Gets the number of unknown parents that satisfies the specified predicate present in the declaration.
//     *
//     * @param predicate The predicate function to determine if a unknown parent satisfies a condition.
//     * @return The number of unknown parents in the declaration satisfying predicate.
//     */
//    fun countUnknownParents(predicate: (KoParentDeclaration) -> Boolean): Int
//
//    /**
//     * Whatever declaration has any unknown parent defined directly in the Kotlin file.
//     * Does not include unknown parents defined in other files such as parent of the parent.
//     *
//     * @return `true` if the declaration has any unknown parent, `false` otherwise.
//     */
//    fun hasUnknownParents(): Boolean
//
//    /**
//     * Determines whether the declaration has at least one unknown parent defined directly
//     * in the Kotlin file whose name matches any of the specified names.
//     * This method does not include unknown parents defined in other files, such as parents of the parent.
//     *
//     * @param name the name of the unknown parent to check.
//     * @param names the names of the unknown parents to check.
//     * @return `true` if there is a matching declaration, `false` otherwise.
//     */
//    fun hasUnknownParentWithName(name: String, vararg names: String): Boolean
//
//    /**
//     * Determines whether the declaration has parents interface defined directly in the Kotlin
//     * file with all the specified names.
//     * This method does not include unknown parents defined in other files, such as parents of the parent.
//     *
//     * @param name The name of the unknown parent to check.
//     * @param names The names of the unknown parents to check.
//     * @return `true` if there are declarations with all the specified names, `false` otherwise.
//     */
//    fun hasUnknownParentsWithAllNames(name: String, vararg names: String): Boolean
//
//    /**
//     * Determines whether the declaration has at least one unknown parent defined directly
//     * in the Kotlin file that satisfies the provided predicate.
//     * This method does not include unknown parents defined in other files, such as parents of the parent.
//     *
//     * @param predicate A function that defines the condition to be met by a unknown parent.
//     * @return `true` if there is a matching declaration, `false` otherwise.
//     */
//    fun hasUnknownParent(predicate: (KoParentDeclaration) -> Boolean): Boolean
//
//    /**
//     * Determines whether the declaration has all unknown parents defined directly
//     * in the Kotlin file that satisfy the provided predicate.
//     * This method does not include unknown parents defined in other files, such as parents of the parent.
//     *
//     * Note that if the unknown parents contains no elements, the function returns `true` because there are no elements in it
//     * that do not match the predicate. See a more detailed explanation of this logic concept in
//     * ["Vacuous truth"](https://en.wikipedia.org/wiki/Vacuous_truth) article.
//     *
//     * @param predicate A function that defines the condition to be met by unknown parents.
//     * @return `true` if all unknown parents satisfy the predicate, `false` otherwise.
//     */
//    fun hasAllUnknownParents(predicate: (KoParentDeclaration) -> Boolean): Boolean
//
//    /**
//     * Determines whether the declaration has at least one unknown parent of the specified `KClass` type.
//     *
//     * @param name the `KClass` type of the unknown parent to check.
//     * @param names the `KClass` types of the unknown parents to check.
//     * @return `true` if there is a matching declaration, `false` otherwise.
//     */
//    fun hasUnknownParentOf(name: KClass<*>, vararg names: KClass<*>): Boolean
//
//    /**
//     * Determines whether the declaration has unknown parents with all the specified `KClass` type.
//     *
//     * @param name the `KClass` type of the unknown parent to check.
//     * @param names the `KClass` types of the unknown parents to check.
//     * @return `true` if the declaration has unknown parents of all the specified `KClass` types, `false` otherwise.
//     */
//    fun hasAllUnknownParentsOf(name: KClass<*>, vararg names: KClass<*>): Boolean
}
