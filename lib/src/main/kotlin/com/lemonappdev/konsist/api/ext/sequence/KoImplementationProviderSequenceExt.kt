package com.lemonappdev.konsist.api.ext.sequence

import com.lemonappdev.konsist.api.provider.KoImplementationProvider

/**
 * List containing declarations that have implementation.
 *
 * @return A sequence containing declarations with the implementation.
 */
fun <T : KoImplementationProvider> List<T>.withImplementation(): List<T> = filter { it.hasImplementation }

/**
 * List containing declarations without implementation.
 *
 * @return A sequence containing declarations without the implementation.
 */
fun <T : KoImplementationProvider> List<T>.withoutImplementation(): List<T> = filterNot { it.hasImplementation }
