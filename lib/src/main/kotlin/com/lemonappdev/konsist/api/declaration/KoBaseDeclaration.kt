package com.lemonappdev.konsist.api.declaration

import com.lemonappdev.konsist.api.container.KoFile

/**
 * Represents a base declaration.
 */
interface KoBaseDeclaration : KoPsiDeclaration {
    /**
     * File containing the declaration.
     */
    val containingFile: KoFile
}
