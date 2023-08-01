package com.lemonappdev.konsist.api.provider.komodifierprovider

import com.lemonappdev.konsist.api.provider.KoBaseProvider

/**
 * An interface representing a Kotlin declaration that provides information about whether it has `data` modifier.
 */
interface KoDataModifierProvider : KoBaseProvider, KoModifierProvider {
    /**
     * Whatever declaration has `data` modifier.
     */
    val hasDataModifier: Boolean
}
