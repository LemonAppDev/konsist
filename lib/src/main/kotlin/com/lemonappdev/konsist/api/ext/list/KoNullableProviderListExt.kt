package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.provider.KoNullableTypeProvider

/**
 * List containing declarations with nullable type.
 *
 * @return A list containing declarations that have nullable type.
 */
fun <T : KoNullableTypeProvider> List<T>.withNullableType(): List<T> = filter { it.isNullable }

/**
 * List containing declarations without nullable type.
 *
 * @return A list containing declarations that don't have nullable type.
 */
fun <T : KoNullableTypeProvider> List<T>.withoutNullableType(): List<T> = filterNot { it.isNullable }
