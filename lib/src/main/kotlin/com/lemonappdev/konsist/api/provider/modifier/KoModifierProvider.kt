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
     * Gets the number of modifiers that satisfies the specified predicate present in the declaration.
     *
     * @param predicate The predicate function to determine if a modifier satisfies a condition.
     * @return The number of modifiers in the declaration.
     */
    fun countModifiers(predicate: (KoModifier) -> Boolean): Int

    /**
     * Whether the declaration has modifiers.
     *
     * @param koModifiers the modifiers to check.
     * @return `true` if the declaration has all the specified modifiers (or any modifier if [koModifiers] is empty), `false` otherwise.
     */
    @Deprecated(
        """
            Will be removed in v1.0.0. 
            If you passed one argument - replace with `hasModifier`, otherwise with `hasAllModifiers`.
            """,
    )
    fun hasModifiers(vararg koModifiers: KoModifier): Boolean

    /**
     * Whatever the declaration has modifiers.
     *
     * @return `true` if the declaration has modifier, `false` otherwise.
     */
    fun hasModifiers(): Boolean

    /**
     * Determines whether the declaration has at least one specified modifier.
     *
     * @param modifiers the modifiers to check.
     * @return `true` if there is a matching declaration, `false` otherwise.
     */
    fun hasModifier(vararg modifiers: KoModifier): Boolean

    /**
     * Determines whether the declaration has all specified modifiers.
     *
     * @param modifiers the modifiers to check.
     * @return `true` if there are declarations with all the specified modifiers, `false` otherwise.
     */
    fun hasAllModifiers(vararg modifiers: KoModifier): Boolean
}
