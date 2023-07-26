package com.lemonappdev.konsist.api.ext.sequence

import com.lemonappdev.konsist.api.provider.KoParametersProvider

/**
 * Sequence containing all declarations with any parameter.
 *
 * @return A sequence containing declarations with any parameter.
 */
fun <T : KoParametersProvider> Sequence<T>.withParameters(): Sequence<T> = filter { it.parameters.toList().isNotEmpty() }

/**
 * A sequence containing declarations with specified parameters.
 *
 * @param name The name of the parameters to include.
 * @param names The names of the parameters to include.
 * @return A sequence containing declarations with all the specified parameters.
 */
fun <T : KoParametersProvider> Sequence<T>.withAllParameters(name: String, vararg names: String): Sequence<T> = filter {
    it.hasParameterNamed(name) && names.all { name -> it.hasParameterNamed(name) }
}

/**
 * Sequence containing all declarations with some parameters.
 *
 * @param name The name of the parameters to include.
 * @param names The names of the parameters to include.
 * @return A sequence containing declarations with at least one of the specified parameters.
 */
fun <T : KoParametersProvider> Sequence<T>.withSomeParameters(name: String, vararg names: String): Sequence<T> = filter {
    it.hasParameterNamed(name) || names.any { name -> it.hasParameterNamed(name) }
}

/**
 * Sequence containing all declarations with no parameters.
 *
 * @return A sequence containing declarations with no parameters.
 */
fun <T : KoParametersProvider> Sequence<T>.withoutParameters(): Sequence<T> = filter { it.parameters.toList().isEmpty() }

/**
 * Sequence containing all declarations without all specified parameters.
 *
 * @param name The name of the parameter to exclude.
 * @param names The names of the parameters to exclude.
 * @return A sequence containing declarations without all the specified parameters.
 */
fun <T : KoParametersProvider> Sequence<T>.withoutAllParameters(name: String, vararg names: String): Sequence<T> = filter {
    !it.hasParameterNamed(name) && names.none { name -> it.hasParameterNamed(name) }
}

/**
 * Sequence containing all declarations without some parameters.
 *
 * @param name The name of the parameters to exclude.
 * @param names The names of the parameters to exclude.
 * @return A sequence containing declarations without at least one of the specified parameters.
 */
fun <T : KoParametersProvider> Sequence<T>.withoutSomeParameters(name: String, vararg names: String): Sequence<T> = filter {
    !it.hasParameterNamed(name) && if (names.isNotEmpty()) {
        names.any { name -> !it.hasParameterNamed(name) }
    } else {
        true
    }
}
