package com.lemonappdev.konsist.api.ext.list.modifierprovider

import com.lemonappdev.konsist.api.provider.modifier.KoVarArgModifierProvider

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
