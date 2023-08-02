package com.lemonappdev.konsist.api.ext.list.modifierprovider

import com.lemonappdev.konsist.api.provider.modifier.KoFunModifierProvider

/**
 * List containing all declarations with `fun` modifier.
 *
 * @return A list containing declarations with the `fun` modifier.
 */
fun <T : KoFunModifierProvider> List<T>.withFunModifier(): List<T> = filter { it.hasFunModifier }

/**
 * List containing all declarations without `fun` modifier.
 *
 * @return A list containing declarations without the `fun` modifier.
 */
fun <T : KoFunModifierProvider> List<T>.withoutFunModifier(): List<T> = filterNot { it.hasFunModifier }
