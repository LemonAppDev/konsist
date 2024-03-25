package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.provider.KoDefaultValueProvider

/**
 * List containing declarations with default value.
 *
 * @param values The default values to include.
 * @return A list containing declarations with the specified default values (or any default value if [values] is empty).
 */
fun <T : KoDefaultValueProvider> List<T>.withDefaultValue(vararg values: String): List<T> =
    filter {
        when {
            values.isEmpty() -> it.hasDefaultValue()
            else -> values.any { value -> it.hasDefaultValue(value) }
        }
    }

/**
 * List containing declarations with default value.
 *
 * @param values The default values to include.
 * @return A list containing declarations with the specified default values (or any default value if [values] is empty).
 */
fun <T : KoDefaultValueProvider> List<T>.withDefaultValue(values: Collection<String>): List<T> = withDefaultValue(*values.toTypedArray())

/**
 * List containing declarations without default value.
 *
 * @param values The default values to exclude.
 * @return A list containing declarations without the specified default values (or none default value if [values] is empty).
 */
fun <T : KoDefaultValueProvider> List<T>.withoutDefaultValue(vararg values: String): List<T> =
    filter {
        when {
            values.isEmpty() -> !it.hasDefaultValue()
            else -> values.none { value -> it.hasDefaultValue(value) }
        }
    }

/**
 * List containing declarations without default value.
 *
 * @param values The default values to exclude.
 * @return A list containing declarations without the specified default values (or none default value if [values] is empty).
 */
fun <T : KoDefaultValueProvider> List<T>.withoutDefaultValue(values: Collection<String>): List<T> =
    withoutDefaultValue(*values.toTypedArray())
