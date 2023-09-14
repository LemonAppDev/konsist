package com.lemonappdev.konsist.api.ext.provider

import com.lemonappdev.konsist.api.provider.KoAnnotationProvider

/**
 * Returns `true` if declaration represents the type of [T].
 *
 * @return `true` if declaration represents the type of [T], `false` otherwise.
 */
inline fun <reified T> KoAnnotationProvider.hasAnnotationOf(): Boolean = hasAnnotationOf(T::class)
