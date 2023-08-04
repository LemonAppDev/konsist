package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.provider.KoGenericTypeProvider

/**
 * List containing elements with generic type.
 *
 * @return A list containing elements with the generic types.
 */
fun <T : KoGenericTypeProvider> List<T>.withGenericType(): List<KoGenericTypeProvider> = filter { it.isGenericType }

/**
 * List containing elements without generic type.
 *
 * @return A list containing elements without the generic types.
 */
fun <T : KoGenericTypeProvider> List<T>.withoutGenericType(): List<KoGenericTypeProvider> = filterNot { it.isGenericType }
