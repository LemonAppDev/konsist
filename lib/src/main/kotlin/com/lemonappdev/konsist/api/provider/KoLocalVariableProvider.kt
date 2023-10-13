package com.lemonappdev.konsist.api.provider

import com.lemonappdev.konsist.api.declaration.KoVariableDeclaration

/**
 * An interface representing a Kotlin declaration that provides information about local variables.
 */
interface KoLocalVariableProvider : KoBaseProvider {
    /**
     * The local variables present in the declaration.
     */
    val localVariables: List<KoVariableDeclaration>

    /**
     * The number of local variables.
     */
    val numLocalVariables: Int

    /**
     * Gets the number of local variables that satisfies the specified predicate present in the declaration.
     *
     * @param predicate The predicate function to determine if a local variable satisfies a condition.
     * @return The number of local variables in the declaration.
     */
    fun countLocalVariables(predicate: (KoVariableDeclaration) -> Boolean): Int

    /**
     * Whether the declaration has local variables.
     *
     * @return `true` if the declaration has any local variable, `false` otherwise.
     */
    fun hasLocalVariables(): Boolean

    /**
     * Determines whether the declaration has at least one local variable whose name matches any of the specified names.
     *
     * @param name the name of the local variable to check.
     * @param names the names of the local variables to check.
     * @return `true` if there is a matching declaration, `false` otherwise.
     */
    fun hasLocalVariableWithName(name: String, vararg names: String): Boolean

    /**
     * Determines whether the declaration has local variables with all the specified names.
     *
     * @param name The name of the local variable to check.
     * @param names The names of the local variables to check.
     * @return `true` if there are declarations with all the specified names, `false` otherwise.
     */
    fun hasLocalVariablesWithAllNames(name: String, vararg names: String): Boolean

    /**
     * Determines whether the declaration has at least one local variable that satisfies the provided predicate.
     *
     * @param predicate A variable that defines the condition to be met by a local variable declaration.
     * @return `true` if there is a matching declaration, `false` otherwise.
     */
    fun hasLocalVariable(predicate: (KoVariableDeclaration) -> Boolean): Boolean

    /**
     * Determines whether the declaration has all local variables that satisfy the provided predicate.
     *
     * Note that if the local variables contains no elements, the variable returns `true` because there are no elements in it
     * that do not match the predicate. See a more detailed explanation of this logic concept in
     * ["Vacuous truth"](https://en.wikipedia.org/wiki/Vacuous_truth) article.
     *
     * @param predicate A variable that defines the condition to be met by local variable declarations.
     * @return `true` if all local variable declarations satisfy the predicate, `false` otherwise.
     */
    fun hasAllLocalVariables(predicate: (KoVariableDeclaration) -> Boolean): Boolean
}
