package com.lemonappdev.konsist.api.provider.modifier

import com.lemonappdev.konsist.api.KoModifier
import com.lemonappdev.konsist.api.provider.KoBaseProvider

/**
 * An interface representing a Kotlin declaration that provides access to its modifiers.
 */
interface KoModifierProvider : KoBaseProvider {
    /**
     * List of modifiers.
     */
    val modifiers: List<KoModifier>

    /**
     * The number of modifiers.
     */
    val numModifiers: Int

    /**
     * Whether the declaration has modifiers.
     *
     * @param koModifiers the modifiers to check.
     * @return `true` if the declaration has all the specified modifiers (or any modifier if [koModifiers] is empty), `false` otherwise.
     */
    fun hasModifiers(vararg koModifiers: KoModifier): Boolean
}
