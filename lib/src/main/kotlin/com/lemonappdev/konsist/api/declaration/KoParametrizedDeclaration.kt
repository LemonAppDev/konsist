package com.lemonappdev.konsist.api.declaration

interface KoParametrizedDeclaration : KoDeclaration {
    val parameters: List<KoParameterDeclaration>?

    fun hasParameterNamed(name: String? = null): Boolean
}
