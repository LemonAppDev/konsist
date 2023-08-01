package com.lemonappdev.konsist.api.provider.komodifierprovider

import com.lemonappdev.konsist.api.KoModifier
import com.lemonappdev.konsist.api.provider.KoBaseProvider

/**
 * An interface representing a Kotlin declaration that provides access to its visibility modifiers.
 */
interface KoVisibilityModifierProvider : KoBaseProvider, KoModifierProvider {
    /**
     * Whether the declaration has public modifier.
     */
    val hasPublicModifier: Boolean

    /**
     * Whether the declaration has public or no visibility modifier.
     */
    val isPublicOrDefault: Boolean

    /**
     * Whether the declaration has private modifier.
     */
    val hasPrivateModifier: Boolean

    /**
     * Whether the declaration has protected modifier.
     */
    val hasProtectedModifier: Boolean

    /**
     * Whether the declaration has internal modifier.
     */
    val hasInternalModifier: Boolean
}
