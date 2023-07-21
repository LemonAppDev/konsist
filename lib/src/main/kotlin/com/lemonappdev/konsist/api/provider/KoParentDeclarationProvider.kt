package com.lemonappdev.konsist.api.provider

import com.lemonappdev.konsist.api.declaration.KoParentDeclaration

interface KoParentDeclarationProvider : KoBaseProvider {
    /**
     * The parent declarations (parent class and parent interfaces) of the declaration.
     */
    val parentDeclarations: List<KoParentDeclaration>

    /**
     * Whatever class has parent declarations (parent class and parent interfaces).
     *
     * @param names the names of the parents to check.
     * @return `true` if the class has parent declarations with the specified names (or any parent if [names] is empty), `false` otherwise.
     */
    fun hasParentDeclarations(vararg names: String): Boolean
}
