package com.lemonappdev.konsist.api.ext.list.komodifierproviderlistext

import com.lemonappdev.konsist.api.provider.komodifierprovider.KoEnumModifierProvider
import com.lemonappdev.konsist.api.provider.komodifierprovider.KoDataModifierProvider
import com.lemonappdev.konsist.api.provider.komodifierprovider.KoFunModifierProvider

/**
 * List containing all declarations with `fun` modifier.
 *
 * @return A list containing declarations with the `fun` modifier.
 */
fun <T : KoFunModifierProvider> List<T>.withFunModifier(): List<T> = filter { it.hasFunModifier }

/**
 * List containing all declarations without `fun` modifier.
 *
 * @return A list containing declarations without the `fun` modifier.
 */
fun <T : KoFunModifierProvider> List<T>.withoutFunModifier(): List<T> = filterNot { it.hasFunModifier }
