package com.lemonappdev.konsist.api.provider

import com.lemonappdev.konsist.api.declaration.KoSetterDeclaration

/**
 * An interface representing a Kotlin declaration that provides access to its setter declaration.
 */
interface KoSetterProvider : KoBaseProvider {
    /**
     * The setter of the declaration.
     */
    val setter: KoSetterDeclaration?

    /**
     * Determines whether the declaration has a setter.
     */
    val hasSetter: Boolean
}
