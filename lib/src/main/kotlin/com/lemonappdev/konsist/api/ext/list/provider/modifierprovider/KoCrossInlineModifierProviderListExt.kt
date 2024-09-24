package com.lemonappdev.konsist.api.ext.list.provider.modifierprovider

import com.lemonappdev.konsist.api.provider.modifier.KoCrossInlineModifierProvider

/**
 * List containing declarations with `crossinline` modifier.
 *
 * @return A list containing declarations with the `crossinline` modifier.
 */
fun <T : KoCrossInlineModifierProvider> List<T>.withCrossInlineModifier(): List<T> = filter { it.hasCrossInlineModifier }

/**
 * List containing declarations without `crossinline` modifier.
 *
 * @return A list containing declarations without the `crossinline` modifier.
 */
fun <T : KoCrossInlineModifierProvider> List<T>.withoutCrossInlineModifier(): List<T> = filterNot { it.hasCrossInlineModifier }
