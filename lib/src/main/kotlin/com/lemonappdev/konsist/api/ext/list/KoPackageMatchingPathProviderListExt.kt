package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.provider.KoPackageMatchingPathProvider

/**
 * List containing elements with a matching path.
 *
 * @return A list containing elements with a matching path.
 */
fun <T : KoPackageMatchingPathProvider> List<T>.withMatchingPath(): List<T> = filter { it.hasMatchingPath }

/**
 * List containing elements without a matching path.
 *
 * @return A list containing elements without a matching path.
 */
fun <T : KoPackageMatchingPathProvider> List<T>.withoutMatchingPath(): List<T> = filterNot { it.hasMatchingPath }
