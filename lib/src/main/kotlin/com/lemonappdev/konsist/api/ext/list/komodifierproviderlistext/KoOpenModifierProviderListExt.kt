package com.lemonappdev.konsist.api.ext.list.komodifierproviderlistext

import com.lemonappdev.konsist.api.provider.komodifierprovider.KoEnumModifierProvider
import com.lemonappdev.konsist.api.provider.komodifierprovider.KoDataModifierProvider
import com.lemonappdev.konsist.api.provider.komodifierprovider.KoOpenModifierProvider

/**
 * List containing all declarations that have `open` modifier.
 *
 * @return A list containing declarations with the `open` modifier.
 */
fun <T : KoOpenModifierProvider> List<T>.withOpenModifier(): List<T> = filter { it.hasOpenModifier }

/**
 * List containing all declarations that don't have `open` modifier.
 *
 * @return A list containing declarations without the `open` modifier.
 */
fun <T : KoOpenModifierProvider> List<T>.withoutOpenModifier(): List<T> = filterNot { it.hasOpenModifier }
