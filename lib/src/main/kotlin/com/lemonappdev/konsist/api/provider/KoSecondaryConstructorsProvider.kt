package com.lemonappdev.konsist.api.provider

import com.lemonappdev.konsist.api.declaration.KoSecondaryConstructorDeclaration

interface KoSecondaryConstructorsProvider : KoBaseProvider {
    /**
     * The secondary constructors of the declaration.
     */
    val secondaryConstructors: Sequence<KoSecondaryConstructorDeclaration>

    /**
     * Whatever declaration has secondary constructors.
     *
     * @return `true` if the declaration has secondary constructors, `false` otherwise.
     */
    fun hasSecondaryConstructors(): Boolean
}
