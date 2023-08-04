package com.lemonappdev.konsist.api.ext.list.modifierprovider

import com.lemonappdev.konsist.api.provider.modifier.KoInnerModifierProvider

/**
 * List containing elements that have `inner` modifier.
 *
 * @return A list containing elements with the `inner` modifier.
 */
fun <T : KoInnerModifierProvider> List<T>.withInnerModifier(): List<T> = filter { it.hasInnerModifier }

/**
 * List containing elements that don't have `inner` modifier.
 *
 * @return A list containing elements without the `inner` modifier.
 */
fun <T : KoInnerModifierProvider> List<T>.withoutInnerModifier(): List<T> = filterNot { it.hasInnerModifier }
