package com.lemonappdev.konsist.api.provider

import com.lemonappdev.konsist.api.declaration.KoParentDeclaration

/**
 * An interface representing a Kotlin declaration that provides access to its parent declarations.
 */
interface KoParentProvider : KoBaseProvider {
    /**
     * The parents (parent class and parent interfaces) of the declaration.
     */
    val parents: List<KoParentDeclaration>

    /**
     * The number of parents.
     */
    val numParents: Int

    /**
     * Whatever class has parents (parent class and parent interfaces).
     *
     * @param names the names of the parents to check.
     * @return `true` if the class has parents with the specified names (or any parent if [names] is empty), `false` otherwise.
     */
    fun hasParents(vararg names: String): Boolean
}
