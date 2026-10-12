package com.lemonappdev.konsist.api.provider

import com.lemonappdev.konsist.api.declaration.KoContextParameterDeclaration

/**
 * An interface representing a Kotlin declaration that provides access to its context parameters,
 * e.g. `context(logger: Logger) fun foo()`.
 */
interface KoContextParametersProvider : KoBaseProvider {
    /**
     * Context parameters of the declaration.
     */
    val contextParameters: List<KoContextParameterDeclaration>

    /**
     * The number of context parameters.
     */
    val numContextParameters: Int

    /**
     * Returns the number of context parameters that satisfy the specified predicate present in the declaration.
     *
     * @param predicate The predicate function to determine if a context parameter satisfies a condition.
     * @return The number of context parameters in the declaration.
     */
    fun countContextParameters(predicate: (KoContextParameterDeclaration) -> Boolean): Int

    /**
     * Determines whether the declaration has any context parameter.
     *
     * @return `true` if the declaration has any context parameter, `false` otherwise.
     */
    fun hasContextParameters(): Boolean

    /**
     * Determines whether the declaration has at least one context parameter whose name matches any of the specified names.
     *
     * @param name the name of the context parameter to check.
     * @param names the names of the context parameters to check.
     * @param ignoreCase Specifies whether the comparison should ignore case.
     *        If `true`, the comparison will be case-insensitive.
     *        If `false`, the comparison will consider case sensitivity.
     * @return `true` if there is a matching declaration, `false` otherwise.
     */
    fun hasContextParameterWithName(
        name: String,
        vararg names: String,
        ignoreCase: Boolean = false,
    ): Boolean

    /**
     * Determines whether the declaration has at least one context parameter whose name matches any of the specified names.
     *
     * @param names the names of the context parameters to check.
     * @param ignoreCase Specifies whether the comparison should ignore case.
     *        If `true`, the comparison will be case-insensitive.
     *        If `false`, the comparison will consider case sensitivity.
     * @return `true` if there is a matching declaration, `false` otherwise.
     */
    fun hasContextParameterWithName(
        names: Collection<String>,
        ignoreCase: Boolean = false,
    ): Boolean

    /**
     * Determines whether the declaration has context parameters with all the specified names.
     *
     * @param name The name of the context parameter to check.
     * @param names The names of additional context parameters to check.
     * @param ignoreCase Specifies whether the comparison should ignore case.
     *        If `true`, the comparison will be case-insensitive.
     *        If `false`, the comparison will consider case sensitivity.
     * @return `true` if there are declarations with all the specified names, `false` otherwise.
     */
    fun hasContextParametersWithAllNames(
        name: String,
        vararg names: String,
        ignoreCase: Boolean = false,
    ): Boolean

    /**
     * Determines whether the declaration has context parameters with all the specified names.
     *
     * @param names The names of the context parameters to check.
     * @param ignoreCase Specifies whether the comparison should ignore case.
     *        If `true`, the comparison will be case-insensitive.
     *        If `false`, the comparison will consider case sensitivity.
     * @return `true` if there are declarations with all the specified names, `false` otherwise.
     */
    fun hasContextParametersWithAllNames(
        names: Collection<String>,
        ignoreCase: Boolean = false,
    ): Boolean

    /**
     * Determines whether the declaration has at least one context parameter that satisfies the provided predicate.
     *
     * @param predicate A function that defines the condition to be met by a context parameter declaration.
     * @return `true` if there is a matching declaration, `false` otherwise.
     */
    fun hasContextParameter(predicate: (KoContextParameterDeclaration) -> Boolean): Boolean

    /**
     * Determines whether the declaration has all context parameters that satisfy the provided predicate.
     *
     * Note that if the context parameters contain no elements, the function returns `true` because there are no elements in it
     * that do not match the predicate.
     *
     * @param predicate A function that defines the condition to be met by context parameter declarations.
     * @return `true` if all context parameter declarations satisfy the predicate, `false` otherwise.
     */
    fun hasAllContextParameters(predicate: (KoContextParameterDeclaration) -> Boolean): Boolean
}
