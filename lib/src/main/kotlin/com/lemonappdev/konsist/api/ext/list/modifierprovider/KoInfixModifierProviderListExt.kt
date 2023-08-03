package com.lemonappdev.konsist.api.ext.list.modifierprovider

import com.lemonappdev.konsist.api.provider.modifier.KoInfixModifierProvider

/**
 * List containing elements with `infix` modifier.
 *
 * @return A list containing elements with the `infix` modifier.
 */
fun <T : KoInfixModifierProvider> List<T>.withInfixModifier(): List<T> = filter { it.hasInfixModifier }

/**
 * List containing elements without `infix` modifier.
 *
 * @return A list containing elements without the `infix` modifier.
 */
fun <T : KoInfixModifierProvider> List<T>.withoutInfixModifier(): List<T> = filterNot { it.hasInfixModifier }
