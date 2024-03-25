package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.provider.KoValueProvider

/**
 * List containing elements with value.
 *
 * @param values The value(s) to include.
 * @return A list containing elements with the specified values (or any value if [values] is empty).
 */
fun <T : KoValueProvider> List<T>.withValue(vararg values: String): List<T> =
    filter {
        when {
            values.isEmpty() -> it.hasValue()
            else -> values.any { value -> it.hasValue(value) }
        }
    }

/**
 * List containing elements with value.
 *
 * @param values The value(s) to include.
 * @return A list containing elements with the specified values (or any value if [values] is empty).
 */
fun <T : KoValueProvider> List<T>.withValue(values: Collection<String>): List<T> = withValue(*values.toTypedArray())

/**
 * List containing elements without value.
 *
 * @param values The value(s) to exclude.
 * @return A list containing elements without the specified values (or none value if [values] is empty).
 */
fun <T : KoValueProvider> List<T>.withoutValue(vararg values: String): List<T> =
    filter {
        when {
            values.isEmpty() -> !it.hasValue()
            else -> values.none { value -> it.hasValue(value) }
        }
    }

/**
 * List containing elements without value.
 *
 * @param values The value(s) to exclude.
 * @return A list containing elements without the specified values (or none value if [values] is empty).
 */
fun <T : KoValueProvider> List<T>.withoutValue(values: Collection<String>): List<T> = withoutValue(*values.toTypedArray())

/**
 * List containing declarations that have a value matching the provided predicate.
 *
 * @param predicate A function that defines the condition to be met by a declaration value.
 * @return A list containing declarations with the value matching the provided predicate.
 */
fun <T : KoValueProvider> List<T>.withValue(predicate: (String) -> Boolean): List<T> =
    filter {
        it.value?.let(predicate) ?: false
    }

/**
 * List containing declarations that don't have a value matching the provided predicate.
 *
 * @param predicate A function that defines the condition to be met by a declaration value.
 * @return A list containing declarations without the value matching the provided predicate.
 */
fun <T : KoValueProvider> List<T>.withoutValue(predicate: (String) -> Boolean): List<T> =
    filterNot {
        it.value?.let(predicate) ?: false
    }
