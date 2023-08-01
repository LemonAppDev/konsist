package com.lemonappdev.konsist.api.ext.list.komodifierproviderlistext

import com.lemonappdev.konsist.api.provider.komodifierprovider.KoActualModifierProvider

/**
 * List containing all declarations that have `actual` modifier.
 *
 * @return A list containing declarations with the `actual` modifier.
 */
fun <T : KoActualModifierProvider> List<T>.withActualModifier(): List<T> = filter { it.hasActualModifier }

/**
 * List containing all declarations that don't have `actual` modifier.
 *
 * @return A list containing declarations without the `actual` modifier.
 */
fun <T : KoActualModifierProvider> List<T>.withoutActualModifier(): List<T> = filterNot { it.hasActualModifier }
