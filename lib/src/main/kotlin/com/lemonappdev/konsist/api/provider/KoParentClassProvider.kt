package com.lemonappdev.konsist.api.provider

import com.lemonappdev.konsist.api.declaration.KoClassDeclaration
import kotlin.reflect.KClass

/**
 * An interface representing a Kotlin declaration that provides access to its parent class.
 */
interface KoParentClassProvider : KoBaseProvider {
    /**
     * The parent class of the declaration.
     */
    val parentClass: KoClassDeclaration?

    /**
     * Determines whether declaration has a specified parent class.
     *
     * @param predicate The predicate function used to determine if a declaration parent class satisfies a condition.
     * @return `true` if the declaration has the specified parent class (or any parent class if [predicate] is `null`),
     * `false` otherwise.
     */
    fun hasParentClass(predicate: ((KoClassDeclaration) -> Boolean)? = null): Boolean

    /**
     * Determines whether declaration has parent class.
     *
     * @param name the name of the parent class to check.
     * @return `true` if the declaration has the specified parent class, `false` otherwise.
     */
    @Deprecated("Will be removed in v1.0.0", ReplaceWith("hasParents()"))
    fun hasParentClass(name: String): Boolean

    /**
     * Determines whether the declaration has parent class defined directly
     * in the Kotlin file whose name matches any of the specified names.
     * This method does not include parent classes defined in other files, such as parents of the parent.
     *
     * @param name the name of the parent class to check.
     * @param names the names of the parent classes to check.
     * @return `true` if there is a matching declaration, `false` otherwise.
     */
    fun hasParentClassWithName(name: String, vararg names: String): Boolean

    /**
     * Determines whatever declaration has a parent class of the specified Kotlin class.
     *
     * @param name The Kotlin class representing the parent class to check for.
     * @param names The Kotlin class(es) representing the parent class(es) to check for.
     * @return `true` if the declaration has a parent class matching the specified KClass, `false` otherwise.
     */
    fun hasParentClassOf(name: KClass<*>, vararg names: KClass<*>): Boolean
}
