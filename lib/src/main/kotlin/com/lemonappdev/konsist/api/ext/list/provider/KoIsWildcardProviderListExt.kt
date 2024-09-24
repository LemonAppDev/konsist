package com.lemonappdev.konsist.api.ext.list.provider

import com.lemonappdev.konsist.api.provider.KoIsWildcardProvider

/**
 * List containing declarations with a wildcard.
 *
 * @return A list containing declarations with a wildcard.
 */
fun <T : KoIsWildcardProvider> List<T>.withWildcard(): List<T> = filter { it.isWildcard }

/**
 * List containing declarations without a wildcard.
 *
 * @return A list containing declarations without a wildcard.
 */
fun <T : KoIsWildcardProvider> List<T>.withoutWildcard(): List<T> = filterNot { it.isWildcard }
