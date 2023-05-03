package com.lemonappdev.konsist.api.ext

import com.lemonappdev.konsist.core.declaration.KoParametrizedDeclarationImpl

fun <T : KoParametrizedDeclarationImpl> Sequence<T>.withParameter() = filter { it.hasParameterNamed() }

fun <T : KoParametrizedDeclarationImpl> Sequence<T>.withoutParameter() = filterNot { it.hasParameterNamed() }

fun <T : KoParametrizedDeclarationImpl> Sequence<T>.withParameters(vararg names: String) = filter {
    names.all { name -> it.hasParameterNamed(name) }
}

fun <T : KoParametrizedDeclarationImpl> Sequence<T>.withSomeParameters(vararg names: String) = filter {
    names.any { name -> it.hasParameterNamed(name) }
}

fun <T : KoParametrizedDeclarationImpl> Sequence<T>.withoutParameters(vararg names: String) = filter {
    names.none { name -> it.hasParameterNamed(name) }
}
