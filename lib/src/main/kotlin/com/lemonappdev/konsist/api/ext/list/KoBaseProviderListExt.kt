package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.ext.provider.print
import com.lemonappdev.konsist.api.provider.KoBaseProvider

/**
 * Print the declarations.
 *
 * @param prefix An optional string to be printed once before the declarations. Default is `null`.
 * @param predicate An optional function that generates the string representation of each declaration.
 *                  If predicate is not provided (default is `null`), the function uses the declaration's
 *                  name (if available) or `toString` method otherwise.
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
