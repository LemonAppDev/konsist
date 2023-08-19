package com.lemonappdev.konsist.api.provider

import com.lemonappdev.konsist.api.declaration.KoTypeDeclaration

/**
 * An interface representing a Kotlin declaration that provides access to the type information.
 */
interface KoPropertyTypeProvider : KoBaseProvider {
    /**
     * Declaration type.
     */
    val type: KoTypeDeclaration?

    /**
     * Whatever declaration has a type.
     *
     * @param name the type name to check for (optional).
     * @return `true` if the declaration has the specified type (or any type if [name] is `null`), `false` otherwise.
     */
    fun hasType(name: String? = null): Boolean
}
