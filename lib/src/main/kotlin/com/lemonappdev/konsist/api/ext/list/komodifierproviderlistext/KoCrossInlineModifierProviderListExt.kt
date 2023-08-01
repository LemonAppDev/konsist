package com.lemonappdev.konsist.api.ext.list.komodifierproviderlistext

import com.lemonappdev.konsist.api.provider.komodifierprovider.KoCrossInlineModifierProvider

/**
 * List containing all declarations with `crossinline` modifier.
 *
 * @return A list containing declarations with the `crossinline` modifier.
 */
fun <T : KoCrossInlineModifierProvider> List<T>.withCrossInlineModifier(): List<T> = filter { it.hasCrossInlineModifier }

/**
 * List containing all declarations without `crossinline` modifier.
 *
 * @return A list containing declarations without the `crossinline` modifier.
 */
fun <T : KoCrossInlineModifierProvider> List<T>.withoutCrossInlineModifier(): List<T> = filterNot { it.hasCrossInlineModifier }
