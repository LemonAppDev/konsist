package com.lemonappdev.konsist.api.ext.provider

import com.lemonappdev.konsist.api.provider.KoParentProvider

/**
 * Determines whether the declaration has a parent of type [T].
 *
 * @return `true` if the declaration has a parent of type [T], `false` otherwise.
 */
inline fun <reified T> KoParentProvider.hasParentOf(): Boolean = hasParentOf(T::class)
