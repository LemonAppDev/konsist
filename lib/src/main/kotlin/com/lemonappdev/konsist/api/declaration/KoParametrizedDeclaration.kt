package com.lemonappdev.konsist.api.declaration

/**
 * Represents a Kotlin parameter declaration.
 */
interface KoParametrizedDeclaration : KoDeclaration {
    /**
     * Parameters of the declaration.
     */
    val parameters: List<KoParameterDeclaration>

    /**
     * Whatever declaration has a parameter with given name.
     */
    fun hasParameterNamed(name: String? = null): Boolean
}
