package com.lemonappdev.konsist.api.ext.list.komodifierproviderlistext

import com.lemonappdev.konsist.api.provider.komodifierprovider.KoEnumModifierProvider
import com.lemonappdev.konsist.api.provider.komodifierprovider.KoDataModifierProvider
import com.lemonappdev.konsist.api.provider.komodifierprovider.KoVarArgModifierProvider
import com.lemonappdev.konsist.api.provider.komodifierprovider.KoOpenModifierProvider

/**
 * List containing all declarations with `vararg` modifier.
 *
 * @return A list containing declarations with the `vararg` modifier.
 */
fun <T : KoVarArgModifierProvider> List<T>.withVarargModifier(): List<T> = filter { it.hasVarArgModifier }

/**
 * List containing all declarations without `vararg` modifier.
 *
 * @return A list containing declarations without the `vararg` modifier.
 */
fun <T : KoVarArgModifierProvider> List<T>.withoutVarargModifier(): List<T> = filterNot { it.hasVarArgModifier }
