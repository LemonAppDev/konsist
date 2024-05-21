package com.lemonappdev.konsist.api.provider

import com.lemonappdev.konsist.api.declaration.KoParameterDeclaration

/**
 * An interface representing a Kotlin declaration that provides access to its parameters.
 */
interface KoParametersProvider : KoBaseProvider {
    /**
     * Parameters of the declaration.
     */
    val parameters: List<KoParameterDeclaration>

    /**
     * The number of parameters.
     */
    val numParameters: Int

    /**
     * Returns the number of parameters that satisfies the specified predicate present in the declaration.
     *
     * @param predicate The predicate function to determine if a parameter satisfies a condition.
     * @return The number of parameters in the declaration.
     */
    fun countParameters(predicate: (KoParameterDeclaration) -> Boolean): Int

    /**
     * Determines whatever declaration has any parameter.
     *
     * @return `true` if the declaration has any parameter, `false` otherwise.
     */
    fun hasParameters(): Boolean

    /**
     * Determines whether the declaration has at least one parameter whose name matches any of the specified names.
     *
     * @param name the name of the parameter to check.
     * @param names the names of the parameters to check.
     * @return `true` if there is a matching declaration, `false` otherwise.
     */
    fun hasParameterWithName(
        name: String,
        vararg names: String,
    ): Boolean

    /**
     * Determines whether the declaration has at least one parameter whose name matches any of the specified names.
     *
     * @param names the names of the parameters to check.
     * @return `true` if there is a matching declaration, `false` otherwise.
     */
    fun hasParameterWithName(names: Collection<String>): Boolean

    /**
     * Determines whether the declaration has parameters with all the specified names.
     *
     * @param name The name of the parameter to check.
     * @param names The names of the parameters to check.
     * @return `true` if there are declarations with all the specified names, `false` otherwise.
     */
    fun hasParametersWithAllNames(
        name: String,
        vararg names: String,
    ): Boolean

    /**
     * Determines whether the declaration has parameters with all the specified names.
     *
     * @param names The names of the parameters to check.
     * @return `true` if there are declarations with all the specified names, `false` otherwise.
     */
    fun hasParametersWithAllNames(names: Collection<String>): Boolean

    /**
     * Determines whether the declaration has at least one parameter that satisfies the provided predicate.
     *
     * @param predicate A function that defines the condition to be met by a parameter declaration.
     * @return `true` if there is a matching declaration, `false` otherwise.
     */
    fun hasParameter(predicate: (KoParameterDeclaration) -> Boolean): Boolean

    /**
     * Determines whether the declaration has all parameters that satisfy the provided predicate.
     *
     * Note that if the parameters contains no elements, the function returns `true` because there are no elements in it
     * that do not match the predicate.
     *
     * @param predicate A function that defines the condition to be met by parameter declarations.
     * @return `true` if all parameter declarations satisfy the predicate, `false` otherwise.
     */
    fun hasAllParameters(predicate: (KoParameterDeclaration) -> Boolean): Boolean
}
