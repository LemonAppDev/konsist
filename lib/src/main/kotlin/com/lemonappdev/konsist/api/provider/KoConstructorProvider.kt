package com.lemonappdev.konsist.api.provider

import com.lemonappdev.konsist.api.declaration.KoConstructorDeclaration

/**
 * An interface representing a Kotlin declaration that provides access to all primary and secondary constructors.
 */
interface KoConstructorProvider : KoBaseProvider {
    /**
     * The all primary and secondary constructors of the declaration.
     */
    val constructors: List<KoConstructorDeclaration>

    /**
     * The number of constructors.
     */
    val numConstructors: Int

    /**
     * Returns the number of constructors that satisfies the specified predicate present in the declaration.
     *
     * @param predicate The predicate function to determine if a constructor satisfies a condition.
     * @return The number of constructors in the declaration.
     */
    fun countConstructors(predicate: (KoConstructorDeclaration) -> Boolean): Int

    /**
     * Determines whatever the declaration has any constructor.
     *
     * @return `true` if the declaration has any constructor, `false` otherwise.
     */
    fun hasConstructors(): Boolean

    /**
     * Determines whatever the declaration has any constructor with the specified predicate.
     *
     * @param predicate The predicate function to determine if a constructor satisfies a condition.
     * @return `true` if the declaration has constructors with the specified predicate, `false` otherwise.
     */
    fun hasConstructor(predicate: (KoConstructorDeclaration) -> Boolean): Boolean

    /**
     * Determines whatever the declaration has all constructors with the specified predicate.
     *
     * Note that if the constructors contains no elements, the function returns `true` because there are no elements in it
     * that do not match the predicate (["Vacuous truth"](https://en.wikipedia.org/wiki/Vacuous_truth)).
     *
     * @param predicate The predicate function to determine if a constructor satisfies a condition.
     * @return `true` if the declaration has all constructors with the specified predicate, `false` otherwise.
     */
    fun hasAllConstructors(predicate: (KoConstructorDeclaration) -> Boolean): Boolean
}
