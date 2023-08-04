package com.lemonappdev.konsist.api.ext.list.modifierprovider

import com.lemonappdev.konsist.api.provider.modifier.KoDataModifierProvider

/**
 * List containing elements that have `data` modifier.
 *
 * @return A list containing elements with the `data` modifier.
 */
fun <T : KoDataModifierProvider> List<T>.withDataModifier(): List<T> = filter { it.hasDataModifier }

/**
 * List containing elements that don't have `data` modifier.
 *
 * @return A list containing elements without the `data` modifier.
 */
fun <T : KoDataModifierProvider> List<T>.withoutDataModifier(): List<T> = filterNot { it.hasDataModifier }
