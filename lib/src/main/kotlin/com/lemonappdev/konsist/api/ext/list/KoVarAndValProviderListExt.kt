package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.provider.komodifierprovider.KoValModifierProvider

/**
 * List containing declarations with `var` modifier.
 *
 * @return A list containing declarations with the `var` modifier.
 */
fun <T : KoValModifierProvider> List<T>.withVarModifier(): List<T> = filter { it.hasVarModifier }

/**
 * List containing declarations without `var` modifier.
 *
 * @return A list containing declarations without the `var` modifier.
 */
fun <T : KoValModifierProvider> List<T>.withoutVarModifier(): List<T> = filterNot { it.hasVarModifier }

/**
 * List containing declarations with `val` modifier.
 *
 * @return A list containing declarations with the `val` modifier.
 */
fun <T : KoValModifierProvider> List<T>.withValModifier(): List<T> = filter { it.hasValModifier }

/**
 * List containing declarations without `val` modifier.
 *
 * @return A list containing declarations without the `val` modifier.
 */
fun <T : KoValModifierProvider> List<T>.withoutValModifier(): List<T> = filterNot { it.hasValModifier }
