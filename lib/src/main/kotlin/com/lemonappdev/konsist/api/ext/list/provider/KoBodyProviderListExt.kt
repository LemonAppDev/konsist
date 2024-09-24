package com.lemonappdev.konsist.api.ext.list.provider

import com.lemonappdev.konsist.api.provider.KoBodyProvider

/**
 * List containing declarations with expression body.
 *
 * @return A list containing the declarations with expression body.
 */
fun <T : KoBodyProvider> List<T>.withExpressionBody(): List<T> = filter { it.hasExpressionBody }

/**
 * List containing declarations without expression body.
 *
 * @return A list containing the declarations without expression body.
 */
fun <T : KoBodyProvider> List<T>.withoutExpressionBody(): List<T> = filterNot { it.hasExpressionBody }

/**
 * List containing declarations with block body.
 *
 * @return A list containing the declarations with block body.
 */
fun <T : KoBodyProvider> List<T>.withBlockBody(): List<T> = filter { it.hasBlockBody }

/**
 * List containing declarations without block body.
 *
 * @return A list containing the declarations without block body.
 */
fun <T : KoBodyProvider> List<T>.withoutBlockBody(): List<T> = filterNot { it.hasBlockBody }
