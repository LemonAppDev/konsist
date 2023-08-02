package com.lemonappdev.konsist.api.provider

import com.lemonappdev.konsist.api.declaration.KoParentClassDeclaration

/**
 * An interface representing a Kotlin declaration that provides access to its parent class.
 */
interface KoParentClassProvider : KoBaseProvider {
    /**
     * The parent class of the declaration.
     */
    val parentClass: KoParentClassDeclaration?

    /**
     * Whatever declaration has parent class.
     *
     * @param name the name of the parent class to check (optional).
     * @return `true` if the declaration has the specified parent class (or any parent class if [name] is `null`), `false` otherwise.
     */
    fun hasParentClass(name: String? = null): Boolean
}
