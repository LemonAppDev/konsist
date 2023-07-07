package com.lemonappdev.konsist.api.declaration

import com.lemonappdev.konsist.api.provider.KoModifierProvider
import com.lemonappdev.konsist.api.provider.KoParentProvider

/**
 * Object declaration.
 */
interface KoObjectDeclaration :
    KoComplexDeclaration,
    KoBaseDeclaration,
    KoParentProvider,
    KoModifierProvider {

    /**
     * Whether this object has a companion modifier.
     *
     * @return `true` if the object has the `companion` modifier, `false` otherwise.
     */
    fun hasCompanionModifier(): Boolean
}
