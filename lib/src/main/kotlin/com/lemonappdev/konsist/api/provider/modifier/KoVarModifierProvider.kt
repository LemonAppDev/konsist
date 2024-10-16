package com.lemonappdev.konsist.api.provider.modifier

import com.lemonappdev.konsist.api.provider.KoBaseProvider

/**
 * An interface representing a Kotlin declaration that provides information about whether it has a `var` modifier.
 */
@Deprecated("Will be removed in version 0.18.0", ReplaceWith(""))
interface KoVarModifierProvider : KoBaseProvider {
    /**
     * Determines whatever the declaration has `var` modifier.
     */
    @Deprecated("Will be removed in version 0.18.0", ReplaceWith("isVar"))
    val hasVarModifier: Boolean
}
