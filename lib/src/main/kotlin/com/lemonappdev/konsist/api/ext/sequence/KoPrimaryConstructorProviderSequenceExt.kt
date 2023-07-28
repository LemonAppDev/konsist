package com.lemonappdev.konsist.api.ext.sequence

import com.lemonappdev.konsist.api.provider.KoPrimaryConstructorProvider

/**
 * Sequence containing all declarations that have primary constructor.
 *
 * @return A sequence containing declarations with primary constructor.
 */
fun <T : KoPrimaryConstructorProvider> Sequence<T>.withPrimaryConstructor(): Sequence<T> = filter { it.hasPrimaryConstructor() }

/**
 * Sequence containing all declarations that don't have primary constructor.
 *
 * @return A sequence containing declarations without primary constructor.
 */
fun <T : KoPrimaryConstructorProvider> Sequence<T>.withoutPrimaryConstructor(): Sequence<T> = filterNot { it.hasPrimaryConstructor() }
