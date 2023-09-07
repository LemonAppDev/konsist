package com.lemonappdev.konsist.api.ext.list.modifierprovider

import com.lemonappdev.konsist.api.provider.modifier.KoConstModifierProvider

/**
 * List containing declarations with `const` modifier.
 *
 * @return A list containing declarations with the `const` modifier.
 */
fun <T : KoConstModifierProvider> List<T>.withConstModifier(): List<T> = filter { it.hasConstModifier }

/**
 * List containing declarations without `const` modifier.
 *
 * @return A list containing declarations without the `const` modifier.
 */
fun <T : KoConstModifierProvider> List<T>.withoutConstModifier(): List<T> = filterNot { it.hasConstModifier }
