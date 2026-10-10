package com.lemonappdev.konsist.api.provider

import com.lemonappdev.konsist.api.declaration.KoBaseDeclaration

/**
 * An interface representing a Kotlin declaration that provides access to its containing declaration.
 */
interface KoContainingDeclarationProvider : KoBaseProvider {
    /**
     * The declaration containing this declaration.
     *
     * @return The [KoBaseDeclaration] containing this declaration.
     */
    val containingDeclaration: KoBaseDeclaration
}
