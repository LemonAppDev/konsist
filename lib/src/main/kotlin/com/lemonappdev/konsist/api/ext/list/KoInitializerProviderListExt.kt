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

