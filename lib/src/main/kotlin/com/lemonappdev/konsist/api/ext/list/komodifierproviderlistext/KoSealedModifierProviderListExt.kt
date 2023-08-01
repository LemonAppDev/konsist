package com.lemonappdev.konsist.api.ext.list.komodifierproviderlistext

import com.lemonappdev.konsist.api.provider.komodifierprovider.KoEnumModifierProvider
import com.lemonappdev.konsist.api.provider.komodifierprovider.KoSealedModifierProvider

/**
 * List containing all declarations that have `sealed` modifier.
 *
 * @return A list containing declarations with the `sealed` modifier.
 */
fun <T : KoSealedModifierProvider> List<T>.withSealedModifier(): List<T> = filter { it.hasSealedModifier }

/**
 * List containing all declarations that don't have `sealed` modifier.
 *
 * @return A list containing declarations without the `sealed` modifier.
 */
fun <T : KoSealedModifierProvider> List<T>.withoutSealedModifier(): List<T> = filterNot { it.hasSealedModifier }
