package com.lemonappdev.konsist.api.ext.list.provider.modifierprovider

import com.lemonappdev.konsist.api.provider.modifier.KoEnumModifierProvider

/**
 * List containing declarations that have `enum` modifier.
 *
 * @return A list containing declarations with the `enum` modifier.
 */
fun <T : KoEnumModifierProvider> List<T>.withEnumModifier(): List<T> = filter { it.hasEnumModifier }

/**
 * List containing declarations that don't have `enum` modifier.
 *
 * @return A list containing declarations without the `enum` modifier.
 */
fun <T : KoEnumModifierProvider> List<T>.withoutEnumModifier(): List<T> = filterNot { it.hasEnumModifier }
