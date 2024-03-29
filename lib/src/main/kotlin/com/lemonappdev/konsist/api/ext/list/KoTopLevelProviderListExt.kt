package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.provider.KoTopLevelProvider

/**
 * List containing the top level declarations.
 *
 * @return A list containing the top-level declarations.
 */
fun <T : KoTopLevelProvider> List<T>.withTopLevel(): List<T> = filter { it.isTopLevel }

/**
 * List containing the non-top level declarations.
 *
 * @return A list containing the non-top level declarations.
 */
fun <T : KoTopLevelProvider> List<T>.withoutTopLevel(): List<T> = filterNot { it.isTopLevel }
