package com.lemonappdev.konsist.api.provider.modifier

import com.lemonappdev.konsist.api.provider.KoBaseProvider

/**
 * An interface representing a Kotlin declaration that provides information about whether it has `abstract` modifier.
 */
interface KoAbstractModifierProvider :
    KoBaseProvider,
    KoModifierProvider {
    /**
     * Determines whatever declaration has `abstract` modifier.
     */
    val hasAbstractModifier: Boolean
}
