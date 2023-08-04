package com.lemonappdev.konsist.api.ext.list.modifierprovider

import com.lemonappdev.konsist.api.provider.modifier.KoActualModifierProvider

/**
 * List containing elements that have `actual` modifier.
 *
 * @return A list containing elements with the `actual` modifier.
 */
fun <T : KoActualModifierProvider> List<T>.withActualModifier(): List<T> = filter { it.hasActualModifier }

/**
 * List containing elements that don't have `actual` modifier.
 *
 * @return A list containing elements without the `actual` modifier.
 */
fun <T : KoActualModifierProvider> List<T>.withoutActualModifier(): List<T> = filterNot { it.hasActualModifier }
