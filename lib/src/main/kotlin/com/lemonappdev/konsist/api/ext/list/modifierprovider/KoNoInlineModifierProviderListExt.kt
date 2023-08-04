package com.lemonappdev.konsist.api.ext.list.modifierprovider

import com.lemonappdev.konsist.api.provider.modifier.KoNoInlineModifierProvider

/**
 * List containing elements with `noinline` modifier.
 *
 * @return A list containing elements with the `noinline` modifier.
 */
fun <T : KoNoInlineModifierProvider> List<T>.withNoInlineModifier(): List<T> = filter { it.hasNoInlineModifier }

/**
 * List containing elements without `noinline` modifier.
 *
 * @return A list containing elements without the `noinline` modifier.
 */
fun <T : KoNoInlineModifierProvider> List<T>.withoutNoInlineModifier(): List<T> = filterNot { it.hasNoInlineModifier }
