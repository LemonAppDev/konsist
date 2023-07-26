package com.lemonappdev.konsist.api.ext.sequence

import com.lemonappdev.konsist.api.declaration.KoClassDeclaration
import com.lemonappdev.konsist.api.provider.KoInitBlockProvider

/**
 * Sequence containing all declarations that have init block(s).
 *
 * @return A sequence containing declarations with the init block(s).
 */
fun <T: KoInitBlockProvider> Sequence<T>.withInitBlocks(): Sequence<T> = filter { it.hasInitBlocks() }

/**
 * Sequence containing all declarations that don't have init block(s).
 *
 * @return A sequence containing declarations without the init block(s).
 */
fun <T: KoInitBlockProvider> Sequence<T>.withoutInitBlocks(): Sequence<T> = filterNot { it.hasInitBlocks() }
