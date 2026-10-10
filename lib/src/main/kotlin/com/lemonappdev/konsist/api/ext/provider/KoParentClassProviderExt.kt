package com.lemonappdev.konsist.api.ext.provider

import com.lemonappdev.konsist.api.provider.KoParentClassProvider

/**
 * Determines whether the declaration has a parent class of type [T].
 *
 * @return `true` if the declaration has a parent class of type [T], `false` otherwise.
 */
inline fun <reified T> KoParentClassProvider.hasParentClassOf(): Boolean = hasParentClassOf(T::class)
