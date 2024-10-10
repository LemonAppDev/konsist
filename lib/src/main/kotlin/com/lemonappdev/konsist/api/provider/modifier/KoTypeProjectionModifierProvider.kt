package com.lemonappdev.konsist.api.provider.modifier

import com.lemonappdev.konsist.api.provider.KoBaseProvider

/**
 * An interface representing a Kotlin declaration that provides information about whether it has `in` or `out` modifier.
 */
interface KoTypeProjectionModifierProvider :
    KoBaseProvider,
    KoModifierProvider {
    /**
     * Determines whatever the declaration has `in` modifier.
     */
    val hasInModifier: Boolean

    /**
     * Determines whatever the declaration has `out` modifier.
     */
    val hasOutModifier: Boolean
}
