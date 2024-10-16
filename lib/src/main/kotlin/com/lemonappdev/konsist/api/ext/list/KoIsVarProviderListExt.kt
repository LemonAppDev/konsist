package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.provider.KoIsVarProvider

/**
 * List containing declarations with `var` keyword.
 *
 * @return A list containing declarations with the `var` keyword.
 */
fun <T : KoIsVarProvider> List<T>.withVar(): List<T> = filter { it.isVar }

/**
 * List containing declarations without `var` keyword.
 *
 * @return A list containing declarations without the `var` keyword.
 */
fun <T : KoIsVarProvider> List<T>.withoutVar(): List<T> = filterNot { it.isVar }
