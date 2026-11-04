package com.lemonappdev.konsist.api.ext.provider

import com.lemonappdev.konsist.api.provider.KoExternalParentProvider

/**
 * Determines whether the declaration has an external parent of type [T].
 * The external parent is a parent defined outside the project codebase (e.g. defined inside an external library).
 *
 * @return `true` if the declaration has an external parent of type [T], `false` otherwise.
 */
inline fun <reified T> KoExternalParentProvider.hasExternalParentOf(): Boolean = hasExternalParentOf(T::class)
