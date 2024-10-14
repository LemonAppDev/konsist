package com.lemonappdev.konsist.api.provider.modifier

import com.lemonappdev.konsist.api.provider.KoBaseProvider

/**
 * An interface representing a Kotlin declaration that provides information about whether it has `out` modifier.
 */
interface KoOutModifierProvider :
    KoBaseProvider,
    KoModifierProvider {
    /**
     * Determines whatever the declaration has `out` modifier.
     */
    val hasOutModifier: Boolean
}
