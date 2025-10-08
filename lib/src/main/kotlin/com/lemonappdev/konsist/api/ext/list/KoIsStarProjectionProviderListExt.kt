package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.provider.KoIsStarProjectionProvider

/**
 * List containing the star projection declarations.
 *
 * @return A list containing the star projection declarations.
 */
fun <T : KoIsStarProjectionProvider> List<T>.withStarProjection(): List<T> = filter { it.isStarProjection }

/**
 * List containing the star projection declarations.
 *
 * @return A list containing the star projection declarations.
 */
fun <T : KoIsStarProjectionProvider> List<T>.withoutStarProjection(): List<T> = filterNot { it.isStarProjection }
