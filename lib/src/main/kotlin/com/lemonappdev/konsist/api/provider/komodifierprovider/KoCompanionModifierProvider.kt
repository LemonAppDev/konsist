package com.lemonappdev.konsist.api.provider.komodifierprovider

import com.lemonappdev.konsist.api.provider.KoBaseProvider

/**
 * An interface representing a Kotlin declaration that provides information about whether it has `companion` modifier.
 */
interface KoCompanionModifierProvider : KoBaseProvider, KoModifierProvider {
    /**
     * Whether the declaration has a `companion` modifier.
     */
    val hasCompanionModifier: Boolean
}
