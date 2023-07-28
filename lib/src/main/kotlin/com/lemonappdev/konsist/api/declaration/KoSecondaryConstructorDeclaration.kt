package com.lemonappdev.konsist.api.declaration

import com.lemonappdev.konsist.api.provider.KoKDocProvider

/**
 * Represents a Kotlin secondary constructor declaration.
 */
interface KoSecondaryConstructorDeclaration :
    KoConstructorDeclaration,
    KoKDocProvider {
    /**
     * String representing the secondary constructor.
     *
     * @return a string representing the secondary constructor.
     */
    override fun toString(): String
}
