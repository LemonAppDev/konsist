package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.provider.KoInitBlockProvider

/**
 * List containing all declarations that have init block(s).
 *
 * @return A list containing declarations with the init block(s).
 */
fun <T : KoInitBlockProvider> List<T>.withInitBlocks(): List<T> = filter { it.hasInitBlocks }

/**
 * List containing all declarations that don't have init block(s).
 *
 * @return A list containing declarations without the init block(s).
 */
fun <T : KoInitBlockProvider> List<T>.withoutInitBlocks(): List<T> = filterNot { it.hasInitBlocks }
