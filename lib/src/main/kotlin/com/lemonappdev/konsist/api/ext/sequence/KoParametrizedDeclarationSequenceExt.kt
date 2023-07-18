package com.lemonappdev.konsist.api.ext.sequence

import com.lemonappdev.konsist.api.declaration.KoParametrizedDeclaration

/**
 * Sequence containing all declarations with any parameter.
 *
 * @return A sequence containing declarations with any parameter.
 */
fun <T : KoParametrizedDeclaration> Sequence<T>.withParameters(): Sequence<T> = filter { it.parameters.isNotEmpty() }

/**
 * A sequence containing declarations with specified parameters.
 *
 * @param name The name of the parameters to include.
 * @param names The names of the parameters to include.
 * @return A sequence containing declarations with all the specified parameters.
 */
fun <T : KoParametrizedDeclaration> Sequence<T>.withAllParameters(name: String, vararg names: String): Sequence<T> = filter {
    it.hasParameterNamed(name) && names.all { name -> it.hasParameterNamed(name) }
}

/**
 * Sequence containing all declarations with some parameters.
 *
 * @param name The name of the parameters to include.
 * @param names The names of the parameters to include.
 * @return A sequence containing declarations with at least one of the specified parameters.
 */
fun <T : KoParametrizedDeclaration> Sequence<T>.withSomeParameters(name: String, vararg names: String): Sequence<T> = filter {
    it.hasParameterNamed(name) || names.any { name -> it.hasParameterNamed(name) }
}

/**
 * Sequence containing all declarations with no parameters.
 *
 * @return A sequence containing declarations with no parameters.
 */
fun <T : KoParametrizedDeclaration> Sequence<T>.withoutParameters(): Sequence<T> = filter { it.parameters.isEmpty() }

/**
 * Sequence containing all declarations that don't have all specified parameters.
 *
 * @param name The name of the parameter to exclude.
 * @param names The names of the parameters to exclude.
 * @return A sequence containing declarations that don't have all the specified parameters.
 */
fun <T : KoParametrizedDeclaration> Sequence<T>.withoutAllParameters(name: String, vararg names: String): Sequence<T> = filter {
    !it.hasParameterNamed(name) && names.none { name -> it.hasParameterNamed(name) }
}

/**
 * Sequence containing all declarations that don't have some parameters.
 *
 * @param name The name of the parameters to exclude.
 * @param names The names of the parameters to exclude.
 * @return A sequence containing declarations that don't have at least one of the specified parameters.
 */
fun <T : KoParametrizedDeclaration> Sequence<T>.withoutSomeParameters(name: String, vararg names: String): Sequence<T> = filter {
    !it.hasParameterNamed(name) && if (names.isNotEmpty()) {
        names.any { name -> !it.hasParameterNamed(name) }
    } else {
        true
    }
}
