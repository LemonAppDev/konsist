package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.provider.KoGenericTypeProvider

/**
 * List containing types that have generic type.
 *
 * @return A list containing types with the generic types.
 */
fun <T : KoGenericTypeProvider> List<T>.withGenericType(): List<KoGenericTypeProvider> = filter { it.isGenericType }

/**
 * List containing types that don't have generic type.
 *
 * @return A list containing types without the generic types.
 */
fun <T : KoGenericTypeProvider> List<T>.withoutGenericType(): List<KoGenericTypeProvider> = filterNot { it.isGenericType }
