package com.lemonappdev.konsist.api.provider

import com.lemonappdev.konsist.api.declaration.KoTypeDeclaration

/**
 * An interface representing a Kotlin declaration that provides access to the explicit type information.
 */
interface KoExplicitTypeProvider : KoBaseProvider {
    /**
     * Declaration explicit type.
     */
    val explicitType: KoTypeDeclaration?

    /**
     * Whatever declaration has an explicit type.
     *
     * @param name the type name to check for (optional).
     * @return `true` if the declaration has the specified type (or any type if [name] is `null`), `false` otherwise.
     */
    fun hasExplicitType(name: String? = null): Boolean
}
