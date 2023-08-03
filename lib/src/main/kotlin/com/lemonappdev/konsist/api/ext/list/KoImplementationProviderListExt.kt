package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.provider.KoImplementationProvider

/**
 * List containing elements that have implementation.
 *
 * @return A list containing elements with the implementation.
 */
fun <T : KoImplementationProvider> List<T>.withImplementation(): List<T> = filter { it.hasImplementation }

/**
 * List containing elements without implementation.
 *
 * @return A list containing elements without the implementation.
 */
fun <T : KoImplementationProvider> List<T>.withoutImplementation(): List<T> = filterNot { it.hasImplementation }
