package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.provider.KoKotlinTypeProvider

/**
 * List containing elements that are built-in types. It can be a basic Kotlin type
 * [Basic types](https://kotlinlang.org/docs/basic-types.html) or collection type
 * [Collections overview](https://kotlinlang.org/docs/collections-overview.html#collection).
 *
 * @return A list containing built-in Kotlin types.
 */
fun <T : KoKotlinTypeProvider> List<T>.withKotlinType(): List<T> = filter { it.isKotlinType }

/**
 * List containing elements that are not built-in types. It cannot be a basic Kotlin type
 * [Basic types](https://kotlinlang.org/docs/basic-types.html) and collection type
 * [Collections overview](https://kotlinlang.org/docs/collections-overview.html#collection).
 *
 * @return A list containing non-built-in Kotlin types.
 */
fun <T : KoKotlinTypeProvider> List<T>.withoutKotlinType(): List<T> = filterNot { it.isKotlinType }
