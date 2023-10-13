package com.lemonappdev.konsist.api.ext.provider

import com.lemonappdev.konsist.api.provider.KoExternalParentProvider

/**
 * Returns `true` if declaration represents the type of [T].
 *
 * @return `true` if declaration represents the type of [T], `false` otherwise.
 */
inline fun <reified T> KoExternalParentProvider.hasExternalParentOf(): Boolean = hasExternalParentOf(T::class)
