package com.lemonappdev.konsist.api.provider

import com.lemonappdev.konsist.api.declaration.KoBaseDeclaration

interface KoLocalDeclarationProvider : KoBaseProvider {
    fun localDeclarations(): Sequence<KoBaseDeclaration>

    fun containsLocalDeclarations(name: String): Boolean
}
