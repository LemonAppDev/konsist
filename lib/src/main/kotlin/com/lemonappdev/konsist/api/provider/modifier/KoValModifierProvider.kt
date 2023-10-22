package com.lemonappdev.konsist.api.provider.modifier

import com.lemonappdev.konsist.api.provider.KoBaseProvider

/**
 * An interface representing a Kotlin declaration that provides information about whether it has a `val` modifier.
 */
interface KoValModifierProvider : KoBaseProvider, KoModifierProvider {
    /**
     * Determines whatever the declaration has `val` modifier.
     */
    val hasValModifier: Boolean
}
