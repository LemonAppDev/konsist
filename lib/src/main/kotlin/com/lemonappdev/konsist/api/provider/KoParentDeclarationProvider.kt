package com.lemonappdev.konsist.api.provider

interface KoParentDeclarationProvider: KoProvider {
    val parentDeclaration: KoParentDeclarationProvider?
}
