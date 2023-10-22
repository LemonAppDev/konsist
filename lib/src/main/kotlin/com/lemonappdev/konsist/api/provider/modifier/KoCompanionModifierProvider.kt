package com.lemonappdev.konsist.api.provider.modifier

import com.lemonappdev.konsist.api.provider.KoBaseProvider

/**
 * An interface representing a Kotlin declaration that provides information about whether it has `companion` modifier.
 */
interface KoCompanionModifierProvider : KoBaseProvider, KoModifierProvider {
    /**
     * Determines whatever the declaration has a `companion` modifier.
     */
    val hasCompanionModifier: Boolean
}
