package com.lemonappdev.konsist.api.provider

import com.lemonappdev.konsist.api.declaration.KoBaseDeclaration

/**
 * An interface representing a Kotlin declaration which may have a parent.
 */
interface KoContainingDeclarationProvider : KoBaseProvider {
    /**
     * The parent of the declaration.
     *
     * @return The [KoBaseDeclaration] representing the parent of the declaration.
     */
    val containingDeclaration: KoBaseDeclaration
}
