package com.lemonappdev.konsist.api.ext.list.modifierprovider

import com.lemonappdev.konsist.api.provider.modifier.KoOutModifierProvider

/**
 * List containing declarations with `out` modifier.
 *
 * @return A list containing declarations with the `out` modifier.
 */
fun <T : KoOutModifierProvider> List<T>.withOutModifier(): List<T> = filter { it.hasOutModifier }

/**
 * List containing declarations without `out` modifier.
 *
 * @return A list containing declarations without the `out` modifier.
 */
fun <T : KoOutModifierProvider> List<T>.withoutOutModifier(): List<T> = filterNot { it.hasOutModifier }
