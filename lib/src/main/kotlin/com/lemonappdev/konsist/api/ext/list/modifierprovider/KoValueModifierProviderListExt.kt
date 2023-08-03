package com.lemonappdev.konsist.api.ext.list.modifierprovider

import com.lemonappdev.konsist.api.provider.modifier.KoValueModifierProvider

/**
 * List containing elements that have `value` modifier.
 *
 * @return A list containing elements with the `value` modifier.
 */
fun <T : KoValueModifierProvider> List<T>.withValueModifier(): List<T> = filter { it.hasValueModifier }

/**
 * List containing elements that don't have `value` modifier.
 *
 * @return A list containing elements without the `value` modifier.
 */
fun <T : KoValueModifierProvider> List<T>.withoutValueModifier(): List<T> = filterNot { it.hasValueModifier }
