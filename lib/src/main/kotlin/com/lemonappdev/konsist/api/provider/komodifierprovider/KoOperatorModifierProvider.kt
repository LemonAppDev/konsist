package com.lemonappdev.konsist.api.provider.komodifierprovider

import com.lemonappdev.konsist.api.provider.KoBaseProvider

/**
 * An interface representing a Kotlin declaration that provides information about whether it has `operator` modifier.
 */
interface KoOperatorModifierProvider : KoBaseProvider, KoModifierProvider {
    /**
     * Whether the declaration has `operator` modifier.
     */
    val hasOperatorModifier: Boolean
}
