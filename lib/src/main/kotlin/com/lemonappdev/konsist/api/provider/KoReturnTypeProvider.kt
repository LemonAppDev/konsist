package com.lemonappdev.konsist.api.provider

import com.lemonappdev.konsist.api.declaration.KoTypeDeclaration

/**
 * An interface representing a Kotlin declaration that provides access to the return type information.
 */
interface KoReturnTypeProvider : KoBaseProvider {
    /**
     * Return type of the declaration.
     */
    val returnType: KoTypeDeclaration?

    /**
     * Whether this declaration has a return type.
     */
    val hasReturnType: Boolean
}
