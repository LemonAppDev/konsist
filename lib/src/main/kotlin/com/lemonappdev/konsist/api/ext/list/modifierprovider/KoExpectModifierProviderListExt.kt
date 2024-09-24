package com.lemonappdev.konsist.api.ext.list.modifierprovider

import com.lemonappdev.konsist.api.provider.modifier.KoExpectModifierProvider

/**
 * List containing declarations that have `expect` modifier.
 *
 * @return A list containing declarations with the `expect` modifier.
 */
fun <T : KoExpectModifierProvider> List<T>.withExpectModifier(): List<T> = filter { it.hasExpectModifier }

/**
 * List containing declarations that don't have `expect` modifier.
 *
 * @return A list containing declarations without the `expect` modifier.
 */
fun <T : KoExpectModifierProvider> List<T>.withoutExpectModifier(): List<T> = filterNot { it.hasExpectModifier }
