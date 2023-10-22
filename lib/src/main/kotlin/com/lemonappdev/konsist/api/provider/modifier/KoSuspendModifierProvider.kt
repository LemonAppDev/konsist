package com.lemonappdev.konsist.api.provider.modifier

import com.lemonappdev.konsist.api.provider.KoBaseProvider

/**
 * An interface representing a Kotlin declaration that provides information about whether it has `suspend` modifier.
 */
interface KoSuspendModifierProvider : KoBaseProvider, KoModifierProvider {
    /**
     * Determines whatever the declaration has `suspend` modifier.
     */
    val hasSuspendModifier: Boolean
}
