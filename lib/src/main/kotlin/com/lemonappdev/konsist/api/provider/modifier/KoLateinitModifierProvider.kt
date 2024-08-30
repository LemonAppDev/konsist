package com.lemonappdev.konsist.api.provider.modifier

import com.lemonappdev.konsist.api.provider.KoBaseProvider

/**
 * An interface representing a Kotlin declaration that provides information about whether it has `lateinit` modifier.
 */
interface KoLateinitModifierProvider :
    KoBaseProvider,
    KoModifierProvider {
    /**
     * Determines whatever the declaration has `lateinit` modifier.
     */
    val hasLateinitModifier: Boolean
}
