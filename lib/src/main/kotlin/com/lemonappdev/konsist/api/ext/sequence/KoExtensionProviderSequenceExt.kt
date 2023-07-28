package com.lemonappdev.konsist.api.ext.sequence

import com.lemonappdev.konsist.api.provider.KoExtensionProvider

/**
 * Sequence containing declarations with extension.
 *
 * @return A sequence containing declarations with extensions.
 */
fun <T : KoExtensionProvider> Sequence<T>.withExtension(): Sequence<T> = filter { it.isExtension() }

/**
 * Sequence containing declarations without extension.
 *
 * @return A sequence containing declarations without extensions.
 */
fun <T : KoExtensionProvider> Sequence<T>.withoutExtension(): Sequence<T> = filterNot { it.isExtension() }
