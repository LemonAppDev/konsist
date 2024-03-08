package com.lemonappdev.konsist.api.provider

import com.lemonappdev.konsist.api.declaration.KoVariableDeclaration

/**
 * An interface representing a Kotlin declaration that provides information about variables.
 */
interface KoVariableProvider : KoBaseProvider {
    /**
     * The variables present in the declaration.
     */
    val variables: List<KoVariableDeclaration>

    /**
     * The number of variables.
     */
    val numVariables: Int

    /**
     * Gets the number of variables that satisfies the specified predicate present in the declaration.
     *
     * @param predicate The predicate function to determine if a variable satisfies a condition.
     * @return The number of variables in the declaration.
     */
    fun countVariables(predicate: (KoVariableDeclaration) -> Boolean): Int

    /**
     * Whether the declaration has variables.
     *
     * @return `true` if the declaration has any variable, `false` otherwise.
     */
    fun hasVariables(): Boolean

    /**
     * Determines whether the declaration has at least one variable whose name matches any of the specified names.
     *
     * @param name the name of the variable to check.
     * @param names the names of the variables to check.
     * @return `true` if there is a matching declaration, `false` otherwise.
     */
    fun hasVariableWithName(
        name: String,
        vararg names: String,
    ): Boolean

    /**
     * Determines whether the declaration has variables with all the specified names.
     *
     * @param name The name of the variable to check.
     * @param names The names of the variables to check.
     * @return `true` if there are declarations with all the specified names, `false` otherwise.
     */
    fun hasVariablesWithAllNames(
        name: String,
        vararg names: String,
    ): Boolean

    /**
     * Determines whether the declaration has at least one variable that satisfies the provided predicate.
     *
     * @param predicate A variable that defines the condition to be met by a variable declaration.
     * @return `true` if there is a matching declaration, `false` otherwise.
     */
    fun hasVariable(predicate: (KoVariableDeclaration) -> Boolean): Boolean

    /**
     * Determines whether the declaration has all variables that satisfy the provided predicate.
     *
     * Note that if the variables contains no elements, the variable returns `true` because there are no elements in it
     * that do not match the predicate. See a more detailed explanation of this logic concept in
     * ["Vacuous truth"](https://en.wikipedia.org/wiki/Vacuous_truth) article.
     *
     * @param predicate A variable that defines the condition to be met by variable declarations.
     * @return `true` if all variable declarations satisfy the predicate, `false` otherwise.
     */
    fun hasAllVariables(predicate: (KoVariableDeclaration) -> Boolean): Boolean
}
