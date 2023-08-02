package com.lemonappdev.konsist.api.ext.list.modifierprovider

import com.lemonappdev.konsist.api.provider.modifier.KoValueModifierProvider

/**
 * List containing all declarations that have `value` modifier.
 *
 * @return A list containing declarations with the `value` modifier.
 */
fun <T : KoValueModifierProvider> List<T>.withValueModifier(): List<T> = filter { it.hasValueModifier }

/**
 * List containing all declarations that don't have `value` modifier.
 *
 * @return A list containing declarations without the `value` modifier.
 */
fun <T : KoValueModifierProvider> List<T>.withoutValueModifier(): List<T> = filterNot { it.hasValueModifier }
