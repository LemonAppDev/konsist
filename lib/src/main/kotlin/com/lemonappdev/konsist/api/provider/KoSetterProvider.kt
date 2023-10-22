package com.lemonappdev.konsist.api.provider

import com.lemonappdev.konsist.api.declaration.KoSetterDeclaration

/**
 * An interface representing a Kotlin declaration that provides access to setter declaration.
 */
interface KoSetterProvider : KoBaseProvider {
    /**
     * The setter of the declaration.
     */
    val setter: KoSetterDeclaration?

    /**
     * Determines whatever declaration has setter.
     */
    val hasSetter: Boolean
}
