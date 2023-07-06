package com.lemonappdev.konsist.api.provider

import com.lemonappdev.konsist.api.KoModifier

interface KoModifierProvider {
    /**
     * List of modifiers.
     */
    val modifiers: List<KoModifier>

    /**
     * Whether the declaration has modifiers.
     *
     * @param koModifiers the modifiers to check.
     * @return `true` if the declaration has all the specified modifiers (or any modifier if [koModifiers] is empty), `false` otherwise.
     */
    fun hasModifiers(vararg koModifiers: KoModifier): Boolean

    /**
     * Whether the declaration has public modifier.
     *
     * @return `true` if the declaration has the `public` modifier, `false` otherwise.
     */
    fun hasPublicModifier(): Boolean

    /**
     * Whether the declaration has public or no visibility modifier.
     *
     * @return `true` if the declaration has the `public` or no visibility modifier, `false` otherwise.
     */
    fun isPublicOrDefault(): Boolean

    /**
     * Whether the declaration has private modifier.
     *
     * @return `true` if the declaration has the `private` modifier, `false` otherwise.
     */
    fun hasPrivateModifier(): Boolean

    /**
     * Whether the declaration has protected modifier.
     *
     * @return `true` if the declaration has the `protected` modifier, `false` otherwise.
     */
    fun hasProtectedModifier(): Boolean

    /**
     * Whether the declaration has internal modifier.
     *
     * @return `true` if the declaration has the `internal` modifier, `false` otherwise.
     */
    fun hasInternalModifier(): Boolean
}
