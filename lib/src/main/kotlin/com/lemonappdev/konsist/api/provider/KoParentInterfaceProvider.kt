package com.lemonappdev.konsist.api.provider

import com.lemonappdev.konsist.api.declaration.KoInterfaceDeclaration
import kotlin.reflect.KClass

/**
 * An interface representing a Kotlin declaration that provides access to its parent interfaces.
 */
interface KoParentInterfaceProvider : KoBaseProvider {
    /**
     * The parent interfaces of the declaration.
     */
    val parentInterfaces: List<KoInterfaceDeclaration>

    /**
     * The number of parent interfaces.
     */
    val numParentInterfaces: Int

    /**
     * Returns the number of parent interfaces that satisfies the specified predicate present in the declaration.
     *
     * @param predicate The predicate function to determine if a parent interface satisfies a condition.
     * @return The number of parent interfaces in the declaration satisfying predicate.
     */
    fun countParentInterfaces(predicate: (KoInterfaceDeclaration) -> Boolean): Int

    /**
     * Determines whatever declaration has parent interfaces.
     *
     * @param names the names of the parent interfaces to check.
     * @return `true` if the declaration has parent interfaces with the specified names (or any parent interface if [names] is empty),
     * `false` otherwise.
     */
    @Deprecated("Will be removed in v1.0.0", ReplaceWith("hasParents()"))
    fun hasParentInterfaces(vararg names: String): Boolean

    /**
     * Determines whatever declaration has any parent interface defined directly in the Kotlin file.
     *
     * @return `true` if the declaration has any parent interface, `false` otherwise.
     */
    fun hasParentInterfaces(): Boolean

    /**
     * Determines whether the declaration has at least one parent interface defined directly
     * in the Kotlin file whose name matches any of the specified names.
     * This method does not include parent interfaces defined in other files, such as parents of the parent.
     *
     * @param name the name of the parent interface to check.
     * @param names the names of the parent interfaces to check.
     * @return `true` if there is a matching declaration, `false` otherwise.
     */
    fun hasParentInterfaceWithName(name: String, vararg names: String): Boolean

    /**
     * Determines whether the declaration has parents interface defined directly in the Kotlin
     * file with all the specified names.
     * This method does not include parent interfaces defined in other files, such as parents of the parent.
     *
     * @param name The name of the parent interface to check.
     * @param names The names of the parent interfaces to check.
     * @return `true` if there are declarations with all the specified names, `false` otherwise.
     */
    fun hasParentInterfacesWithAllNames(name: String, vararg names: String): Boolean

    /**
     * Determines whether the declaration has at least one parent interface defined directly
     * in the Kotlin file that satisfies the provided predicate.
     * This method does not include parent interfaces defined in other files, such as parents of the parent.
     *
     * @param predicate A function that defines the condition to be met by a parent interface.
     * @return `true` if there is a matching declaration, `false` otherwise.
     */
    fun hasParentInterface(predicate: (KoInterfaceDeclaration) -> Boolean): Boolean

    /**
     * Determines whether the declaration has all parent interfaces defined directly
     * in the Kotlin file that satisfy the provided predicate.
     * This method does not include parent interfaces defined in other files, such as parents of the parent.
     *
     * Note that if the parent interfaces contains no elements, the function returns `true` because there are no elements in it
     * that do not match the predicate.
     *
     * @param predicate A function that defines the condition to be met by parent interfaces.
     * @return `true` if all parent interfaces satisfy the predicate, `false` otherwise.
     */
    fun hasAllParentInterfaces(predicate: (KoInterfaceDeclaration) -> Boolean): Boolean

    /**
     * Determines whether the declaration has at least one parent interface of the specified `KClass` type.
     *
     * @param name the `KClass` type of the parent interface to check.
     * @param names the `KClass` types of the parent interfaces to check.
     * @return `true` if there is a matching declaration, `false` otherwise.
     */
    fun hasParentInterfaceOf(name: KClass<*>, vararg names: KClass<*>): Boolean

    /**
     * Determines whether the declaration has parent interfaces with all the specified `KClass` type.
     *
     * @param name the `KClass` type of the parent interface to check.
     * @param names the `KClass` types of the parent interfaces to check.
     * @return `true` if the declaration has parent interfaces of all the specified `KClass` types, `false` otherwise.
     */
    fun hasAllParentInterfacesOf(name: KClass<*>, vararg names: KClass<*>): Boolean
}
