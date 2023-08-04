package com.lemonappdev.konsist.api.ext.list.modifierprovider

import com.lemonappdev.konsist.api.provider.modifier.KoVarModifierProvider

/**
 * List containing elements with `var` modifier.
 *
 * @return A list containing elements with the `var` modifier.
 */
fun <T : KoVarModifierProvider> List<T>.withVarModifier(): List<T> = filter { it.hasVarModifier }

/**
 * List containing elements without `var` modifier.
 *
 * @return A list containing elements without the `var` modifier.
 */
fun <T : KoVarModifierProvider> List<T>.withoutVarModifier(): List<T> = filterNot { it.hasVarModifier }
