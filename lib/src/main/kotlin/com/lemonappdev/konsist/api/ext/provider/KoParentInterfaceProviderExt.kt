package com.lemonappdev.konsist.api.ext.provider

import com.lemonappdev.konsist.api.provider.KoParentInterfaceProvider

/**
 * Returns `true` if declaration represents the type of [T].
 *
 * @return `true` if declaration represents the type of [T], `false` otherwise.
 */
inline fun <reified T> KoParentInterfaceProvider.hasParentInterfaceOf(): Boolean = hasParentInterfaceOf(T::class)
