package com.lemonappdev.konsist.api.declaration

import com.lemonappdev.konsist.api.provider.KoBaseProvider

/**
 * Represents a Kotlin base declaration.
 */
interface KoBaseDeclaration : KoBaseProvider {
    /**
     * String representing the declaration.
     *
     * @return a string representing the declaration.
     */
    override fun toString(): String
}
