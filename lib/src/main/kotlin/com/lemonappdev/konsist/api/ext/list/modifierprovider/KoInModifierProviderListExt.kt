package com.lemonappdev.konsist.api.ext.list.modifierprovider

import com.lemonappdev.konsist.api.provider.modifier.KoInModifierProvider

/**
 * List containing declarations with `in` modifier.
 *
 * @return A list containing declarations with the `in` modifier.
 */
fun <T : KoInModifierProvider> List<T>.withInModifier(): List<T> = filter { it.hasInModifier }

/**
 * List containing declarations without `in` modifier.
 *
 * @return A list containing declarations without the `in` modifier.
 */
fun <T : KoInModifierProvider> List<T>.withoutInModifier(): List<T> = filterNot { it.hasInModifier }
