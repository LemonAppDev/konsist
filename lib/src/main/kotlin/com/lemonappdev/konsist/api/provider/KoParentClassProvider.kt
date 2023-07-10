package com.lemonappdev.konsist.api.provider

import com.lemonappdev.konsist.api.declaration.KoParentDeclaration

interface KoParentClassProvider: KoParentProvider {
    /**
     * The parent class of the declaration.
     */
    val parentClass: KoParentDeclaration?

    /**
     * Whatever declaration has parent class.
     *
     * @param name the name of the parent class to check (optional).
     * @return `true` if the declaration has the specified parent class (or any parent class if [name] is `null`), `false` otherwise.
     */
    fun hasParentClass(name: String? = null): Boolean
}
