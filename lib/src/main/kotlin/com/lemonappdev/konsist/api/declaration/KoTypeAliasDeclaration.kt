package com.lemonappdev.konsist.api.declaration

import com.lemonappdev.konsist.api.provider.KoParentProvider

/**
 * Represents a Kotlin type alias declaration.
 */
interface KoTypeAliasDeclaration :
    KoDeclaration,
    KoBaseDeclaration,
    KoParentProvider {
    /**
     * Type alias type.
     */
    val type: KoTypeDeclaration

    /**
     * Whether the type alias has actual modifier.
     *
     * @return `true` if the type alias has the `actual` modifier, `false` otherwise.
     */
    fun hasActualModifier(): Boolean
}
