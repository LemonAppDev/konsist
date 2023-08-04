package com.lemonappdev.konsist.api.ext.list.modifierprovider

import com.lemonappdev.konsist.api.provider.modifier.KoOverrideModifierProvider

/**
 * List containing elements with `override` modifier.
 *
 * @return A list containing elements with the `override` modifier.
 */
fun <T : KoOverrideModifierProvider> List<T>.withOverrideModifier(): List<T> = filter { it.hasOverrideModifier }

/**
 * List containing elements without `override` modifier.
 *
 * @return A list containing elements without the `override` modifier.
 */
fun <T : KoOverrideModifierProvider> List<T>.withoutOverrideModifier(): List<T> = filterNot { it.hasOverrideModifier }
