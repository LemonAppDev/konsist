package com.lemonappdev.konsist.api.provider.modifier

import com.lemonappdev.konsist.api.provider.KoBaseProvider

/**
 * An interface representing a Kotlin declaration that provides access to its visibility modifiers.
 */
interface KoVisibilityModifierProvider : KoBaseProvider, KoModifierProvider {
    /**
     * Determines whatever the declaration has public modifier.
     */
    val hasPublicModifier: Boolean

    /**
     * Determines whatever the declaration has public or no visibility modifier.
     */
    val hasPublicOrDefaultModifier: Boolean

    /**
     * Determines whatever the declaration has private modifier.
     */
    val hasPrivateModifier: Boolean

    /**
     * Determines whatever the declaration has protected modifier.
     */
    val hasProtectedModifier: Boolean

    /**
     * Determines whatever the declaration has internal modifier.
     */
    val hasInternalModifier: Boolean
}
