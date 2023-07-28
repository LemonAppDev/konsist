package com.lemonappdev.konsist.api.ext.sequence

import com.lemonappdev.konsist.api.provider.KoImplementationProvider

/**
 * Sequence containing declarations that have implementation.
 *
 * @return A sequence containing declarations with the implementation.
 */
fun <T : KoImplementationProvider> Sequence<T>.withImplementation(): Sequence<T> = filter { it.hasImplementation() }

/**
 * Sequence containing declarations without implementation.
 *
 * @return A sequence containing declarations without the implementation.
 */
fun <T : KoImplementationProvider> Sequence<T>.withoutImplementation(): Sequence<T> = filterNot { it.hasImplementation() }
