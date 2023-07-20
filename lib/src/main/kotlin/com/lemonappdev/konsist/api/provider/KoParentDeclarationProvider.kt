package com.lemonappdev.konsist.api.provider

interface KoParentDeclarationProvider : KoBaseProvider {
    val parentDeclaration: KoParentDeclarationProvider?
}
