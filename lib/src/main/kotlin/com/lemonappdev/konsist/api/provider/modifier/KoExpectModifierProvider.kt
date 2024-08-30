package com.lemonappdev.konsist.api.provider.modifier

import com.lemonappdev.konsist.api.provider.KoBaseProvider

/**
 * An interface representing a Kotlin declaration that provides information about whether it has `expect` modifier.
 */
interface KoExpectModifierProvider :
    KoBaseProvider,
    KoModifierProvider {
    /**
     * Determines whatever declaration has `expect` modifier.
     */
    val hasExpectModifier: Boolean
}
