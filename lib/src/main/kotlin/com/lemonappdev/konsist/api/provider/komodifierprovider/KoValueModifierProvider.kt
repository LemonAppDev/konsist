package com.lemonappdev.konsist.api.provider.komodifierprovider

import com.lemonappdev.konsist.api.provider.KoBaseProvider

/**
 * An interface representing a Kotlin declaration that provides information about whether it has `value` modifier.
 */
interface KoValueModifierProvider : KoBaseProvider, KoModifierProvider {
    /**
     * Whatever declaration has `value` modifier.
     */
    val hasValueModifier: Boolean
}
