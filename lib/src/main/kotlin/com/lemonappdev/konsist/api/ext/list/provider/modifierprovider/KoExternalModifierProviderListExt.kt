package com.lemonappdev.konsist.api.ext.list.provider.modifierprovider

import com.lemonappdev.konsist.api.provider.modifier.KoExternalModifierProvider

/**
 * List containing declarations with `external` modifier.
 *
 * @return A list containing declarations with the `external` modifier.
 */
fun <T : KoExternalModifierProvider> List<T>.withExternalModifier(): List<T> = filter { it.hasExternalModifier }

/**
 * List containing declarations without `external` modifier.
 *
 * @return A list containing declarations without the `external` modifier.
 */
fun <T : KoExternalModifierProvider> List<T>.withoutExternalModifier(): List<T> = filterNot { it.hasExternalModifier }
