package com.lemonappdev.konsist.api.provider

import com.lemonappdev.konsist.api.declaration.KoAnnotationDeclaration
import com.lemonappdev.konsist.api.declaration.KoParentDeclaration

/**
 * An interface representing a Kotlin declaration that provides access to its parent declarations.
 */
interface KoParentProvider : KoBaseProvider {
    /**
     * The parents (parent class and parent interfaces) defined directly in the Kotlin file.
     * Does not include parents defined in other files such as parent of the parent.
     */
    val parents: List<KoParentDeclaration>

    /**
     * The number of parents.
     */
    val numParents: Int

    /**
     * Gets the number of parents that satisfies the specified predicate present in the declaration.
     *
     * @param predicate The predicate function to determine if an parent satisfies a condition.
     * @return The number of parents in the declaration.
     */
    fun countParents(predicate: (KoParentDeclaration) -> Boolean): Int

    /**
     * Whatever class has parents (parent class and parent interfaces) defined directly in the Kotlin file.
     * Does not include parents defined in other files such as parent of the parent.
     *
     * @param names the names of the parents to check.
     * @return `true` if the class has parents with the specified names (or any parent if [names] is empty), `false` otherwise.
     */
    fun hasParents(vararg names: String): Boolean
}
