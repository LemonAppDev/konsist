package com.lemonappdev.konsist.api.ext.list.modifierprovider

import com.lemonappdev.konsist.api.provider.modifier.KoSealedModifierProvider

/**
 * List containing elements that have `sealed` modifier.
 *
 * @return A list containing elements with the `sealed` modifier.
 */
fun <T : KoSealedModifierProvider> List<T>.withSealedModifier(): List<T> = filter { it.hasSealedModifier }

/**
 * List containing elements that don't have `sealed` modifier.
 *
 * @return A list containing elements without the `sealed` modifier.
 */
fun <T : KoSealedModifierProvider> List<T>.withoutSealedModifier(): List<T> = filterNot { it.hasSealedModifier }
