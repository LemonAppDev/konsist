package com.lemonappdev.konsist.api.provider.modifier

import com.lemonappdev.konsist.api.provider.KoBaseProvider

/**
 * An interface representing a Kotlin declaration that provides information about whether it has `tailrec` modifier.
 */
interface KoTailrecModifierProvider : KoBaseProvider, KoModifierProvider {
    /**
     * Whether the declaration has `tailrec` modifier.
     */
    val hasTailrecModifier: Boolean
}
