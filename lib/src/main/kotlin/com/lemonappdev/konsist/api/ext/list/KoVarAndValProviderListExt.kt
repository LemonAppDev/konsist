package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.provider.KoVarAndValProvider

/**
 * List containing declarations with `var` modifier.
 *
 * @return A list containing declarations with the `var` modifier.
 */
fun <T : KoVarAndValProvider> List<T>.withVarModifier(): List<T> = filter { it.isVar }

/**
 * List containing declarations without `var` modifier.
 *
 * @return A list containing declarations without the `var` modifier.
 */
fun <T : KoVarAndValProvider> List<T>.withoutVarModifier(): List<T> = filterNot { it.isVar }

/**
 * List containing declarations with `val` modifier.
 *
 * @return A list containing declarations with the `val` modifier.
 */
fun <T : KoVarAndValProvider> List<T>.withValModifier(): List<T> = filter { it.isVal }

/**
 * List containing declarations without `val` modifier.
 *
 * @return A list containing declarations without the `val` modifier.
 */
fun <T : KoVarAndValProvider> List<T>.withoutValModifier(): List<T> = filterNot { it.isVal }
