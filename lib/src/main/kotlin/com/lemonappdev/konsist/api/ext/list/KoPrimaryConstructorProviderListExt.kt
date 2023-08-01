package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.provider.KoPrimaryConstructorProvider

/**
 * List containing all declarations that have primary constructor.
 *
 * @return A list containing declarations with primary constructor.
 */
fun <T : KoPrimaryConstructorProvider> List<T>.withPrimaryConstructor(): List<T> = filter { it.hasPrimaryConstructor }

/**
 * List containing all declarations that don't have primary constructor.
 *
 * @return A list containing declarations without primary constructor.
 */
fun <T : KoPrimaryConstructorProvider> List<T>.withoutPrimaryConstructor(): List<T> = filterNot { it.hasPrimaryConstructor }
