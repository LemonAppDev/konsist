package com.lemonappdev.konsist.api.ext.sequence

import com.lemonappdev.konsist.api.provider.KoWildcardProvider

/**
 * Sequence containing all imports with a wildcard.
 *
 * @return A sequence containing imports with a wildcard.
 */
fun <T : KoWildcardProvider> Sequence<T>.withWildcard(): Sequence<KoWildcardProvider> = filter { it.isWildcard }

/**
 * Sequence containing all imports without a wildcard.
 *
 * @return A sequence containing imports without a wildcard.
 */
fun <T : KoWildcardProvider> Sequence<T>.withoutWildcard(): Sequence<KoWildcardProvider> = filterNot { it.isWildcard }
