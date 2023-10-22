package com.lemonappdev.konsist.api.provider.modifier

import com.lemonappdev.konsist.api.provider.KoBaseProvider

/**
 * An interface representing a Kotlin declaration that provides information about whether it has `const` modifier.
 */
interface KoConstModifierProvider : KoBaseProvider, KoModifierProvider {
    /**
     * Determines whatever the declaration has `const` modifier.
     */
    val hasConstModifier: Boolean
}
