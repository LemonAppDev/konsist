package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.provider.KoInitializerProvider

/**
 * List containing declarations that have been initialized.
 *
 * @return A list containing declarations that have been initialized.
 */
fun <T : KoInitializerProvider> List<T>.withInitialized(): List<T> = filter { it.isInitialized }

/**
 * List containing declarations that have not been initialized.
 *
 * @return A list containing declarations that have not been initialized.
 */
fun <T : KoInitializerProvider> List<T>.withoutInitialized(): List<T> = filterNot { it.isInitialized }

/**
 * List containing declarations that have implementation.
 *
 * @return A list containing declarations with the implementation.
 */
@Deprecated("Will be removed in v0.16.0", ReplaceWith("withInitialized()"))
fun <T : KoInitializerProvider> List<T>.withImplementation(): List<T> = filter { it.hasImplementation }

/**
 * List containing declarations without implementation.
 *
 * @return A list containing declarations without the implementation.
 */
@Deprecated("Will be removed in v0.16.0", ReplaceWith("withoutInitialized()"))
fun <T : KoInitializerProvider> List<T>.withoutImplementation(): List<T> = filterNot { it.hasImplementation }
