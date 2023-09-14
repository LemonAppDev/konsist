package com.lemonappdev.konsist.api.ext.list.modifierprovider

import com.lemonappdev.konsist.api.provider.modifier.KoDataModifierProvider

/**
 * List containing declarations that have `data` modifier.
 *
 * @return A list containing declarations with the `data` modifier.
 */
fun <T : KoDataModifierProvider> List<T>.withDataModifier(): List<T> = filter { it.hasDataModifier }

/**
 * List containing declarations that don't have `data` modifier.
 *
 * @return A list containing declarations without the `data` modifier.
 */
fun <T : KoDataModifierProvider> List<T>.withoutDataModifier(): List<T> = filterNot { it.hasDataModifier }
