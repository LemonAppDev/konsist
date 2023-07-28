package com.lemonappdev.konsist.api.ext.sequence

import com.lemonappdev.konsist.api.provider.KoBaseProvider

/**
 * Print the declarations.
 *
 * @return The original sequence of declarations.
 */
fun <T : KoBaseProvider> Sequence<T>.print(): Sequence<T> {
    forEach { println(it.toString()) }
    return this
}
