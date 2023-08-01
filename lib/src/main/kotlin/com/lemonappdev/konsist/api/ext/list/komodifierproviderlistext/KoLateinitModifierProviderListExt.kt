package com.lemonappdev.konsist.api.ext.list.komodifierproviderlistext

import com.lemonappdev.konsist.api.provider.komodifierprovider.KoEnumModifierProvider
import com.lemonappdev.konsist.api.provider.komodifierprovider.KoDataModifierProvider
import com.lemonappdev.konsist.api.provider.komodifierprovider.KoFunModifierProvider
import com.lemonappdev.konsist.api.provider.komodifierprovider.KoLateinitModifierProvider

/**
 * List containing declarations with `lateinit` modifier.
 *
 * @return A list containing declarations with the `lateinit` modifier.
 */
fun <T : KoLateinitModifierProvider> List<T>.withLateinitModifier(): List<T> = filter { it.hasLateinitModifier }

/**
 * List containing declarations without `lateinit` modifier.
 *
 * @return A list containing declarations without the `lateinit` modifier.
 */
fun <T : KoLateinitModifierProvider> List<T>.withoutLateinitModifier(): List<T> = filterNot { it.hasLateinitModifier }
