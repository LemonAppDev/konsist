package com.lemonappdev.konsist.api.provider.modifier

import com.lemonappdev.konsist.api.provider.KoBaseProvider

/**
 * An interface representing a Kotlin declaration that provides information about whether it has `sealed` modifier.
 */
interface KoSealedModifierProvider : KoBaseProvider, KoModifierProvider {
    /**
     * Whatever declaration has `sealed` modifier.
     */
    val hasSealedModifier: Boolean
}
