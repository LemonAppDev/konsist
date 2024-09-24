package com.lemonappdev.konsist.api.ext.list.provider.modifierprovider

import com.lemonappdev.konsist.api.provider.modifier.KoActualModifierProvider

/**
 * List containing declarations that have `actual` modifier.
 *
 * @return A list containing declarations with the `actual` modifier.
 */
fun <T : KoActualModifierProvider> List<T>.withActualModifier(): List<T> = filter { it.hasActualModifier }

/**
 * List containing declarations that don't have `actual` modifier.
 *
 * @return A list containing declarations without the `actual` modifier.
 */
fun <T : KoActualModifierProvider> List<T>.withoutActualModifier(): List<T> = filterNot { it.hasActualModifier }
