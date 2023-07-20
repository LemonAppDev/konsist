package com.lemonappdev.konsist.api.provider

interface KoLocalDeclarationProvider : KoBaseProvider {
    fun localDeclarations(): Sequence<KoBaseProvider>

    fun containsLocalDeclarations(name: String): Boolean
}
