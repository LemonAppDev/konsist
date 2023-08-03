package com.lemonappdev.konsist.api.ext.list.modifierprovider

import com.lemonappdev.konsist.api.provider.modifier.KoOperatorModifierProvider

/**
 * List containing elements with `operator` modifier.
 *
 * @return A list containing elements with the `operator` modifier.
 */
fun <T : KoOperatorModifierProvider> List<T>.withOperatorModifier(): List<T> = filter { it.hasOperatorModifier }

/**
 * List containing elements without `operator` modifier.
 *
 * @return A list containing elements without the `operator` modifier.
 */
fun <T : KoOperatorModifierProvider> List<T>.withoutOperatorModifier(): List<T> = filterNot { it.hasOperatorModifier }
