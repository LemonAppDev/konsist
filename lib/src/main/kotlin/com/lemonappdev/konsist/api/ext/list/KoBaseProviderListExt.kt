package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.provider.KoBaseProvider

/**
 * Print the elements.
 *
 * @param prefix An optional string to be printed before each element. Default is an empty string.
 * @return The original list of elements.
 */
fun <T : KoBaseProvider> List<T>.print(prefix: String = "", predicate: ((T) -> String)? = null): List<T> {
    println(prefix)
    forEach {
        if (predicate == null) {
            println(it.toString())
        } else {
            println(predicate(it))
        }
    }
    return this
}
