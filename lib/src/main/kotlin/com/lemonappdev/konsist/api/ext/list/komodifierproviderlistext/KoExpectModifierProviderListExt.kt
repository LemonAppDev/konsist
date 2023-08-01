package com.lemonappdev.konsist.api.ext.list.komodifierproviderlistext

import com.lemonappdev.konsist.api.provider.komodifierprovider.KoEnumModifierProvider
import com.lemonappdev.konsist.api.provider.komodifierprovider.KoDataModifierProvider
import com.lemonappdev.konsist.api.provider.komodifierprovider.KoExpectModifierProvider

/**
 * List containing all declarations that have `expect` modifier.
 *
 * @return A list containing declarations with the `expect` modifier.
 */
fun <T : KoExpectModifierProvider> List<T>.withExpectModifier(): List<T> = filter { it.hasExpectModifier }

/**
 * List containing all declarations that don't have `expect` modifier.
 *
 * @return A list containing declarations without the `expect` modifier.
 */
fun <T : KoExpectModifierProvider> List<T>.withoutExpectModifier(): List<T> = filterNot { it.hasExpectModifier }
