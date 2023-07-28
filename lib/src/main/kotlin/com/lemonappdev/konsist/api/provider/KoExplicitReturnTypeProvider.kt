package com.lemonappdev.konsist.api.provider

import com.lemonappdev.konsist.api.declaration.KoTypeDeclaration

/**
 * An interface representing a Kotlin declaration that provides access to the explicit return type information.
 */
interface KoExplicitReturnTypeProvider : KoBaseProvider {
    /**
     * Return type of the declaration.
     */
    val explicitReturnType: KoTypeDeclaration?

    /**
     * Whether this declaration has a return type.
     *
     * @return `true` if the declaration has the return type, `false` otherwise.
     */
    fun hasExplicitReturnType(): Boolean
}
