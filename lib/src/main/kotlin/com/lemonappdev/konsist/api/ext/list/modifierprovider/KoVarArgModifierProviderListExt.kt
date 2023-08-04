package com.lemonappdev.konsist.api.ext.list.modifierprovider

import com.lemonappdev.konsist.api.provider.modifier.KoVarArgModifierProvider

/**
 * List containing elements with `vararg` modifier.
 *
 * @return A list containing elements with the `vararg` modifier.
 */
fun <T : KoVarArgModifierProvider> List<T>.withVarargModifier(): List<T> = filter { it.hasVarArgModifier }

/**
 * List containing elements without `vararg` modifier.
 *
 * @return A list containing elements without the `vararg` modifier.
 */
fun <T : KoVarArgModifierProvider> List<T>.withoutVarargModifier(): List<T> = filterNot { it.hasVarArgModifier }
