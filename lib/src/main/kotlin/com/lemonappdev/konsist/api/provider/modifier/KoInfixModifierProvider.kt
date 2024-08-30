package com.lemonappdev.konsist.api.provider.modifier

import com.lemonappdev.konsist.api.provider.KoBaseProvider

/**
 * An interface representing a Kotlin declaration that provides information about whether it has `infix` modifier.
 */
interface KoInfixModifierProvider : KoBaseProvider, KoModifierProvider {
    /**
     * Determines whatever the declaration has `infix` modifier.
     */
    val hasInfixModifier: Boolean
}
