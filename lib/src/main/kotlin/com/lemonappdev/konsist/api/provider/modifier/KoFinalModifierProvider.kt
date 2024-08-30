package com.lemonappdev.konsist.api.provider.modifier

import com.lemonappdev.konsist.api.provider.KoBaseProvider

/**
 * An interface representing a Kotlin declaration that provides information about whether it has `final` modifier.
 */
interface KoFinalModifierProvider :
    KoBaseProvider,
    KoModifierProvider {
    /**
     * Determines whatever declaration has `final` modifier.
     */
    val hasFinalModifier: Boolean
}
