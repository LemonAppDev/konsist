package com.lemonappdev.konsist.api.ext.list.modifierprovider

import com.lemonappdev.konsist.api.provider.modifier.KoInlineModifierProvider

/**
 * List containing elements with `inline` modifier.
 *
 * @return A list containing elements with the `inline` modifier.
 */
fun <T : KoInlineModifierProvider> List<T>.withInlineModifier(): List<T> = filter { it.hasInlineModifier }

/**
 * List containing elements without `inline` modifier.
 *
 * @return A list containing elements without the `inline` modifier.
 */
fun <T : KoInlineModifierProvider> List<T>.withoutInlineModifier(): List<T> = filterNot { it.hasInlineModifier }
