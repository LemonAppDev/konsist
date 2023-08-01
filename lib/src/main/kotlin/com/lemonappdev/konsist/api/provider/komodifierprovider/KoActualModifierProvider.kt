package com.lemonappdev.konsist.api.provider.komodifierprovider

import com.lemonappdev.konsist.api.provider.KoBaseProvider

/**
 * An interface representing a Kotlin declaration that provides information about whether it has `actual` modifier.
 */
interface KoActualModifierProvider : KoBaseProvider, KoModifierProvider {
    /**
     * Whatever class has `actual` modifier.
     */
    val hasActualModifier: Boolean
}
