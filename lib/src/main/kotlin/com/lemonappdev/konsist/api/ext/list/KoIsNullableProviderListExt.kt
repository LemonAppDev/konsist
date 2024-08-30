package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.provider.KoIsNullableProvider

/**
 * List containing declarations with nullable type.
 *
 * @return A list containing declarations that have nullable type.
 */
fun <T : KoIsNullableProvider> List<T>.withNullable(): List<T> = filter { it.isNullable }

/**
 * List containing declarations without nullable type.
 *
 * @return A list containing declarations that don't have nullable type.
 */
fun <T : KoIsNullableProvider> List<T>.withoutNullable(): List<T> = filterNot { it.isNullable }
