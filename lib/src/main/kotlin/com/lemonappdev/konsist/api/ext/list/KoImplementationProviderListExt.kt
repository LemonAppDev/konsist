package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.provider.KoImplementationProvider

/**
 * List containing declarations that have implementation.
 *
 * @return A list containing declarations with the implementation.
 */
fun <T : KoImplementationProvider> List<T>.withImplementation(): List<T> = filter { it.hasImplementation }

/**
 * List containing declarations without implementation.
 *
 * @return A list containing declarations without the implementation.
 */
fun <T : KoImplementationProvider> List<T>.withoutImplementation(): List<T> = filterNot { it.hasImplementation }
