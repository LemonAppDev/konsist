package com.lemonappdev.konsist.api.provider

import com.lemonappdev.konsist.api.declaration.KoSecondaryConstructorDeclaration

/**
 * An interface representing a Kotlin declaration that provides access to its secondary constructors.
 */
interface KoSecondaryConstructorsProvider : KoBaseProvider {
    /**
     * The secondary constructors of the declaration.
     */
    val secondaryConstructors: Sequence<KoSecondaryConstructorDeclaration>

    /**
     * Whatever declaration has secondary constructors.
     */
    val hasSecondaryConstructors: Boolean
}
