package com.lemonappdev.konsist.api.ext.list.modifierprovider

import com.lemonappdev.konsist.api.provider.modifier.KoConstModifierProvider

/**
 * List containing elements with `const` modifier.
 *
 * @return A list containing elements with the `const` modifier.
 */
fun <T : KoConstModifierProvider> List<T>.withConstModifier(): List<T> = filter { it.hasConstModifier }

/**
 * List containing elements without `const` modifier.
 *
 * @return A list containing elements without the `const` modifier.
 */
fun <T : KoConstModifierProvider> List<T>.withoutConstModifier(): List<T> = filterNot { it.hasConstModifier }
