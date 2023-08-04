package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.provider.KoWildcardProvider

/**
 * List containing elements with a wildcard.
 *
 * @return A list containing elements with a wildcard.
 */
fun <T : KoWildcardProvider> List<T>.withWildcard(): List<KoWildcardProvider> = filter { it.isWildcard }

/**
 * List containing elements without a wildcard.
 *
 * @return A list containing elements without a wildcard.
 */
fun <T : KoWildcardProvider> List<T>.withoutWildcard(): List<KoWildcardProvider> = filterNot { it.isWildcard }
