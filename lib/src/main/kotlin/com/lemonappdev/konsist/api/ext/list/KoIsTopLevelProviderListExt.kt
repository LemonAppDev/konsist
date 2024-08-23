package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.provider.KoIsTopLevelProvider

/**
 * List containing the top level declarations.
 *
 * @return A list containing the top-level declarations.
 */
fun <T : KoIsTopLevelProvider> List<T>.withTopLevel(): List<T> = filter { it.isTopLevel }

/**
 * List containing the non-top level declarations.
 *
 * @return A list containing the non-top level declarations.
 */
fun <T : KoIsTopLevelProvider> List<T>.withoutTopLevel(): List<T> = filterNot { it.isTopLevel }
