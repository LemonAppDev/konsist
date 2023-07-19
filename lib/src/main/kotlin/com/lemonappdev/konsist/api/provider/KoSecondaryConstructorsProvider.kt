package com.lemonappdev.konsist.api.provider

import com.lemonappdev.konsist.api.declaration.KoSecondaryConstructorDeclaration

interface KoSecondaryConstructorsProvider : KoProvider {
    /**
     * The secondary constructors of the declaration.
     */
    val secondaryConstructors: List<KoSecondaryConstructorDeclaration>

    /**
     * Whatever declaration has secondary constructors.
     *
     * @return `true` if the declaration has secondary constructors, `false` otherwise.
     */
    fun hasSecondaryConstructors(): Boolean
}
