package com.lemonappdev.konsist.api.provider

import com.lemonappdev.konsist.api.declaration.KoClassDeclaration

/**
 * An interface representing a Kotlin declaration that provides access to its parent class.
 */
interface KoParentClassProvider : KoBaseProvider {
    /**
     * The parent class of the declaration.
     */
    val parentClass: KoClassDeclaration?

    /**
     * Whether declaration has a specified parent class.
     *
     * @param predicate The predicate function used to determine if a declaration parent class satisfies a condition.
     * @return `true` if the declaration has the specified parent class, `false` otherwise.
     */
    fun hasParentClass(predicate: ((KoClassDeclaration) -> Boolean)? = null): Boolean

    /**
     * Whatever declaration has parent class.
     *
     * @param name the name of the parent class to check (optional).
     * @return `true` if the declaration has the specified parent class (or any parent class if [name] is `null`), `false` otherwise.
     */
    @Deprecated("Will be removed in v1.0.0", ReplaceWith("hasParents()"))
    fun hasParentClass(name: String?): Boolean
}
