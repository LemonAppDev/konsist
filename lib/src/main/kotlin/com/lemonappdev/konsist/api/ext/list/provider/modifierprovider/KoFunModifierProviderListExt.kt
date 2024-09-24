package com.lemonappdev.konsist.api.ext.list.provider.modifierprovider

import com.lemonappdev.konsist.api.provider.modifier.KoFunModifierProvider

/**
 * List containing declarations with `fun` modifier.
 *
 * @return A list containing declarations with the `fun` modifier.
 */
fun <T : KoFunModifierProvider> List<T>.withFunModifier(): List<T> = filter { it.hasFunModifier }

/**
 * List containing declarations without `fun` modifier.
 *
 * @return A list containing declarations without the `fun` modifier.
 */
fun <T : KoFunModifierProvider> List<T>.withoutFunModifier(): List<T> = filterNot { it.hasFunModifier }
