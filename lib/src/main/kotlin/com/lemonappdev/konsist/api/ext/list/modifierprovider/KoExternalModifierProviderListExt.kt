package com.lemonappdev.konsist.api.ext.list.modifierprovider

import com.lemonappdev.konsist.api.provider.modifier.KoExternalModifierProvider

/**
 * List containing elements with `external` modifier.
 *
 * @return A list containing elements with the `external` modifier.
 */
fun <T : KoExternalModifierProvider> List<T>.withExternalModifier(): List<T> = filter { it.hasExternalModifier }

/**
 * List containing elements without `external` modifier.
 *
 * @return A list containing elements without the `external` modifier.
 */
fun <T : KoExternalModifierProvider> List<T>.withoutExternalModifier(): List<T> = filterNot { it.hasExternalModifier }
