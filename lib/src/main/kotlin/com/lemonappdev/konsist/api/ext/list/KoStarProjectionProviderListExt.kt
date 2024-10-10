package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.provider.KoStarProjectionProvider

/**
 * List containing the star projection declarations.
 *
 * @return A list containing the star projection declarations.
 */
fun <T : KoStarProjectionProvider> List<T>.withStarProjection(): List<T> = filter { it.isStarProjection }

/**
 * List containing the star projection declarations.
 *
 * @return A list containing the star projection declarations.
 */
fun <T : KoStarProjectionProvider> List<T>.withoutStarProjection(): List<T> = filterNot { it.isStarProjection }
