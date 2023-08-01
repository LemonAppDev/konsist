package com.lemonappdev.konsist.api.provider.komodifierprovider

import com.lemonappdev.konsist.api.provider.KoBaseProvider

/**
 * An interface representing a Kotlin declaration that provides information about whether it has `inline` modifier.
 */
interface KoInlineModifierProvider : KoBaseProvider, KoModifierProvider {
    /**
     * Whether the declaration has `inline` modifier.
     */
    val hasInlineModifier: Boolean
}
