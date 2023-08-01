package com.lemonappdev.konsist.api.provider.komodifierprovider

import com.lemonappdev.konsist.api.provider.KoBaseProvider

/**
 * An interface representing a Kotlin declaration that provides information about whether it has `noinline` modifier.
 */
interface KoNoInlineModifierProvider : KoBaseProvider, KoModifierProvider {
    /**
     * Whether the declaration has `noinline` modifier.
     */
    val hasNoInlineModifier: Boolean
}
