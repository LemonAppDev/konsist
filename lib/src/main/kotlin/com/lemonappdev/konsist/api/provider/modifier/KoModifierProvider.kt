package com.lemonappdev.konsist.api.provider.modifier

import com.lemonappdev.konsist.api.KoModifier
import com.lemonappdev.konsist.api.declaration.KoAnnotationDeclaration
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
    fun hasModifiers(vararg koModifiers: KoModifier): Boolean
}
