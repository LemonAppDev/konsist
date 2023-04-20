package com.lemonappdev.konsist.core.ext

import com.lemonappdev.konsist.core.declaration.KoParametrizedDeclaration

fun Sequence<KoParametrizedDeclaration>.withParameter() = filter { it.hasParameterNamed() }

fun Sequence<KoParametrizedDeclaration>.withoutParameter() = filterNot { it.hasParameterNamed() }

fun Sequence<KoParametrizedDeclaration>.withParameters(vararg names: String) = filter { koParametrizedDeclaration ->
    names.all { koParametrizedDeclaration.hasParameterNamed(it) }
}

fun Sequence<KoParametrizedDeclaration>.withSomeParameters(vararg names: String) = filter { koParametrizedDeclaration ->
    names.any { koParametrizedDeclaration.hasParameterNamed(it) }
}

fun Sequence<KoParametrizedDeclaration>.withoutParameters(vararg names: String) = filter { koParametrizedDeclaration ->
    names.none { koParametrizedDeclaration.hasParameterNamed(it) }
}
