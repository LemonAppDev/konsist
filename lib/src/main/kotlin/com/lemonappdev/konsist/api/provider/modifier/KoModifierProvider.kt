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
     * @param koModifiers the modifiers to check.
     * @return `true` if the declaration has all the specified modifiers (or any modifier if [koModifiers] is empty), `false` otherwise.
     */
    @Deprecated(
        """
            Will be removed in v0.16.0. 
            If you passed one argument - replace with `hasModifier`, otherwise with `hasAllModifiers`.
            """,
    )
    fun hasModifiers(vararg koModifiers: KoModifier): Boolean

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
}
