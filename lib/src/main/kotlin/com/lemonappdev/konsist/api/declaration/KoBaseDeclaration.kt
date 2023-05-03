package com.lemonappdev.konsist.api.declaration

interface KoBaseDeclaration : KoPsiDeclaration {
    /**
     * KoFile containing the declaration
     */
    val containingFile: KoFileDeclaration
}
