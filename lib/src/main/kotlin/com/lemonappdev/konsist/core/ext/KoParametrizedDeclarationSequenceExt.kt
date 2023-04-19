package com.lemonappdev.konsist.core.ext

import com.lemonappdev.konsist.core.declaration.KoParametrizedDeclaration

fun Sequence<KoParametrizedDeclaration>.withParameter() = filter { it.hasParameterNamed() }

fun Sequence<KoParametrizedDeclaration>.withoutParameter() = filterNot { it.hasParameterNamed() }

fun Sequence<KoParametrizedDeclaration>.withParameters(vararg name: String) = filter { koParametrizedDeclaration ->
    name.all { koParametrizedDeclaration.hasParameterNamed(it) }
}

fun Sequence<KoParametrizedDeclaration>.withSomeParameters(vararg name: String) = filter { koParametrizedDeclaration ->
    name.any { koParametrizedDeclaration.hasParameterNamed(it) }
}

fun Sequence<KoParametrizedDeclaration>.withoutParameters(vararg name: String) = filter { koParametrizedDeclaration ->
    name.none { koParametrizedDeclaration.hasParameterNamed(it) }
}
