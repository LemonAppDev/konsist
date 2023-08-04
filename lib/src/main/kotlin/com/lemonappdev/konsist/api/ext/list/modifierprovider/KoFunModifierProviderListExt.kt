package com.lemonappdev.konsist.api.ext.list.modifierprovider

import com.lemonappdev.konsist.api.provider.modifier.KoFunModifierProvider

/**
 * List containing elements with `fun` modifier.
 *
 * @return A list containing elements with the `fun` modifier.
 */
fun <T : KoFunModifierProvider> List<T>.withFunModifier(): List<T> = filter { it.hasFunModifier }

/**
 * List containing elements without `fun` modifier.
 *
 * @return A list containing elements without the `fun` modifier.
 */
fun <T : KoFunModifierProvider> List<T>.withoutFunModifier(): List<T> = filterNot { it.hasFunModifier }
