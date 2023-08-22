package com.lemonappdev.konsist.api.provider

import com.lemonappdev.konsist.api.declaration.KoBaseDeclaration
import com.lemonappdev.konsist.api.declaration.KoClassDeclaration

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
     * Gets the number of local declarations that satisfies the specified predicate present in the declaration.
     *
     * @param predicate The predicate function to determine if a local declaration satisfies a condition.
     * @return The number of local declarations in the declaration.
     */
    fun countLocalDeclarations(predicate: (KoBaseDeclaration) -> Boolean): Int

    /**
     * Checks whether the declaration contains a local declaration with the specified name.
     *
     * @param predicate The predicate function to determine if a local declaration satisfies a condition.
     * @return `true` if the declaration contains a local declaration with the specified name, `false` otherwise.
     */
    fun containsLocalDeclaration(predicate: (KoBaseDeclaration) -> Boolean): Boolean
}
