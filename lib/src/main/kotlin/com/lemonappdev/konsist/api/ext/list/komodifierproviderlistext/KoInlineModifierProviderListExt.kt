package com.lemonappdev.konsist.api.ext.list.komodifierproviderlistext

import com.lemonappdev.konsist.api.provider.komodifierprovider.KoEnumModifierProvider
import com.lemonappdev.konsist.api.provider.komodifierprovider.KoDataModifierProvider
import com.lemonappdev.konsist.api.provider.komodifierprovider.KoInlineModifierProvider
import com.lemonappdev.konsist.api.provider.komodifierprovider.KoModifierProvider

/**
 * List containing declarations with `inline` modifier.
 *
 * @return A list containing declarations with the `inline` modifier.
 */
fun <T : KoInlineModifierProvider> List<T>.withInlineModifier(): List<T> = filter { it.hasInlineModifier }

/**
 * List containing declarations without `inline` modifier.
 *
 * @return A list containing declarations without the `inline` modifier.
 */
fun <T : KoInlineModifierProvider> List<T>.withoutInlineModifier(): List<T> = filterNot { it.hasInlineModifier }
