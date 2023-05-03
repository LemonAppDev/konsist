package com.lemonappdev.konsist.core.declaration

interface KoParametrizedDeclaration : KoDeclaration {
    val parameters: List<KoParameterDeclaration>

    fun hasParameterNamed(name: String? = null): Boolean
}
