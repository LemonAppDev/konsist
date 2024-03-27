package com.lemonappdev.konsist.api.provider

import com.lemonappdev.konsist.api.declaration.KoArgumentDeclaration

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
     * Returns the number of arguments that satisfies the specified predicate present in the declaration.
     *
     * @param predicate The predicate function to determine if an argument satisfies a condition.
     * @return The number of arguments in the declaration.
     */
    fun countArguments(predicate: (KoArgumentDeclaration) -> Boolean): Int

    /**
     * Determines whatever the declaration has arguments.
     *
     * @return `true` if the declaration has any argument, `false` otherwise.
     */
    fun hasArguments(): Boolean

    /**
     * Determines whether the declaration has at least one argument whose name matches any of the specified names.
     *
     * @param name the name of the argument to check.
     * @param names the names of the arguments to check.
     * @return `true` if there is a matching declaration, `false` otherwise.
     */
    fun hasArgumentWithName(
        name: String,
        vararg names: String,
    ): Boolean

    /**
     * Determines whether the declaration has at least one argument whose name matches any of the specified names.
     *
     * @param names the names of the arguments to check.
     * @return `true` if there is a matching declaration, `false` otherwise.
     */
    fun hasArgumentWithName(names: Collection<String>): Boolean

    /**
     * Determines whether the declaration has arguments with all the specified names.
     *
     * @param name The name of the argument to check.
     * @param names The names of the arguments to check.
     * @return `true` if there are declarations with all the specified names, `false` otherwise.
     */
    fun hasArgumentsWithAllNames(
        name: String,
        vararg names: String,
    ): Boolean

    /**
     * Determines whether the declaration has arguments with all the specified names.
     *
     * @param names The names of the arguments to check.
     * @return `true` if there are declarations with all the specified names, `false` otherwise.
     */
    fun hasArgumentsWithAllNames(names: Collection<String>): Boolean

    /**
     * Determines whether the declaration has at least one argument that satisfies the provided predicate.
     *
     * @param predicate A function that defines the condition to be met by a argument declaration.
     * @return `true` if there is a matching declaration, `false` otherwise.
     */
    fun hasArgument(predicate: (KoArgumentDeclaration) -> Boolean): Boolean

    /**
     * Determines whether the declaration has all arguments that satisfy the provided predicate.
     *
     * Note that if the arguments contains no elements, the function returns `true` because there are no elements in it
     * that do not match the predicate.
     *
     * @param predicate A function that defines the condition to be met by argument declarations.
     * @return `true` if all argument declarations satisfy the predicate, `false` otherwise.
     */
    fun hasAllArguments(predicate: (KoArgumentDeclaration) -> Boolean): Boolean
}
