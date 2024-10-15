package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.provider.KoIsExtensionProvider

/**
 * List containing declarations that are extensions.
 *
 * @return A list containing declarations that are extensions.
 */
fun <T : KoIsExtensionProvider> List<T>.withExtension(): List<T> = filter { it.isExtension }

/**
 * List containing declarations that are not extensions.
 *
 * @return A list containing declarations that are not extensions.
 */
fun <T : KoIsExtensionProvider> List<T>.withoutExtension(): List<T> = filterNot { it.isExtension }
