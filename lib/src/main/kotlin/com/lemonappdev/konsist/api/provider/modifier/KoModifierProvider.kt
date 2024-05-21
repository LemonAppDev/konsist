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
     * Determines whatever the declaration has modifiers.
     *
     * @return `true` if the declaration has modifier, `false` otherwise.
     */
    fun hasModifiers(): Boolean

    /**
     * Determines whether the declaration has at least one specified modifier.
     *
     * @param modifier the modifier to check.
     * @param modifiers the modifiers to check.
     * @return `true` if there is a matching declaration, `false` otherwise.
     */
    fun hasModifier(
        modifier: KoModifier,
        vararg modifiers: KoModifier,
    ): Boolean

    /**
     * Determines whether the declaration has at least one specified modifier.
     *
     * @param modifiers the modifiers to check.
     * @return `true` if there is a matching declaration, `false` otherwise.
     */
    fun hasModifier(modifiers: Collection<KoModifier>): Boolean

    /**
     * Determines whether the declaration has all specified modifiers.
     *
     * @param modifier the modifier to check.
     * @param modifiers the modifiers to check.
     * @return `true` if there are declarations with all the specified modifiers, `false` otherwise.
     */
    fun hasAllModifiers(
        modifier: KoModifier,
        vararg modifiers: KoModifier,
    ): Boolean

    /**
     * Determines whether the declaration has all specified modifiers.
     *
     * @param modifiers the modifiers to check.
     * @return `true` if there are declarations with all the specified modifiers, `false` otherwise.
     */
    fun hasAllModifiers(modifiers: Collection<KoModifier>): Boolean
}
