package com.lemonappdev.konsist.api.provider

import com.lemonappdev.konsist.api.declaration.KoBaseDeclaration

/**
 * An interface representing a Kotlin declaration that provides information about local declarations.
 */
interface KoLocalDeclarationProvider : KoBaseProvider {
    /**
     * The local declarations present in the declaration.
     */
    val localDeclarations: List<KoBaseDeclaration>

    /**
     * The number of local declarations.
     */
    val numLocalDeclarations: Int

    /**
     * Returns the number of local declarations that satisfies the specified predicate present in the declaration.
     *
     * @param predicate The predicate function to determine if a local declaration satisfies a condition.
     * @return The number of local declarations in the declaration.
     */
    fun countLocalDeclarations(predicate: (KoBaseDeclaration) -> Boolean): Int

    /**
     * Determines whatever the declaration contains a local declaration with the specified name.
     *
     * @param predicate The predicate function to determine if a local declaration satisfies a condition.
     * @return `true` if the declaration contains a local declaration with the specified name, `false` otherwise.
     */
    @Deprecated("Will be removed in v1.0.0", ReplaceWith("hasLocalDeclaration()"))
    fun containsLocalDeclaration(predicate: (KoBaseDeclaration) -> Boolean): Boolean

    /**
     * Determines whatever the declaration has local declarations.
     *
     * @return `true` if the declaration has any local declaration, `false` otherwise.
     */
    fun hasLocalDeclarations(): Boolean

    /**
     * Determines whether the declaration has at least one local declaration that satisfies the provided predicate.
     *
     * @param predicate A function that defines the condition to be met by a local declaration.
     * @return `true` if there is a matching declaration, `false` otherwise.
     */
    fun hasLocalDeclaration(predicate: (KoBaseDeclaration) -> Boolean): Boolean

    /**
     * Determines whether the declaration has all local declarations that satisfy the provided predicate.
     *
     * Note that if the local declarations contains no elements, the function returns `true` because there are no elements in it
     * that do not match the predicate.
     *
     * @param predicate A function that defines the condition to be met by local declarations.
     * @return `true` if all local declarations satisfy the predicate, `false` otherwise.
     */
    fun hasAllLocalDeclarations(predicate: (KoBaseDeclaration) -> Boolean): Boolean
}
