package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.provider.KoIsGenericProvider

/**
 * List containing declarations that are generic.
 *
 * @return A list containing only the elements that are generic.
 */
fun <T : KoIsGenericProvider> List<T>.withGeneric(): List<T> = filter { it.isGeneric }

/**
 * List containing declarations that are not generic.
 *
 * @return A list containing only the elements that are not generic.
 */
fun <T : KoIsGenericProvider> List<T>.withoutGeneric(): List<T> = filterNot { it.isGeneric }
