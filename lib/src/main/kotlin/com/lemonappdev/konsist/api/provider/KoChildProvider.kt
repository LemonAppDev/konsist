package com.lemonappdev.konsist.api.provider

import com.lemonappdev.konsist.api.declaration.KoChildDeclaration
import kotlin.reflect.KClass

/**
 * An interface representing a Kotlin declaration that provides access to its child declarations.
 */
interface KoChildProvider : KoBaseProvider {
    /**
     * The children of the declaration.
     *
     * @param indirectChildren specifies whether to include children defined in other files such as child of the child.
     * @return a list of [KoChildDeclaration] representing the children of the declaration.
     */
    fun children(indirectChildren: Boolean = false): List<KoChildDeclaration>

    /**
     * The number of children.
     *
     * @param indirectChildren specifies whether to include children defined in other files such as child of the child.
     * @return The number of children.
     */
    fun numChildren(indirectChildren: Boolean = false): Int

    /**
     * Gets the number of children that satisfies the specified predicate present in the declaration.
     *
     * @param indirectChildren specifies whether to include children defined in other files such as child of the child.
     * @param predicate The predicate function to determine if a child satisfies a condition.
     * @return The number of children in the declaration satisfying predicate.
     */
    fun countChildren(
        indirectChildren: Boolean = false,
        predicate: (KoChildDeclaration) -> Boolean,
    ): Int

    /**
     * Whatever declaration has any child defined directly in the Kotlin file.
     *
     * @param indirectChildren specifies whether to include children defined in other files such as child of the child.
     * @return `true` if the declaration has any child, `false` otherwise.
     */
    fun hasChildren(indirectChildren: Boolean = false): Boolean

    /**
     * Determines whether the declaration has at least one child defined directly
     * in the Kotlin file whose name matches any of the specified names.
     *
     * @param name the name of the child to check.
     * @param names the names of the children to check.
     * @param indirectChildren specifies whether to include children defined in other files such as child of the child.
     * @return `true` if there is a matching declaration, `false` otherwise.
     */
    fun hasChildWithName(
        name: String,
        vararg names: String,
        indirectChildren: Boolean = false,
    ): Boolean

    /**
     * Determines whether the declaration has children defined directly in the Kotlin
     * file with all the specified names.
     *
     * @param name The name of the child to check.
     * @param names The names of the children to check.
     * @param indirectChildren specifies whether to include children defined in other files such as child of the child.
     * @return `true` if there are declarations with all the specified names, `false` otherwise.
     */
    fun hasChildrenWithAllNames(
        name: String,
        vararg names: String,
        indirectChildren: Boolean = false,
    ): Boolean

    /**
     * Determines whether the declaration has at least one child defined directly
     * in the Kotlin file that satisfies the provided predicate.
     *
     * @param indirectChildren specifies whether to include children defined in other files such as child of the child.
     * @param predicate A function that defines the condition to be met by a child declaration.
     * @return `true` if there is a matching declaration, `false` otherwise.
     */
    fun hasChild(
        indirectChildren: Boolean = false,
        predicate: (KoChildDeclaration) -> Boolean,
    ): Boolean

    /**
     * Determines whether the declaration has all children defined directly
     * in the Kotlin file that satisfy the provided predicate.
     *
     * Note that if the children contains no elements, the function returns `true` because there are no elements in it
     * that do not match the predicate. See a more detailed explanation of this logic concept in
     * ["Vacuous truth"](https://en.wikipedia.org/wiki/Vacuous_truth) article.
     *
     * @param indirectChildren specifies whether to include children defined in other files such as child of the child.
     * @param predicate A function that defines the condition to be met by child declarations.
     * @return `true` if all child declarations satisfy the predicate, `false` otherwise.
     */
    fun hasAllChildren(
        indirectChildren: Boolean = false,
        predicate: (KoChildDeclaration) -> Boolean,
    ): Boolean

    /**
     * Determines whether the declaration has at least one child of the specified `KClass` type.
     *
     * @param name the `KClass` type of the child to check.
     * @param names the `KClass` types of the children to check.
     * @param indirectChildren specifies whether to include children defined in other files such as child of the child.
     * @return `true` if there is a matching declaration, `false` otherwise.
     */
    fun hasChildOf(
        name: KClass<*>,
        vararg names: KClass<*>,
        indirectChildren: Boolean = false,
    ): Boolean

    /**
     * Determines whether the declaration has children with all the specified `KClass` type.
     *
     * @param name the `KClass` type of the child to check.
     * @param names the `KClass` types of the children to check.
     * @param indirectChildren specifies whether to include children defined in other files such as child of the child.
     * @return `true` if the declaration has children of all the specified `KClass` types, `false` otherwise.
     */
    fun hasAllChildrenOf(
        name: KClass<*>,
        vararg names: KClass<*>,
        indirectChildren: Boolean = false,
    ): Boolean
}
