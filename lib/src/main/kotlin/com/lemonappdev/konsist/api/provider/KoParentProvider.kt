package com.lemonappdev.konsist.api.provider

import com.lemonappdev.konsist.api.declaration.KoParentDeclaration

interface KoParentProvider {
    /**
     * The parents (parent class and parent interfaces) of the declaration.
     */
    val parents: List<KoParentDeclaration>

    /**
     * Whatever class has parents (parent class and parent interfaces).
     *
     * @param names the names of the parents to check.
     * @return `true` if the class has parents with the specified names (or any parent if [names] is empty), `false` otherwise.
     */
    fun hasParents(vararg names: String): Boolean
}
