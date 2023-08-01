package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.provider.KoWildcardProvider

/**
 * List containing all imports with a wildcard.
 *
 * @return A list containing imports with a wildcard.
 */
fun <T : KoWildcardProvider> List<T>.withWildcard(): List<KoWildcardProvider> = filter { it.isWildcard }

/**
 * List containing all imports without a wildcard.
 *
 * @return A list containing imports without a wildcard.
 */
fun <T : KoWildcardProvider> List<T>.withoutWildcard(): List<KoWildcardProvider> = filterNot { it.isWildcard }
