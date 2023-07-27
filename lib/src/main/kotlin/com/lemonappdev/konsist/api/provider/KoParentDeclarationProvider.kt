package com.lemonappdev.konsist.api.provider

import com.lemonappdev.konsist.api.declaration.KoParentDeclaration

/**
 * An interface representing a Kotlin declaration that provides access to its parent declarations.
 */
interface KoParentDeclarationProvider : KoBaseProvider {
    /**
     * The parent declarations (parent class and parent interfaces) of the declaration.
     */
    val parentDeclarations: Sequence<KoParentDeclaration>

    /**
     * Whatever class has parent declarations (parent class and parent interfaces).
     *
     * @param names the names of the parents to check.
     * @return `true` if the class has parent declarations with the specified names (or any parent if [names] is empty), `false` otherwise.
     */
    fun hasParentDeclarations(vararg names: String): Boolean
}
