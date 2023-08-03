package com.lemonappdev.konsist.api.ext.list.modifierprovider

import com.lemonappdev.konsist.api.provider.modifier.KoEnumModifierProvider

/**
 * List containing elements that have `enum` modifier.
 *
 * @return A list containing elements with the `enum` modifier.
 */
fun <T : KoEnumModifierProvider> List<T>.withEnumModifier(): List<T> = filter { it.hasEnumModifier }

/**
 * List containing elements that don't have `enum` modifier.
 *
 * @return A list containing elements without the `enum` modifier.
 */
fun <T : KoEnumModifierProvider> List<T>.withoutEnumModifier(): List<T> = filterNot { it.hasEnumModifier }
