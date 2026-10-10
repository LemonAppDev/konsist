package com.lemonappdev.konsist.api.ext.provider

import com.lemonappdev.konsist.api.provider.KoParentInterfaceProvider

/**
 * Determines whether the declaration has a parent interface of type [T].
 *
 * @return `true` if the declaration has a parent interface of type [T], `false` otherwise.
 */
inline fun <reified T> KoParentInterfaceProvider.hasParentInterfaceOf(): Boolean = hasParentInterfaceOf(T::class)
