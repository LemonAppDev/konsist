package com.lemonappdev.konsist.api.ext.list.modifierprovider

import com.lemonappdev.konsist.api.provider.modifier.KoCrossInlineModifierProvider

/**
 * List containing elements with `crossinline` modifier.
 *
 * @return A list containing elements with the `crossinline` modifier.
 */
fun <T : KoCrossInlineModifierProvider> List<T>.withCrossInlineModifier(): List<T> = filter { it.hasCrossInlineModifier }

/**
 * List containing elements without `crossinline` modifier.
 *
 * @return A list containing elements without the `crossinline` modifier.
 */
fun <T : KoCrossInlineModifierProvider> List<T>.withoutCrossInlineModifier(): List<T> = filterNot { it.hasCrossInlineModifier }
