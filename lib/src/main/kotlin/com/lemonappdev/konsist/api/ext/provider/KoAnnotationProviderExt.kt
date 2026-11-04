package com.lemonappdev.konsist.api.ext.provider

import com.lemonappdev.konsist.api.provider.KoAnnotationProvider

/**
 * Determines whether the declaration has an annotation of type [T].
 *
 * @return `true` if the declaration has an annotation of type [T], `false` otherwise.
 */
inline fun <reified T> KoAnnotationProvider.hasAnnotationOf(): Boolean = hasAnnotationOf(T::class)
