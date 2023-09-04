package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.provider.KoArgumentNameProvider

/**
 * List containing elements with argument name.
 *
 * @param name The argument name to include.
 * @param names The argument name(s) to include.
 * @return A list containing elements with the specified argument names.
 */
fun <T : KoArgumentNameProvider> List<T>.withArgumentName(name: String, vararg names: String): List<T> = filter {
    it.argumentName == name || names.any { name -> it.argumentName == name }
}

/**
 * List containing elements without argument name.
 *
 * @param name The argument name to exclude.
 * @param names The argument name(s) to exclude.
 * @return A list containing elements without the specified argument names.
 */
fun <T : KoArgumentNameProvider> List<T>.withoutArgumentName(name: String, vararg names: String): List<T> = filter {
    it.argumentName != name && names.none { name -> it.argumentName == name }
}

/**
 * List containing elements with value.
 *
 * @param value The value to include.
 * @param values The value(s) to include.
 * @return A list containing elements with the specified values.
 */
fun <T : KoArgumentNameProvider> List<T>.withValue(value: String, vararg values: String): List<T> = filter {
    it.value == value || values.any { value -> it.value == value }
}

/**
 * List containing elements without value.
 *
 * @param value The value to exclude.
 * @param values The value(s) to exclude.
 * @return A list containing elements without the specified values.
 */
fun <T : KoArgumentNameProvider> List<T>.withoutValue(value: String, vararg values: String): List<T> = filter {
    it.value != value && values.none { value -> it.value == value }
}
