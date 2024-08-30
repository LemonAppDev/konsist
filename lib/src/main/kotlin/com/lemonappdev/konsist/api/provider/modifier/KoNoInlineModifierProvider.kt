package com.lemonappdev.konsist.api.provider.modifier

import com.lemonappdev.konsist.api.provider.KoBaseProvider

/**
 * An interface representing a Kotlin declaration that provides information about whether it has `noinline` modifier.
 */
interface KoNoInlineModifierProvider :
    KoBaseProvider,
    KoModifierProvider {
    /**
     * Determines whatever the declaration has `noinline` modifier.
     */
    val hasNoInlineModifier: Boolean
}
