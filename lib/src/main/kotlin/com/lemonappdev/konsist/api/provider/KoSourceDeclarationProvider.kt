package com.lemonappdev.konsist.api.provider

import com.lemonappdev.konsist.api.declaration.KoBaseDeclaration

/**
 * Provides access to the source declaration of a declaration.
 */
interface KoSourceDeclarationProvider : KoBaseProvider {

    /**
     * The source declaration associated with this declaration.
     */
    val sourceDeclaration: KoBaseDeclaration
}
