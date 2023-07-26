package com.lemonappdev.konsist.api.ext.sequence

import com.lemonappdev.konsist.api.declaration.KoTypeDeclaration
import com.lemonappdev.konsist.api.provider.KoGenericTypeProvider

/**
 * Sequence containing types that have generic type.
 *
 * @return A sequence containing types with the generic types.
 */
fun <T: KoGenericTypeProvider> Sequence<T>.withGenericType(): Sequence<KoGenericTypeProvider> = filter { it.isGenericType }

/**
 * Sequence containing types that don't have generic type.
 *
 * @return A sequence containing types without the generic types.
 */
fun <T: KoGenericTypeProvider> Sequence<T>.withoutGenericType(): Sequence<KoGenericTypeProvider> = filterNot { it.isGenericType }
