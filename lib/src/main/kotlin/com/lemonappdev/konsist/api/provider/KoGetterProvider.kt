package com.lemonappdev.konsist.api.provider

import com.lemonappdev.konsist.api.declaration.KoGetterDeclaration

/**
 * An interface representing a Kotlin declaration that provides access to the getter declaration.
 */
interface KoGetterProvider : KoBaseProvider {
    /**
     * The getter of the declaration.
     */
    val getter: KoGetterDeclaration?

    /**
     * Determines whether the declaration has a getter.
     */
    val hasGetter: Boolean
}
