package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.provider.KoBaseProvider
import com.lemonappdev.konsist.api.provider.KoTextProvider

/**
 * Print the elements.
 *
 * @param prefix An optional string to be printed before each element. Default is an empty string.
 * @return The original list of elements.
 */
fun <T : KoBaseProvider> List<T>.print(prefix: String = ""): List<T> {
    forEach { println(prefix + it.toString()) }
    return this
}
