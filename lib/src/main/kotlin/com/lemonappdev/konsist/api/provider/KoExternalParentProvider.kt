package com.lemonappdev.konsist.api.provider

import com.lemonappdev.konsist.api.declaration.KoParentDeclaration

/**
 * An interface representing a Kotlin declaration that provides access to its external parents.
 */
interface KoExternalParentProvider : KoBaseProvider {
    /**
     * The external parents of the declaration.
     * Does not include external parents defined in other files such as parent of the parent.
     */
    val externalParents: List<KoParentDeclaration>

//    /**
//     * The number of external parents.
//     */
//    val numExternalParents: Int
//
//    /**
//     * Gets the number of external parents that satisfies the specified predicate present in the declaration.
//     *
//     * @param predicate The predicate function to determine if a external parent satisfies a condition.
//     * @return The number of external parents in the declaration satisfying predicate.
//     */
//    fun countExternalParents(predicate: (KoParentDeclaration) -> Boolean): Int
//
//    /**
//     * Whatever declaration has any external parent defined directly in the Kotlin file.
//     * Does not include external parents defined in other files such as parent of the parent.
//     *
//     * @return `true` if the declaration has any external parent, `false` otherwise.
//     */
//    fun hasExternalParents(): Boolean
//
//    /**
//     * Determines whether the declaration has at least one external parent defined directly
//     * in the Kotlin file whose name matches any of the specified names.
//     * This method does not include external parents defined in other files, such as parents of the parent.
//     *
//     * @param name the name of the external parent to check.
//     * @param names the names of the external parents to check.
//     * @return `true` if there is a matching declaration, `false` otherwise.
//     */
//    fun hasExternalParentWithName(name: String, vararg names: String): Boolean
//
//    /**
//     * Determines whether the declaration has parents interface defined directly in the Kotlin
//     * file with all the specified names.
//     * This method does not include external parents defined in other files, such as parents of the parent.
//     *
//     * @param name The name of the external parent to check.
//     * @param names The names of the external parents to check.
//     * @return `true` if there are declarations with all the specified names, `false` otherwise.
//     */
//    fun hasExternalParentsWithAllNames(name: String, vararg names: String): Boolean
//
//    /**
//     * Determines whether the declaration has at least one external parent defined directly
//     * in the Kotlin file that satisfies the provided predicate.
//     * This method does not include external parents defined in other files, such as parents of the parent.
//     *
//     * @param predicate A function that defines the condition to be met by a external parent.
//     * @return `true` if there is a matching declaration, `false` otherwise.
//     */
//    fun hasExternalParent(predicate: (KoParentDeclaration) -> Boolean): Boolean
//
//    /**
//     * Determines whether the declaration has all external parents defined directly
//     * in the Kotlin file that satisfy the provided predicate.
//     * This method does not include external parents defined in other files, such as parents of the parent.
//     *
//     * Note that if the external parents contains no elements, the function returns `true` because there are no elements in it
//     * that do not match the predicate. See a more detailed explanation of this logic concept in
//     * ["Vacuous truth"](https://en.wikipedia.org/wiki/Vacuous_truth) article.
//     *
//     * @param predicate A function that defines the condition to be met by external parents.
//     * @return `true` if all external parents satisfy the predicate, `false` otherwise.
//     */
//    fun hasAllExternalParents(predicate: (KoParentDeclaration) -> Boolean): Boolean
//
//    /**
//     * Determines whether the declaration has at least one external parent of the specified `KClass` type.
//     *
//     * @param name the `KClass` type of the external parent to check.
//     * @param names the `KClass` types of the external parents to check.
//     * @return `true` if there is a matching declaration, `false` otherwise.
//     */
//    fun hasExternalParentOf(name: KClass<*>, vararg names: KClass<*>): Boolean
//
//    /**
//     * Determines whether the declaration has external parents with all the specified `KClass` type.
//     *
//     * @param name the `KClass` type of the external parent to check.
//     * @param names the `KClass` types of the external parents to check.
//     * @return `true` if the declaration has external parents of all the specified `KClass` types, `false` otherwise.
//     */
//    fun hasAllExternalParentsOf(name: KClass<*>, vararg names: KClass<*>): Boolean
}
