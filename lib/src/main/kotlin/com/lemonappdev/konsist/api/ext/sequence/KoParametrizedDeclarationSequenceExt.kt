package com.lemonappdev.konsist.api.ext.sequence

import com.lemonappdev.konsist.api.declaration.KoParametrizedDeclaration

/**
 * Sequence containing all declarations that have all parameters.
 */
fun <T : KoParametrizedDeclaration> Sequence<T>.withParameters(vararg names: String): Sequence<T> = filter {
    names.all { name -> it.hasParameterNamed(name) }
}

/**
 * Sequence containing all declarations that have some parameters.
 */
fun <T : KoParametrizedDeclaration> Sequence<T>.withSomeParameters(vararg names: String): Sequence<T> = filter {
    names.any { name -> it.hasParameterNamed(name) }
}

/**
 * Sequence containing all declarations that don't have parameters.
 */
fun <T : KoParametrizedDeclaration> Sequence<T>.withoutParameters(vararg names: String): Sequence<T> = filter {
    names.none { name -> it.hasParameterNamed(name) }
}
