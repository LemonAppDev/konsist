package com.lemonappdev.konsist.api.ext.list.modifierprovider

import com.lemonappdev.konsist.api.provider.modifier.KoExpectModifierProvider

/**
 * List containing elements that have `expect` modifier.
 *
 * @return A list containing elements with the `expect` modifier.
 */
fun <T : KoExpectModifierProvider> List<T>.withExpectModifier(): List<T> = filter { it.hasExpectModifier }

/**
 * List containing elements that don't have `expect` modifier.
 *
 * @return A list containing elements without the `expect` modifier.
 */
fun <T : KoExpectModifierProvider> List<T>.withoutExpectModifier(): List<T> = filterNot { it.hasExpectModifier }
