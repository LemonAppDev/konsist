package com.lemonappdev.konsist.api.provider.modifier

import com.lemonappdev.konsist.api.provider.KoBaseProvider

/**
 * An interface representing a Kotlin declaration that provides information about whether it has `enum` modifier.
 */
interface KoEnumModifierProvider : KoBaseProvider, KoModifierProvider {
    /**
     * Determines whatever declaration has `enum` modifier.
     */
    val hasEnumModifier: Boolean
}
