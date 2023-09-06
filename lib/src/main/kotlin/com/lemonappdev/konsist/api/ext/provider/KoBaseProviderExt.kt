package com.lemonappdev.konsist.api.ext.provider

import com.lemonappdev.konsist.api.provider.KoBaseProvider
import com.lemonappdev.konsist.api.provider.KoNameProvider

/**
 * Print declaration.
 *
 * @param prefix An optional string to be printed before the declaration content. Default is null.
 */
fun <T : KoBaseProvider> T.print(prefix: String? = null, predicate: ((T) -> String)? = null): T {
    prefix?.let { println(it) }

    if (predicate != null) {
        println(predicate(this))
    } else {
        val text = if (this is KoNameProvider && name != "") name else toString()
        println(text)
    }

    return this
}
