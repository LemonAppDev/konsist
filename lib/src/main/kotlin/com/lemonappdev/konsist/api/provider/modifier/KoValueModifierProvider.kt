package com.lemonappdev.konsist.api.provider.modifier

import com.lemonappdev.konsist.api.provider.KoBaseProvider

/**
 * An interface representing a Kotlin declaration that provides information about whether it has `value` modifier.
 */
interface KoValueModifierProvider : KoBaseProvider, KoModifierProvider {
    /**
     * Determines whatever declaration has `value` modifier.
     */
    val hasValueModifier: Boolean
}
