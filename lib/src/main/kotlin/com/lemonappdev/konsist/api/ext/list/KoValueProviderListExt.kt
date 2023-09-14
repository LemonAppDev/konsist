package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.provider.KoValueProvider

/**
 * List containing elements with value.
 *
 * @param value The value to include.
 * @param values The value(s) to include.
 * @return A list containing elements with the specified values.
 */
fun <T : KoValueProvider> List<T>.withValue(value: String, vararg values: String): List<T> = filter {
    it.value == value || values.any { value -> it.value == value }
}

/**
 * List containing elements without value.
 *
 * @param value The value to exclude.
 * @param values The value(s) to exclude.
 * @return A list containing elements without the specified values.
 */
fun <T : KoValueProvider> List<T>.withoutValue(value: String, vararg values: String): List<T> = filter {
    it.value != value && values.none { value -> it.value == value }
}
