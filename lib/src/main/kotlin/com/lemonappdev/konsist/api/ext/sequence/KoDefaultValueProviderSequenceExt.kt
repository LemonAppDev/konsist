package com.lemonappdev.konsist.api.ext.sequence

import com.lemonappdev.konsist.api.provider.KoDefaultValueProvider

/**
 * Sequence containing all declarations with default value.
 *
 * @param values The default values to include.
 * @return A sequence containing declarations with the specified default values (or any default value if [values] is empty).
 */
fun <T : KoDefaultValueProvider> Sequence<T>.withDefaultValue(vararg values: String): Sequence<T> = filter {
    when {
        values.isEmpty() -> it.hasDefaultValue()
        else -> values.any { value -> it.hasDefaultValue(value) }
    }
}

/**
 * Sequence containing all declarations without default value.
 *
 * @param values The default values to exclude.
 * @return A sequence containing declarations without the specified default values (or none default value if [values] is empty).
 */
fun <T : KoDefaultValueProvider> Sequence<T>.withoutDefaultValue(vararg values: String): Sequence<T> = filter {
    when {
        values.isEmpty() -> !it.hasDefaultValue()
        else -> values.none { value -> it.hasDefaultValue(value) }
    }
}
