package com.lemonappdev.konsist.core.declaration

interface KoBaseDeclaration : KoPsiDeclaration {
    /**
     * KoFile containing the declaration
     */
    val containingFile: KoFileDeclarationImpl
}
