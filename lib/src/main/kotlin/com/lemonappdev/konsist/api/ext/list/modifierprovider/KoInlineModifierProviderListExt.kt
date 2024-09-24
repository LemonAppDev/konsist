package com.lemonappdev.konsist.api.ext.list.modifierprovider

import com.lemonappdev.konsist.api.provider.modifier.KoInlineModifierProvider

/**
 * List containing declarations with `inline` modifier.
 *
 * @return A list containing declarations with the `inline` modifier.
 */
fun <T : KoInlineModifierProvider> List<T>.withInlineModifier(): List<T> = filter { it.hasInlineModifier }

/**
 * List containing declarations without `inline` modifier.
 *
 * @return A list containing declarations without the `inline` modifier.
 */
fun <T : KoInlineModifierProvider> List<T>.withoutInlineModifier(): List<T> = filterNot { it.hasInlineModifier }
