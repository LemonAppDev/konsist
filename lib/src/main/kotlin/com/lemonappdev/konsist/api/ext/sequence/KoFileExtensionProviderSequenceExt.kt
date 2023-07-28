package com.lemonappdev.konsist.api.ext.sequence

import com.lemonappdev.konsist.api.provider.KoFileExtensionProvider

/**
 * Sequence containing files with extension.
 *
 * @param extension The extension to include.
 * @param extensions The extensions to include.
 * @return A sequence containing files with extensions matching the specified extensions.
 */
fun <T : KoFileExtensionProvider> Sequence<T>.withExtension(extension: String, vararg extensions: String): Sequence<T> = filter {
    it.hasExtension(extension) || extensions.any { extension -> it.hasExtension(extension) }
}

/**
 * Sequence containing files without extension.
 *
 * @param extension The extension to exclude.
 * @param extensions The extensions to exclude.
 * @return A sequence containing files without extensions matching the specified extensions.
 */
fun <T : KoFileExtensionProvider> Sequence<T>.withoutExtension(extension: String, vararg extensions: String): Sequence<T> = filter {
    !it.hasExtension(extension) && extensions.none { extension -> it.hasExtension(extension) }
}
