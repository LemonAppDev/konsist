package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.provider.KoBaseProvider

/**
 * Print the declarations.
 *
 * @return The original list of declarations.
 */
fun <T : KoBaseProvider> List<T>.print(): List<T> {
    forEach { println(it.toString()) }
    return this
}
