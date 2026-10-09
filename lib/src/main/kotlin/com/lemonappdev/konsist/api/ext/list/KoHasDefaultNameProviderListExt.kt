package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.provider.KoHasDefaultNameProvider

/**
 * List containing declarations that use a default name.
 *
 * @return A list containing declarations that use a default name.
 */
fun <T : KoHasDefaultNameProvider> List<T>.withDefaultName(): List<T> = filter { it.hasDefaultName }

/**
 * Returns a list containing declarations that use an explicit (non-default) name.
 *
 * @return A list of declarations that do not use a default name.
 */
fun <T : KoHasDefaultNameProvider> List<T>.withoutDefaultName(): List<T> = filterNot { it.hasDefaultName }
