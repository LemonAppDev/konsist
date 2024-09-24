package com.lemonappdev.konsist.api.ext.list.provider

import com.lemonappdev.konsist.api.provider.KoIsConstructorDefinedProvider

/**
 * List containing declarations defined within a constructor.
 *
 * @return A list containing declarations defined within a constructor.
 */
fun <T : KoIsConstructorDefinedProvider> List<T>.withConstructorDefined(): List<T> = filter { it.isConstructorDefined }

/**
 * List containing declarations defined outside a constructor.
 *
 * @return A list containing declarations defined outside a constructor.
 */
fun <T : KoIsConstructorDefinedProvider> List<T>.withoutConstructorDefined(): List<T> = filterNot { it.isConstructorDefined }
