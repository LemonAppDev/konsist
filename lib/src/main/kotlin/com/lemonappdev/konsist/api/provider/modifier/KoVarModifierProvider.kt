package com.lemonappdev.konsist.api.provider.modifier

import com.lemonappdev.konsist.api.provider.KoBaseProvider

/**
 * An interface representing a Kotlin declaration that provides information about whether it has a `var` modifier.
 */
interface KoVarModifierProvider : KoBaseProvider, KoModifierProvider {
    /**
     * Determines whatever the declaration has `var` modifier.
     */
    val hasVarModifier: Boolean
}
