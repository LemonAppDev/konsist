package com.lemonappdev.konsist.api.provider.modifier

import com.lemonappdev.konsist.api.provider.KoBaseProvider

/**
 * An interface representing a Kotlin declaration that provides information about whether it has `external` modifier.
 */
interface KoExternalModifierProvider :
    KoBaseProvider,
    KoModifierProvider {
    /**
     * Determines whether the declaration has the `external` modifier.
     */
    val hasExternalModifier: Boolean
}
