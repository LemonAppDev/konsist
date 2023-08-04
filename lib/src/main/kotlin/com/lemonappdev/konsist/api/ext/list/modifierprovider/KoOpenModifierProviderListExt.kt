package com.lemonappdev.konsist.api.ext.list.modifierprovider

import com.lemonappdev.konsist.api.provider.modifier.KoOpenModifierProvider

/**
 * List containing elements that have `open` modifier.
 *
 * @return A list containing elements with the `open` modifier.
 */
fun <T : KoOpenModifierProvider> List<T>.withOpenModifier(): List<T> = filter { it.hasOpenModifier }

/**
 * List containing elements that don't have `open` modifier.
 *
 * @return A list containing elements without the `open` modifier.
 */
fun <T : KoOpenModifierProvider> List<T>.withoutOpenModifier(): List<T> = filterNot { it.hasOpenModifier }
