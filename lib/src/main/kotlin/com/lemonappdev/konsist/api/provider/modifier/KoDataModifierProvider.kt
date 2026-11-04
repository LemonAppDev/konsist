package com.lemonappdev.konsist.api.provider.modifier

import com.lemonappdev.konsist.api.provider.KoBaseProvider

/**
 * An interface representing a Kotlin declaration that provides information about whether it has `data` modifier.
 */
interface KoDataModifierProvider :
    KoBaseProvider,
    KoModifierProvider {
    /**
     * Determines whether the declaration has the `data` modifier.
     */
    val hasDataModifier: Boolean
}
