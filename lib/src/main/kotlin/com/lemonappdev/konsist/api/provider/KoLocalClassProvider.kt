package com.lemonappdev.konsist.api.provider

import com.lemonappdev.konsist.api.declaration.KoClassDeclaration

/**
 * An interface representing a Kotlin declaration that provides information about local classes.
 */
interface KoLocalClassProvider : KoBaseProvider {
    /**
     * The local classes present in the declaration.
     */
    val localClasses: List<KoClassDeclaration>

    /**
     * The number of local classes.
     */
    val numLocalClasses: Int

    /**
     * Returns the number of local classes that satisfies the specified predicate present in the declaration.
     *
     * @param predicate The predicate function to determine if a local class satisfies a condition.
     * @return The number of local classes in the declaration.
     */
    fun countLocalClasses(predicate: (KoClassDeclaration) -> Boolean): Int

    /**
     * Determines whatever the declaration contains a local class with the specified name.
     *
     * @param predicate The predicate function to determine if a local class satisfies a condition.
     * @return `true` if the declaration contains a local class with the specified predicate, `false` otherwise.
     */
    @Deprecated("Will be removed in v0.16.0", ReplaceWith("hasLocalClass()"))
    fun containsLocalClass(predicate: (KoClassDeclaration) -> Boolean): Boolean

    /**
     * Determines whatever the declaration has local classes.
     *
     * @return `true` if the declaration has any local class, `false` otherwise.
     */
    fun hasLocalClasses(): Boolean

    /**
     * Determines whether the declaration has at least one local class whose name matches any of the specified names.
     *
     * @param name the name of the local class to check.
     * @param names the names of the local classes to check.
     * @return `true` if there is a matching declaration, `false` otherwise.
     */
    fun hasLocalClassWithName(name: String, vararg names: String): Boolean

    /**
     * Determines whether the declaration has local classes with all the specified names.
     *
     * @param name The name of the local class to check.
     * @param names The names of the local classes to check.
     * @return `true` if there are declarations with all the specified names, `false` otherwise.
     */
    fun hasLocalClassesWithAllNames(name: String, vararg names: String): Boolean

    /**
     * Determines whether the declaration has at least one local class that satisfies the provided predicate.
     *
     * @param predicate A function that defines the condition to be met by a local class declaration.
     * @return `true` if there is a matching declaration, `false` otherwise.
     */
    fun hasLocalClass(predicate: (KoClassDeclaration) -> Boolean): Boolean

    /**
     * Determines whether the declaration has all local classes that satisfy the provided predicate.
     *
     * Note that if the local classes contains no elements, the function returns `true` because there are no elements in it
     * that do not match the predicate.
     *
     * @param predicate A function that defines the condition to be met by local class declarations.
     * @return `true` if all local class declarations satisfy the predicate, `false` otherwise.
     */
    fun hasAllLocalClasses(predicate: (KoClassDeclaration) -> Boolean): Boolean
}
