package com.lemonappdev.konsist.api.provider

import com.lemonappdev.konsist.api.declaration.KoSecondaryConstructorDeclaration

/**
 * An interface representing a Kotlin declaration that provides access to its secondary constructors.
 */
interface KoSecondaryConstructorsProvider : KoBaseProvider {
    /**
     * The secondary constructors of the declaration.
     */
    val secondaryConstructors: List<KoSecondaryConstructorDeclaration>

    /**
     * The number of secondary constructors.
     */
    val numSecondaryConstructors: Int

    /**
     * Returns the number of secondary constructors that satisfies the specified predicate present in the declaration.
     *
     * @param predicate The predicate function to determine if a secondary constructor satisfies a condition.
     * @return The number of secondary constructors in the declaration.
     */
    fun countSecondaryConstructors(predicate: (KoSecondaryConstructorDeclaration) -> Boolean): Int

    /**
     * Determines whatever declaration has secondary constructors.
     *
     * @return `true` if the declaration has secondary constructor, `false` otherwise.
     */
    fun hasSecondaryConstructors(): Boolean

    /**
     * Determines whether the declaration has at least one secondary constructor that satisfies the provided predicate.
     *
     * @param predicate A function that defines the condition to be met by a secondary constructor declaration.
     * @return `true` if there is a matching declaration, `false` otherwise.
     */
    fun hasSecondaryConstructor(predicate: (KoSecondaryConstructorDeclaration) -> Boolean): Boolean

    /**
     * Determines whether the declaration has all secondary constructors that satisfy the provided predicate.
     *
     * Note that if the secondary constructors contains no elements, the function returns `true` because there are no
     * elements in it that do not match the predicate.
     *
     * @param predicate A function that defines the condition to be met by secondary constructor declarations.
     * @return `true` if all secondary constructor declarations satisfy the predicate, `false` otherwise.
     */
    fun hasAllSecondaryConstructors(predicate: (KoSecondaryConstructorDeclaration) -> Boolean): Boolean
}
