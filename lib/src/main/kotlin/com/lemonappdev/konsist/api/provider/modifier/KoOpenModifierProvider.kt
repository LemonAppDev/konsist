package com.lemonappdev.konsist.api.provider.modifier

import com.lemonappdev.konsist.api.provider.KoBaseProvider

/**
 * An interface representing a Kotlin declaration that provides information about whether it has `open` modifier.
 */
interface KoOpenModifierProvider : KoBaseProvider, KoModifierProvider {
    /**
     * Whatever declaration has `open` modifier.
     */
    val hasOpenModifier: Boolean
}
