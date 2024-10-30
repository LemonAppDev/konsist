package com.lemonappdev.konsist.api.provider.modifier

import com.lemonappdev.konsist.api.provider.KoBaseProvider

/**
 * An interface representing a Kotlin declaration that provides information about whether it has a `val` modifier.
 */
@Deprecated("Will be removed in version 0.19.0", ReplaceWith("KoIsValProvider"))
interface KoValModifierProvider : KoBaseProvider {
    /**
     * Determines whatever the declaration has `val` modifier.
     */
    @Deprecated("Will be removed in version 0.19.0", ReplaceWith("isVal"))
    val hasValModifier: Boolean
}
