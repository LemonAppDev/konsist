package com.lemonappdev.konsist.api.provider.modifier

import com.lemonappdev.konsist.api.provider.KoBaseProvider

/**
 * An interface representing a Kotlin declaration that provides information about whether it has `annotation` modifier.
 */
interface KoAnnotationModifierProvider :
    KoBaseProvider,
    KoModifierProvider {
    /**
     * Determines whatever declaration has `annotation` modifier.
     */
    val hasAnnotationModifier: Boolean
}
