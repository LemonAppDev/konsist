package com.lemonappdev.konsist.api.ext.sequence

import com.lemonappdev.konsist.api.provider.KoTopLevelProvider

/**
 * Sequence containing the top level declarations.
 *
 * @return A sequence containing the top-level declarations.
 */
fun <T : KoTopLevelProvider> Sequence<T>.withTopLevel(): Sequence<T> = filter { it.isTopLevel() }

/**
 * Sequence containing the non-top level declarations.
 *
 * @return A sequence containing the non-top level declarations.
 */
fun <T : KoTopLevelProvider> Sequence<T>.withoutTopLevel(): Sequence<T> = filterNot { it.isTopLevel() }
