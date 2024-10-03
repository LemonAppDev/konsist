package com.lemonappdev.konsist.api.ext.list.provider.modifierprovider

import com.lemonappdev.konsist.api.provider.modifier.KoOperatorModifierProvider

/**
 * List containing declarations with `operator` modifier.
 *
 * @return A list containing declarations with the `operator` modifier.
 */
fun <T : KoOperatorModifierProvider> List<T>.withOperatorModifier(): List<T> = filter { it.hasOperatorModifier }

/**
 * List containing declarations without `operator` modifier.
 *
 * @return A list containing declarations without the `operator` modifier.
 */
fun <T : KoOperatorModifierProvider> List<T>.withoutOperatorModifier(): List<T> = filterNot { it.hasOperatorModifier }
