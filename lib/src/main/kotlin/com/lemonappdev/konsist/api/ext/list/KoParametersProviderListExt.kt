package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.provider.KoParametersProvider

/**
 * List containing all declarations with any parameter.
 *
 * @return A list containing declarations with any parameter.
 */
fun <T : KoParametersProvider> List<T>.withParameters(): List<T> = filter { it.parameters.toList().isNotEmpty() }

/**
 * A list containing declarations with specified parameters.
 *
 * @param name The name of the parameters to include.
 * @param names The names of the parameters to include.
 * @return A list containing declarations with all the specified parameters.
 */
fun <T : KoParametersProvider> List<T>.withAllParameters(name: String, vararg names: String): List<T> = filter {
    it.hasParameterNamed(name) && names.all { name -> it.hasParameterNamed(name) }
}

/**
 * List containing all declarations with some parameters.
 *
 * @param name The name of the parameters to include.
 * @param names The names of the parameters to include.
 * @return A list containing declarations with at least one of the specified parameters.
 */
fun <T : KoParametersProvider> List<T>.withSomeParameters(name: String, vararg names: String): List<T> = filter {
    it.hasParameterNamed(name) || names.any { name -> it.hasParameterNamed(name) }
}

/**
 * List containing all declarations with no parameters.
 *
 * @return A list containing declarations with no parameters.
 */
fun <T : KoParametersProvider> List<T>.withoutParameters(): List<T> = filter { it.parameters.toList().isEmpty() }

/**
 * List containing all declarations without all specified parameters.
 *
 * @param name The name of the parameter to exclude.
 * @param names The names of the parameters to exclude.
 * @return A list containing declarations without all the specified parameters.
 */
fun <T : KoParametersProvider> List<T>.withoutAllParameters(name: String, vararg names: String): List<T> = filter {
    !it.hasParameterNamed(name) && names.none { name -> it.hasParameterNamed(name) }
}

/**
 * List containing all declarations without some parameters.
 *
 * @param name The name of the parameters to exclude.
 * @param names The names of the parameters to exclude.
 * @return A list containing declarations without at least one of the specified parameters.
 */
fun <T : KoParametersProvider> List<T>.withoutSomeParameters(name: String, vararg names: String): List<T> = filter {
    !it.hasParameterNamed(name) && if (names.isNotEmpty()) {
        names.any { name -> !it.hasParameterNamed(name) }
    } else {
        true
    }
}
