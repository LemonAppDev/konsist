package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.provider.KoExtensionProvider

/**
 * List containing elements with extension.
 *
 * @return A list containing elements with extensions.
 */
fun <T : KoExtensionProvider> List<T>.withExtensionDeclaration(): List<T> = filter { it.isExtension }

/**
 * List containing elements without extension.
 *
 * @return A list containing elements without extensions.
 */
fun <T : KoExtensionProvider> List<T>.withoutExtensionDeclaration(): List<T> = filterNot { it.isExtension }
