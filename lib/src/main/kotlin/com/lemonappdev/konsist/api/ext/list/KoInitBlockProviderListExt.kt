package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.declaration.KoInitBlockDeclaration
import com.lemonappdev.konsist.api.provider.KoInitBlockProvider

/**
 * List containing init block declarations.
 */
val <T : KoInitBlockProvider> List<T>.initBlocks: List<KoInitBlockDeclaration>
    get() = flatMap { it.initBlocks }

/**
 * List containing declarations that have init block(s).
 *
 * @return A list containing declarations with the init block(s).
 */
fun <T : KoInitBlockProvider> List<T>.withInitBlocks(): List<T> = filter { it.hasInitBlocks }

/**
 * List containing declarations that don't have init block(s).
 *
 * @return A list containing declarations without the init block(s).
 */
fun <T : KoInitBlockProvider> List<T>.withoutInitBlocks(): List<T> = filterNot { it.hasInitBlocks }
