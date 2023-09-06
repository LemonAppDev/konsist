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
     * Gets the number of constructors that satisfies the specified predicate present in the declaration.
     *
     * @param predicate The predicate function to determine if a constructor satisfies a condition.
     * @return The number of constructors in the declaration.
     */
    fun countConstructors(predicate: (KoConstructorDeclaration) -> Boolean): Int
}
