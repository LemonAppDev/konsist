package com.lemonappdev.konsist.api.ext.sequence

import com.lemonappdev.konsist.api.provider.KoExtensionProvider

/**
 * List containing declarations with extension.
 *
 * @return A sequence containing declarations with extensions.
 */
fun <T : KoExtensionProvider> List<T>.withExtension(): List<T> = filter { it.isExtension }

/**
 * List containing declarations without extension.
 *
 * @return A sequence containing declarations without extensions.
 */
fun <T : KoExtensionProvider> List<T>.withoutExtension(): List<T> = filterNot { it.isExtension }
