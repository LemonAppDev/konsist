package com.lemonappdev.konsist.api.provider

import com.lemonappdev.konsist.api.declaration.KoTypeDeclaration

/**
 * An interface representing a Kotlin declaration that provides access to its type information.
 */
interface KoTypeProvider : KoBaseProvider {
    /**
     * Type of the declaration.
     */
    val type: KoTypeDeclaration
}
