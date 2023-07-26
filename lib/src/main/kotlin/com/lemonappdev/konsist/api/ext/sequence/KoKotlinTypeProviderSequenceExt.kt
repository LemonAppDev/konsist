package com.lemonappdev.konsist.api.ext.sequence

import com.lemonappdev.konsist.api.declaration.KoTypeDeclaration
import com.lemonappdev.konsist.api.provider.KoKotlinTypeProvider

/**
 * Sequence containing Kotlin types that are built-in types. It can be a basic Kotlin type
 * [Basic types](https://kotlinlang.org/docs/basic-types.html) or collection type
 * [Collections overview](https://kotlinlang.org/docs/collections-overview.html#collection).
 *
 * @return A sequence containing built-in Kotlin types.
 */
fun <T: KoKotlinTypeProvider> Sequence<T>.withKotlinType(): Sequence<T> = filter { it.isKotlinType }

/**
 * Sequence containing Kotlin types that are not built-in types. It cannot be a basic Kotlin type
 * [Basic types](https://kotlinlang.org/docs/basic-types.html) and collection type
 * [Collections overview](https://kotlinlang.org/docs/collections-overview.html#collection).
 *
 * @return A sequence containing non-built-in Kotlin types.
 */
fun <T: KoKotlinTypeProvider> Sequence<T>.withoutKotlinType(): Sequence<T> = filterNot { it.isKotlinType }
