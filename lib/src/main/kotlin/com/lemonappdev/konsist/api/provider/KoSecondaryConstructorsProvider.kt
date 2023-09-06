package com.lemonappdev.konsist.api.provider

import com.lemonappdev.konsist.api.declaration.KoAnnotationDeclaration
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
     * Whatever declaration has secondary constructors.
     */
    val hasSecondaryConstructors: Boolean

    /**
     * Gets the number of secondary constructors that satisfies the specified predicate present in the declaration.
     *
     * @param predicate The predicate function to determine if a secondary constructor satisfies a condition.
     * @return The number of secondary constructors in the declaration.
     */
    fun countSecondaryConstructors(predicate: (KoSecondaryConstructorDeclaration) -> Boolean): Int
}
