package com.lemonappdev.konsist.api.provider.modifier

import com.lemonappdev.konsist.api.provider.KoBaseProvider

/**
 * An interface representing a Kotlin declaration that provides information about whether it has `override` modifier.
 */
interface KoOverrideModifierProvider : KoBaseProvider, KoModifierProvider {
    /**
     * Determines whatever the declaration has `override` modifier.
     */
    val hasOverrideModifier: Boolean
}
