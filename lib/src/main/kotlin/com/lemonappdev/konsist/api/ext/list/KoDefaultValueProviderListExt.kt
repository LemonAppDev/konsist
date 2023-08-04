package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.provider.KoDefaultValueProvider

/**
 * List containing elements with default value.
 *
 * @param values The default values to include.
 * @return A list containing elements with the specified default values (or any default value if [values] is empty).
 */
fun <T : KoDefaultValueProvider> List<T>.withDefaultValue(vararg values: String): List<T> = filter {
    when {
        values.isEmpty() -> it.hasDefaultValue()
        else -> values.any { value -> it.hasDefaultValue(value) }
    }
}

/**
 * List containing elements without default value.
 *
 * @param values The default values to exclude.
 * @return A list containing elements without the specified default values (or none default value if [values] is empty).
 */
fun <T : KoDefaultValueProvider> List<T>.withoutDefaultValue(vararg values: String): List<T> = filter {
    when {
        values.isEmpty() -> !it.hasDefaultValue()
        else -> values.none { value -> it.hasDefaultValue(value) }
    }
}
