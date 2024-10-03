package com.lemonappdev.konsist.api.ext.list.provider

import com.lemonappdev.konsist.api.ext.provider.print
import com.lemonappdev.konsist.api.provider.KoBaseProvider

/**
 * Print the declarations.
 *
 * @param prefix An optional string to be printed before each declaration. Default is null.
 * @param predicate An optional function that generates the string representation of each declaration.
 *                  If predicate is not provided (default is `null`), the function uses `toString` method.
 * @return The original list of declarations.
 */
fun <T : KoBaseProvider> List<T>.print(
    prefix: String? = null,
    predicate: ((T) -> String)? = null,
): List<T> {
    prefix?.let { println(it) }

    forEach { it.print(predicate = predicate) }

    return this
}
