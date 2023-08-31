package com.lemonappdev.konsist.api.provider

import com.lemonappdev.konsist.api.declaration.KoParentClassDeclaration

/**
 * An interface representing a Kotlin declaration that provides access to its parent class.
 */
@Deprecated("Will be removed in v1.0.0", ReplaceWith("KoParentProvider"))

interface KoParentClassProvider : KoBaseProvider {
    /**
     * The parent class of the declaration.
     */
    @Deprecated("Will be removed in v1.0.0", ReplaceWith("parents"))
    val parentClass: KoParentClassDeclaration?

    /**
     * Whatever declaration has parent class.
     *
     * @param name the name of the parent class to check (optional).
     * @return `true` if the declaration has the specified parent class (or any parent class if [name] is `null`), `false` otherwise.
     */
    @Deprecated("Will be removed in v1.0.0", ReplaceWith("hasParents()"))
    fun hasParentClass(name: String? = null): Boolean
}
