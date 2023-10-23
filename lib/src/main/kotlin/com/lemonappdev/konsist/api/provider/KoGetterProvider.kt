package com.lemonappdev.konsist.api.provider

import com.lemonappdev.konsist.api.declaration.KoGetterDeclaration

/**
 * An interface representing a Kotlin declaration that provides access to getter declaration.
 */
interface KoGetterProvider : KoBaseProvider {
    /**
     * The getter of the declaration.
     */
    val getter: KoGetterDeclaration?

    /**
     * Determines whatever declaration has getter.
     */
    val hasGetter: Boolean
}
