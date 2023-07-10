package com.lemonappdev.konsist.api.ext.sequence

import com.lemonappdev.konsist.api.provider.KoParametersProvider

/**
 * Sequence containing all declarations that have all parameters.
 *
 * @param names The names of the parameters to include.
 * @return A sequence containing declarations that have all the specified parameters.
 */
fun <T : KoParametersProvider> Sequence<T>.withParameters(vararg names: String): Sequence<T> = filter {
    when {
        names.isEmpty() -> it.parameters.isNotEmpty()
        else -> names.all { name -> it.hasParameterNamed(name) }
    }
}

/**
 * Sequence containing all declarations that have some parameters.
 *
 * @param name The name of the parameters to include.
 * @param names The names of the parameters to include.
 * @return A sequence containing declarations that have at least one of the specified parameters.
 */
fun <T : KoParametersProvider> Sequence<T>.withSomeParameters(name: String, vararg names: String): Sequence<T> = filter {
    it.hasParameterNamed(name) || names.any { name -> it.hasParameterNamed(name) }
}

/**
 * Sequence containing all declarations that don't have parameters.
 *
 * @param names The names of the parameters to exclude.
 * @return A sequence containing declarations that don't have any of the specified parameters.
 */
fun <T : KoParametersProvider> Sequence<T>.withoutParameters(vararg names: String): Sequence<T> = filter {
    when {
        names.isEmpty() -> it.parameters.isEmpty()
        else -> names.none { name -> it.hasParameterNamed(name) }
    }
}
