package com.lemonappdev.konsist.api.ext.list.provider.modifierprovider

import com.lemonappdev.konsist.api.provider.modifier.KoOverrideModifierProvider

/**
 * List containing declarations with `override` modifier.
 *
 * @return A list containing declarations with the `override` modifier.
 */
fun <T : KoOverrideModifierProvider> List<T>.withOverrideModifier(): List<T> = filter { it.hasOverrideModifier }

/**
 * List containing declarations without `override` modifier.
 *
 * @return A list containing declarations without the `override` modifier.
 */
fun <T : KoOverrideModifierProvider> List<T>.withoutOverrideModifier(): List<T> = filterNot { it.hasOverrideModifier }
