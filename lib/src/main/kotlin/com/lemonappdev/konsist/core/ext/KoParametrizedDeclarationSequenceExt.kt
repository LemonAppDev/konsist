package com.lemonappdev.konsist.core.ext

import com.lemonappdev.konsist.core.declaration.KoParametrizedDeclaration

fun Sequence<KoParametrizedDeclaration>.withParameter() = filter { it.hasParameterNamed() }

fun Sequence<KoParametrizedDeclaration>.withoutParameter() = filterNot { it.hasParameterNamed() }

fun Sequence<KoParametrizedDeclaration>.withParameters(vararg names: String) = filter {
    names.all { name -> it.hasParameterNamed(name) }
}

fun Sequence<KoParametrizedDeclaration>.withSomeParameters(vararg names: String) = filter {
    names.any { name -> it.hasParameterNamed(name) }
}

fun Sequence<KoParametrizedDeclaration>.withoutParameters(vararg names: String) = filter {
    names.none { name -> it.hasParameterNamed(name) }
}
