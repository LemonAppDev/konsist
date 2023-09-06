package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.provider.KoConstructorDefinedProvider

/**
 * List containing elements defined within a constructor.
 *
 * @return A list containing elements defined within a constructor.
 */
fun <T : KoConstructorDefinedProvider> List<T>.withConstructorDefined(): List<T> = filter { it.isConstructorDefined }

/**
 * List containing elements defined outside a constructor.
 *
 * @return A list containing elements defined outside a constructor.
 */
fun <T : KoConstructorDefinedProvider> List<T>.withoutConstructorDefined(): List<T> = filterNot { it.isConstructorDefined }
