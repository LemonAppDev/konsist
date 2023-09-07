package com.lemonappdev.konsist.api.ext.list.modifierprovider

import com.lemonappdev.konsist.api.provider.modifier.KoSealedModifierProvider

/**
 * List containing declarations that have `sealed` modifier.
 *
 * @return A list containing declarations with the `sealed` modifier.
 */
fun <T : KoSealedModifierProvider> List<T>.withSealedModifier(): List<T> = filter { it.hasSealedModifier }

/**
 * List containing declarations that don't have `sealed` modifier.
 *
 * @return A list containing declarations without the `sealed` modifier.
 */
fun <T : KoSealedModifierProvider> List<T>.withoutSealedModifier(): List<T> = filterNot { it.hasSealedModifier }
