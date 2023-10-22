package com.lemonappdev.konsist.api.provider.modifier

import com.lemonappdev.konsist.api.provider.KoBaseProvider

/**
 * An interface representing a Kotlin declaration that provides information about whether it has `inner` modifier.
 */
interface KoInnerModifierProvider : KoBaseProvider, KoModifierProvider {
    /**
     * Determines whatever declaration has `inner` modifier.
     */
    val hasInnerModifier: Boolean
}
