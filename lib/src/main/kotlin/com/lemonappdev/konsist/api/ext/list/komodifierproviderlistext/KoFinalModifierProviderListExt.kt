package com.lemonappdev.konsist.api.ext.list.komodifierproviderlistext

import com.lemonappdev.konsist.api.provider.komodifierprovider.KoFinalModifierProvider

/**
 * List containing all declarations that have `final` modifier.
 *
 * @return A list containing declarations with the `final` modifier.
 */
fun <T : KoFinalModifierProvider> List<T>.withFinalModifier(): List<T> = filter { it.hasFinalModifier }

/**
 * List containing all declarations that don't have `final` modifier.
 *
 * @return A list containing declarations without the `final` modifier.
 */
fun <T : KoFinalModifierProvider> List<T>.withoutFinalModifier(): List<T> = filterNot { it.hasFinalModifier }
