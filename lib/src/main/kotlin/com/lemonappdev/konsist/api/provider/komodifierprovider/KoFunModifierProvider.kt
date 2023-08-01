package com.lemonappdev.konsist.api.provider.komodifierprovider

import com.lemonappdev.konsist.api.provider.KoBaseProvider

/**
 * An interface representing a Kotlin declaration that provides information about whether it has `fun` modifier.
 */
interface KoFunModifierProvider : KoBaseProvider, KoModifierProvider {
    /**
     * Whatever declaration has a `fun` modifier.
     */
    val hasFunModifier: Boolean
}
