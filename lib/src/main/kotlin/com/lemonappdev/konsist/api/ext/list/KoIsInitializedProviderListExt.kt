package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.provider.KoIsInitializedProvider

/**
 * List containing declarations that have been initialized.
 *
 * @return A list containing declarations that have been initialized.
 * @see KoIsInitializedProvider.isInitialized
 */
fun <T : KoIsInitializedProvider> List<T>.withInitialized(): List<T> = filter { it.isInitialized }

/**
 * List containing declarations that have not been initialized.
 *
 * @return A list containing declarations that have not been initialized.
 * @see KoIsInitializedProvider.isInitialized
 */
fun <T : KoIsInitializedProvider> List<T>.withoutInitialized(): List<T> = filterNot { it.isInitialized }
