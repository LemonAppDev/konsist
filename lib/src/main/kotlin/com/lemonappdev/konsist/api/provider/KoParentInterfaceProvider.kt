package com.lemonappdev.konsist.api.provider

import com.lemonappdev.konsist.api.declaration.KoInterfaceDeclaration
import kotlin.reflect.KClass

/**
 * An interface representing a Kotlin declaration that provides access to its parent interfaces.
 */
interface KoParentInterfaceProvider : KoBaseProvider {
    /**
     * The parent interfaces of the declaration.
     * Does not include parent interfaces defined in other files such as parent of the parent.
     */
    @Deprecated("Will be removed in v0.16.0.", replaceWith = ReplaceWith("parentInterfaces()"))
    val parentInterfaces: List<KoInterfaceDeclaration>

    /**
     * Gets the number of parent interfaces.
     */
    @Deprecated("Will be removed in v0.16.0.", replaceWith = ReplaceWith("numParentInterfaces()"))
    val numParentInterfaces: Int

    /**
     * The parent interfaces of the declaration.
     *
     * @param indirectParents specifies whether to include parent interfaces defined in other files such as parent of the parent.
     * @return a list of [KoInterfaceDeclaration] representing the parent interfaces of the declaration.
     */
    fun parentInterfaces(indirectParents: Boolean = false): List<KoInterfaceDeclaration>

    /**
     * Returns the number of parent interfaces.
     *
     * @param indirectParents specifies whether to include parent interfaces defined in other files such as parent of the parent.
     * @return The number of parent interfaces.
     */
    fun numParentInterfaces(indirectParents: Boolean = false): Int

    /**
     * Returns the number of parent interfaces that satisfies the specified predicate present in the declaration.
     *
     * @param indirectParents specifies whether to include parent interfaces defined in other files such as parent of the parent.
     * @param predicate The predicate function to determine if a parent interface satisfies a condition.
     * @return The number of parent interfaces in the declaration satisfying predicate.
     */
    fun countParentInterfaces(indirectParents: Boolean = false, predicate: (KoInterfaceDeclaration) -> Boolean): Int

    /**
     * Determines whatever declaration has parent interfaces.
     *
     * @param names the names of the parent interfaces to check.
     * @return `true` if the declaration has parent interfaces with the specified names (or any parent interface if [names] is empty),
     * `false` otherwise.
     */
    @Deprecated("Will be removed in v0.16.0", ReplaceWith("hasParents()"))
    fun hasParentInterfaces(vararg names: String): Boolean

    /**
     * Determines whatever declaration has any parent interface.
     *
     * @param indirectParents specifies whether to include parent interfaces defined in other files such as parent of the parent.
     * @return `true` if the declaration has any parent interface, `false` otherwise.
     */
    fun hasParentInterfaces(indirectParents: Boolean = false): Boolean

    /**
     * Determines whether the declaration has at least one parent interface whose name matches any of the specified names.
     *
     * @param name the name of the parent interface to check.
     * @param names the names of the parent interfaces to check.
     * @param indirectParents specifies whether to include parent interfaces defined in other files such as parent of the parent.
     * @return `true` if there is a matching declaration, `false` otherwise.
     */
    fun hasParentInterfaceWithName(name: String, vararg names: String, indirectParents: Boolean = false): Boolean

    /**
     * Determines whether the declaration has parent interfaces defined with all the specified names.
     *
     * @param name The name of the parent interface to check.
     * @param names The names of the parent interfaces to check.
     * @param indirectParents specifies whether to include parent interfaces defined in other files such as parent of the parent.
     * @return `true` if there are declarations with all the specified names, `false` otherwise.
     */
    fun hasParentInterfacesWithAllNames(name: String, vararg names: String, indirectParents: Boolean = false): Boolean

    /**
     * Determines whether the declaration has at least one parent interface that satisfies the provided predicate.
     *
     * @param indirectParents specifies whether to include parent interfaces defined in other files such as parent of the parent.
     * @param predicate A function that defines the condition to be met by a parent interface.
     * @return `true` if there is a matching declaration, `false` otherwise.
     */
    fun hasParentInterface(indirectParents: Boolean = false, predicate: (KoInterfaceDeclaration) -> Boolean): Boolean

    /**
     * Determines whether the declaration has all parent interfaces that satisfy the provided predicate.
     *
     * Note that if the parent interfaces contains no elements, the function returns `true` because there are no elements in it
     * that do not match the predicate.
     *
     * @param indirectParents specifies whether to include parent interfaces defined in other files such as parent of the parent.
     * @param predicate A function that defines the condition to be met by parent interfaces.
     * @return `true` if all parent interfaces satisfy the predicate, `false` otherwise.
     */
    fun hasAllParentInterfaces(indirectParents: Boolean = false, predicate: (KoInterfaceDeclaration) -> Boolean): Boolean

    /**
     * Determines whether the declaration has at least one parent interface of the specified `KClass` type.
     *
     * @param name the `KClass` type of the parent interface to check.
     * @param names the `KClass` types of the parent interfaces to check.
     * @param indirectParents specifies whether to include parent interfaces defined in other files such as parent of the parent.
     * @return `true` if there is a matching declaration, `false` otherwise.
     */
    fun hasParentInterfaceOf(name: KClass<*>, vararg names: KClass<*>, indirectParents: Boolean = false): Boolean

    /**
     * Determines whether the declaration has parent interfaces with all the specified `KClass` type.
     *
     * @param name the `KClass` type of the parent interface to check.
     * @param names the `KClass` types of the parent interfaces to check.
     * @param indirectParents specifies whether to include parent interfaces defined in other files such as parent of the parent.
     * @return `true` if the declaration has parent interfaces of all the specified `KClass` types, `false` otherwise.
     */
    fun hasAllParentInterfacesOf(name: KClass<*>, vararg names: KClass<*>, indirectParents: Boolean = false): Boolean
}
