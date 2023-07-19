package com.lemonappdev.konsist.api.provider

import com.lemonappdev.konsist.api.declaration.KoTypeDeclaration

interface KoExplicitReturnTypeProvider : KoProvider {
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
