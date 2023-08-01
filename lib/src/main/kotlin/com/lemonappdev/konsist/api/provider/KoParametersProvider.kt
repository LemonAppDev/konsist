package com.lemonappdev.konsist.api.provider

import com.lemonappdev.konsist.api.declaration.KoParameterDeclaration

/**
 * An interface representing a Kotlin declaration that provides access to its parameters.
 */
interface KoParametersProvider : KoBaseProvider {
    /**
     * Parameters of the declaration.
     */
    val parameters: List<KoParameterDeclaration>

    /**
     * The number of parameters.
     */
    val numParameters: Int

    /**
     * Whatever declaration has a parameter with given name.
     *
     * @param name the name of the parameter to check.
     * @return `true` if the declaration has a parameter with the specified name, `false` otherwise.
     */
    fun hasParameterNamed(name: String): Boolean
}
