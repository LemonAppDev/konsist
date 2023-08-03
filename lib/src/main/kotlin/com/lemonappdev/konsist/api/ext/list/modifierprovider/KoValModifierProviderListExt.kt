package com.lemonappdev.konsist.api.ext.list.modifierprovider

import com.lemonappdev.konsist.api.provider.modifier.KoValModifierProvider

/**
 * List containing elements with `val` modifier.
 *
 * @return A list containing elements with the `val` modifier.
 */
fun <T : KoValModifierProvider> List<T>.withValModifier(): List<T> = filter { it.hasValModifier }

/**
 * List containing elements without `val` modifier.
 *
 * @return A list containing elements without the `val` modifier.
 */
fun <T : KoValModifierProvider> List<T>.withoutValModifier(): List<T> = filterNot { it.hasValModifier }
