package com.lemonappdev.konsist.api.ext.provider

import com.lemonappdev.konsist.api.provider.KoRepresentsTypeProvider

/**
 * Returns `true` if this declaration represents the type of [T].
 *
 * @return `true` if this declaration represents the type of [T], `false` otherwise.
 */
inline fun <reified T> KoRepresentsTypeProvider.representsTypeOf(): Boolean =
    T::class.simpleName?.let { representsType(it) } ?: false || T::class.qualifiedName?.let { representsType(it) } ?: false
