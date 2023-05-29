package com.lemonappdev.konsist.api.ext.sequence

import com.lemonappdev.konsist.api.declaration.KoParametrizedDeclaration

/**
 * Sequence containing all declarations that have all parameters.
 *
 * @param names The names of the parameters to include.
 * @return A sequence containing declarations that have all the specified parameters.
 */
fun <T : KoParametrizedDeclaration> Sequence<T>.withParameters(vararg names: String): Sequence<T> = filter {
    names.all { name -> it.hasParameterNamed(name) }
}

/**
 * Sequence containing all declarations that have some parameters.
 *
 * @param names The names of the parameters to include.
 * @return A sequence containing declarations that have at least one of the specified parameters.
 */
fun <T : KoParametrizedDeclaration> Sequence<T>.withSomeParameters(vararg names: String): Sequence<T> = filter {
    names.any { name -> it.hasParameterNamed(name) }
}

/**
 * Sequence containing all declarations that don't have parameters.
 *
 * @param names The names of the parameters to exclude.
 * @return A sequence containing declarations that don't have any of the specified parameters.
 */
fun <T : KoParametrizedDeclaration> Sequence<T>.withoutParameters(vararg names: String): Sequence<T> = filter {
    names.none { name -> it.hasParameterNamed(name) }
}
