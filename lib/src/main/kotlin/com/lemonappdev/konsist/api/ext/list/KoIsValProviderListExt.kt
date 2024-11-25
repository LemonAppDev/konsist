package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.provider.KoIsValProvider

/**
 * List containing declarations with `val` keyword.
 *
 * @return A list containing declarations with the `val` keyword.
 */
fun <T : KoIsValProvider> List<T>.withVal(): List<T> = filter { it.isVal }

/**
 * List containing declarations without `val` keyword.
 *
 * @return A list containing declarations without the `val` keyword.
 */
fun <T : KoIsValProvider> List<T>.withoutVal(): List<T> = filterNot { it.isVal }
