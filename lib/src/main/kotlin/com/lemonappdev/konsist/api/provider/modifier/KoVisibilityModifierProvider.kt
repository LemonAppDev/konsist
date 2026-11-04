package com.lemonappdev.konsist.api.provider.modifier

import com.lemonappdev.konsist.api.provider.KoBaseProvider

/**
 * An interface representing a Kotlin declaration that provides access to its visibility modifiers.
 */
interface KoVisibilityModifierProvider :
    KoBaseProvider,
    KoModifierProvider {
    /**
     * Determines whether the declaration has the `public` modifier.
     */
    val hasPublicModifier: Boolean

    /**
     * Determines whether the declaration has the `public` modifier or no visibility modifier.
     */
    val hasPublicOrDefaultModifier: Boolean

    /**
     * Determines whether the declaration has the `private` modifier.
     */
    val hasPrivateModifier: Boolean

    /**
     * Determines whether the declaration has the `protected` modifier.
     */
    val hasProtectedModifier: Boolean

    /**
     * Determines whether the declaration has the `internal` modifier.
     */
    val hasInternalModifier: Boolean
}
