package com.lemonappdev.konsist.api.declaration

/**
 * Represents a base declaration.
 */
interface KoBaseDeclaration : KoPsiDeclaration {
    /**
     * File containing the declaration.
     */
    val containingFile: KoFileDeclaration
}
