package com.lemonappdev.konsist.api.provider.modifier

import com.lemonappdev.konsist.api.provider.KoBaseProvider

/**
 * An interface representing a Kotlin declaration that provides information about whether it has `operator` modifier.
 */
interface KoOperatorModifierProvider :
    KoBaseProvider,
    KoModifierProvider {
    /**
     * Determines whatever the declaration has `operator` modifier.
     */
    val hasOperatorModifier: Boolean
}
