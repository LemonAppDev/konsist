package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.provider.KoBaseProvider

/**
 * Print the elements.
 *
 * @return The original list of elements.
 */
fun <T : KoBaseProvider> List<T>.print(): List<T> {
    forEach { println(it.toString()) }
    return this
}
