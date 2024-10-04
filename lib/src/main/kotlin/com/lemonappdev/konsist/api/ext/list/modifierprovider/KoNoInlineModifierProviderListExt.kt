package com.lemonappdev.konsist.api.ext.list.modifierprovider

import com.lemonappdev.konsist.api.provider.modifier.KoNoInlineModifierProvider

/**
 * List containing declarations with `noinline` modifier.
 *
 * @return A list containing declarations with the `noinline` modifier.
 */
fun <T : KoNoInlineModifierProvider> List<T>.withNoInlineModifier(): List<T> = filter { it.hasNoInlineModifier }

/**
 * List containing declarations without `noinline` modifier.
 *
 * @return A list containing declarations without the `noinline` modifier.
 */
fun <T : KoNoInlineModifierProvider> List<T>.withoutNoInlineModifier(): List<T> = filterNot { it.hasNoInlineModifier }
