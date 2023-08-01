package com.lemonappdev.konsist.api.provider.komodifierprovider

import com.lemonappdev.konsist.api.provider.KoBaseProvider

/**
 * An interface representing a Kotlin declaration that provides information about whether it has a `var` modifier.
 */
interface KoVarModifierProvider : KoBaseProvider, KoModifierProvider {
    /**
     * Whether the declaration has `var` modifier.
     */
    val hasVarModifier: Boolean
}
