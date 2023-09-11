package com.lemonappdev.konsist.api.provider

import com.lemonappdev.konsist.api.declaration.KoArgumentDeclaration
import com.lemonappdev.konsist.api.declaration.KoImportDeclaration

/**
 * An interface representing a Kotlin declaration that provides access to arguments.
 *
 */
interface KoArgumentProvider : KoBaseProvider {
    /**
     * List of arguments.
     */
    val arguments: List<KoArgumentDeclaration>

    /**
     * The number of arguments.
     */
    val numArguments: Int

    /**
     * Gets the number of arguments that satisfies the specified predicate present in the declaration.
     *
     * @param predicate The predicate function to determine if an argument satisfies a condition.
     * @return The number of arguments in the declaration.
     */
    fun countArguments(predicate: (KoArgumentDeclaration) -> Boolean): Int

    /**
     * Whether the declaration has arguments.
     *
     * @return `true` if the declaration has any argument, `false` otherwise.
     */
    fun hasArguments(): Boolean

    /**
     * Whether the declaration has any argument with the specified predicate.
     *
     * @param predicate The predicate function to determine if an argument satisfies a condition.
     * @return `true` if the declaration has arguments with the specified predicate, `false` otherwise.
     */
    fun hasArgument(predicate: (KoArgumentDeclaration) -> Boolean): Boolean

    /**
     * Whether the declaration has all arguments with the specified predicate.
     *
     * Note that if the arguments contains no elements, the function returns `true` because there are no elements in it
     * that do not match the predicate. See a more detailed explanation of this logic concept in
     * ["Vacuous truth"](https://en.wikipedia.org/wiki/Vacuous_truth) article.
     *
     * @param predicate The predicate function to determine if an argument satisfies a condition.
     * @return `true` if the declaration has all arguments with the specified predicate, `false` otherwise.
     */
    fun hasAllArguments(predicate: (KoArgumentDeclaration) -> Boolean): Boolean
}
