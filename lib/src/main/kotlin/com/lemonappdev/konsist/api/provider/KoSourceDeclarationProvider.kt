package com.lemonappdev.konsist.api.provider

import com.lemonappdev.konsist.api.declaration.KoBaseDeclaration

interface KoSourceDeclarationProvider : KoBaseProvider {
    val sourceDeclaration: KoBaseDeclaration
}
