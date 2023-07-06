package com.lemonappdev.konsist.api.declaration

import com.lemonappdev.konsist.api.provider.KoParentProvider

/**
 * Object declaration.
 */
interface KoObjectDeclaration :
    KoComplexDeclaration,
    KoBaseDeclaration,
    KoParentProvider {
    /**
     * Whether this object has a data modifier.
     *
     * @return `true` if the object has the `data` modifier, `false` otherwise.
     */
    fun hasDataModifier(): Boolean

    /**
     * Whether this object has a companion modifier.
     *
     * @return `true` if the object has the `companion` modifier, `false` otherwise.
     */
    fun hasCompanionModifier(): Boolean
}
