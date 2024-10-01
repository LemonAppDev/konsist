package com.lemonappdev.konsist.api.provider.modifier

import com.lemonappdev.konsist.api.provider.KoBaseProvider

/**
 * An interface representing a Kotlin declaration that provides information about whether it has `fun` modifier.
 */
@Deprecated("Will be removed in version 0.19.0", ReplaceWith("koScope.functions()"))
interface KoFunModifierProvider :
    KoBaseProvider,
    KoModifierProvider {
    /**
     * Determines whatever declaration has a `fun` modifier.
     */
    @Deprecated("Will be removed in version 0.19.0", ReplaceWith("koScope.functions()"))
    val hasFunModifier: Boolean
}
