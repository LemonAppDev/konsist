package com.lemonappdev.konsist.api.ext.sequence

import com.lemonappdev.konsist.api.declaration.KoParametrizedDeclaration

/**
 * Sequence containing all declarations that have parameter.
 */
fun <T : KoParametrizedDeclaration> Sequence<T>.withParameter() = filter { it.hasParameterNamed() }

/**
 * Sequence containing all declarations that don't have parameter.
 */
fun <T : KoParametrizedDeclaration> Sequence<T>.withoutParameter() = filterNot { it.hasParameterNamed() }

/**
 * Sequence containing all declarations that have all parameters.
 */
fun <T : KoParametrizedDeclaration> Sequence<T>.withParameters(vararg names: String) = filter {
    names.all { name -> it.hasParameterNamed(name) }
}

/**
 * Sequence containing all declarations that have some parameters.
 */
fun <T : KoParametrizedDeclaration> Sequence<T>.withSomeParameters(vararg names: String) = filter {
    names.any { name -> it.hasParameterNamed(name) }
}

/**
 * Sequence containing all declarations that don't have parameters.
 */
fun <T : KoParametrizedDeclaration> Sequence<T>.withoutParameters(vararg names: String) = filter {
    names.none { name -> it.hasParameterNamed(name) }
}
