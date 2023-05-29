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
     *
     * @param name the name of the parameter to check.
     * @return `true` if the declaration has a parameter with the specified name, `false` otherwise.
     */
    fun hasParameterNamed(name: String): Boolean
}
