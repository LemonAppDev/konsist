package com.lemonappdev.konsist.api.ext

import com.lemonappdev.konsist.api.declaration.KoParametrizedDeclaration

fun <T : KoParametrizedDeclaration> Sequence<T>.withParameter() = filter { it.hasParameterNamed() }

fun <T : KoParametrizedDeclaration> Sequence<T>.withoutParameter() = filterNot { it.hasParameterNamed() }

fun <T : KoParametrizedDeclaration> Sequence<T>.withParameters(vararg names: String) = filter {
    names.all { name -> it.hasParameterNamed(name) }
}

fun <T : KoParametrizedDeclaration> Sequence<T>.withSomeParameters(vararg names: String) = filter {
    names.any { name -> it.hasParameterNamed(name) }
}

fun <T : KoParametrizedDeclaration> Sequence<T>.withoutParameters(vararg names: String) = filter {
    names.none { name -> it.hasParameterNamed(name) }
}
