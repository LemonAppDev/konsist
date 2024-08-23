package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.provider.KoWildcardProvider

/**
 * List containing declarations with a wildcard.
 *
 * @return A list containing declarations with a wildcard.
 */
@Deprecated("Will be removed in version 0.18.0", ReplaceWith("method from KoIsWildcardProvider"))
fun <T : KoWildcardProvider> List<T>.withWildcard(): List<T> = filter { it.isWildcard }

/**
 * List containing declarations without a wildcard.
 *
 * @return A list containing declarations without a wildcard.
 */
@Deprecated("Will be removed in version 0.18.0", ReplaceWith("method from KoIsWildcardProvider"))
fun <T : KoWildcardProvider> List<T>.withoutWildcard(): List<T> = filterNot { it.isWildcard }
