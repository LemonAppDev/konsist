package com.lemonappdev.konsist.api.provider

import com.lemonappdev.konsist.api.declaration.KoTypeParameterDeclaration

interface KoTypeParameterProvider : KoBaseProvider {
    /**
     * Type parameters of the declaration.
     */
    val typeParameters: List<KoTypeParameterDeclaration>

    /**
     * The number of type parameters.
     */
    val numTypeParameters: Int

    /**
     * Returns the number of type parameters that satisfies the specified predicate present in the declaration.
     *
     * @param predicate The predicate function to determine if a type parameter satisfies a condition.
     * @return The number of type parameters in the declaration.
     */
    fun countTypeParameters(predicate: (KoTypeParameterDeclaration) -> Boolean): Int

    /**
     * Determines whatever declaration has any type parameter.
     *
     * @return `true` if the declaration has any type parameter, `false` otherwise.
     */
    fun hasTypeParameters(): Boolean

    /**
     * Determines whether the declaration has at least one type parameter whose name matches any of the specified names.
     *
     * @param name the name of the type parameter to check.
     * @param names the names of the type parameters to check.
     * @return `true` if there is a matching declaration, `false` otherwise.
     */
    fun hasTypeParameterWithName(
        name: String,
        vararg names: String,
    ): Boolean

    /**
     * Determines whether the declaration has at least one type parameter whose name matches any of the specified names.
     *
     * @param names the names of the type parameters to check.
     * @return `true` if there is a matching declaration, `false` otherwise.
     */
    fun hasTypeParameterWithName(names: Collection<String>): Boolean

    /**
     * Determines whether the declaration has type parameters with all the specified names.
     *
     * @param name The name of the type parameter to check.
     * @param names The names of the type parameters to check.
     * @return `true` if there are declarations with all the specified names, `false` otherwise.
     */
    fun hasTypeParametersWithAllNames(
        name: String,
        vararg names: String,
    ): Boolean

    /**
     * Determines whether the declaration has type parameters with all the specified names.
     *
     * @param names The names of the type parameters to check.
     * @return `true` if there are declarations with all the specified names, `false` otherwise.
     */
    fun hasTypeParametersWithAllNames(names: Collection<String>): Boolean

    /**
     * Determines whether the declaration has at least one type parameter that satisfies the provided predicate.
     *
     * @param predicate A function that defines the condition to be met by a type parameter declaration.
     * @return `true` if there is a matching declaration, `false` otherwise.
     */
    fun hasTypeParameter(predicate: (KoTypeParameterDeclaration) -> Boolean): Boolean

    /**
     * Determines whether the declaration has all type parameters that satisfy the provided predicate.
     *
     * Note that if the type parameters contains no elements, the function returns `true` because there are no elements in it
     * that do not match the predicate.
     *
     * @param predicate A function that defines the condition to be met by type parameter declarations.
     * @return `true` if all type parameter declarations satisfy the predicate, `false` otherwise.
     */
    fun hasAllTypeParameters(predicate: (KoTypeParameterDeclaration) -> Boolean): Boolean
}
