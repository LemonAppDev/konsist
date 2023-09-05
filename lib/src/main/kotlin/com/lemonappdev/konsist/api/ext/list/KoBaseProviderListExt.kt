package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.provider.KoBaseProvider
import com.lemonappdev.konsist.api.provider.KoTextProvider

/**
 * Print the elements.
 *
 * @param prefix An optional string to be printed before each element. Default is null.
 * @param predicate An optional function that generates the string representation of each element.
 *                  Default is null, which means the default `toString` method is used.
 * @return The original list of elements.
 */
fun <T : KoBaseProvider> List<T>.print(prefix: String? = null, predicate: ((T) -> String)? = null): List<T> {
    prefix?.let { println(it) }

    forEach {
        if (predicate != null) {
            println(predicate(it))
        } else {
            it.print()
        }
    }
    return this
}
